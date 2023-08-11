package com.gr4.api_gestion_budgets.service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gr4.api_gestion_budgets.models.Alerte;
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

    @Autowired
    private AlerteService alerteService;
    
    public BudgetServiceImpl(BudgetRepository budgetRepository, DepenseRepository depenseRepository) {
        this.budgetRepository = budgetRepository;
        this.depenseRepository = depenseRepository;
    }
        

    @Override
   public ResponseEntity<String> addBudget(Budget budget) {
    
      int mt_tot = budget.getMont_bud();
      if(mt_tot>0){


        budget.setMont_total(mt_tot);
         budgetRepository.save(budget);
    return new ResponseEntity<>("Budget créer avec succèss", HttpStatus.CREATED);
      }
      else{
   
        return new ResponseEntity<>("Le montant du budget doit être supérieur à 0 ", HttpStatus.BAD_REQUEST);

      }

        // budgetRepository.save(budget);
    
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
    // public Budget addDepenseToBudget(int Id, Depense depense, Budget budget) {
    //     Budget existingBudget = budgetRepository.findById(Id).orElse(null);

    //     if (existingBudget != null) {
    //         int montantDepense = depense.getMont_depense();
    //         int nouveauMontantTotal = existingBudget.getMont_bud() - montantDepense;

    //         // Vérifier que le montant total ne devient pas négatif
    //         if (nouveauMontantTotal >= 0) {
    //             existingBudget.setMont_bud(nouveauMontantTotal);

    //             // Enregistrer la modification du budget dans la base de données
    //             budgetRepository.save(existingBudget);

    //             // Définir la relation entre la dépense et le budget
    //             depense.setBudget(existingBudget);
    //             depenseRepository.save(depense);

    //             String msg = "Votre budget est de " + budget.getMont_bud() + " Fcfa." +
    //                      "\nPour une dépense de " + depense.getBudget().getCategorie().getNom() +
    //                      ". \nMaintenant votre solde principal est de : " + budget.getMont_bud() + " Fcfa !";
    //         EmailDetails details = new EmailDetails(budget.getUser().getEmail(), msg, "Détails de votre dépense");
    //         emailServiceImpl.sendSimpleMail(details);

    //             return existingBudget;
    //         } else {
    //             throw new IllegalArgumentException("Montant de la dépense trop élevé pour le budget actuel.");
    //         }
    //     } else {
    //         throw new IllegalArgumentException("Budget non trouvé avec l'ID spécifié.");
    //     }
    // }



    public String creerDepense(Depense depense) {
        // Récupérer le budget associé à la dépense
        
        // Récupérer le budget associé à la dépense
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    // SimpleDateFormat heure = new SimpleDateFormat("hh:mm:ss");
    // Créer un objet SimpleDateFormat pour formater la date
    // Obtenir la date actuelle et la formater
    Date currentDate = new Date();
    // long currentTimDate = currentDate.getTime();
    String dateActuel = dateFormat.format(currentDate);

    // Obtenir l'heure locale et la formater
    LocalTime localTime = LocalTime.now();
    DateTimeFormatter heureFormatter = DateTimeFormatter.ofPattern("hh'h' mm'min' ss's'");
    String heureActuel = localTime.format(heureFormatter);
        // Integer idb = budget.getId();
       Budget budgets = budgetRepository.findById(depense.getBudget().getId()).orElse(null);
    
       

        // Vérifier si le budget existe
        if (budgets == null) {
            return "Budget non trouvé pour l'ID spécifié.";
        }
    
        int montantDepense = depense.getMont_depense();
        int montantBudget = budgets.getMont_bud();

        int mt_tot = budgets.getMont_total();
            // Sauvegarder le montant initial du budget
          int montantInitialBudget = montantBudget;

          
          int mt_al = budgets.getMont_dalerte();

        //     if(mt_al==budgets.getMont_bud()){


        // //  message d'alerte quand le user atteind presque le montant d'alerte 
        //  String msgg = " Votre budget est " + budgets.getMont_bud() + " Fcfa !" + " rappelez - vous \n votre montant d'alerte est : "+ budgets.getMont_dalerte()+
        //                 " votre budget\n après la dernière depense est de :"
        //                  + montantInitialBudget + " FCFA " + " votre budget initial est :" + mt_tot;
        //      //objet de la classe emaildetails pour envoyer l'email à l'utilisateur
        //     EmailDetails details = new EmailDetails
        //     (depense.getUser().getEmail(), msgg, " Alerte ");
        //     //Envoi de l'email avec la methode sendSimpleMail implementer dans emailServiceImpl
        //     emailServiceImpl.sendSimpleMail(details);
        //     return "Alerte , votre montant d'alerte est " + budgets.getMont_dalerte() + 
        //     " veuillez essayer de ne pas depasser votre montant d'alerte" + " votre budget initial est :" + budgets.getMont_total() ;
        // } 

     

        // Vérifier si le montant de la dépense est supérieur au montant du budget
        if (montantDepense > montantBudget) {
              String msg_a = "Votre tentative de dépense de " + montantDepense +
        " Fcfa a été annulée car \nelle est supérieur au montant de votre budget actuel " + budgets.getMont_bud() + " Fcfa."  + "\n"+
        " Veuillez ajuster vos dépenses en conséquence.";
             EmailDetails details = new EmailDetails(depense.getUser().getEmail(), msg_a, "Annulation de Dépense");
         emailServiceImpl.sendSimpleMail(details);
            return "Le montant de la dépense ne doit pas dépasser celui du budget.";
        } 
        // Vérifier si le montant de la dépense est supérieur ou égal à 20% du montant d'alerte
        else if (montantBudget <=  mt_al) {

            budgetRepository.save(budgets);
             // Envoyer un e-mail pour informer de l'annulation de la dépense
        String msgg = "Votre dépense de " + montantDepense +
        " Fcfa a été enregistré mais attention \nvotre budget restant est : "+ budgets.getMont_bud()+ " rappelez vous, \nvotre montant d'alerte est " 
        + budgets.getMont_dalerte() + " Fcfa." +
        " Veuillez ajuster vos dépenses en conséquence.";
       EmailDetails details = new EmailDetails
       (depense.getUser().getEmail(), msgg, "Alerte dépense");
         emailServiceImpl.sendSimpleMail(details);

           

      return "Le montant de la dépense est inferieur au montant d'alerte. Mais la dépense a été enregistrée .";
      }
        
        else {
            // Enregistrer la dépense dans la base de données
            depenseRepository.save(depense);
             
            // Mettre à jour le montant restant dans le budget
            
            int montantRestant = montantBudget - montantDepense;
            budgets.setMont_bud(montantRestant);
            budgetRepository.save(budgets);
            
    
            // Envoyer un e-mail pour lui informer de la dépense
            String msg = " Vous avez depensez  "  + depense.getMont_depense() + " Fcfa. " 
            + " " + depense.getUser().getPrenom().toUpperCase()+
                         "\n pour une dépense de " + budgets.getCategorie().getNom()
                          + " le  : " + dateActuel +
                          " à "+ heureActuel +
                         "  description : " + depense.getDescription() +
                         ". \n actuellement votre budget est de : " 
                         + budgets.getMont_bud() + " Fcfa !" + 
                         " votre budget de la dernière depense était de :"
                          + montantInitialBudget + " votre budget initial est :" + mt_tot;
            EmailDetails details = new EmailDetails
            (depense.getUser().getEmail(), msg, " Alerte depense ");
            emailServiceImpl.sendSimpleMail(details);

            
            // // Créer une instance d'Alerte et la sauvegarder
            //     Alerte alerte = new Alerte();
            //     alerte.setMessage(msg);
            //     // alerte.setDate(d); // Vous devez importer java.util.Date
            //     alerte.setUser(depense.getUser()); // Assurez-vous que l'utilisateur est défini correctement
            //     alerteService.saveAlerte(alerte); // Cette méthode doit être implémentée dans AlerteService
                    
            // Retourner un message de succès avec le montant restant dans le budget
            return "Dépense créée avec succès. Montant restant dans le budget : " + montantRestant + 
            " votre montant de la dernière depense était de : " + montantInitialBudget + " Fcfa.";
         
        
        }
        
    
    }

    // public void transferBudgetsAtEndOfMonth() {
    //     // Obtenir la date actuelle
    //     Date currentDate = new Date();

    //     // Rechercher les budgets dont la date_fin est avant la date actuelle
    //     List<Budget> budgetsToTransfer = budgetRepository.findByDate_finBefore(currentDate);

    //     // Obtenir le mois actuel
    //     Calendar calendar = Calendar.getInstance();
    //     int currentMonth = calendar.get(Calendar.MONTH);

    //     for (Budget budget : budgetsToTransfer) {
    //         // Obtenir le mois de la date de fin du budget
    //         calendar.setTime(budget.getDate_fin());
    //         int budgetMonth = calendar.get(Calendar.MONTH);

    //         // Transférer la somme si le mois de la date de fin correspond au mois actuel
    //         if (currentMonth == budgetMonth) {
    //             int montTotal = budget.getMont_total();
    //             int montBud = budget.getMont_bud();

    //             // Transférer montTotal à montBud et mettre à jour la base de données
    //             montBud += montTotal;
    //             budget.setMont_bud(montBud);
    //             budget.setMont_total(0); // Réinitialiser montTotal

    //             budgetRepository.save(budget);
    //         }
    //     }
    // }
    
        
}



    

    

