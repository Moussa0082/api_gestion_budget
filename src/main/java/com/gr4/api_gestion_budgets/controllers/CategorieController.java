package com.gr4.api_gestion_budgets.controllers;

import com.gr4.api_gestion_budgets.models.Categorie;
import com.gr4.api_gestion_budgets.service.CategorieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CategorieController {
    private final CategorieService categorieService;

    @PostMapping("/categorie")
    public Categorie create(@RequestBody Categorie quiz){
        return categorieService.creer(quiz);
    }

    @GetMapping("/read")
    public List<Categorie> read(){
        return categorieService.lire();
    }


    @PutMapping("/update/{id}")
    public Categorie Update(@PathVariable long id, @RequestBody Categorie quiz){
        return categorieService.modifier(id, quiz);
    }


    @DeleteMapping("/delete")
    public String delete(@PathVariable long id){
        return categorieService.supprimer(id);
    }

}
