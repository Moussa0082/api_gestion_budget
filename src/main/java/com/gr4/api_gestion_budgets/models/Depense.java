package com.gr4.api_gestion_budgets.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Depense {


  

    public enum TypeDepense{
        MENSUEL,
        QUOTIDIEN,
        HEBDOMADAIRE
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column
    private TypeDepense typeDepense;

    @Column
    private Integer mont_depense;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date date_depense;

    @Column
    private String description;


    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_budget", nullable = false)
    private Budget budget;

}
