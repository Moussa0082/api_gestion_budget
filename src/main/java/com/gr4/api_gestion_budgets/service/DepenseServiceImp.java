package com.gr4.api_gestion_budgets.service;

import com.gr4.api_gestion_budgets.models.Depense;
import com.gr4.api_gestion_budgets.repository.DepenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DepenseServiceImp implements DepenseService {

    private  DepenseRepository depensesRepository;


    @Override
    public List<Depense> lire() {
        return depensesRepository.findAll();
    }

    @Override
    public Optional<Depense> findById(Integer id) {
        return depensesRepository.findById(id);

    }


}
