package com.example.example.retrofit;

import com.example.example.model.User;
import com.example.example.model.UserLogIn;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    @POST("/api/auth/login/")
    Single<User> authorization(@Body UserLogIn userLogIn);


}
