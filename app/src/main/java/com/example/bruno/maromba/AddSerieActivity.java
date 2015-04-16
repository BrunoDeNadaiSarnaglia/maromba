package com.example.bruno.maromba;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Bruno on 4/16/2015.
 */
public class AddSerieActivity extends Activity {

    /**
     * Will pop up a activity that will show just a text field that will be
     * the name of your new serie
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_serie);

    }


    public void addSerieToDatabase(View view) {
        this.finish();
    }
}
