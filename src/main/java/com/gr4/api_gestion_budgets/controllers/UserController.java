package com.gr4.api_gestion_budgets.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gr4.api_gestion_budgets.models.User;
import com.gr4.api_gestion_budgets.repository.UserRepository;
import com.gr4.api_gestion_budgets.services.UserServiceImpl;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository= userRepository;
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody User user){
        return userServiceImpl.addUser(user);
    }


    //Methode de connection
     @PostMapping("/connexion")
    public ResponseEntity<String> seConnecter(@RequestBody User user) {
        // Récupérer les informations d'authentification du user
        String nom = user.getNom();

        String passwords = user.getPassword();

        // Rechercher l'inscription par nom d'utilisateur dans la base de données
        User u = userRepository.findUserByNom(nom);

        // Vérifier si l'inscription existe et si le mot de passe est correct
        if (u != null && u.getPassword().equals(passwords)) {
            return new ResponseEntity<>("Connexion réussie ! " + " Bienvenue "+ u.getNom(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Nom d'utilisateur ou mot de passe incorrect.", HttpStatus.UNAUTHORIZED);
        }
    }
    
}
