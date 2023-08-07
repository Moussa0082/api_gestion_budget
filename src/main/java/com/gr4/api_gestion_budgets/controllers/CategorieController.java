package com.gr4.api_gestion_budgets.controllers;

import com.gr4.api_gestion_budgets.models.Categorie;
import com.gr4.api_gestion_budgets.service.CategorieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/apiCat")
public class CategorieController {
    private final CategorieService categorieService;

    //Créer une categorie
    @PostMapping("/create")
    public Categorie create(@RequestBody Categorie categorie){
        return categorieService.creer(categorie);
    }
    //Afficher les categories
    @GetMapping("/read")
    public List<Categorie> read(){
        return categorieService.lire();
    }
    //Lire une categorie spécifique
    @GetMapping("/read/{id}")
    public Optional<Categorie> getCustomer(@PathVariable Long id) {
        return categorieService.findById(id);
    }

    //Mettre à jour une catégorie
    @PutMapping("/update/{id}")
    public Categorie Update(@PathVariable long id, @RequestBody Categorie quiz){
        return categorieService.modifier(id, quiz);
    }

    //Effacer une categorie
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable long id){
        return categorieService.supprimer(id);
    }

}
