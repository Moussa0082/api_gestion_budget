package com.gr4.api_gestion_budgets.service;

import com.gr4.api_gestion_budgets.models.Categorie;

import java.util.List;
import java.util.Optional;

public interface CategorieService {

    Categorie creer(Categorie categorie);

    List<Categorie> lire();

    Categorie modifier(long id, Categorie categorie);

    String supprimer(long id);

    Optional<Categorie> findById(Long id);
}
