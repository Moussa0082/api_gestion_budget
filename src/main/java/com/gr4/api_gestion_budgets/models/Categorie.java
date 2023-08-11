package com.gr4.api_gestion_budgets.models;
import com.fasterxml.jackson.annotation.JsonIgnore;



import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Categorie {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nom;

    @OneToOne(mappedBy = "categorie")
    @JsonIgnore
    private  Budget budget;


}
