package com.gr4.api_gestion_budgets.service;

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
public class BudgetServiceImpl implements BudgetService{

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
        } catch(Exception e) {
             e.printStackTrace();
        }
       return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> supprimerBudget(Integer id, Budget budget) {
      
        budgetRepository.deleteById(id);
        return new ResponseEntity<>("supprimer avec succès", HttpStatus.OK);
    }

   

   // Méthode pour ajouter une dépense à un budget donné
    public Budget addDepenseToBudget(int Id, Depense depense, Budget budget) {
        Budget existingBudget = budgetRepository.findById(Id).orElse(null);

        if (existingBudget != null) {
            int montantDepense = depense.getMont_depense();
            int nouveauMontantTotal = existingBudget.getMont_bud() - montantDepense;

            // Vérifier que le montant total ne devient pas négatif
            if (nouveauMontantTotal >= 0) {
                existingBudget.setMont_bud(nouveauMontantTotal);

                // Enregistrer la modification du budget dans la base de données
                budgetRepository.save(existingBudget);

                // Définir la relation entre la dépense et le budget
                depense.setBudget(existingBudget);
                depenseRepository.save(depense);

                String msg = "Votre budget est de " + budget.getMont_bud() + " Fcfa." +
                         "\nPour une dépense de " + depense.getBudget().getCategorie().getNom() +
                         ". \nMaintenant votre solde principal est de : " + budget.getMont_bud() + " Fcfa !";
            EmailDetails details = new EmailDetails(depense.getUser().getEmail(), msg, "Détails de votre dépense");
            emailServiceImpl.sendSimpleMail(details);

                return existingBudget;
            } else {
                throw new IllegalArgumentException("Montant de la dépense trop élevé pour le budget actuel.");
            }
        } else {
            throw new IllegalArgumentException("Budget non trouvé avec l'ID spécifié.");
        }
    }





    public String creerDepense(Depense depense) {
        // Récupérer le budget associé à la dépense

        // Integer idb = budget.getId();
       Budget budgets = budgetRepository.findById(depense.getBudget().getId()).orElse(null);
    
        // Vérifier si le budget existe
        if (budgets == null) {
            return "Budget non trouvé pour l'ID spécifié.";
        }
    
        int montantDepense = depense.getMont_depense();
        int montantBudget = budgets.getMont_bud();
    
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
                         "\n pour une dépense de " + budgets.getCategorie().getNom() +
                         ". \n actuellement votre budget est de : " + budgets.getMont_bud() + " Fcfa !";
            EmailDetails details = new EmailDetails(depense.getUser().getEmail(), msg, "Détails de votre dépense");
            emailServiceImpl.sendSimpleMail(details);
    
            // Retourner un message de succès avec le montant restant dans le budget
            return "Dépense créée avec succès. Montant restant dans le budget : " + montantRestant + "votre montant initial était :";
        }
    }
    

    
}
    

