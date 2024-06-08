package com.example.hw1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private DataManager dataManager;
    //    private HomeFragment homeFragment;
    private HomeRecyclerViewAdapter mAdapter;
    private RecyclerView resultRV;

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
        this.dataManager = new DataManager();
//        resultRV =
//        resultRV = R.id.homeFragment_LST_textList; // Connect between xml to Recyclerview

//        homeFragment = new HomeFragment();
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.mainFragment,homeFragment)
//                .commit();


    }
}