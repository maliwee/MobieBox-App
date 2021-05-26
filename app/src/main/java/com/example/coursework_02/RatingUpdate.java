package com.example.coursework_02;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class RatingUpdate extends AppCompatActivity {
    String key = "k_wgjm7o59";
    String searchMovie;
    String url;
    String urlForRating;
    TextView txt;
//    TextView ratingTxt;
    String id;
    String moveTitle;
    TextView txtTitle;
    private static final String TAG = "MyActivity";


    public class DownloadJSON extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            URL url;
            HttpURLConnection httpURLConnection;
            InputStream inputStream;
            InputStreamReader inputStreamReader;
            String result = "";

            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                inputStream = httpURLConnection.getInputStream();


                inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();

                while (data != -1) {
                    result += (char) data;

                    data = inputStreamReader.read();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_update);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txt = findViewById(R.id.txtSelTitle);
        txtTitle = findViewById(R.id.txtTite);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(RatingUpdate.this, Rating.class);
                startActivity(home);
            }
        });
        moveTitle = getIntent().getStringExtra("SelectedMovie");
        txt.setText(moveTitle);

        getMovieData();
        getImdbRating();
    }

    public void getMovieData() {
        Log.d(TAG, "---------------------inside------------------------" + moveTitle);

        searchMovie = moveTitle.trim();
        url = "https://imdb-api.com/en/API/SearchTitle/" + key + "/" + searchMovie;
        Log.d(TAG, "----------------------movie id-----------------------" + url);


        DownloadJSON downloadJSON = new DownloadJSON();

        try {
            String result = downloadJSON.execute(url).get();

            JSONObject jsonObject = new JSONObject(result);
            JSONArray movies = jsonObject.getJSONArray("results");

            id = movies.getJSONObject(0).getString("id");


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    public void getImdbRating() {

        DownloadJSON downloadJSON = new DownloadJSON();
        try {
            urlForRating = "https://imdb-api.com/en/API/Ratings/k_kzd4x28s/" + id;

            String res = downloadJSON.execute(urlForRating).get();
            JSONObject jsonObject2 = new JSONObject(res);
            String rating = jsonObject2.getString("imDb");
            txtTitle.setText(rating);
            Log.d(TAG, "----------------------movie id-----------------------" + rating);

            Log.d(TAG, "----------------------movie id-----------------------" + id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }


    }
}