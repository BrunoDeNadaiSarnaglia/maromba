package com.example.bruno.maromba;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bruno.maromba.databaseQueries.DatabaseHelper;
import com.example.bruno.maromba.databaseQueries.ExercisesInformationQuery;

import java.util.List;

/**
 * Created by Bruno on 4/16/2015.
 */
public class ExerciseInformationActivity extends Activity {

    ExercisesInformationQuery exercisesInformationQuery = new ExercisesInformationQuery();
    String email;
    String serie;
    String exercise;


    /**
     * it will create a activity that will list the information of each exercise.
     * It will query in the database using email, serie and exercise and will
     * show in the screen the values
     * @param savedInstanceState
     */

    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_information);

        TextView textView = (TextView) findViewById(R.id.email_serie_exercise_text_view);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        Intent intent = getIntent();
        email = intent.getExtras().getString("username");
        serie = intent.getExtras().getString("serie");
        exercise = intent.getExtras().getString("exercise");
        textView.setText(email + " > " + serie + " > " + exercise);

        List<String> exercisesInformation = exercisesInformationQuery.getInformation(email, serie, exercise, sqLiteDatabase);

        TextView textViewRepeats = (TextView) findViewById(R.id.repeats);
        TextView textViewTimes = (TextView) findViewById(R.id.times);
        TextView textViewWeight = (TextView) findViewById(R.id.weight);

        textViewRepeats.setText(exercisesInformation.get(0));
        textViewTimes.setText(exercisesInformation.get(1));
        textViewWeight.setText(exercisesInformation.get(2));
    }


    public void deleteExercise(View view) {
        sqLiteDatabase.delete("exercises", "email = ? AND serie = ? AND exercise = ?", new String[]{email, serie, exercise});
        this.finish();
    }
}
