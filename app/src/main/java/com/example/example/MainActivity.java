package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    Button enter, registration;
    EditText login, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enter = (Button) findViewById(R.id.enter);
        registration = (Button) findViewById(R.id.regis);

        login = (EditText) findViewById(R.id.fio);
        password = (EditText) findViewById(R.id.password);

    }

    //Интересное решение, но можно и разделить, усложняет читаемость, что думаешь?
    //Почему так решила ??))

    public void enter(View view){
        String log = login.getText().toString();
        System.out.println("login = " + log);
        String pass = password.getText().toString();
        System.out.println("password = " + pass);
        //

        //отправка на сервер и проверка -> вход в личный каб
        //Intent intent = new Intent(MainActivity.this, Account.class);
        //startActivity(intent);
    }

    public void registration(View view){
        Intent intent = new Intent(MainActivity.this, Registration.class);
        startActivity(intent);
    }

}