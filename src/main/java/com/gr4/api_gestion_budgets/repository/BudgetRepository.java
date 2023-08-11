package com.gr4.api_gestion_budgets.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr4.api_gestion_budgets.models.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Integer>{
    
    // List<Budget> findByDate_finBeforeAndMont_totalGreaterThan(Date currentDate, Integer montTotal);

    // // Ajoutez cette méthode pour rechercher les budgets dont la date_fin est avant la date actuelle
    // List<Budget> findByDate_finBefore(Date currentDate);
   }
