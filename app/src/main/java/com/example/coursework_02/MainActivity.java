package com.example.coursework_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseOption database = new DatabaseOption(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnRegisterOnClick(View view) {
        Intent intent1 = new Intent(MainActivity.this, RegisterMovie.class);
        startActivity(intent1);

    }

    public void btnSearchOnClick(View view) {
        Cursor data = database.getMovieInfo();
        if (data.getCount() == 0) {
            Toast.makeText(MainActivity.this, "NO Movies to Search", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent1 = new Intent(MainActivity.this, Search.class);
            startActivity(intent1);
        }
    }

    public void btnEditOnClick(View view) {
        Cursor data = database.getMovieInfo();
        if (data.getCount() == 0) {
            Toast.makeText(MainActivity.this, "NO any Movie Added for Edit", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent1 = new Intent(MainActivity.this, EditMovie.class);
            startActivity(intent1);
        }
    }

    public void btnDisplayOnClick(View view) {
        Cursor data = database.getMovieInfo();
        if (data.getCount() == 0) {
            Toast.makeText(MainActivity.this, "NO any Movies to Display", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent1 = new Intent(MainActivity.this, DisplayMovie.class);
            startActivity(intent1);
        }
    }

    public void btnFavouritesOnClick(View view) {
        Cursor data = database.getFavouriteMovieInfo();
        if (data.getCount() == 0) {
            Toast.makeText(MainActivity.this, "NO any Movies Added for Edit", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent1 = new Intent(MainActivity.this, FavouriteMovie.class);
            startActivity(intent1);
        }
    }

    public void btnRatingOnClick(View view) {
        Cursor data = database.getMovieInfo();
        if (data.getCount() == 0) {
            Toast.makeText(MainActivity.this, "NO any Movies to Rate", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent1 = new Intent(MainActivity.this, Rating.class);
            startActivity(intent1);
        }
    }
}