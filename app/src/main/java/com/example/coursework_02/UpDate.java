package com.example.coursework_02;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpDate extends AppCompatActivity {
    DatabaseOption dataBase = new DatabaseOption(this);

    private String title;
    private String year;
    private  String director;
    private String rating;
    private String review;
    private String actor;
    private String status;


    private EditText txtTitle;
    private EditText txtYear;
    private EditText txtDirector;
    private EditText txtActors;
    private EditText txtRating;
    private EditText txtReview;
    private EditText txtStatus;
    private Button update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_date);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        txtTitle = findViewById(R.id.txtUpDateTitle);
        txtYear = findViewById(R.id.txtUpDateYear);
        txtDirector = findViewById(R.id.txtUpDateDirector);
        txtActors = findViewById(R.id.txtUpDateActors);
        txtRating = findViewById(R.id.txtUpDateRating);
        txtReview = findViewById(R.id.txtUpDateReview);
        txtStatus = findViewById(R.id.txtStatus);
        update = findViewById(R.id.btnUpdate);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(UpDate.this, EditMovie.class);
                startActivity(home);
            }

        });

        getData();
    }

    public void getData() {
        String moveTitle = getIntent().getStringExtra("SelectedMovie");

        Cursor cursor = dataBase.getSelectedMovie(moveTitle);

        if (cursor.getCount() == 0) {
            Toast.makeText(UpDate.this, "NO any Movie Added", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                title = cursor.getString(0);
                year = cursor.getString(1);
                director = cursor.getString(2);
                actor = cursor.getString(3);
                rating = cursor.getString(4);
                review = cursor.getString(5);
                status=cursor.getString(6);

            }
        }

        txtTitle.setText(title);
        txtYear.setText(year);
        txtDirector.setText(director);
        txtActors.setText(actor);
        txtRating.setText(rating);
        txtReview.setText(review);
        txtStatus.setText(status);

    }
    private boolean CheckInputs() {
        int rating = Integer.parseInt(txtRating.getText().toString());
        int year = Integer.parseInt(txtYear.getText().toString());


        if (rating > 11 || rating < 1) {
            return false;
        } else if (year < 1895) {
            return false;
        } else {
            return true;
        }
    }

    public void btnUpdateOnclick(View view)
    {
        String Title=txtTitle.getText().toString();
        String Year=txtYear.getText().toString();
        String Director=txtDirector.getText().toString();
        String Actors=txtActors.getText().toString();
        String Rating=txtRating.getText().toString();
        String Review=txtReview.getText().toString();
        String Favourite=txtStatus.getText().toString();

        if (CheckInputs())
        {
            Boolean checkData = dataBase.updateMove(Title,Year,Director,Actors,Rating,Review,Favourite);
            if(checkData==true)
                Toast.makeText(UpDate.this,"UpDated..",Toast.LENGTH_SHORT).show();

            else
                Toast.makeText(UpDate.this," Move not Updated",Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(UpDate.this,"Enter Valid Data",Toast.LENGTH_SHORT).show();

        }

    }
}