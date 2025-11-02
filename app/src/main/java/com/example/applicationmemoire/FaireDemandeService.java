package com.example.applicationmemoire;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmemoire.apiservice.ApiCallback;
import com.example.applicationmemoire.dto.UtilisateurDTO;
import com.example.applicationmemoire.dto.request.DemandeServiceRequestDTO;
import com.example.applicationmemoire.dto.response.PrestataireResponseDTO;
import com.example.applicationmemoire.dto.response.ServiceResponseDTO;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class FaireDemandeService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_faire_demande_service);

        Intent intent=getIntent();

        UtilisateurDTO user = (UtilisateurDTO) intent.getSerializableExtra("user");
        String token=intent.getStringExtra("token");
        ServiceResponseDTO service=(ServiceResponseDTO) intent.getSerializableExtra("service");
        PrestataireResponseDTO prestataire=(PrestataireResponseDTO) intent.getSerializableExtra("prestataire");


        TextInputEditText nomService = findViewById(R.id.nomService);
        TextInputEditText nomPrestataire = findViewById(R.id.nomPrestataire);
        TextInputEditText details = findViewById(R.id.detailsDemande);
        TextInputEditText date = findViewById(R.id.dateRendezVous);
        MaterialButton btnConfirmer = findViewById(R.id.btnConfirmerDemande);


        nomService.setText(service.getNom());
        nomPrestataire.setText(prestataire.getPrenom() + " " + prestataire.getNom());
        details.setText("");

        date.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            DatePickerDialog picker = new DatePickerDialog(this,
                    (view, year, month, dayOfMonth) -> {
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        date.setText(selectedDate);
                    },
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH)
            );
            picker.show();
        });

        btnConfirmer.setOnClickListener(v -> {
            String detailsTxt = details.getText().toString();
            String dateTxt = date.getText().toString();
            // → ici tu envoies la requête vers ton API Spring Boot
            DemandeServiceRequestDTO demande=new DemandeServiceRequestDTO();
            demande.setIdclient();
            demande.setIdprestataire();
            demande.setIdservice();
            demande.setDetailsdemande();
            demande.setDaterendezvous(date.getText().toString());
            ApiManager apiManager=new ApiManager();

            apiManager.faireDemandeService(new DemandeServiceRequestDTO(), new ApiCallback() {
                @Override
                public void onSuccess(Object result) {

                }

                @Override
                public void onError(Throwable t) {

                }
            });
        });



    }
}