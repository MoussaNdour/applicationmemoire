package com.example.applicationmemoire;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.applicationmemoire.apiservice.ApiCallback;
import com.example.applicationmemoire.dto.response.PrestataireResponseDTO;

import java.util.List;

public class Prestataires extends AppCompatActivity {

    LinearLayout prestataires;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_prestataires);

        prestataires=findViewById(R.id.prestataires);

        ApiManager apiManager=new ApiManager();

        apiManager.getPrestataires(new ApiCallback<List<PrestataireResponseDTO>>() {
            @Override
            public void onSuccess(List<PrestataireResponseDTO> result) {
                if(result.size()!=0){
                    for(PrestataireResponseDTO prestataire:result){
                        View vueSimplePresta=getLayoutInflater().inflate(R.layout.vuesimplepresta,prestataires,false);
                        TextView prenom_nom=vueSimplePresta.findViewById(R.id.prenom_nom);
                        prenom_nom.setText(prestataire.getPrenom() + " " + prestataire.getNom());
                        TextView metier=vueSimplePresta.findViewById(R.id.metier);
                        metier.setText(prestataire.getDescription());
                        TextView telephone=vueSimplePresta.findViewById(R.id.telephone);
                        telephone.setText(String.valueOf(prestataire.getTelephone()));
                        TextView adresse=vueSimplePresta.findViewById(R.id.adresse);
                        adresse.setText(prestataire.getAdresse());
                        TextView email=vueSimplePresta.findViewById(R.id.email);
                        email.setText(prestataire.getEmail());

                        prestataires.addView(vueSimplePresta);
                    }
                }
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }
}