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

    HashMap<String, ArrayList<String>> seriesName = new HashMap<String, ArrayList<String>>();

    /**
     * Mock class to simulate the search for series
     */
    public SeriesQuery() {
        seriesName.put("a@b", new ArrayList<String>(Arrays.asList("serie A", "serie B", "serie C")));
        seriesName.put("c@d", new ArrayList<String>(Arrays.asList("serie D", "serie E", "serie F")));
        seriesName.put("e@f", new ArrayList<String>());
        seriesName.put("a@c", new ArrayList<String>());
    }

    public static List<String> getSeriesNames(String email, SQLiteDatabase sqLiteDatabase){
        ArrayList<String> stringArrayList = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT serie FROM series WHERE email = ?", new String[] {email});
        while(cursor.moveToNext()){
            stringArrayList.add(cursor.getString(0));
        }
//        if(!seriesName.containsKey(email))
//            return new ArrayList<String>();
//        else
//            return seriesName.get(email);
        return stringArrayList;
    }

    public void deleteAccount(String email){
        seriesName.remove(email);
    }
}
