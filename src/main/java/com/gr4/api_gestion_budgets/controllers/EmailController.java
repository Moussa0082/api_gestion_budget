package com.gr4.api_gestion_budgets.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gr4.api_gestion_budgets.models.EmailDetails;
import com.gr4.api_gestion_budgets.service.EmailService;

@RestController
@RequestMapping("/mail")
public class EmailController {

     @Autowired private EmailService emailService;
 
    @Operation(summary = "Envoyer un mail")
    @PostMapping("/sendMail")
    public String
    sendMail(@RequestBody EmailDetails details)
    {
        String status
            = emailService.sendSimpleMail(details);
 
        return status;
    }
    
}
