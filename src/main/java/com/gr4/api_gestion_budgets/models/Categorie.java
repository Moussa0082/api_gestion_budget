package com.gr4.api_gestion_budgets.models;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Categorie {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable= false)
    private String nom;

    @OneToMany(mappedBy = "categorie" ,cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"categorie"})
    private  List<Budget> budget;

    



}
