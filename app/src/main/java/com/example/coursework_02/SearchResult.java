package com.example.coursework_02;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchResult extends AppCompatActivity {
    DatabaseOption dataBase = new DatabaseOption(this);
    private ListView resultView;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        resultView = findViewById(R.id.resultView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(SearchResult.this, Search.class);
                startActivity(home);
            }
        });
        String moveTitle = getIntent().getStringExtra("Search");
        Cursor data = dataBase.searchedMovie(moveTitle);

        ArrayList<String> movieList = new ArrayList<>();
        if (data.getCount() == 0) {
            Toast.makeText(SearchResult.this, "NO any Movie Added", Toast.LENGTH_SHORT).show();
        } else {
            while (data.moveToNext()) {
                movieList.add(data.getString(0));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movieList);
                resultView.setAdapter(listAdapter);
            }
        }

    }
}
