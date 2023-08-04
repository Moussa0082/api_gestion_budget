package com.gr4.api_gestion_budgets.interfacesServices;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.gr4.api_gestion_budgets.models.Budget;

public interface BudgetService{

  //Créer un budget
    ResponseEntity<String> addBudget(Budget budget);

    //Modifier budget
    ResponseEntity<String> updateBudget(Integer id, Budget budget );

    //Liste des budget
    ResponseEntity <List<Budget>> getAllBudget();

    //Supprimer budget

    ResponseEntity <String> supprimerBudget(Integer id, Budget budget);

    
}
