package com.example.example.rest;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface Rest {

    @GET("")
    Single<String> getHello();


}
