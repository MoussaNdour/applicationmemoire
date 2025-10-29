package com.example.applicationmemoire.dto.response;

import java.io.Serializable;


public class ServiceResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idservice;
    private String nom ;
    private String description ;
    private String categorie ;

    private String icone;


    /**
     * Constructor
     */
    public ServiceResponseDTO() {
        super();
    }

    public int getIdservice() {
        return idservice;
    }

    public void setIdservice(int idservice) {
        this.idservice = idservice;
    }

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

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }
}

