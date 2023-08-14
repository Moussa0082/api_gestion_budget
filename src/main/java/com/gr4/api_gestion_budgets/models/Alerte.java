package com.gr4.api_gestion_budgets.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Alerte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

  
    
    @Column(length = 1000)
    private String message;

    // @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private String date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}





