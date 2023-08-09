package com.gr4.api_gestion_budgets.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@Getter
@Setter
@Data
@Entity
public class Alert {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column
    private Date date_alerte;

    private String getMessage() {
        return null;
    }


    @ManyToOne()
    @JoinColumn(name = "categorie")
    private Categorie categorie;


    //Commentaire
}




