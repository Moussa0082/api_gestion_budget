package com.gr4.api_gestion_budgets.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Budget{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   

    @Column(nullable = false)
    private int mt_total;

    @OneToOne(mappedBy = "budget")
    private User user;
   

}
