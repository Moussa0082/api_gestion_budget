package com.gr4.api_gestion_budgets.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.gr4.api_gestion_budgets.models.Alerte;
import com.gr4.api_gestion_budgets.models.User;
import com.gr4.api_gestion_budgets.repository.AlerteRepository;

@Service
public class AlerteService {

    @Autowired
    private AlerteRepository alerteRepository;

    public List<Alerte> getAlertesByUser(User user) {
        return alerteRepository.findByUser(user);
    }

    public void deleteAlerte(int alerteId) {
        alerteRepository.deleteById(alerteId);
    }
    

    public void saveAlerte(Alerte alerte) {
        alerteRepository.save(alerte);
    }
}


