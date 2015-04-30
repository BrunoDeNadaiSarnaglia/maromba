package com.example.bruno.maromba;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bruno.maromba.databaseQueries.DatabaseHelper;
import com.example.bruno.maromba.databaseQueries.SerieInSeriesTable;

import java.util.concurrent.TimeUnit;

/**
 * Created by Bruno on 4/16/2015.
 */
public class AddExerciseActivity extends Activity {

    /**
     * Will pop a form to fill the information of each exercise
     * @param savedInstanceState
     */

    SQLiteDatabase sqLiteDatabase;
    String email;
    String serie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exercise);
        DatabaseHelper database = new DatabaseHelper(this);
        sqLiteDatabase = database.getWritableDatabase();
        Intent intent = getIntent();
        email = intent.getExtras().getString("username");
        serie = intent.getExtras().getString("serie");
    }


    /**
     * When trying trying to add. It will create a contentValues and will insert the values
     * @param view
     */
    public void addExerciseToDatabase(View view) {
        EditText editText = (EditText) findViewById(R.id.repeat_amount);
        EditText editText1 = (EditText) findViewById(R.id.times_amount);
        EditText editText2 = (EditText) findViewById(R.id.weight_amount);
        EditText editText3 = (EditText) findViewById(R.id.name_exercise);
        String name = editText3.getText().toString();
        String repeat = editText.getText().toString();
        String times = editText1.getText().toString();
        String weight = editText2.getText().toString();
        if(name.length() == 0 || repeat.length() == 0 || times.length() == 0 || weight.length() == 0){
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT);
            return;
        }
        if(!SerieInSeriesTable.query(email, serie, sqLiteDatabase)){
            Toast.makeText(this, "Serie not in the database", Toast.LENGTH_SHORT);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("serie", serie);
        contentValues.put("email", email);
        contentValues.put("exercise", name);
        contentValues.put("numTimes", repeat);
        contentValues.put("numRepetition", times);
        contentValues.put("weight", weight);
        try {
            Long id = sqLiteDatabase.insert("exercises", null, contentValues);
            //id is the number of the row which these values were inserted. If something went wrong, it returns -1
            if(id == -1){
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
//        Intent ExerciseScreen = new Intent(AddExerciseActivity.this, ExerciseActivity.class);
//        final int result = 1;
//        ExerciseScreen.putExtra("username", email);
//        ExerciseScreen.putExtra("serie", serie);
//        startActivityForResult(ExerciseScreen, result);
        this.finish();
    }
}
