package com.example.applicationmemoire;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmemoire.apiservice.ApiCallback;
import com.example.applicationmemoire.dto.response.PrestataireResponseDTO;
import com.example.applicationmemoire.dto.response.ServiceResponseDTO;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.button.MaterialButton;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    LinearLayout les_demandes,les_prestataires;
    FlexboxLayout services_populaires;

    TextView afficherTousServices;

    MaterialButton se_connecter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        les_demandes=findViewById(R.id.les_demandes);
        les_prestataires=findViewById(R.id.les_prestataires);
        services_populaires=findViewById(R.id.servicesPopulaires);
        se_connecter=findViewById(R.id.se_connecter);

        afficherTousServices=findViewById(R.id.afficherTousServices);

        ApiManager api = new ApiManager();


        api.getServices(new ApiCallback<List<ServiceResponseDTO>>() {
            @Override
            public void onSuccess(List<ServiceResponseDTO> result) {

                if(result.size()!=0){
                    for(ServiceResponseDTO servicedto:result){
                        View service=getLayoutInflater().inflate(R.layout.service,services_populaires,false);
                        ImageView imageService=service.findViewById(R.id.serviceImage);
                        TextView serviceName=service.findViewById(R.id.serviceName);
                        serviceName.setText(servicedto.getNom());
                        @SuppressLint("DiscouragedApi") int resId = MainActivity.this.getResources().getIdentifier(
                                servicedto.getIcone()+"_24px",
                                "drawable",
                                MainActivity.this.getPackageName()
                        );
                        imageService.setImageResource(resId);
                        services_populaires.addView(service);
                    }
                }
            }

            @Override
            public void onError(Throwable t) {
                Log.e("API", "Erreur : " + t.getMessage());
                TextView message=new TextView(MainActivity.this);
                message.setText("Aucun service n'a ete trouve");
                services_populaires.addView(message);
            }
        });

        api.getPrestataires(new ApiCallback<List<PrestataireResponseDTO>>() {
            @Override
            public void onSuccess(List<PrestataireResponseDTO> result) {
                for(PrestataireResponseDTO prestataire:result){

                    View vuePrestataire=getLayoutInflater().inflate(R.layout.profileprestataire, les_prestataires, false);

                    // Optionnel : modifier dynamiquement le contenu
                    TextView name = vuePrestataire.findViewById(R.id.prenom_nom);
                    TextView  description= vuePrestataire.findViewById(R.id.description);
                    ImageView imagePrestataire=vuePrestataire.findViewById(R.id.imagePrestataire);

                    name.setText(prestataire.getPrenom() + " " + prestataire.getNom());
                    description.setText("Description...");
                    imagePrestataire.setImageResource(MainActivity.this.getResources().getIdentifier(
                            "default_24px",
                            "drawable",
                            MainActivity.this.getPackageName()
                    ));

                    les_prestataires.addView(vuePrestataire);
                }
            }

            @Override
            public void onError(Throwable t) {
                TextView message=new TextView(MainActivity.this);
                message.setText("Aucun prestataire n'a ete trouver");
            }
        });



        for (int i = 0; i < 10; i++) {  // Exemple : 10 cartes
            // Inflater la vue
            View cardView = getLayoutInflater().inflate(R.layout.demande, les_demandes, false);


//            avatar.setImageResource(R.drawable.avatar_exemple); // ou charger avec Glide/Picasso

            // Ajouter la vue au container horizontal
            les_demandes.addView(cardView);
        }

        afficherTousServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Services.class);

                startActivity(intent);
            }
        });

        se_connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Connexion.class);
                startActivity(intent);
            }
        });

    }
}