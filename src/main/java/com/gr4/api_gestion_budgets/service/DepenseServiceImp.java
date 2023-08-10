package com.gr4.api_gestion_budgets.service;

import com.gr4.api_gestion_budgets.models.Budget;
import com.gr4.api_gestion_budgets.models.Depense;
import com.gr4.api_gestion_budgets.repository.BudgetRepository;
import com.gr4.api_gestion_budgets.repository.DepenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DepenseServiceImp implements DepenseService {

    private DepenseRepository depensesRepository;

    private BudgetRepository budgetRepository;


    @Override
    public List<Depense> lire() {
        return depensesRepository.findAll();
    }

    @Override
    public Optional<Depense> findById(Integer id) {
        return depensesRepository.findById(id);

    }

    @Override
    public void supprimer(Integer id) {
        Optional<Depense> depenseOptional = depensesRepository.findById(id);
        if (depenseOptional.isPresent()) {
            Depense depense = depenseOptional.get();
            Budget budget = depense.getBudget();

            // Ajouter le montant de la dépense au montant du budget
            int mont_depense = depense.getMont_depense();
            int mont_budget = budget.getMont_bud();
            int nouveauMontantBudget = mont_budget + mont_depense;
            budget.setMont_bud(nouveauMontantBudget);
            budgetRepository.save(budget);

            // Supprimer la dépense
            depensesRepository.deleteById(id);
        } else {
            throw new RuntimeException("Dépense non trouvée");
        }
    }

}
