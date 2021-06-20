package com.example.example;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.example.USER.UserLocal;
import com.example.example.model.User;
import com.example.example.model.UserLogIn;
import com.example.example.retrofit.UserApi;
import com.example.example.rubish.Account;
import com.example.example.service.UserApiServer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button enter, registration;
    EditText login, password;
    Retrofit retrofit;
    private UserApi rest;
    private CompositeDisposable compositeDisposable;

    //pleas work

    UserApiServer userApiServer;
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

    private void configureRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.11.0.108:1883/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        rest = retrofit.create(UserApi.class);
    }


    public void enter(View view) {
        String log = login.getText().toString();
        String pass = password.getText().toString();
        UserLogIn userLogIn = new UserLogIn(log, pass);
        //UserLogIn userLogIn = new UserLogIn("999-999-999-99", "14122000");
        compositeDisposable.add(userApiServer.getRestApi().authorization(userLogIn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BiConsumer<User, Throwable>() {
                    @Override
                    public void accept(User u, Throwable throwable) throws Exception {
                        if (throwable != null) {
                            System.out.println("error");
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Ошибка!")
                                    .setMessage("Неверный логин или пароль!")
                                    .setCancelable(false)
                                    .setNegativeButton("ОК",
                                            (dialog, id) -> dialog.cancel());
                            AlertDialog alert = builder.create();
                            alert.show();
                        } else {
                            UserLocal.setLocalUser(u);
                            Intent intent = new Intent(MainActivity.this, Account.class);
                            startActivity(intent);
                        }
                    }
                }));
    }
    public void registration(View view) {
        Intent intent = new Intent(MainActivity.this, Registration.class);
        startActivity(intent);
    }

}