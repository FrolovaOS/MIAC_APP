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
import com.example.example.USER.UserLocal;
import com.example.example.model.Measurement;
import com.example.example.model.Recomendations;
import com.example.example.model.UserMeasurement;
import com.example.example.service.MeasurementServer;
import com.example.example.service.RecomendationsServer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;

public class Account extends AppCompatActivity {


    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private ArrayList<Measurement> notes;
    private String request;
    private String timestamp;
    private Recomendations recomendations;
    NavigationLayout navigationLayout;
    RelativeLayout left_drawer;
    MeasurementServer measurementServerrver = new MeasurementServer();
    RecomendationsServer recomendationsServer = new RecomendationsServer();
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    AdapterNotesDB adapter;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        //setupMenu();
    }

    public void setupMenu() {
        left_drawer = (RelativeLayout) findViewById(R.id.left_drawer);

        if (recomendations == null) System.out.println("пусто блять");
        navigationLayout = new NavigationLayout(this, left_drawer, recomendations);

        left_drawer.addView(navigationLayout);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_search);

        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        getList();
        getRecomendations();
        adapter = new AdapterNotesDB(this, notes);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }



    public void addNote(View view) {
        Intent intent = new Intent(Account.this, AddMeasurement.class);
        startActivity(intent);
    }

    public void setRecomendations(Recomendations recomendations){
        this.recomendations = recomendations;
    }

    private void getRecomendations() {
        compositeDisposable.add(recomendationsServer.getRestApi().getRecomendations(UserLocal.getKey(), UserLocal.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BiConsumer<Recomendations, Throwable>() {
                    @Override
                    public void accept(Recomendations recomendations, Throwable throwable) throws Exception {
                        if (throwable != null) {
                            System.out.println("ERROR");
                            throwable.printStackTrace();
                        } else {
                            setRecomendations(recomendations);
                        }
                        setupMenu();
                    }
                }));
    }

    public void getList() {
        compositeDisposable.add(measurementServerrver.getRestApi().getAllNote(UserLocal.getKey(), UserLocal.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BiConsumer<UserMeasurement, Throwable>() {
                    @Override
                    public void accept(UserMeasurement u, Throwable throwable) throws Exception {
                        if (throwable != null) {
                            System.out.println("ERROR");
                            throwable.printStackTrace();
                        } else {
                            notes = new ArrayList<>(u.getMeasurements());
                            adapter.setMeasurements(notes);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }));
    }

}
