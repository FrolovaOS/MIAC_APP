package com.example.example.rest;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface Rest {

    @GET("https://localhost/swagger/")
    Single<String> getHello();


}
