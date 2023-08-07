package com.gr4.api_gestion_budgets.controllers;


import com.gr4.api_gestion_budgets.models.Categorie;
import com.gr4.api_gestion_budgets.models.Depense;
import com.gr4.api_gestion_budgets.service.DepenseService;
import lombok.AllArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/apiDep")
public class DepenseController {
    private DepenseService depenseService;

    //Créer une dépense
    @PostMapping("/create")
    public Depense create(@RequestBody Depense depense){
        return depenseService.creer(depense);
    }
    //Lire une depense spécifique
    @GetMapping("/read/{id}")
    public Optional<Depense> getCustomer(@PathVariable Long id) {
        return depenseService.findById(id);
    }

    //Lire les dépenses
    @GetMapping("/read")
    public List<Depense> read(){
        return  depenseService.lire();
    }
}
