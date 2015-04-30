package com.example.bruno.maromba.loginChecker;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Bruno on 4/9/2015.
 */
public class CheckEmailPasswordCombination {

    /**
     * Query the database and check if the password and the queried password are equals
     * @param email email used to access account
     * @param password password used to access account
     * @param sqLiteDatabase database being queried
     * @return
     */

    public static boolean valid(String email, String password, SQLiteDatabase sqLiteDatabase){
        String databasePassword = new QueryDatabaseForPassword().getPassword(email, sqLiteDatabase);
        if(password.equals(databasePassword)) {
            return true;
        }
        return false;
    }
}
