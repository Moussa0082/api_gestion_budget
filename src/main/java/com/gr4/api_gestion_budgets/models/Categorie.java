package com.gr4.api_gestion_budgets.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Entity
@Data
public class Categorie {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable= false)
    @NotBlank(message = "le nom ne doit pas etre vide")
    private String nom_cat;

    @OneToOne(mappedBy = "categorie")
    @JsonIgnore
    private  Budget budget;

    



}
