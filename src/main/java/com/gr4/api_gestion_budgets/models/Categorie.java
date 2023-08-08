package com.gr4.api_gestion_budgets.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nom_cat;

    @OneToOne(mappedBy = "categorie")
    private  Budget budget;

    @OneToMany(mappedBy = "categorie")
    private List<Depense> depense;


}
