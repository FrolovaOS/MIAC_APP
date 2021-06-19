package com.example.example.retrofit;

import com.example.example.model.MeasurementAdd;
import com.example.example.rubish.Measurement;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MeasurementApi {

    @GET("api/measurement/")
    Single<List<Measurement>> getAllNote(@Header("authorization") String auth);

    @POST("api/measurement/")
    Completable addNewNode(@Header("authorization") String auth, @Body MeasurementAdd note);

    @DELETE("api/measurement/{id}/")
    Completable deleteNode(@Header("authorization") String auth, @Path("id") int id);

}
