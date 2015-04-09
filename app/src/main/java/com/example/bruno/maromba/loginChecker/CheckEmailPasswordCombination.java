package com.example.bruno.maromba.loginChecker;

/**
 * Created by Bruno on 4/9/2015.
 */
public class CheckEmailPasswordCombination {

    /**
     * check if email and password match
     * @param email email used to access account
     * @param password password used to access account
     * @return
     */

    public boolean valid(String email, String password){
        String databasePassword = new QueryDatabaseForPassword().getPassword(email);
        if(password.equals(databasePassword)) {
            return true;
        }
        return false;
    }
}
