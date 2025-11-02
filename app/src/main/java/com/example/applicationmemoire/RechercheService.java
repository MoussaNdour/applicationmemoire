package com.example.applicationmemoire;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

import com.example.applicationmemoire.dto.response.ServiceResponseDTO;
import com.google.android.flexbox.FlexboxLayout;


public class RechercheService extends AppCompatActivity {

    FlexboxLayout vueServices;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_service);

        vueServices=findViewById(R.id.services_trouvees);

        Intent intent=getIntent();
        List<ServiceResponseDTO> services = (List<ServiceResponseDTO>) intent.getSerializableExtra("services");
        if(services.size()!=0){
            for(ServiceResponseDTO service:services){
                View vueService=getLayoutInflater().inflate(R.layout.service,vueServices,false);
                ImageView imageService=vueService.findViewById(R.id.serviceImage);
                TextView nomService=vueService.findViewById(R.id.serviceName);

                @SuppressLint("DiscouragedApi") int resId = RechercheService.this.getResources().getIdentifier(
                        service.getIcone()+"_24px",
                        "drawable",
                        RechercheService.this.getPackageName()
                );

                imageService.setImageResource(resId);
                nomService.setText(service.getNom());

                vueService.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(RechercheService.this,PrestataireParService.class);
                        intent.putExtra("idservice",service.getIdservice());
                        startActivity(intent);
                    }
                });

                vueServices.addView(vueService);
            }
        }
        else{
            TextView message=new TextView(RechercheService.this);
            message.setText("Aucun service trouve pour cette recherche");
            vueServices.addView(message);
        }

    }
}