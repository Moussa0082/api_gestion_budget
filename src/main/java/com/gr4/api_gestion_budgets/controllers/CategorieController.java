package com.gr4.api_gestion_budgets.controllers;

import com.gr4.api_gestion_budgets.models.Categorie;
import com.gr4.api_gestion_budgets.service.CategorieService;
import com.gr4.api_gestion_budgets.service.CategorieServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Data
@RequestMapping("/apiCat")
public class CategorieController {
    private final   CategorieService categorieService;

    @Operation(summary = "Créer une categorie")
    @PostMapping("/create")
    public String create(@RequestBody Categorie categorie) {
        return categorieService.creer(categorie);
    }

    @Operation(summary = "Lire la liste des ctegories")
    @GetMapping("/read")
    public List<Categorie> read(){
        return categorieService.lire();
    }

    @Operation(summary = "Lire une categorie spécifique")
    @GetMapping("/read/{id}")
    public Optional<Categorie> getCustomer(@PathVariable int id) {
        return categorieService.findById(id);
    }

    @Operation(summary = "Mettre à jour une categorie spécifique")
    @PutMapping("/update/{id}")
    public Categorie Update(@PathVariable int id, @RequestBody Categorie categorie){
        return categorieService.modifier(id, categorie);
    }

    @Operation(summary = "Supprimer une categorie")
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        return categorieService.supprimer(id);
    }

}
