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
public class ExerciseQuery {

    HashMap<String, List<String>> exercisesName = new HashMap<String, List<String>>();


    /**
     * Mock class to simulate the search for exercises
     */

    public ExerciseQuery() {
        exercisesName.put("serie A", new ArrayList<String>(Arrays.asList("leg press", "squat", "wall sit")));
        exercisesName.put("serie B", new ArrayList<String>(Arrays.asList("chest fly", "push-up", "pull-up")));
        exercisesName.put("serie C", new ArrayList<String>(Arrays.asList("shoulder press", "shoulder fly", "biceps curl")));
        exercisesName.put("serie D", new ArrayList<String>(Arrays.asList("shoulder press", "shoulder fly", "biceps curl")));
        exercisesName.put("serie E", new ArrayList<String>(Arrays.asList("leg press", "squat", "wall sit")));
        exercisesName.put("serie F", new ArrayList<String>(Arrays.asList("chest fly", "push-up", "pull-up")));
    }

    public List<String> getExercises(String email, String serie, SQLiteDatabase sqLiteDatabase){
        ArrayList<String> arrayList = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT exercise FROM exercises WHERE email = ? AND serie = ?", new String[] {email, serie});
        while(cursor.moveToNext()){
            arrayList.add(cursor.getString(0));
        }
//        if(!exercisesName.containsKey(serie))
//            return null;
//        else
//            return exercisesName.get(serie);
        return arrayList;
    }
}
