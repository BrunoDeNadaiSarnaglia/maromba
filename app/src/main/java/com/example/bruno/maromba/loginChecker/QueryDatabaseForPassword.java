package com.example.bruno.maromba.loginChecker;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Bruno on 4/9/2015.
 */
public class QueryDatabaseForPassword {

    /**
     * query the database and return the password for given email or the null if doesn't exists
     * @param email
     * @param sqLiteDatabase database
     * @return String with the password
     */

    public String getPassword(String email, SQLiteDatabase sqLiteDatabase){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT password FROM login WHERE email = ?", new String[] {email});
        while(cursor.moveToNext()){
            return cursor.getString(0);
        }
        return null;
    }
}
