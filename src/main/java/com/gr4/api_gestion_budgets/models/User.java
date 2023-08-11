package com.gr4.api_gestion_budgets.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
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

    @Column(unique = true, nullable = false, length= 100)
    private String email;

    @Column(nullable = false, length= 50)
    private String password;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Budget> budget;
     

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Alerte> alertes;

}
