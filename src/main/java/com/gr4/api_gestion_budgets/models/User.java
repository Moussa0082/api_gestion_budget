package com.gr4.api_gestion_budgets.models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class User {

    public enum Sexe{
        masculin,
        feminin
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length= 100)
    private String nom;

    @Column(nullable = false, length= 100)
    private String prenom;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    @Column(nullable = false, length= 100)
    private String profession;

    @Column(nullable = false, length= 100)
    private String email;

    @Column(nullable = false, length= 50)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Budget budget;

    //Commentaire
}