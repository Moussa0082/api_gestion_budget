package com.gr4.api_gestion_budgets.service;

import com.gr4.api_gestion_budgets.repository.AlerteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AlerteServiceImp implements AlerteService {
    private final AlerteRepository alerteRepository;
    @Override

    public AlerteRepository message(String message){
        return alerteRepository;
    }
        @Autowired
        private JavaMailSender mailSender;

        public void sendEmail(String to, String subject, String body) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);
        }
    }

