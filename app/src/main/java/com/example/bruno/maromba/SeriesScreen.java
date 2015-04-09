package com.example.bruno.maromba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Bruno on 4/9/2015.
 */
public class SeriesScreen extends Activity {


    /**
     * initialize the new activity with a background image and email data send from the previous activity
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.series_layout);


        //load data from the previous activity
        Intent intent = getIntent();
        String email = intent.getExtras().getString("username");

        TextView emailTextView = (TextView) findViewById(R.id.email_text_view);
        emailTextView.setText(email);
    }
}
