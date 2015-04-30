package com.example.bruno.maromba.databaseQueries;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Bruno on 4/16/2015.
 */
public class ExercisesInformationQuery {

    HashMap<String, List<String>> exercisesInformationTable = new HashMap<String, List<String>>();


    /**
     * Mock class to simulate the search for Information of each exercise
     */

    public ExercisesInformationQuery() {
        exercisesInformationTable.put("leg press", Arrays.asList("3", "10", "80"));
        exercisesInformationTable.put("squat", Arrays.asList("3", "11", "79"));
        exercisesInformationTable.put("wall sit", Arrays.asList("3", "12", "78"));
        exercisesInformationTable.put("chest fly", Arrays.asList("3", "13", "77"));
        exercisesInformationTable.put("push-up", Arrays.asList("3", "14", "76"));
        exercisesInformationTable.put("pull-up", Arrays.asList("3", "15", "75"));
        exercisesInformationTable.put("shoulder press", Arrays.asList("3", "16", "74"));
        exercisesInformationTable.put("shoulder fly", Arrays.asList("3", "17", "73"));
        exercisesInformationTable.put("biceps curl", Arrays.asList("3", "18", "72"));
    }

    public List<String> getInformation(String email, String serie, String exercise, SQLiteDatabase sqLiteDatabase){
        ArrayList<String> arrayList = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT numTimes, numRepetition, weight FROM exercises WHERE email = ? AND serie = ? AND exercise = ?", new String[] {email, serie, exercise});
        while(cursor.moveToNext()){
            arrayList.add(cursor.getString(0));
            arrayList.add(cursor.getString(1));
            arrayList.add(cursor.getString(2));
        }
//        if(!exercisesInformationTable.containsKey(exercise)){
//            return null;
//        }
//        else{
//            return exercisesInformationTable.get(exercise);
//        }
        return arrayList;
    }
}
