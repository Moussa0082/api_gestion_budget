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
    private Long id;

    @Column
    private String nom_cat;

    @Column
    private Integer mont_cat;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date date_debut;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date date_fin;

    @Column
    private Integer mont_dalerte;

    @ManyToOne
    @JoinColumn(name = "id_budget")
    private  Budget budget;

    @OneToMany(mappedBy = "categorie")
    private List<Depense> depense;

    @OneToMany(mappedBy = "categorie")
    private List<Alerte> alerte;


}
