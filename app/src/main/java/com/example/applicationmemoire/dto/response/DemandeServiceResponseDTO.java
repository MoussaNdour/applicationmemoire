package com.example.applicationmemoire.dto.response;


import java.io.Serializable;
import java.util.Date;

public class DemandeServiceResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int iddemande;

    private String nomService;

    private String prenomClient;

    private String nomClient;

    private String statut;

    private Date daterendezvous;

    private String statutpaiement;

    private String prenomPrestataire;

    private String nomPrestataire;

    private int duree;

    private String metierClient;

    private String descriptionPrestataire;

    private String adresseClient;

    private String detailsdemande;

    /**
     * Constructor
     */
    public DemandeServiceResponseDTO() {
        super();
    }


    public int getIddemande() {
        return iddemande;
    }

    public void setIddemande(int iddemande) {
        this.iddemande = iddemande;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDaterendezvous() {
        return daterendezvous;
    }

    public void setDaterendezvous(Date daterendezvous) {
        this.daterendezvous = daterendezvous;
    }

    public String getStatutpaiement() {
        return statutpaiement;
    }

    public void setStatutpaiement(String statutpaiement) {
        this.statutpaiement = statutpaiement;
    }

    public String getPrenomPrestataire() {
        return prenomPrestataire;
    }

    public void setPrenomPrestataire(String prenomPrestataire) {
        this.prenomPrestataire = prenomPrestataire;
    }

    public String getNomPrestataire() {
        return nomPrestataire;
    }

    public void setNomPrestataire(String nomPrestataire) {
        this.nomPrestataire = nomPrestataire;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getMetierClient() {
        return metierClient;
    }

    public void setMetierClient(String metierClient) {
        this.metierClient = metierClient;
    }

    public String getDescriptionPrestataire() {
        return descriptionPrestataire;
    }

    public void setDescriptionPrestataire(String descriptionPrestataire) {
        this.descriptionPrestataire = descriptionPrestataire;
    }

    public String getAdresseClient() {
        return adresseClient;
    }

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    public String getDetailsdemande() {
        return detailsdemande;
    }

    public void setDetailsdemande(String detailsdemande) {
        this.detailsdemande = detailsdemande;
    }
}

