package com.example.bruno.maromba;

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

import com.example.bruno.maromba.databaseQueries.db;
import com.example.bruno.maromba.loginChecker.CheckEmailPasswordCombination;
import com.example.bruno.maromba.loginChecker.EmailAndPasswordValidator;


public class MainActivity extends ActionBarActivity {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    db db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        db = new db(this);
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
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
        EmailAndPasswordValidator emailAndPasswordValidator = new EmailAndPasswordValidator();
        if(!emailAndPasswordValidator.check(email, password)){
            Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            return;
        }
        CheckEmailPasswordCombination checkEmailPasswordCombination = new CheckEmailPasswordCombination();
        if(!checkEmailPasswordCombination.valid(email, password)){
            Toast.makeText(MainActivity.this, "Login attempt failed", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent seriesScreen = new Intent(this, SeriesScreen.class);
        final int result = 1;
        seriesScreen.putExtra("username", email);
        startActivityForResult(seriesScreen, result);
    }
}
