package com.gr4.api_gestion_budgets.service;

import com.gr4.api_gestion_budgets.models.Categorie;

import java.util.List;
import java.util.Optional;

public interface CategorieService {

    Categorie creer(Categorie categorie);

    List<Categorie> lire();

    Categorie modifier(Integer id, Categorie categorie);

    String supprimer(Integer id);

    Optional<Categorie> findById(Integer id);
}
