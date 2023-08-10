package com.gr4.api_gestion_budgets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.gr4.api_gestion_budgets.models.EmailDetails;


// Annotation
@Service
// Class
// Implementing EmailService interface
public class EmailServiceImpl implements EmailService {
 
    @Autowired private JavaMailSender javaMailSender;
 
    @Value("mohamedelmoctaralmaimoune@gmail.com") private String sender;
  

    @Override
    public String sendSimpleMail(EmailDetails details) {
       // Method 1
    // To send a simple email
    
 
        // Try block to check for exceptions
        try {
 
            // Creating a simple mail message
            SimpleMailMessage mailMessage
                = new SimpleMailMessage();
 
            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getEmail());
            mailMessage.setText(details.getMesage());
            mailMessage.setSubject(details.getSujet());
 
            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Email envoyer avec succès...";
        }
 
        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Erreur lors de l'envoi de l'email ";
        }
    }   
    
}