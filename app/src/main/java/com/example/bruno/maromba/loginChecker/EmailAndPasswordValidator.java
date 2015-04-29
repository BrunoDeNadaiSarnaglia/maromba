package com.example.bruno.maromba.loginChecker;

/**
 * Created by Bruno on 4/9/2015.
 */
public class EmailAndPasswordValidator {


    /**
     * Check  the format of email and password
     * @param email email used to access account
     * @param password password used to access account
     * @return true if both are correct otherwise false
     */
    public static boolean check(String email, String password){
        return validEmail(email) && validPassword(password);
    }

    /**
     * check if there is an @ in the String
     * @param email email used to access account
     * @return true if email is, valid otherwise false
     */
    private static boolean validEmail(String email) {
        if(email == null)
            return false;
        if(!email.contains("@")){
            return false;
        }
        return true;
    }

    /**
     * check if length is greater then 6
     * @param password password used to access account
     * @return true if password is valid, otherwise false
     */
    private static boolean validPassword(String password) {
        if(password == null)
            return false;
        if(password.length() < 6){
            return false;
        }
        return true;
    }
}
