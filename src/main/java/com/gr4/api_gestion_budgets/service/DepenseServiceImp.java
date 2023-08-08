package com.gr4.api_gestion_budgets.service;

import com.gr4.api_gestion_budgets.models.Depense;
import com.gr4.api_gestion_budgets.repository.DepenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepenseServiceImp implements DepenseService {

    private final DepenseRepository depenseRepository;

    @Override
    public Depense creer(Depense depense) {
        return depenseRepository.save(depense);
    }

    @Override
    public List<Depense> lire() {
        return depenseRepository.findAll();
    }

    @Override
    public Optional<Depense> findById(Long id) {
        return depenseRepository.findById(id);
    }


}
