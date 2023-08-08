package com.gr4.api_gestion_budgets.repository;

import com.gr4.api_gestion_budgets.models.Depense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepenseRepository extends JpaRepository<Depense, Long> {
}
