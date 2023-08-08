package com.gr4.api_gestion_budgets.controllers;


import com.gr4.api_gestion_budgets.models.Depense;
import com.gr4.api_gestion_budgets.service.DepenseService;
import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api ")
public class DepenseController {
    private DepenseService depenseService;

    @PostMapping("/depense")
    public Depense create(@RequestBody Depense depense){
        return depenseService.creer(depense);
    }

    @GetMapping("/LDepense")
    public List<Depense> read(@PathVariable Long id){
        return  depenseService.lire();
    }
}
