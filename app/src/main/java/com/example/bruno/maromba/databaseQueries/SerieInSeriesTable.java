package com.example.bruno.maromba.databaseQueries;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Bruno on 4/29/2015.
 */
public class SerieInSeriesTable {

    public static boolean query(String email, String serie, SQLiteDatabase sqLiteDatabase){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT serie FROM series WHERE email = ?", new String[]{email});
        while (cursor.moveToNext()){
            if(serie.equals(cursor.getString(0))){
                return true;
            }
        }
        return false;
    }
}
