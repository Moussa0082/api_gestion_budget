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
    private Integer id;


    @Column(nullable = false)
    private Integer mont_bud;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date date_debut;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date date_fin;

    @Column
    private Integer mont_dalerte;
    
    @Column
    private Integer mont_total;
    

    
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;


    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private Categorie categorie;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Depense> depense;

}
