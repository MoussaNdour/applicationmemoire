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
import com.example.applicationmemoire.dto.response.ServiceResponseDTO;
import com.google.android.flexbox.FlexboxLayout;

import java.util.List;

public class Services extends AppCompatActivity {

    FlexboxLayout les_services;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        les_services=findViewById(R.id.les_services);

        ApiManager apiManager=new ApiManager();

        apiManager.getServices(new ApiCallback<List<ServiceResponseDTO>>() {
            @Override
            public void onSuccess(List<ServiceResponseDTO> result) {
                if(result.size()!=0){
                    for(ServiceResponseDTO service:result){
                        View vueService=getLayoutInflater().inflate(R.layout.service,les_services,false);
                        ImageView imageService=vueService.findViewById(R.id.serviceImage);
                        TextView nomService=vueService.findViewById(R.id.serviceName);

                        @SuppressLint("DiscouragedApi") int resId = Services.this.getResources().getIdentifier(
                                service.getIcone()+"_24px",
                                "drawable",
                                Services.this.getPackageName()
                        );

                        imageService.setImageResource(resId);
                        nomService.setText(service.getNom());

                        vueService.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(Services.this,PrestataireParService.class);
                                intent.putExtra("idservice",service.getIdservice());
                                startActivity(intent);
                            }
                        });

                        les_services.addView(vueService);
                    }
                }
                else{
                    TextView message=new TextView(Services.this);
                    message.setText("Il n'existe aucun service disponible pour le moment");
                    les_services.addView(message);
                }
            }

            @Override
            public void onError(Throwable t) {
                TextView message=new TextView(Services.this);
                message.setText("Il n'existe aucun service disponible pour le moment");
                les_services.addView(message);
            }
        });

    }
}