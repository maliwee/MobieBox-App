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

public class FavouriteMovie extends AppCompatActivity {
    DatabaseOption database;
    private Button btnSave;
    private ListView favouriteMovies;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = new DatabaseOption(this);
        btnSave = findViewById(R.id.btnUpdate);
        favouriteMovies = (ListView) findViewById(R.id.favouriteListView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(FavouriteMovie.this, MainActivity.class);
                startActivity(home);
            }
        });

        Cursor data = database.getFavouriteMovieInfo();
        ArrayList<String> favouriteMovieList = new ArrayList<>();
        if (data.getCount() == 0) {
            Toast.makeText(FavouriteMovie.this, "NO any Movie Added to Favourites", Toast.LENGTH_SHORT).show();
        } else {

            while (data.moveToNext()) {
                favouriteMovieList.add(data.getString(0));
                listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, favouriteMovieList);
                favouriteMovies.setAdapter(listAdapter);
            }
            for (int i = 0; i < listAdapter.getCount(); i++) {
                favouriteMovies.setItemChecked(i, true);
            }
        }
    }
    public void favourites()
    {


        for (int i = 0; i < favouriteMovies.getCount(); i++) {

            if (favouriteMovies.isItemChecked(i)) {
                String title = String.valueOf(favouriteMovies.getItemAtPosition(i));
                if (database.addToFavourite(title, "Favourite")) {
                    Toast.makeText(FavouriteMovie.this, "Added to favourites", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FavouriteMovie.this, "Not added ", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                String title = String.valueOf(favouriteMovies.getItemAtPosition(i));
                database.addToFavourite(title, "Not Favourite");
            }
        }
    }

    public void btnSaveOnClick(View view)
    {
        favourites();
    }


}