package com.example.example.service;

import com.example.example.R;
import com.example.example.retrofit.UserApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserApiServer {

    UserApi userApi;

    public UserApiServer() {
        Retrofit retrofit = createRetrofit();
        this.userApi = retrofit.create(UserApi.class);
    }

    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://84.252.131.194:1883/")
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

    public UserApi getRestApi() {
        return userApi;
    }

}
