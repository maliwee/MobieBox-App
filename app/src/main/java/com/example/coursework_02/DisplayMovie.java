package com.example.coursework_02;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayMovie extends AppCompatActivity {
    DatabaseOption database;
    private Button btnAddFav;
    private ListView movies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialising the Variables
        database = new DatabaseOption(this);
        btnAddFav = findViewById(R.id.btnAddfavourites);
        movies = (ListView) findViewById(R.id.movieList);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(DisplayMovie.this, MainActivity.class);
                startActivity(home);
            }
        });
        Cursor data = database.getMovieInfo();
        ArrayList<String> movieList = new ArrayList<>();
        if (data.getCount() == 0) {
            Toast.makeText(DisplayMovie.this, "NO any Movie Added", Toast.LENGTH_SHORT).show();
        } else {
            while (data.moveToNext()) {
                movieList.add(data.getString(0));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, movieList);
                movies.setAdapter(listAdapter);
            }
        }

    }

    public void favourites() {
        for (int i = 0; i < movies.getCount(); i++) {
            if (movies.isItemChecked(i)) {
                String title = String.valueOf(movies.getItemAtPosition(i));
                if (database.addToFavourite(title, "Favourite")) {
                    Toast.makeText(DisplayMovie.this, "Added to favourites", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DisplayMovie.this, "Not added ", Toast.LENGTH_SHORT).show();
                }
            }
            else
                {
                    String title = String.valueOf(movies.getItemAtPosition(i));
                   database.addToFavourite(title, "Not Favourite");
                }
        }
    }

//btn click
    public void btnFavouritesOnClick(View view)
    {
        favourites();
    }
}