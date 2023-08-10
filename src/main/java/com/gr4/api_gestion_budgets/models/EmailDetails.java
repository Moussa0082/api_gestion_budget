package com.gr4.api_gestion_budgets.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailDetails {

    private String email;

    private String mesage;

    private String sujet;
    
}
