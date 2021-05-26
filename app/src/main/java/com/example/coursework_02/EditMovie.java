package com.example.coursework_02;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditMovie extends AppCompatActivity {
    DatabaseOption database;
    private ListView movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = new DatabaseOption(this);
        movies = (ListView) findViewById(R.id.movieListEdit);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(EditMovie.this, MainActivity.class);
                startActivity(home);
            }
        });
        Cursor data = database.getMovieInfo();
        ArrayList<String> movieList = new ArrayList<>();
        if (data.getCount() == 0) {
            Toast.makeText(EditMovie.this, "NO any Movie Added", Toast.LENGTH_SHORT).show();
        } else {
            while (data.moveToNext()) {
                movieList.add(data.getString(0));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movieList);
                movies.setAdapter(listAdapter);
            }
        }

        movies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title =movieList.get(position);
                Intent intent1 = new Intent(EditMovie.this, UpDate.class);
                intent1.putExtra("SelectedMovie", title);
                startActivity(intent1);
            }
        });
    }


}