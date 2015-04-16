package com.example.bruno.maromba;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Bruno on 4/16/2015.
 */
public class AddExerciseActivity extends Activity {

    /**
     * Will pop a form to fill the information of each exercise
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exercise);

    }

    public void addExerciseToDatabase(View view) {
        this.finish();
    }
}
