package com.example.bruno.maromba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bruno.maromba.databaseQueries.ExerciseQuery;

import java.util.List;

/**
 * Created by Bruno on 4/16/2015.
 */
public class ExerciseActivity extends Activity {


    ExerciseQuery exerciseQuery = new ExerciseQuery();
    String email;
    String serie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_layout);



        Intent intent = getIntent();
        email = intent.getExtras().getString("username");
        serie = intent.getExtras().getString("serie");

        List<String> exerciseNames = exerciseQuery.getExercises(email, serie);

        TextView emailSerieTextView = (TextView) findViewById(R.id.email_serie_text_view);
        emailSerieTextView.setText(email + " > " + serie);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exerciseNames);

        ListView theListView = (ListView) findViewById(R.id.exerciseListView);

        theListView.setAdapter(arrayAdapter);
        /**
         * It will pop up a new activity showing the information
         */
        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String picked = String.valueOf(parent.getItemAtPosition(position));
//                Toast.makeText(SeriesScreen.this, picked, Toast.LENGTH_SHORT).show();
                Intent ExerciseScreen = new Intent(ExerciseActivity.this, ExerciseInformationActivity.class);
                final int result = 1;
                ExerciseScreen.putExtra("username", email);
                ExerciseScreen.putExtra("serie", serie);
                ExerciseScreen.putExtra("exercise", picked);
                startActivityForResult(ExerciseScreen, result);
            }
        });
    }

    public void deleteSerie(View view) {

    }


    /**
     * will add exercise to the serie you are looking
     * @param view
     */
    public void addExercise(View view) {
        Intent intent = new Intent(ExerciseActivity.this, AddExerciseActivity.class);
        final int result = 1;
        intent.putExtra("username", email);
        intent.putExtra("serie", serie);
        startActivityForResult(intent, result);

    }
}
