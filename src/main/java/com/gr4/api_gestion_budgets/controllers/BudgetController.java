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
import com.gr4.api_gestion_budgets.models.Depense;
import com.gr4.api_gestion_budgets.repository.BudgetRepository;
import com.gr4.api_gestion_budgets.service.BudgetServiceImpl;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/budget")
public class BudgetController {

    

    @Autowired
    BudgetServiceImpl budgetServiceImpl;

    @Autowired
    BudgetRepository budgetRepository;

   
    public BudgetController(BudgetServiceImpl budgetServiceImpl) {
        this.budgetServiceImpl = budgetServiceImpl;
    }
     
    @Operation(summary = "Ajouter un budget")
    @PostMapping("add")
    ResponseEntity<String> addBudget(@RequestBody Budget budget){
        // Budget br = budgetRepository.findBudgetById(null);
       return budgetServiceImpl.addBudget(budget);
        // return new ResponseEntity<>("Budget Créer avec succès avec un montant de  " + br.getMont_bud() , HttpStatus.OK);

    }

    
    @Operation(summary = "Mettre à jour un budget existant")
   @PutMapping("/update/{id}")
    public Budget Update(@PathVariable Integer id, @RequestBody Budget budget){

        return budgetServiceImpl.modifier(id, budget);
    }



    
    @Operation(summary = "Afficher la liste des budgets existants")
    @GetMapping("/all")
    public ResponseEntity<List<Budget>> getAllBudget() {

        return budgetServiceImpl.getAllBudget();
    }


    // Endpoint pour ajouter une dépense à un budget
    // @PutMapping("/{Id}/addDepense")
    // public ResponseEntity<Budget> addDepenseToBudget(@PathVariable int Id, @RequestBody Depense depense, Budget budget) {
    //     try {
    //         Budget updatedBudget = budgetServiceImpl.addDepenseToBudget(Id, depense, budget);
    //         return new ResponseEntity<>(updatedBudget, HttpStatus.CREATED);
    //     } catch (IllegalArgumentException e) {
    //         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
      @Operation(summary = "Depenser de l'argent")
    @PutMapping("/{Id}/addDep")
    public String creer(@RequestBody Depense depense){
        return budgetServiceImpl.creerDepense(depense);
    }

    
}
