package com.example.applicationmemoire;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmemoire.apiservice.ApiService;
import com.example.applicationmemoire.dto.RegisterUtilisateurDTO;
import com.example.applicationmemoire.dto.UtilisateurDTO;
import com.google.android.material.button.MaterialButton;

import java.util.Calendar;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Inscription extends AppCompatActivity {

    private MaterialButton bouton_s_inscrire;
    private EditText champ_prenom,champ_nom,champ_email,champ_telephone,champ_adresse,champ_mdp, champ_confirm_mdp;
    private DatePicker champ_date_nais;
    private Spinner champ_type_compte;
    private ImageButton retour;

    private TextView champ_error;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inscription);

        retour=findViewById(R.id.retour_connexion);

        champ_prenom=findViewById(R.id.prenom);
        champ_nom=findViewById(R.id.nom);
        champ_adresse=findViewById(R.id.adresse);
        champ_email=findViewById(R.id.email);
        champ_telephone=findViewById(R.id.telephone);
        champ_mdp=findViewById(R.id.mdp);
        champ_confirm_mdp=findViewById(R.id.confirm_mdp);
        champ_date_nais=findViewById(R.id.date_nais);
        champ_type_compte=findViewById(R.id.type_compte);
        champ_error=findViewById(R.id.error_field);
        bouton_s_inscrire=findViewById(R.id.s_inscrire);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Inscription.this,Connexion.class);
                startActivity(intent);
            }
        });

        bouton_s_inscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prenom=champ_prenom.getText().toString();
                String nom=champ_nom.getText().toString();
                String email=champ_email.getText().toString();
                String telephone=champ_telephone.getText().toString();
                String adresse=champ_adresse.getText().toString();
                String mot_de_pass=champ_mdp.getText().toString();
                String confirm_mdp=champ_confirm_mdp.getText().toString();


                int day =champ_date_nais.getDayOfMonth();
                int month = champ_date_nais.getMonth();
                int year = champ_date_nais.getYear();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                Date date_de_naissance = calendar.getTime();

                String type_compte=champ_type_compte.getSelectedItem().toString();

                //test and validation of datas
                if(prenom.isBlank() || nom.isBlank() || email.isBlank() || telephone.isBlank() || adresse.isBlank() || mot_de_pass.isBlank() || confirm_mdp.isBlank()){
                    champ_error.setText("Tous les champs doivent etre remplies");
                }

                // Crée l'interceptor
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY); // log complet : headers + body

// Ajoute à OkHttpClient
                OkHttpClient client = new OkHttpClient.Builder()
                        .addInterceptor(logging)
                        .build();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.6:8080/")
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiService apiService = retrofit.create(ApiService.class);

                RegisterUtilisateurDTO newUser = new RegisterUtilisateurDTO();

                newUser.setTelephone(Long.parseLong(telephone));
                newUser.setPrenom(prenom.trim());
                newUser.setNom(nom.trim());
                newUser.setAdresse(adresse.trim());
                newUser.setEmail(email.trim());
                newUser.setMotdepasse(mot_de_pass.trim());



                if(type_compte.toUpperCase().equals("CLIENT")) {
                    apiService.inscrireClient(newUser).enqueue(new Callback<UtilisateurDTO>() {
                        @Override
                        public void onResponse(Call<UtilisateurDTO> call, Response<UtilisateurDTO> response) {
                            if (response.isSuccessful()) {
                                UtilisateurDTO inscrit = response.body();
                                if (inscrit != null) {
                                    Toast.makeText(Inscription.this,
                                            "Inscrit avec succès : " + inscrit.getPrenom() + " " + inscrit.getNom(),
                                            Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(Inscription.this, "Erreur: " + response.code(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UtilisateurDTO> call, Throwable t) {
                            Toast.makeText(Inscription.this, "Échec réseau : " + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    apiService.inscrirePrestataire(newUser).enqueue(new Callback<UtilisateurDTO>() {
                        @Override
                        public void onResponse(Call<UtilisateurDTO> call, Response<UtilisateurDTO> response) {
                            if (response.isSuccessful()) {
                                UtilisateurDTO inscrit = response.body();
                                if (inscrit != null) {
                                    Toast.makeText(Inscription.this,
                                            "Inscrit avec succès : " + inscrit.getPrenom() + " " + inscrit.getNom(),
                                            Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(Inscription.this, "Erreur: " + response.code(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UtilisateurDTO> call, Throwable t) {
                            Toast.makeText(Inscription.this, "Échec réseau : " + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }


            }
        });

    }

}