package com.gr4.api_gestion_budgets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gr4.api_gestion_budgets.models.Budget;
import com.gr4.api_gestion_budgets.repository.BudgetRepository;
import com.gr4.api_gestion_budgets.service.BudgetServiceImpl;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    private BudgetServiceImpl budgetServiceImpl;

    @Autowired
    BudgetRepository budgetRepository;

    // public BudgetController(BudgetServiceImpl budgetServiceImpl){
    //     this.budgetServiceImpl = budgetServiceImpl;
    // }
         
    @PostMapping("add")
    ResponseEntity<String> addBudget(@RequestBody Budget budget){
        // Budget br = budgetRepository.findBudgetById(null);
       return budgetServiceImpl.addBudget(budget);
        // return new ResponseEntity<>("Budget Créer avec succès avec un montant de  " + br.getMont_bud() , HttpStatus.OK);

    }
    
   @PutMapping("/update/{id}")
    public Budget Update(@PathVariable Integer id, @RequestBody Budget budget){

        return budgetServiceImpl.modifier(id, budget);
    }


    

    @GetMapping("/all")
    public ResponseEntity<List<Budget>> getAllBudget() {

        return budgetServiceImpl.getAllBudget();
    }

}
