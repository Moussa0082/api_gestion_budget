package com.gr4.api_gestion_budgets.service;

import com.gr4.api_gestion_budgets.models.Depense;

import java.util.List;
import java.util.Optional;


public interface DepenseService {

    List<Depense> lire();

    Optional<Depense> findById(Integer id);
}
