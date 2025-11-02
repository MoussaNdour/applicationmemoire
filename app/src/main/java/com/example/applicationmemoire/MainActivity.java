package com.example.applicationmemoire;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmemoire.apiservice.ApiCallback;
import com.example.applicationmemoire.dto.response.DemandeServiceResponseDTO;
import com.example.applicationmemoire.dto.response.PrestataireResponseDTO;
import com.example.applicationmemoire.dto.response.ServiceResponseDTO;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.search.SearchBar;
import com.google.android.material.search.SearchView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    LinearLayout les_demandes,les_prestataires;
    FlexboxLayout services_populaires;

    TextView afficherTousServices,afficherTousPrestataires;

    MaterialButton se_connecter;

    SearchView barre_recherche;

    SearchBar searchBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        les_demandes=findViewById(R.id.les_demandes);
        les_prestataires=findViewById(R.id.les_prestataires);
        services_populaires=findViewById(R.id.servicesPopulaires);
        se_connecter=findViewById(R.id.se_connecter);
        afficherTousPrestataires=findViewById(R.id.voirTousPrestataires);

        barre_recherche=findViewById(R.id.barre_recherche);
        searchBar=findViewById(R.id.search_bar);

        afficherTousServices=findViewById(R.id.afficherTousServices);

        ApiManager api = new ApiManager();

        searchBar.setOnClickListener(v -> barre_recherche.show());

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

                        service.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(MainActivity.this,PrestataireParService.class);
                                intent.putExtra("idservice",servicedto.getIdservice());
                                startActivity(intent);
                            }
                        });
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


        api.getDemandesService(new ApiCallback<List<DemandeServiceResponseDTO>>() {
            @Override
            public void onSuccess(List<DemandeServiceResponseDTO> result) {
                if(result.size()!=0){
                    for (DemandeServiceResponseDTO demande:result) {

                        View cardView = getLayoutInflater().inflate(R.layout.demande, les_demandes, false);
                        TextView prenom_nom=cardView.findViewById(R.id.prenom_nom);
                        prenom_nom.setText(demande.getPrenomClient()+" "+demande.getNomClient());

                        TextView metierClient= cardView.findViewById(R.id.metierClient);
                        metierClient.setText(demande.getMetierClient());

                        TextView adresseClient=cardView.findViewById(R.id.adresseClient);
                        adresseClient.setText(demande.getAdresseClient());

                        TextView detailsDemande=cardView.findViewById(R.id.detailsDemande);
                        detailsDemande.setText(demande.getDetailsdemande());


                        les_demandes.addView(cardView);
                    }
                }
                else{
                    TextView texte=new TextView(MainActivity.this);
                    texte.setText("Aucune demande n'est dispo pour le moment");

                    les_demandes.addView(texte);
                }
            }

            @Override
            public void onError(Throwable t) {
                TextView texte=new TextView(MainActivity.this);
                texte.setText("Les demandes seront visibles quand vous aurez une connexion");

                les_demandes.addView(texte);
            }
        });



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


//        barre_recherche.getEditText().addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String text=s.toString().trim();
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        barre_recherche.getEditText().setOnEditorActionListener((v, actionId, event) -> {
            String query = barre_recherche.getText().toString();

            api.rechercheService(new ApiCallback<List<ServiceResponseDTO>>() {
                @Override
                public void onSuccess(List<ServiceResponseDTO> result) {
                    Intent intent=new Intent(MainActivity.this,RechercheService.class);
                    intent.putExtra("services",(Serializable) result);
                    startActivity(intent);
                }

                @Override
                public void onError(Throwable t) {
                    Intent intent=new Intent(MainActivity.this,RechercheService.class);
                    intent.putExtra("services", (Serializable) new ArrayList<ServiceResponseDTO>());
                    startActivity(intent);
                }
            }, query);
            barre_recherche.hide();
            return true;
        });

        afficherTousPrestataires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Prestataires.class);

                startActivity(intent);
            }
        });

    }
}