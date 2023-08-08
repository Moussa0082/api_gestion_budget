package com.gr4.api_gestion_budgets.service;

import com.gr4.api_gestion_budgets.models.Depense;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepenseService {

    Depense creer(Depense depense);

    List<Depense> lire();

}
