package com.gr4.api_gestion_budgets.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.gr4.api_gestion_budgets.interfacesServices.BudgetService;
import com.gr4.api_gestion_budgets.models.Budget;

public class BudgetServiceimpl implements BudgetService{

    @Override
    public ResponseEntity<String> addBudget(Budget budget) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addBudget'");
    }

    @Override
    public ResponseEntity<String> updateBudget(Integer id, Budget budget) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBudget'");
    }

    @Override
    public ResponseEntity<List<Budget>> getAllBudget() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllBudget'");
    }

    @Override
    public ResponseEntity<String> supprimerBudget(Integer id, Budget budget) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'supprimerBudget'");
    }
    
}
