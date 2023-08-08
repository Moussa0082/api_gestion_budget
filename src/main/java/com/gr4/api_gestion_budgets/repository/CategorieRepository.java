package com.gr4.api_gestion_budgets.repository;

import com.gr4.api_gestion_budgets.models.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
}
