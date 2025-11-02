package com.example.applicationmemoire.apiservice;

import com.example.applicationmemoire.dto.LoginUtilisateurDTO;
import com.example.applicationmemoire.dto.RegisterUtilisateurDTO;
import com.example.applicationmemoire.dto.UtilisateurDTO;
import com.example.applicationmemoire.dto.request.DemandeServiceRequestDTO;
import com.example.applicationmemoire.dto.response.DemandeServiceResponseDTO;
import com.example.applicationmemoire.dto.response.PrestataireResponseDTO;
import com.example.applicationmemoire.dto.response.ServiceResponseDTO;

import java.util.Map;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("api/v1/public/register/client")
    Call<UtilisateurDTO> inscrireClient(@Body RegisterUtilisateurDTO user);

    @POST("api/v1/public/register/prestataire")
    Call<UtilisateurDTO> inscrirePrestataire(@Body RegisterUtilisateurDTO user);

    @POST("api/v1/public/login")
    Call<Map<String,Object>> connectUser(@Body LoginUtilisateurDTO user);

    @GET("api/v1/main/services")
    Call<List<ServiceResponseDTO>> getServices();

    @GET("api/v1/main/prestataires")
    Call<List<PrestataireResponseDTO>> getPrestataires();

    @GET("api/v1/main/prestataireByService/{idservice}")
    Call<List<PrestataireResponseDTO>> getPrestataireByService(@Path("idservice") int idservice);


    @GET("api/v1/demandeservice/demandesEntAttente")
    Call<List<DemandeServiceResponseDTO>> getDemandesService();

    @GET("api/v1/main/rechercheService/{nom}")
    Call<List<ServiceResponseDTO>> rechercheServiceParNom(@Path("nom") String nom);

    @GET("api/v1/service/{idservice}")
    Call<ServiceResponseDTO> getService(@Path("idservice") int idservice);

    @POST("/api/v1/demandeservice")
    Call<DemandeServiceResponseDTO> createDemande(@Body DemandeServiceRequestDTO demande);
}
