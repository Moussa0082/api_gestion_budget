package com.gr4.api_gestion_budgets.service;

import java.util.ArrayList;
import java.util.List;

import com.gr4.api_gestion_budgets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gr4.api_gestion_budgets.models.Budget;
import com.gr4.api_gestion_budgets.repository.BudgetRepository;

@Service
public class BudgetServiceImpl implements BudgetService{

    @Autowired
    private BudgetRepository budgetRepository;

        

    @Override
   public ResponseEntity<String> addBudget(Budget budget) {

        budgetRepository.save(budget);
    return new ResponseEntity<>("Budget créer avec succèss", HttpStatus.CREATED);
    
   }

   

    @Override
    public Budget modifier(Integer id, Budget budget) {
        return budgetRepository.findById(id)
        .map(bud -> {
            bud.setMont_bud(budget.getMont_bud());
            bud.setMont_dalerte(budget.getMont_dalerte());
            bud.setDate_debut(budget.getDate_debut());
            bud.setDate_fin(budget.getDate_fin());
            return budgetRepository.save(bud);
        }).orElseThrow(() -> new RuntimeException(("Budget non trouvé")));
    }


    

    @Override
    public ResponseEntity<List<Budget>> getAllBudget() {
       try {
            return new ResponseEntity<>(budgetRepository.findAll(), HttpStatus.OK);
        } catch(Exception e) {
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
    

