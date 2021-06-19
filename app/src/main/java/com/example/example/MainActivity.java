package com.example.example;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.example.rest.Rest;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


import com.example.example.model.User;
import com.example.example.model.UserLogIn;
import com.example.example.model.UserRegistration;
import com.example.example.service.UserApiServer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    Button enter, registration;
    EditText login, password;
    Retrofit retrofit;
    private Rest rest;
    private CompositeDisposable compositeDisposable;

    //pleas work

    UserApiServer userApiServer;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enter = (Button) findViewById(R.id.enter);
        registration = (Button) findViewById(R.id.regis);

        login = (EditText) findViewById(R.id.fio);
        password = (EditText) findViewById(R.id.password);

        configureRetrofit();
        userApiServer = new UserApiServer();
        compositeDisposable = new CompositeDisposable();

    }


    public void setUser(User u) {
        user = u;
    }

    public void enter(View view) {
        String log = login.getText().toString();
        String pass = password.getText().toString();
        UserLogIn userLogIn = new UserLogIn(log, pass);
        compositeDisposable.add(userApiServer.getRestApi().authorization(userLogIn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new BiConsumer<User, Throwable>() {
                    @Override
                    public void accept(User user, Throwable throwable) throws Exception {
                        if (throwable != null) {
                            System.out.println("erro");
                            //TODO ошибка должна выдаваться пользователю
                        } else {
                            setUser(user);
                        }
                    }
                }));
    }
    public void registration(View view) {
        Intent intent = new Intent(MainActivity.this, Registration.class);
        startActivity(intent);
    }

}