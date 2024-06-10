package com.example.common;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class PanelBaseActivity extends AppCompatActivity {
    private HomeFragment homeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.mainFragment, homeFragment)
                .commit();


    }
}