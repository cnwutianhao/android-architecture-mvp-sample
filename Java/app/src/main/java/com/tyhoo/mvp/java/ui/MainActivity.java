package com.tyhoo.mvp.java.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tyhoo.mvp.java.R;
import com.tyhoo.mvp.java.util.ActivityUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);

        if (mainFragment == null) {
            mainFragment = new MainFragment();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    mainFragment, R.id.content_frame);
        }

        // Create the presenter.
        new MainPresenter(this, mainFragment);
    }
}