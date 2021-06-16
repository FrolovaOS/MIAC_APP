package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button enter, registration;
    EditText login, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enter = (Button) findViewById(R.id.enter);
        enter.setOnClickListener(this);
        registration = (Button) findViewById(R.id.regis);
        registration.setOnClickListener(this);


        login = (EditText) findViewById(R.id.fio);
        password = (EditText) findViewById(R.id.password);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enter:
                String log = login.getText().toString();
                System.out.println("login = " + log);
                String pass = password.getText().toString();
                System.out.println("password = " + pass);
                //отправка на сервер и проверка -> вход в личный каб
                //Intent intent = new Intent(MainActivity.this, Account.class);
                //startActivity(intent);
                break;
            case R.id.regis:
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
                break;
        }
    }
}