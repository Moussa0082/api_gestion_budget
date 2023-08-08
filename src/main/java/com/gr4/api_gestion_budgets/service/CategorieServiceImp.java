package com.gr4.api_gestion_budgets.service;

import com.gr4.api_gestion_budgets.models.Categorie;
import com.gr4.api_gestion_budgets.repository.CategorieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategorieServiceImp implements CategorieService {

    private final CategorieRepository categorieRepository;

    @Override
    public Categorie creer(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public List<Categorie> lire() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie modifier(Integer id, Categorie categorie) {
        return categorieRepository.findById(id)
                .map(ca -> {
                    ca.setNom_cat(categorie.getNom_cat());
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
