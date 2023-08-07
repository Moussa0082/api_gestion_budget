package com.gr4.api_gestion_budgets.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Alerte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date date_alerte;

    @ManyToOne
    @JoinColumn(name = "id_alerte")
    private Categorie categorie;

}
