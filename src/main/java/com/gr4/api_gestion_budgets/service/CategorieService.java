package com.gr4.api_gestion_budgets.service;

import com.gr4.api_gestion_budgets.models.Categorie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategorieService {

    Categorie creer(Categorie categorie);

    List<Categorie> lire();

    Categorie modifier(long id, Categorie categorie);

    String supprimer(long id);
}
