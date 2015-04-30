package com.example.bruno.maromba.loginChecker;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Bruno on 4/9/2015.
 */
public class QueryDatabaseForPassword {

    /**
     * Function mocking the search on the database
     * @param email
     * @param sqLiteDatabase
     * @return
     */

    public String getPassword(String email, SQLiteDatabase sqLiteDatabase){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT password FROM login WHERE email = ?", new String[] {email});
        while(cursor.moveToNext()){
            return cursor.getString(0);
        }
//        if(email.equals("a@b"))
//            return "123456";
        return null;
    }
}
