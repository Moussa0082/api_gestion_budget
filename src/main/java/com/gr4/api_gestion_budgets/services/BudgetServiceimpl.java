package com.gr4.api_gestion_budgets.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gr4.api_gestion_budgets.interfacesServices.BudgetService;
import com.gr4.api_gestion_budgets.models.Budget;
import com.gr4.api_gestion_budgets.repository.BudgetRepository;

@Service
public class BudgetServiceImpl implements BudgetService{

    @Autowired
    private BudgetRepository budgetRepository;

        

    @Override
   public ResponseEntity<Integer> addBudget(Budget budget) {
    // Chercher le budget existant dans la base de données par son id
           throw new UnsupportedOperationException("Unimplemented method 'updateBudget'");

}




    @Override
    public ResponseEntity<String> updateBudget(Integer id, Budget budget) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBudget'");
    }

    @Override
    public ResponseEntity<List<Budget>> getAllBudget() {
       try {
            return new ResponseEntity<>(budgetRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
             e.printStackTrace();
        }
       return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> supprimerBudget(Integer id, Budget budget) {
      
        budgetRepository.deleteById(id);
        return new ResponseEntity<>("supprimer avec succès", HttpStatus.OK);
    }
    
    }
    

