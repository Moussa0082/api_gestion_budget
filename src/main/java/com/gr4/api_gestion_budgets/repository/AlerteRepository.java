package com.gr4.api_gestion_budgets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr4.api_gestion_budgets.models.Alerte;
import com.gr4.api_gestion_budgets.models.User;

public interface AlerteRepository extends JpaRepository<Alerte, Integer>{
 
    List<Alerte> findByUser(User user);
}
