package com.example.example.retrofit;

import com.example.example.model.User;
import com.example.example.model.UserLogIn;
import com.example.example.model.UserRegistration;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    @POST("/api/auth/login/")
    Single<User> authorization(@Body UserLogIn userLogIn);

    @POST("/api/auth/registration/")
    Single<User> registration(@Body UserRegistration userRegistration);
}
