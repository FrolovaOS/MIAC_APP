package com.example.example.retrofit;

import com.example.example.model.Recomendations;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface RecomendationsApi {

    @GET("api/recomendations/{id}")
    Single<Recomendations> getRecomendations(@Header("authorization") String auth, @Path("id") int id);

}
