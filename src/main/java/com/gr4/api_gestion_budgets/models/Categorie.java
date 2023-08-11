package com.gr4.api_gestion_budgets.models;
import com.fasterxml.jackson.annotation.JsonIgnore;



import jakarta.persistence.*;


@Entity
public class Categorie {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(nullable = false)
    private String nom;

    @OneToOne(mappedBy = "categorie")
    @JsonIgnore
    private  Budget budget;

    public Categorie() {
    }

    public Categorie(int id, String nom, Budget budget) {
        this.id = id;
        this.nom = nom;
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }
}
