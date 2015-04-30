package com.example.bruno.maromba.databaseQueries;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Bruno on 4/16/2015.
 */
public class SeriesQuery {

    /**
     * do a select in the database and return the series given a email
     * @param email
     * @param sqLiteDatabase database being queried
     * @return list of String representing the name of each series
     */

    public static List<String> getSeriesNames(String email, SQLiteDatabase sqLiteDatabase){
        ArrayList<String> stringArrayList = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT serie FROM series WHERE email = ?", new String[] {email});
        while(cursor.moveToNext()){
            stringArrayList.add(cursor.getString(0));
        }
        return stringArrayList;
    }

}
