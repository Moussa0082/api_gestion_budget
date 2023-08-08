package com.gr4.api_gestion_budgets.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gr4.api_gestion_budgets.interfacesServices.UserService;
import com.gr4.api_gestion_budgets.models.User;
import com.gr4.api_gestion_budgets.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<String> addUser(User user) {
        userRepository.save(user);
        return new ResponseEntity<>("Compte créer avec succès", HttpStatus.CREATED);
       
    }

    @Override
    public ResponseEntity<List<User>> getAllUser() {
     
       try {
            return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
             e.printStackTrace();
        }
       return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    
}