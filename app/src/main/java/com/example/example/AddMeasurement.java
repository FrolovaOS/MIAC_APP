package com.example.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.example.USER.UserLocal;
import com.example.example.model.MeasurementAdd;
import com.example.example.rubish.Account;
import com.example.example.service.MeasurementServer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.internal.observers.CallbackCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class AddMeasurement extends AppCompatActivity {

    private CompositeDisposable compositeDisposable;

    private String timestamp;


    private Spinner spinner;

    public EditText lowPres, highPres, pulse1, satur;
    private String item;
    private String[] countries = {"Подъем на этаж", "Бег", "Прогулка", "Волнение"};

    MeasurementServer server = new MeasurementServer();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_meassurement);

        lowPres = (EditText) findViewById(R.id.lowPressure);
        highPres = (EditText) findViewById(R.id.highPressure);
        pulse1 = (EditText) findViewById(R.id.pulse);
        satur = (EditText) findViewById(R.id.saturation6);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                addMeasure();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    private void addMeasure() {
        int highPressure = Integer.parseInt(lowPres.getText().toString());
        int lowPressure = Integer.parseInt(highPres.getText().toString());
        int pulse = Integer.parseInt(pulse1.getText().toString());
        int seturation = Integer.parseInt(satur.getText().toString());;//TODO

        MeasurementAdd measurementAdd = new MeasurementAdd(lowPressure, highPressure, pulse, seturation, UserLocal.getId(), item);

        server.getRestApi().addNewNode(UserLocal.getKey(), measurementAdd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CallbackCompletableObserver(
                        throwable -> {
//                            TODO вывести сообщение об ошибке
                        },
                        () -> {
//                          TODO всё получилось
                        }
                ));

        Intent intent = new Intent(AddMeasurement.this, Account.class);
        startActivity(intent);
        finish();
    }

}
