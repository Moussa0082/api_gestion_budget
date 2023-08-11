package com.gr4.api_gestion_budgets.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.gr4.api_gestion_budgets.models.Budget;

public interface BudgetService{

    //Créer un budget
    ResponseEntity<String> addBudget(Budget budget);

    //Modifier budget
    // ResponseEntity<String> updateBudget(Integer id, Budget budget );
    Budget modifier(Integer id, Budget budget);
    //Liste des budget
    ResponseEntity <List<Budget>> getAllBudget();


    ResponseEntity<String> supprimerBudget(Integer id, Budget budget);

    //Méthode pour définir la durée du budget sur un mois
    boolean checkBudgetDuration(Budget budget);
}
