package com.example.applicationmemoire;

import com.example.applicationmemoire.apiservice.ApiCallback;
import com.example.applicationmemoire.apiservice.ApiService;
import com.example.applicationmemoire.dto.request.DemandeServiceRequestDTO;
import com.example.applicationmemoire.dto.response.DemandeServiceResponseDTO;
import com.example.applicationmemoire.dto.response.PrestataireResponseDTO;
import com.example.applicationmemoire.dto.response.ServiceResponseDTO;
import com.google.gson.Gson;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    public OkHttpClient client;

    public Retrofit retrofit;

    public ApiService apiService;

    public Gson gson;


    public List<ServiceResponseDTO> services;

    public List<PrestataireResponseDTO> prestataires;
    public ApiManager(){

        this.gson=new Gson();

        this.client = new OkHttpClient.Builder().build();

        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.6:8080/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.apiService = retrofit.create(ApiService.class);
    }

    public void getServices(ApiCallback<List<ServiceResponseDTO>> callback) {
        apiService.getServices().enqueue(new Callback<List<ServiceResponseDTO>>() {
            @Override
            public void onResponse(Call<List<ServiceResponseDTO>> call, Response<List<ServiceResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError(new Exception("Erreur réponse API"));
                }
            }

            @Override
            public void onFailure(Call<List<ServiceResponseDTO>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }


    public void getPrestataireParService(ApiCallback<List<PrestataireResponseDTO>> callback,int idservice){
        apiService.getPrestataireByService(idservice).enqueue(new Callback<List<PrestataireResponseDTO>>() {
            @Override
            public void onResponse(Call<List<PrestataireResponseDTO>> call, Response<List<PrestataireResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError(new Exception("Erreur réponse API"));
                }
            }

            @Override
            public void onFailure(Call<List<PrestataireResponseDTO>> call, Throwable t) {
                callback.onError(t);
            }
        });


    }

    public void getPrestataires(ApiCallback<List<PrestataireResponseDTO>> callback) {
        apiService.getPrestataires().enqueue(new Callback<List<PrestataireResponseDTO>>() {
            @Override
            public void onResponse(Call<List<PrestataireResponseDTO>> call, Response<List<PrestataireResponseDTO>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError(new Exception("Erreur réponse API"));
                }
            }

            @Override
            public void onFailure(Call<List<PrestataireResponseDTO>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public void getDemandesService(ApiCallback<List<DemandeServiceResponseDTO>> callback){
        apiService.getDemandesService().enqueue(new Callback<List<DemandeServiceResponseDTO>>() {
            @Override
            public void onResponse(Call<List<DemandeServiceResponseDTO>> call, Response<List<DemandeServiceResponseDTO>> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                }
                else{
                    callback.onError(new Exception("Erreur reponse api"));
                }
            }

            @Override
            public void onFailure(Call<List<DemandeServiceResponseDTO>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public void rechercheService(ApiCallback<List<ServiceResponseDTO>> callback, String nom){
        apiService.rechercheServiceParNom(nom).enqueue(new Callback<List<ServiceResponseDTO>>() {
            @Override
            public void onResponse(Call<List<ServiceResponseDTO>> call, Response<List<ServiceResponseDTO>> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                }
                else{
                    callback.onError(new Exception("Erreur reponse api"));
                }
            }

            @Override
            public void onFailure(Call<List<ServiceResponseDTO>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public void getServiceById(ApiCallback<ServiceResponseDTO> callback, int idservice){
        apiService.getService(idservice).enqueue(new Callback<ServiceResponseDTO>() {
            @Override
            public void onResponse(Call<ServiceResponseDTO> call, Response<ServiceResponseDTO> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                }
                else{
                    callback.onError(new Exception("Erreur requete api"));
                }
            }

            @Override
            public void onFailure(Call<ServiceResponseDTO> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public void faireDemandeService(DemandeServiceRequestDTO demande, ApiCallback callback){
        apiService.createDemande(demande).enqueue(new Callback<DemandeServiceResponseDTO>() {
            @Override
            public void onResponse(Call<DemandeServiceResponseDTO> call, Response<DemandeServiceResponseDTO> response) {
                if(response.isSuccessful())
                    callback.onSuccess(null);
                else
                    callback.onError(new Exception("Erreur appel api"));
            }

            @Override
            public void onFailure(Call<DemandeServiceResponseDTO> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

}
