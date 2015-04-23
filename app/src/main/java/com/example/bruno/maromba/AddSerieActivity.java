package com.example.bruno.maromba;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bruno.maromba.databaseQueries.db;

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

    db database;
    SQLiteDatabase sqLiteDatabase;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_serie);
        db database = new db(this);
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
        ContentValues contentValues = new ContentValues();
        contentValues.put("serie", serieName);
        contentValues.put("email", email);
        System.out.println("deu certo!\n" );
        try {
            Long id = sqLiteDatabase.insert("series", null, contentValues);
            //id is the number of the row which these values were inserted. If something went wrong, it returns -1
            Toast.makeText(this, id.toString(), Toast.LENGTH_LONG).show();
            if(id == -1){
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.finish();
    }
}
