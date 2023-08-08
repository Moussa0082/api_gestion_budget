package com.gr4.api_gestion_budgets.service;

import com.gr4.api_gestion_budgets.models.Categorie;
import com.gr4.api_gestion_budgets.repository.CategorieRepository;
import com.gr4.api_gestion_budgets.service.CategorieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Categorie modifier(long id, Categorie categorie) {
        return categorieRepository.findById(id)
                .map(ca -> {
                    ca.setNom_cat(categorie.getNom_cat());
                    ca.setMont_cat(categorie.getMont_cat());
                    ca.setMont_dalerte(categorie.getMont_dalerte());
                    ca.setDate_debut(categorie.getDate_debut());
                    ca.setDate_fin(categorie.getDate_fin());
                    return categorieRepository.save(ca);
                }).orElseThrow(() -> new RuntimeException(("Categorie non trouvé")));
    }

    @Override
    public String supprimer(long id) {
        categorieRepository.deleteById(id);
        return "Categorie supprimée avec succés";
    }
}
