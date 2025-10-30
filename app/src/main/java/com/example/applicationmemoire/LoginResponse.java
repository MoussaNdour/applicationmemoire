package com.example.applicationmemoire;

import com.example.applicationmemoire.dto.UtilisateurDTO;

public class LoginResponse {
    private String token;
    private UtilisateurDTO utilisateur;

    public String getToken() { return token; }
    public UtilisateurDTO getUtilisateur() { return utilisateur; }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUtilisateur(UtilisateurDTO utilisateur) {
        this.utilisateur = utilisateur;
    }
}

