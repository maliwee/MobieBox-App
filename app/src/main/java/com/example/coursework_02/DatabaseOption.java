package com.example.coursework_02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOption extends SQLiteOpenHelper {
    public DatabaseOption(Context context) {
        super(context, "RegisteredMoves.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MoveDB) {
        MoveDB.execSQL("create Table RegisteredMovedetails(Title TEXT ,Year TEXT,Director TEXT, Actors TEXT, Rating TEXT,Review TEXT,Favourite TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MoveDB, int oldVersion, int newVersion) {
        MoveDB.execSQL("drop table if exists RegisteredMovedetails");
    }

    //Method to add data to the data base
    public Boolean addMovie(String Title, String Year, String Director, String Actors, String Rating, String Review, String Favourite) {
        SQLiteDatabase MoveDB = this.getWritableDatabase();
        ContentValues MoveInfo = new ContentValues();
        MoveInfo.put("Title", Title);
        MoveInfo.put("Year", Year);
        MoveInfo.put("Director", Director);
        MoveInfo.put("Actors", Actors);
        MoveInfo.put("Rating", Rating);
        MoveInfo.put("Review", Review);
        MoveInfo.put("Favourite", Favourite);

        long data = MoveDB.insert("RegisteredMovedetails", null, MoveInfo);
        if (data == -1) {
            return false;
        } else {
            return true;
        }

    }

    //Method to update the  data base
    public Boolean updateMove(String Title, String Year, String Director, String Actors, String Rating, String Review, String Favourite) {
        SQLiteDatabase MoveDB = this.getWritableDatabase();
        ContentValues MoveInfo = new ContentValues();
        MoveInfo.put("Title", Title);
        MoveInfo.put("Year", Year);
        MoveInfo.put("Director", Director);
        MoveInfo.put("Actors", Actors);
        MoveInfo.put("Rating", Rating);
        MoveInfo.put("Review", Review);
        MoveInfo.put("Favourite", Favourite);

        Cursor cursor = MoveDB.rawQuery("Select * from RegisteredMovedetails where Title=?", new String[]{Title});
        if (cursor.getCount() > 0) {
            long data = MoveDB.update("RegisteredMovedetails", MoveInfo, "Title=?", new String[]{Title});
            if (data == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    //Method to get every move n the data base
    public Cursor getMovieInfo() {
        SQLiteDatabase MoveDB = this.getWritableDatabase();
        Cursor cursor = MoveDB.rawQuery("Select * from RegisteredMovedetails ORDER BY Title COLLATE NOCASE ASC  ", null);
        return cursor;
    }

    //Method to get favourite movies from the data base
    public Cursor getFavouriteMovieInfo() {

        SQLiteDatabase MoveDB = this.getWritableDatabase();

        Cursor cursor = MoveDB.rawQuery("Select * from RegisteredMovedetails where Favourite='Favourite'", null);
        return cursor;
    }

    //Method to aget selected movie for the data base
    public Cursor getSelectedMovie(String Title) {

        SQLiteDatabase MoveDB = this.getReadableDatabase();
        ContentValues MoveInfo = new ContentValues();

        MoveInfo.put("Title", Title);
        Cursor cursor = MoveDB.rawQuery("Select * from RegisteredMovedetails where Title =? ", new String[]{Title});
        return cursor;
    }

    //Method to update fav column
    public Boolean addToFavourite(String Title, String Favourite) {
        SQLiteDatabase MoveDB = this.getWritableDatabase();
        ContentValues MoveInfo = new ContentValues();

        MoveInfo.put("Title", Title);
        MoveInfo.put("Favourite", Favourite);

        Cursor cursor = MoveDB.rawQuery("Select * from RegisteredMovedetails where Title=? ", new String[]{Title});
        if (cursor.getCount() > 0) {
            long data = MoveDB.update("RegisteredMovedetails", MoveInfo, "Title=?", new String[]{Title});
            if (data == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    //Method to Search from data base
    public Cursor searchedMovie(String text) {

        SQLiteDatabase MoveDB = this.getWritableDatabase();
        Cursor cursor = MoveDB.rawQuery("Select * from RegisteredMovedetails where Title LIKE? OR Director LIKE? OR Actors Like?  ", new String[]{text});
        return cursor;
    }


}
