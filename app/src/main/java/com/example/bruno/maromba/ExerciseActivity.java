package com.example.bruno.maromba;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bruno.maromba.databaseQueries.DatabaseHelper;
import com.example.bruno.maromba.databaseQueries.ExerciseQuery;

import java.util.List;

/**
 * Created by Bruno on 4/16/2015.
 */
public class ExerciseActivity extends Activity {


    ExerciseQuery exerciseQuery = new ExerciseQuery();
    String email;
    String serie;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        create();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        create();
    }

    protected void create(){
        setContentView(R.layout.exercise_layout);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getWritableDatabase();


        Intent intent = getIntent();
        email = intent.getExtras().getString("username");
        serie = intent.getExtras().getString("serie");

        List<String> exerciseNames = exerciseQuery.getExercises(email, serie, sqLiteDatabase);

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
                Intent ExerciseScreen = new Intent(ExerciseActivity.this, ExerciseInformationActivity.class);
                final int result = 1;
                ExerciseScreen.putExtra("username", email);
                ExerciseScreen.putExtra("serie", serie);
                ExerciseScreen.putExtra("exercise", picked);
                startActivityForResult(ExerciseScreen, result);
            }
        });
    }


    /**
     * delete the serie form series table and all exercise from that series
     * @param view
     */

    public void deleteSerie(View view) {
        sqLiteDatabase.delete("series", "email = ? AND serie = ?", new String[]{email, serie});
        sqLiteDatabase.delete("exercises", "email = ? AND serie = ?", new String[]{email, serie});
        this.finish();
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
