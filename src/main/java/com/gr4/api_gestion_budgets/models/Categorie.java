package com.gr4.api_gestion_budgets.models;

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

    @Temporal(TemporalType.DATE)
    @Column
    private Date date_debut;

    @Temporal(TemporalType.DATE)
    @Column
    private Date date_fin;

    @Column
    private Integer mont_dalerte;
//text
    @ManyToOne
    @JoinColumn(name = "id_budget")
    private  Budget budget;

    @OneToMany(mappedBy = "categorie")
    private List<Depense> depense;


}
