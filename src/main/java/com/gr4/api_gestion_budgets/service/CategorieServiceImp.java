package com.gr4.api_gestion_budgets.service;

import com.gr4.api_gestion_budgets.models.Categorie;
import com.gr4.api_gestion_budgets.repository.CategorieRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Data
public class CategorieServiceImp implements CategorieService {
    private final CategorieRepository categorieRepository;


    @Override
    public String creer(Categorie categorie) {
        try {
            Categorie categorie1 = categorieRepository.getByNom(categorie.getNom());
            if (categorie1 == null && categorie.getNom() != null && categorie.getNom() != "" && categorie.getNom() != " ") {
                categorieRepository.save(categorie);
                return "Categorie créer avec succés";
            } else if (categorie1 != null) {
                return "Categorie existe déjà!";

            } else if (categorie.getNom() == null || categorie.getNom() == "" || categorie.getNom() == " ") {
                return "Le nom ne doit pas etre vide";

            }
        } catch (RuntimeException e) {
            return "message: " + e;
        }
        return "cette categorie existe déjà";
    }

//        String msg = "";
//        if (categorie1 == null && categorie.getNom() != null){
//            categorieRepository.save(categorie);
//            msg = "Catégorie creer avec succès";
//        }
//        if (categorie1 != null) {
//            msg = "Une Catégorie avec le nom "+categorie1+" existe déjà";
//            return null;
//        }
//        return msg;


    @Override
    public List<Categorie> lire() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie modifier(Integer id, Categorie categorie) {
        return categorieRepository.findById(id)
                .map(ca -> {
                    ca.setNom(categorie.getNom());
                    return categorieRepository.save(ca);
                }).orElseThrow(() -> new RuntimeException(("Categorie non trouvé")));
    }

    @Override
    public String supprimer(Integer id) {
        categorieRepository.deleteById(id);
        return "Categorie supprimée avec succés";
    }

    @Override
    public Optional<Categorie> findById(Integer id) {
        return categorieRepository.findById(id);
    }
}
