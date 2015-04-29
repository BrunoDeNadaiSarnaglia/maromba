package com.example.bruno.maromba.databaseQueries;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
import android.widget.Toast;

import com.example.bruno.maromba.MainActivity;

/**
 * Created by Bruno on 4/23/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    /**
     * class that will create the database
     * changing database_version number it will call onUpgrade
     * dropping the tables and creating other tables
     */

    private static final String DATABASE_NAME = "maromba_database";
    private static final String TABLE_NAME = "login";
    private static final String TABLE_NAME_SERIES = "series";
    private static final String TABLE_NAME_EXERCISES = "exercises";
    private static final Integer DATABASE_VERSION = 17;
    private Context context = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    /**
     * create the tables executing the queries listed bellow as string
     * @param sqLiteDatabase
     */

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
//            DatabaseHelper.execSQL(query);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (email VARCHAR(45) PRIMARY KEY, password VARCHAR(45));");
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME_SERIES + " (email VARCHAR(45) , serie VARCHAR(45), PRIMARY KEY (email, serie));");
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME_EXERCISES + " (email VARCHAR(45) , serie VARCHAR(45), exercise VARCHAR(45), numTimes VARCHAR(45), numRepetition VARCHAR(45), weight VARCHAR(45)), PRIMARY KEY(email, serie, exercise);");
            Toast.makeText(context, "onCreated called", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    /**
     * drop old tables and call onCreate to build the database
     * @param sqLiteDatabase
     * @param oldVersion
     * @param newVersion
     */

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        try{
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SERIES + ";");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_EXERCISES + ";");
            onCreate(sqLiteDatabase);
        } catch (Exception e){
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
