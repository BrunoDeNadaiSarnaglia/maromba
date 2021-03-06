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
import android.widget.Toast;

import com.example.bruno.maromba.databaseQueries.DatabaseHelper;
import com.example.bruno.maromba.databaseQueries.SeriesQuery;

import java.util.List;

/**
 * Created by Bruno on 4/9/2015.
 */
public class SeriesScreen extends Activity {


    /**
     * initialize the new activity with a background image and email data send from the previous activity
     * @param savedInstanceState
     */

    SeriesQuery seriesQuery = new SeriesQuery();
    String email;
    SQLiteDatabase sqLiteDatabase;

    /**
     * Load de series based on the email and create a list view to access each exercise information
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        create();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        create();
    }

    /**
     * function created to refactor onRestart and onCreate
     */

    protected void create(){
        setContentView(R.layout.series_layout);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        //load data from the previous activity
        Intent intent = getIntent();
        email = intent.getExtras().getString("username");

        List<String> seriesNames = seriesQuery.getSeriesNames(email, sqLiteDatabase);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, seriesNames);

        ListView theListView = (ListView) findViewById(R.id.theListView);

        theListView.setAdapter(arrayAdapter);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            /**
             * pop up a new activity based on the list view clicked
             * it will show the exercises of the serie choosed
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String picked = String.valueOf(parent.getItemAtPosition(position));
                Intent ExerciseScreen = new Intent(SeriesScreen.this, ExerciseActivity.class);
                final int result = 1;
                ExerciseScreen.putExtra("username", email);
                ExerciseScreen.putExtra("serie", picked);
                startActivityForResult(ExerciseScreen, result);
            }
        });

        TextView emailTextView = (TextView) findViewById(R.id.email_text_view);
        emailTextView.setText(email);
    }

        /**
         * it pop up a new activity that has a form to add exercise to the serie you are looking
         * @param view
         */
    public void addExercise(View view) {
        Intent AddSerieScreen = new Intent(SeriesScreen.this, AddSerieActivity.class);
        final int result = 1;
        AddSerieScreen.putExtra("username", email);
        startActivityForResult(AddSerieScreen, result);
    }

    /**
     * given an email, delete all the records related to this email
     * @param view
     */

    public void deleteAccount(View view) {
        sqLiteDatabase.delete("login", "email = ?", new String[]{email});
        sqLiteDatabase.delete("series", "email = ?", new String[]{email});
        sqLiteDatabase.delete("exercises", "email = ?", new String[]{email});
        this.finish();

    }
}
