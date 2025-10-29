package com.example.applicationmemoire;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationmemoire.apiservice.ApiService;
import com.example.applicationmemoire.dto.LoginUtilisateurDTO;
import com.example.applicationmemoire.dto.UtilisateurDTO;


import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Callback;
import java.util.Map;
import com.google.gson.Gson;


public class Connexion extends AppCompatActivity {
    EditText champ_email,champ_mdp;
    TextView champ_erreur;
    Button bouton_connecter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_connexion);

        champ_email=findViewById(R.id.email_field);
        champ_mdp=findViewById(R.id.password_field);
        champ_erreur=findViewById(R.id.error_field);

        bouton_connecter=findViewById(R.id.connect_button);

        bouton_connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=champ_email.getText().toString();
                String mot_de_passe=champ_mdp.getText().toString();

                if(email.isBlank() || mot_de_passe.isBlank()){
                    champ_erreur.setText("Vous devez renseigner le mail et le mot de passe");
                }
                else {
                    // Crée l'interceptor
                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);


                    OkHttpClient client = new OkHttpClient.Builder()
                            .addInterceptor(logging)
                            .build();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.1.10:8080/")
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    ApiService apiService = retrofit.create(ApiService.class);

                    LoginUtilisateurDTO user=new LoginUtilisateurDTO();
                    user.setEmail(email.trim());
                    user.setMot_de_passe(mot_de_passe.trim());

                    apiService.connectUser(user).enqueue(new Callback<Map<String,Object>>() {
                        @Override
                        public void onResponse(Call<Map<String,Object>> call, Response<Map<String,Object>> response) {
                            if(response.isSuccessful()){
                                Map<String,Object> map=response.body();
                                String token=(String)map.get("Token");

                                // Conversion manuelle
                                Gson gson = new Gson();
                                String userJson = gson.toJson(map.get("utilisateur"));
                                UtilisateurDTO user = gson.fromJson(userJson, UtilisateurDTO.class);

                                if(user!=null){
                                    Intent intent=new Intent(Connexion.this,Profile.class);
                                    intent.putExtra("token",token);
                                    intent.putExtra("user",user);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(Connexion.this,"Probleme inconnu",Toast.LENGTH_LONG).show();
                                }
                            }
                            else{
                                champ_erreur.setText("Email ou mot de passe incorrecte");
                                Toast.makeText(Connexion.this,"Erreur: " + response.code(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Map<String,Object>> call, Throwable t) {
                            champ_erreur.setText("Vous devez etre connecter a internet pour pouvoir vous connecter");
                        }
                    });
                }
            }
        });
    }
}