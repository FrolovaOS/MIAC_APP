package com.example.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Account extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private ArrayList<Note> notes ;
    private String request;
    private String timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_search);

        //потом удалить
        notes = new ArrayList<>();
        Note n = new Note();
        n.setHighPressure("125");
        n.setLowPressure("60");
        n.setPulse("145");
        n.setTimestamp("1599632547");
        notes.add(n);

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
        Intent intent = new Intent(Account.this, addNotes.class);
        startActivity(intent);
    }
}
