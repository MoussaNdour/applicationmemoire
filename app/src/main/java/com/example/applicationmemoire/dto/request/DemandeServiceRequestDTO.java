package com.example.applicationmemoire.dto.request;


import java.util.Date;

public class DemandeServiceRequestDTO {


    private Integer idclient;


    private Integer idprestataire;


    private Integer idservice;


    private String detailsdemande;


    private Date daterendezvous;


    private Integer idcontrat;

    public Integer getIdclient() {
        return idclient;
    }

    public void setIdclient(Integer idclient) {
        this.idclient = idclient;
    }

    public Integer getIdprestataire() {
        return idprestataire;
    }

    public void setIdprestataire(Integer idprestataire) {
        this.idprestataire = idprestataire;
    }

    public Integer getIdservice() {
        return idservice;
    }

    public void setIdservice(Integer idservice) {
        this.idservice = idservice;
    }


    public String getDetailsdemande() {
        return detailsdemande;
    }

    public void setDetailsdemande(String detailsdemande) {
        this.detailsdemande = detailsdemande;
    }

    public Date getDaterendezvous() {
        return daterendezvous;
    }

    public void setDaterendezvous(Date daterendezvous) {
        this.daterendezvous = daterendezvous;
    }

    public Integer getIdcontrat() {
        return idcontrat;
    }

    public void setIdcontrat(Integer idcontrat) {
        this.idcontrat = idcontrat;
    }
}
