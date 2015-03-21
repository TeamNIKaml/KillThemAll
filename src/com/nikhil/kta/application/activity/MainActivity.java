package com.nikhil.kta.application.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.nikhil.kta.view.GameView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GameView(this));
    }
}
