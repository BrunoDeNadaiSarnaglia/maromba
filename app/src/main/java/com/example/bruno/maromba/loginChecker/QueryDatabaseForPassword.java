package com.example.bruno.maromba.loginChecker;

import java.util.ArrayList;

/**
 * Created by Bruno on 4/9/2015.
 */
public class QueryDatabaseForPassword {

    /**
     * Function mocking the search on the database
     * @param email
     * @return
     */

    public String getPassword(String email){
        if(email.equals("a@b"))
            return "123456";
        return null;
    }
}
