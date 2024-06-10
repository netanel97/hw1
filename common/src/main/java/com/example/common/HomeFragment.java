package com.example.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.common.databinding.FragmentMainBinding;
import com.google.gson.Gson;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private FragmentMainBinding binding;

    private HomeRecyclerViewAdapter mAdapter;
    private RecyclerView resultRV;
    private ArrayList<Note> notesList;

    private SharedPreferences sp;

    private static final String PREFS_NAME = "ReHubPrefs";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        notesList = new ArrayList<>(); // Initialize notes list
        sp = getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        resultRV = binding.homeFragmentLSTTextList; // Connect between xml to Recyclerview
        mAdapter = new HomeRecyclerViewAdapter(getContext());
//        mAdapter.updateList(); TODO:need to send data or pull data from the db need to think
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        resultRV.setAdapter(mAdapter);
        resultRV.setLayoutManager(linearLayoutManager);
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddNoteDialog();
            }
        });
        return view;
    }
    private void savePreferences() {
        // Serialize the ArrayList of Notes into a JSON string
        Gson gson = new Gson();
        String notesJson = gson.toJson(notesList);

        // Save the JSON string to SharedPreferences
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("notes", notesJson);
        editor.apply();
    }

    private void showAddNoteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Note");

        final EditText input = new EditText(getContext());
        builder.setView(input);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String note = input.getText().toString();
            Note newNote = new Note(note,false);
            if (!note.isEmpty()) {
                notesList.add(newNote);
                mAdapter.updateList(notesList);
                savePreferences();

            } else {
                Toast.makeText(getContext(), "Note cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}