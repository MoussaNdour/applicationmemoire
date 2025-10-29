package com.example.applicationmemoire;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmemoire.apiservice.ApiCallback;
import com.example.applicationmemoire.dto.response.PrestataireResponseDTO;
import com.google.android.flexbox.FlexboxLayout;

import java.util.List;


public class PrestataireParService extends AppCompatActivity {

    private FlexboxLayout les_prestataire;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_prestataire_par_service);

        les_prestataire=findViewById(R.id.prestatairesParService);

        Intent intent=getIntent();
        int idservice=intent.getIntExtra("idservice",0);

        ApiManager apiManager=new ApiManager();

        apiManager.getPrestataireParService(new ApiCallback<List<PrestataireResponseDTO>>() {
            @Override
            public void onSuccess(List<PrestataireResponseDTO> result) {
                for(PrestataireResponseDTO prestataire:result){

                    View vuePrestataire=getLayoutInflater().inflate(R.layout.profileprestataire,les_prestataire , false);

                    // Optionnel : modifier dynamiquement le contenu
                    TextView name = vuePrestataire.findViewById(R.id.prenom_nom);
                    TextView  description= vuePrestataire.findViewById(R.id.description);
                    ImageView imagePrestataire=vuePrestataire.findViewById(R.id.imagePrestataire);

                    name.setText(prestataire.getPrenom() + " " + prestataire.getNom());
                    description.setText("Description...");
                    imagePrestataire.setImageResource(PrestataireParService.this.getResources().getIdentifier(
                            "default_24px",
                            "drawable",
                            PrestataireParService.this.getPackageName()
                    ));

                    les_prestataire.addView(vuePrestataire);
                }
            }

            @Override
            public void onError(Throwable t) {

            }
        },idservice);

    }
}