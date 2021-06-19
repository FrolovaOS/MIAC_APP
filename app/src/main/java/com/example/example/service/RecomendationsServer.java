package com.example.example.service;

import com.example.example.retrofit.MeasurementApi;
import com.example.example.retrofit.RecomendationsApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecomendationsServer {

    RecomendationsApi recomendationsApi;

    public RecomendationsServer() {
        Retrofit retrofit = createRetrofit();
        this.recomendationsApi = retrofit.create(RecomendationsApi.class);
    }

    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://10.11.0.108:1883/")
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private OkHttpClient createOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    public RecomendationsApi getRestApi() {
        return recomendationsApi;
    }

}
