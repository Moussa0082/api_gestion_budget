package com.gr4.api_gestion_budgets.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr4.api_gestion_budgets.models.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Integer>{
    
    Optional<Budget> findById(Integer idBudget);
}
