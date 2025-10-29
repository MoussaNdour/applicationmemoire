package com.example.applicationmemoire.dto.request;

import java.io.Serializable;

public class ServiceRequestDTO implements Serializable {

    private String nom;
    private String description;
    private String categorie;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}

