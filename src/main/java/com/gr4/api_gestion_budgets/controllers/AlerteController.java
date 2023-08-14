package com.gr4.api_gestion_budgets.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gr4.api_gestion_budgets.models.Alerte;
import com.gr4.api_gestion_budgets.models.User;
import com.gr4.api_gestion_budgets.service.AlerteService;

@RestController
@RequestMapping("/alertes")
public class AlerteController {

    @Autowired
    private AlerteService alerteService;

    @GetMapping("/historique/{userId}")
    public ResponseEntity<List<Alerte>> getHistoriqueAlertes(@PathVariable int userId) {
        User user = new User();
        user.setId(userId); // Créez un objet User avec l'ID correspondant

        List<Alerte> historiqueAlertes = alerteService.getAlertesByUser(user);

        return new ResponseEntity<>(historiqueAlertes, HttpStatus.OK);
    }

    @DeleteMapping("/{alerteId}")
    public ResponseEntity<String> deleteAlerte(@PathVariable int alerteId) {
        alerteService.deleteAlerte(alerteId);
        return new ResponseEntity<>("Alerte supprimée avec succès", HttpStatus.OK);
    }
}