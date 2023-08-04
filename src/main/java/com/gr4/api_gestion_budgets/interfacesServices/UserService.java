package com.gr4.api_gestion_budgets.interfacesServices;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.gr4.api_gestion_budgets.models.User;

public interface UserService {

    //Créer un utilisateur
    ResponseEntity<String> addUser(User user);

    //Liste des utilisateurs
    ResponseEntity <List<User>> getAllUser();

    
}
