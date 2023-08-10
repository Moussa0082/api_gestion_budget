package com.gr4.api_gestion_budgets.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;



import java.util.Date;
import java.util.List;


@Entity
@Data
public class Budget{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;


    @Column(nullable = false)
    private Integer mont_bud;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date date_debut;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date date_fin;

    @Column
    private Integer mont_dalerte;

    
    @OneToOne(mappedBy = "budget")
    private User user;

    

    @OneToOne
    @JoinColumn(name = "id_categorie", nullable=false)
    private Categorie categorie;

    @OneToMany(mappedBy = "budget")
    @JsonIgnore
    private List<Depense> depense;

}
