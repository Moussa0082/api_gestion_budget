package com.gr4.api_gestion_budgets.controllers;


import com.gr4.api_gestion_budgets.models.Categorie;
import com.gr4.api_gestion_budgets.models.Depense;
import com.gr4.api_gestion_budgets.service.DepenseService;
import io.swagger.v3.oas.annotations.Operation;
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


    @Operation(summary = "Lire une dépense")
    @GetMapping("/read/{id}")
    public Optional<Depense> getCustomer(@PathVariable int id) {
        return depenseService.findById(id);
    }

    @Operation(summary = "Lire les depenses")
    @GetMapping("/read")
    public List<Depense> read(){
        return  depenseService.lire();
    }
}
