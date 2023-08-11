package com.gr4.api_gestion_budgets.service;

import com.gr4.api_gestion_budgets.models.EmailDetails;

public interface EmailService {

     // Method
    // To send a simple email
    String sendSimpleMail(EmailDetails details);

}
