package com.gr4.api_gestion_budgets.controllers;


import com.gr4.api_gestion_budgets.models.Depense;
import com.gr4.api_gestion_budgets.service.DepenseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/apiDep")
public class DepenseController {
    private DepenseService depenseService;

    //Lire une depense spécifique
    @GetMapping("/read/{id}")
    public Optional<Depense> getDepense(@PathVariable Integer id) {
        return depenseService.findById(id);
    }

    //Lire les dépenses
    @GetMapping("/read")
    public List<Depense> read(){
        return  depenseService.lire();
    }
}
