package com.gr4.api_gestion_budgets.service;

import java.util.ArrayList;
import java.util.List;

import com.gr4.api_gestion_budgets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.gr4.api_gestion_budgets.models.Budget;
import com.gr4.api_gestion_budgets.models.Depense;
import com.gr4.api_gestion_budgets.repository.BudgetRepository;
import com.gr4.api_gestion_budgets.repository.DepenseRepository;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private DepenseRepository depenseRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    //Methode pour mail
    private void sendAlertEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Alerte de dépenses dépassant le budget");
        message.setText("Vous avez dépassé votre budget. Veuillez vérifier vos dépenses.");
        javaMailSender.send(message);
    }

    public BudgetServiceImpl(BudgetRepository budgetRepository, DepenseRepository depenseRepository) {
        this.budgetRepository = budgetRepository;
        this.depenseRepository = depenseRepository;
    }


    @Override
    public ResponseEntity<String> addBudget(Budget budget) {

        budgetRepository.save(budget);
        return new ResponseEntity<>("Budget créer avec succèss", HttpStatus.CREATED);

    }


    @Override
    public Budget modifier(Integer id, Budget budget) {
        return budgetRepository.findById(id)
                .map(bud -> {
                    bud.setMont_bud(budget.getMont_bud());
                    bud.setMont_dalerte(budget.getMont_dalerte());
                    bud.setDate_debut(budget.getDate_debut());
                    bud.setDate_fin(budget.getDate_fin());
                    return budgetRepository.save(bud);
                }).orElseThrow(() -> new RuntimeException(("Budget non trouvé")));
    }


    @Override
    public ResponseEntity<List<Budget>> getAllBudget() {
        try {
            return new ResponseEntity<>(budgetRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> supprimerBudget(Integer id, Budget budget) {

        budgetRepository.deleteById(id);
        return new ResponseEntity<>("supprimer avec succès", HttpStatus.OK);
    }
//    // Méthode pour ajouter une dépense à un budget donné
//    public Budget addDepenseToBudget(int Id, Depense depense) {
//        Budget existingBudget = budgetRepository.findById(Id).orElse(null);
//
//        if (existingBudget != null) {
//            int montantDepense = depense.getMont_depense();
//            int nouveauMontantTotal = existingBudget.getMont_bud() - montantDepense;
//
//            if (montantDepense > nouveauMontantTotal) {
//                User user = existingBudget.getUser();
//                sendAlertEmail(user);
//           }
//
//            // Vérifier que le montant total ne devient pas négatif
//            if (nouveauMontantTotal >= 0) {
//                existingBudget.setMont_bud(nouveauMontantTotal);
//
//                // Enregistrer la modification du budget dans la base de données
//                budgetRepository.save(existingBudget);
//
//                // Définir la relation entre la dépense et le budget
//                depense.setBudget(existingBudget);
//                depenseRepository.save(depense);
//
//                return existingBudget;
//            } else {
//                throw new IllegalArgumentException("Montant de la dépense trop élevé pour le budget actuel.");
//            }
//        } else {
//            throw new IllegalArgumentException("Budget non trouvé avec l'ID spécifié.");
//        }
//    }
}


    

