package com.example.example;

import android.annotation.SuppressLint;
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
import com.example.example.model.Measurement;
import com.example.example.rubish.Account;
import com.example.example.service.MeasurementServer;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.observers.CallbackCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class addNotes extends AppCompatActivity {

    private String timestamp,lowPressure, highPressure, pulse;
    private Spinner spinner;

    EditText lowPres, highPres, pulse1;
    private String item;
    String[] countries = { "Подъем на этаж", "Бег", "Прогулка", "Волнение"};
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

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
    }


    @SuppressLint("SimpleDateFormat")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save: {


                timestamp = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm").format(new Date());
                lowPressure = lowPres.getText().toString();
                highPressure = highPres.getText().toString();
                pulse = pulse1.getText().toString();
                Measurement measurement = new Measurement(timestamp, lowPressure, highPressure, pulse);
                //отправить данные в бд ( не забыть item)

                server.getRestApi().addNewNode(UserLocal.getLocalUser().getAccess_token(), measurement)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new CallbackCompletableObserver(
                                throwable -> {
                                    System.out.println("Err");
//                            TODO вывести сообщение об ошибке
                                },
                                () -> {
                                    System.out.println("Sucsses");
//                          TODO всё получилось
                                }
                        ));


                System.out.println("timestamp = " + timestamp + ", lowsPress = " + lowPressure + ", highPress = " + highPressure + ", pulse = "+pulse);
                Intent intent = new Intent(addNotes.this, Account.class);
                startActivity(intent);
                finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);

}

}
