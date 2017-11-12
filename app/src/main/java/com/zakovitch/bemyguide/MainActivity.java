package com.zakovitch.bemyguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zakovitch.backendmanager.DataManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataManager.getResponse(this);

    }
}
