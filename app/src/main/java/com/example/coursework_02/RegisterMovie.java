package com.example.coursework_02;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterMovie extends AppCompatActivity {
    DatabaseOption dataBase = new DatabaseOption(this);

    private EditText txtTitle;
    private EditText txtYear;
    private EditText txtDirector;
    private EditText txtActors;
    private EditText txtRating;
    private EditText txtReview;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtTitle = findViewById(R.id.txtTitle);
        txtYear = findViewById(R.id.txtYear);
        txtDirector = findViewById(R.id.txtDirector);
        txtActors = findViewById(R.id.txtActors);
        txtRating = findViewById(R.id.txtRating);
        txtReview = findViewById(R.id.txtReview);
        btnSave = findViewById(R.id.btnUpdate);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(RegisterMovie.this, MainActivity.class);
                startActivity(home);
            }
        });
    }

    private boolean CheckInputs() {
        int rating = Integer.parseInt(txtRating.getText().toString());
        int year = Integer.parseInt(txtYear.getText().toString());


        if (rating > 11 || rating < 1) {
            return false;
        } else if (year < 1895) {
            Toast.makeText(RegisterMovie.this, "Year must greater than 1895.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    public void btnSaveOnclick(View view) {

        String Title=txtTitle.getText().toString();
        String Year=txtYear.getText().toString();
        String Director=txtDirector.getText().toString();
        String Actors=txtActors.getText().toString();
        String Rating=txtRating.getText().toString();
        String Review=txtReview.getText().toString();
        String Favourite="";

        if (CheckInputs())
        {
            Boolean checkData = dataBase.addMovie(Title,Year,Director,Actors,Rating,Review,Favourite);
            if(checkData==true)
                Toast.makeText(RegisterMovie.this,"New Move added",Toast.LENGTH_SHORT).show();

            else
                    Toast.makeText(RegisterMovie.this,"New Move not added",Toast.LENGTH_SHORT).show();

        }
        else
            {
                Toast.makeText(RegisterMovie.this,"no data",Toast.LENGTH_SHORT).show();

            }

        txtTitle.getText().clear();
        txtYear.getText().clear();
        txtDirector.getText().clear() ;
        txtActors.getText().clear();
        txtRating.getText().clear() ;
        txtReview .getText().clear();


    }
}