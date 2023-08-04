package com.gr4.api_gestion_budgets.models;

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

    @Temporal(TemporalType.DATE)
    @Column
    private Date date_depense;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "categorie")
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private User user;



}
