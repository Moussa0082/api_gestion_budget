package com.gr4.api_gestion_budgets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gr4.api_gestion_budgets.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    
    User findUserByEmail (String email);

    boolean existsByEmail(String email);
}
