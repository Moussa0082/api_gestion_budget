package com.gr4.api_gestion_budgets.service;

import com.gr4.api_gestion_budgets.repository.AlerteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor

public class AlerteServiceImp implements AlerteService {
    private final AlerteRepository alerteRepository;
    @Override

    public AlerteRepository message(String message){
        return alerteRepository;
    }


}
