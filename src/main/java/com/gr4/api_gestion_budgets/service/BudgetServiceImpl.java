package com.gr4.api_gestion_budgets.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gr4.api_gestion_budgets.models.Budget;
import com.gr4.api_gestion_budgets.models.Depense;
import com.gr4.api_gestion_budgets.models.EmailDetails;
import com.gr4.api_gestion_budgets.repository.BudgetRepository;
import com.gr4.api_gestion_budgets.repository.DepenseRepository;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    BudgetRepository budgetRepository;

    @Autowired
    DepenseRepository depenseRepository;

    @Autowired
    EmailServiceImpl emailServiceImpl;


    public BudgetServiceImpl(BudgetRepository budgetRepository, DepenseRepository depenseRepository) {
        this.budgetRepository = budgetRepository;
        this.depenseRepository = depenseRepository;
    }


    @Override
    public ResponseEntity<String> addBudget(Budget budget) {
        if (!checkBudgetDuration(budget)) {
            return new ResponseEntity<>("La durée entre date_debut et date_fin ne doit pas dépasser un mois.", HttpStatus.BAD_REQUEST);
        }
        budgetRepository.save(budget);
        return new ResponseEntity<>("Budget créé avec succès", HttpStatus.CREATED);
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

    //Méthode pour définir la date sur un mois
    @Override
    public boolean checkBudgetDuration(Budget budget) {
        LocalDate date_debut = budget.getDate_debut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date_fin = budget.getDate_fin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return date_debut.plusMonths(1).isAfter(date_fin) || date_debut.plusMonths(1).isEqual(date_fin);
    }


    public String creerDepense(Depense depense) {
        // Récupérer le budget associé à la dépense

        // Integer idb = budget.getId();
        Budget budgets = budgetRepository.findById(depense.getBudget().getId()).orElse(null);

        // Vérifier si le budget existe
        if (budgets == null) {
            return "Budget non trouvé pour l'ID spécifié.";
        }
        // Utiliser LocalDate pour les dates
        LocalDate date_depense = depense.getDate_depense().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date_debut = budgets.getDate_debut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date_fin = budgets.getDate_fin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (date_depense.isBefore(date_debut) || date_depense.isAfter(date_fin)) {
            return "La date de dépense doit être comprise entre " + date_debut + " et " + date_fin + " du budget.";
        }

        int montantDepense = depense.getMont_depense();
        int montantBudget = budgets.getMont_bud();

            // Sauvegarder le montant initial du budget
          int montantInitialBudget = montantBudget;

        // Vérifier si le montant de la dépense est supérieur au montant du budget
        if (montantDepense > montantBudget) {
            return "Le montant de la dépense ne doit pas dépasser celui du budget.";
        } else {
            // Enregistrer la dépense dans la base de données
            depenseRepository.save(depense);

            // Mettre à jour le montant restant dans le budget
            int montantRestant = montantBudget - montantDepense;
            budgets.setMont_bud(montantRestant);
            budgetRepository.save(budgets);

            // Envoyer un e-mail pour informer de la dépense

            String msg = " Vous avez depensez " + depense.getMont_depense() + " Fcfa." +
                         "\n pour une dépense de " + budgets.getCategorie().getNom() + " le " + depense.getDate_depense() +
                         "description : " + depense.getDescription() +
                         ". \n actuellement votre budget est de : " + budgets.getMont_bud() + " Fcfa !" + "votre budget initial était de :" + montantInitialBudget;
            EmailDetails details = new EmailDetails(budgets.getUser().getEmail(), msg, "Alerte depense");

            emailServiceImpl.sendSimpleMail(details);

            // Retourner un message de succès avec le montant restant dans le budget
            return "Dépense créée avec succès. Montant restant dans le budget : " + montantRestant + 
            "votre montant initial était :" + montantInitialBudget + " Fcfa.";
        }
    }
}
    

    

