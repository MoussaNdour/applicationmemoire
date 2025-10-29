package com.example.applicationmemoire;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmemoire.dto.UtilisateurDTO;


public class Profile extends AppCompatActivity {
    TextView affiche_prenom,affiche_nom,affiche_email,affiche_telephone,affiche_adresse;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);

        affiche_prenom=findViewById(R.id.affiche_prenom);
        affiche_nom=findViewById(R.id.affiche_nom);
        affiche_email=findViewById(R.id.affiche_email);
        affiche_telephone=findViewById(R.id.affiche_telephone);
        affiche_adresse=findViewById(R.id.affiche_adresse);

        Intent intent=getIntent();
        UtilisateurDTO user= (UtilisateurDTO) intent.getSerializableExtra("user");

        //affichage
        affiche_prenom.setText(user.getPrenom());
        affiche_nom.setText(user.getNom());
        affiche_telephone.setText(String.valueOf(user.getTelephone()));
        affiche_email.setText(user.getEmail());
        affiche_adresse.setText(user.getAdresse());


    }
}