package com.example.bruno.maromba;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bruno.maromba.databaseQueries.DatabaseHelper;

import java.util.concurrent.TimeUnit;

/**
 * Created by Bruno on 4/16/2015.
 */
public class AddSerieActivity extends Activity {

    /**
     * Will pop up a activity that will show just a text field that will be
     * the name of your new serie
     * @param savedInstanceState
     */

    SQLiteDatabase sqLiteDatabase;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_serie);
        DatabaseHelper database = new DatabaseHelper(this);
        sqLiteDatabase = database.getWritableDatabase();
        Intent intent = getIntent();
        email = intent.getExtras().getString("username");
    }


    /**
     * When trying trying to add. It will create a contentValues and will insert the values
     * @param view
     */
    public void addSerieToDatabase(View view) {
        EditText editText = (EditText) findViewById(R.id.serie_name);
        String serieName = editText.getText().toString();
        if(serieName.length() == 0){
            Toast.makeText(this, "Invalid Serie Name", Toast.LENGTH_SHORT).show();
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("serie", serieName);
        contentValues.put("email", email);
        try {
            Long id = sqLiteDatabase.insert("series", null, contentValues);
            //id is the number of the row which these values were inserted. If something went wrong, it returns -1
            if(id == -1){
                Toast.makeText(this, "Series already exists", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
//        Intent AddSerieScreen = new Intent(AddSerieActivity.this, SeriesScreen.class);
//        final int result = 1;
//        AddSerieScreen.putExtra("username", email);
//        startActivityForResult(AddSerieScreen, result);
        this.finish();
    }
}
