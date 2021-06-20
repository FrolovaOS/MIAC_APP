package com.example.example;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.example.USER.UserLocal;
import com.example.example.model.User;
import com.example.example.model.UserRegistration;
import com.example.example.rubish.Account;
import com.example.example.service.UserApiServer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    Button okey, cancel;
    EditText snills, email, pass1, pass2, firstName, lastName;

    private CompositeDisposable compositeDisposable;

    UserApiServer userApiServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registr);

        okey = (Button) findViewById(R.id.okey);
        okey.setOnClickListener(this);
        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(this);


        snills = (EditText) findViewById(R.id.snills);
        email = (EditText) findViewById(R.id.email);
        pass1 = (EditText) findViewById(R.id.passwordR1);
        pass2 = (EditText) findViewById(R.id.passwordR2);
        firstName = (EditText) findViewById(R.id.firstName123);
        lastName = (EditText) findViewById(R.id.lastName123);

        userApiServer = new UserApiServer();
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.okey:
                String username = snills.getText().toString();
                String email1 = email.getText().toString();
                String password1 = pass1.getText().toString();
                String password2 = pass2.getText().toString();
                String first_name = firstName.getText().toString();
                String last_name = lastName.getText().toString();

                UserRegistration userRegistration = new UserRegistration(username,
                        email1,
                        password1,
                        password2,
                        first_name,
                        last_name);

                compositeDisposable.add(userApiServer.getRestApi().registration(userRegistration)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BiConsumer<User, Throwable>() {
                            @Override
                            public void accept(User user, Throwable throwable) throws Exception {
                                if (throwable != null) {
                                    System.out.println("error");
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Registration.this);
                                    builder.setTitle("Ошибка!")
                                            .setMessage("Неверные данные!")
                                            .setCancelable(false)
                                            .setNegativeButton("ОК",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            dialog.cancel();
                                                        }
                                                    });
                                    AlertDialog alert = builder.create();
                                    alert.show();
                                } else {
                                     UserLocal.setLocalUser(user);
                                    Intent intent = new Intent(Registration.this, Account.class);
                                    startActivity(intent);
                                }
                            }

                        }));

                //отправка на сервер и проверка, запись в бд -> вход в личный каб
                //Intent intent = new Intent(MainActivity.this, Account.class);
                //startActivity(intent);
                break;
            case R.id.cancel:
                Intent intent = new Intent(Registration.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
