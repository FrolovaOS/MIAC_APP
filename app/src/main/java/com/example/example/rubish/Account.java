package com.example.example.rubish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.example.AddMeasurement;
import com.example.example.NavigationLayout;
import com.example.example.R;
import com.example.example.model.MeasurementAdd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Account extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private ArrayList<Measurement> notes ;
    private String request;
    private String timestamp;
    NavigationLayout navigationLayout;
    RelativeLayout left_drawer;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        setupMenu();
    }

    public void setupMenu()
    {
        left_drawer=(RelativeLayout) findViewById(R.id.left_drawer);
        navigationLayout=new NavigationLayout(getApplicationContext(),left_drawer);

        left_drawer.addView(navigationLayout);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_search);

        //потом удалить
//        notes = new ArrayList<>();
//        MeasurementAdd n = new MeasurementAdd();
//        n.setHighPressure("125");
//        n.setLowPressure("60");
//        n.setPulse("145");
//        n.setTimestamp(timestamp = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm").format(new Date()));
//        notes.add(n);
//        MeasurementAdd n2 = new MeasurementAdd();
//        n2.setHighPressure("125");
//        n2.setLowPressure("60");
//        n2.setPulse("145");
//        n2.setTimestamp(timestamp = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm").format(new Date()));
//        notes.add(n2);
//        MeasurementAdd n3 = new MeasurementAdd();
//        n3.setHighPressure("125");
//        n3.setLowPressure("60");
//        n3.setPulse("145");
//        n3.setSaturation("32");
//        n3.setTimestamp(timestamp = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm").format(new Date()));
//        notes.add(n3);
//        MeasurementAdd n4 = new MeasurementAdd();
//        n4.setHighPressure("125");
//        n4.setLowPressure("60");
//        n4.setPulse("145");
//        n4.setSaturation("37");
//        n4.setTimestamp(timestamp = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm").format(new Date()));
//        notes.add(n4);
//        MeasurementAdd n5 = new MeasurementAdd();
//        n5.setHighPressure("115");
//        n5.setLowPressure("65");
//        n5.setPulse("150");
//        n5.setSaturation("31");
//        n5.setTimestamp(timestamp = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm").format(new Date()));
//        notes.add(n5);

        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //подкачка из бд в notes
        final AdapterNotesDB adapter = new AdapterNotesDB(this, notes);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    public void addNote(View view) {
        Intent intent = new Intent(Account.this, AddMeasurement.class);
        startActivity(intent);
    }
}