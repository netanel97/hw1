package com.example.common;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PanelBaseActivity extends AppCompatActivity {
    protected DataManager dataManager;
    private HomeFragment homeFragment;

    private SharedPreferences sp;
    private static final String PREFS_NAME = "ReHubPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sp = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        this.dataManager = new DataManager();
        loadPreferences();
        homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.mainFragment, homeFragment)
                .commit();


    }

    private void loadPreferences() {
        String notesJson = sp.getString("notes", null);

        // Check if there are any notes saved
        if (notesJson != null) {
            // Deserialize the JSON string into an ArrayList of Notes
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Note>>() {
            }.getType();
            ArrayList<Note> allNotes = gson.fromJson(notesJson, type);

            // Set the loaded notes to your DataManager
            dataManager.setAllNotes(allNotes);
        }
    }
}