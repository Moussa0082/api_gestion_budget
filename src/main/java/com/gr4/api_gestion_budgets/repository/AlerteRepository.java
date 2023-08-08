package com.gr4.api_gestion_budgets.repository;

import com.gr4.api_gestion_budgets.models.Alerte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlerteRepository extends JpaRepository<Alerte, Integer> {
}
