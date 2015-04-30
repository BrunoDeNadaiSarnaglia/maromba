package com.example.bruno.maromba;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bruno.maromba.databaseQueries.DatabaseHelper;
import com.example.bruno.maromba.loginChecker.CheckEmailPasswordCombination;
import com.example.bruno.maromba.loginChecker.EmailAndPasswordValidator;


public class MainActivity extends ActionBarActivity {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        databaseHelper = new DatabaseHelper(this);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * check email and password are valids and match with each other
     * case something wrong, pop up a warning, else opens a new activity
     * @param view
     */
    public void login(View view) {
        String password = String.valueOf(mPasswordView.getText());
        String email = String.valueOf(mEmailView.getText());
        if(!EmailAndPasswordValidator.check(email, password)){
            Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!CheckEmailPasswordCombination.valid(email, password, sqLiteDatabase)){
            Toast.makeText(MainActivity.this, "Login attempt failed", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent seriesScreen = new Intent(this, SeriesScreen.class);
        final int result = 1;
        seriesScreen.putExtra("username", email);
        startActivityForResult(seriesScreen, result);
    }

    /**
     * test if email and password are valid, query the database and check if they match.
     * In case everything is ok, goes to the next activity, otherwise show a message that something wrong occurred
     * @param view
     */

    public void signUp(View view){
        EditText editTextEmail = (EditText) findViewById(R.id.email);
        EditText editTextPassword = (EditText) findViewById(R.id.password);
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        if(!EmailAndPasswordValidator.check(email, password)){
            Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        Long id = sqLiteDatabase.insert("login", null, contentValues);
        if(id == -1){
            Toast.makeText(this, "Email already registered", Toast.LENGTH_SHORT).show();
        } else{
            Intent seriesScreen = new Intent(this, SeriesScreen.class);
            final int result = 1;
            seriesScreen.putExtra("username", email);
            startActivityForResult(seriesScreen, result);
        }
    }
}
