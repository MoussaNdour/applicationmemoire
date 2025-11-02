package com.example.applicationmemoire;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmemoire.apiservice.ApiCallback;
import com.example.applicationmemoire.dto.UtilisateurDTO;
import com.example.applicationmemoire.dto.response.PrestataireResponseDTO;
import com.example.applicationmemoire.dto.response.ServiceResponseDTO;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

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
                if(result.size()!=0){
                    for(PrestataireResponseDTO prestataire:result){

                        View vuePrestataire=getLayoutInflater().inflate(R.layout.faire_demande,les_prestataire , false);

                        // Optionnel : modifier dynamiquement le contenu
                        TextView name = vuePrestataire.findViewById(R.id.prenom_nom);
                        TextView  description= vuePrestataire.findViewById(R.id.description);
                        ImageView imagePrestataire=vuePrestataire.findViewById(R.id.imagePrestataire);
                        MaterialButton bouton_demander=vuePrestataire.findViewById(R.id.bouton_demander);

                        bouton_demander.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Gson gson=new Gson();
                                CacheManager cacheManager=new CacheManager(PrestataireParService.this);
                                LoginResponse data = gson.fromJson(cacheManager.readJson(), LoginResponse.class);
                                if(data==null){
                                    Intent intent=new Intent(PrestataireParService.this,Connexion.class);
                                    startActivity(intent);
                                }
                                else{
                                    apiManager.getServiceById(new ApiCallback<ServiceResponseDTO>() {
                                        @Override
                                        public void onSuccess(ServiceResponseDTO result) {
                                            String jwt=data.getToken();
                                            UtilisateurDTO user=data.getUtilisateur();
                                            Intent intent=new Intent(PrestataireParService.this,FaireDemandeService.class);



                                            intent.putExtra("token",jwt);
                                            intent.putExtra("user",user);
                                            intent.putExtra("service",result);
                                            intent.putExtra("prestataire",prestataire);

                                            startActivity(intent);
                                        }

                                        @Override
                                        public void onError(Throwable t) {

                                        }
                                    },idservice);

                                }
                            }
                        });

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
                else{
                    TextView message=new TextView(PrestataireParService.this);
                    message.setText("Aucun prestataire n'est dispo pour ce service");
                    les_prestataire.addView(message);
                }
            }

            @Override
            public void onError(Throwable t) {

            }
        },idservice);

    }
}