package com.example.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    Button okey, cancel;
    EditText phone, email, pass1, pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registr);

        okey = (Button) findViewById(R.id.okey);
        okey.setOnClickListener(this);
        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(this);


        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        pass1 = (EditText) findViewById(R.id.passwordR1);
        pass2 = (EditText) findViewById(R.id.passwordR2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.okey:
                String phone1 = phone.getText().toString();
                System.out.println("name = " + phone1);
                String e = email.getText().toString();
                System.out.println("lastName = " + e);
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
