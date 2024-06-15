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
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private FragmentMainBinding binding;

    private HomeRecyclerViewAdapter mAdapter;
    private RecyclerView resultRV;

    private SharedPreferences sp;

    private static final String PREFS_NAME = "TodoListPrefs";

    private DataManager dataManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sp = getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        dataManager = new DataManager();
        loadPreferences();
        binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        resultRV = binding.homeFragmentLSTTextList; // Connect between xml to Recyclerview
        mAdapter = new HomeRecyclerViewAdapter(getContext());
        mAdapter.updateList(this.dataManager.getAllNotes());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        resultRV.setAdapter(mAdapter);
        resultRV.setLayoutManager(linearLayoutManager);
        binding.btnAdd.setOnClickListener(view1 -> showAddNoteDialog());
        mAdapter.setItemSetOnListener(new HomeRecyclerViewAdapter.ItemSetOnListener() {
            @Override
            public void isDone(int pos) {
                dataManager.getAllNotes().get(pos).setDone(!dataManager.getAllNotes().get(pos).isDone());
                mAdapter.updateList(dataManager.getAllNotes());
                savePreferences();

            }
        });
        return view;
    }

    private void loadPreferences() {
        String notesJson = sp.getString(PREFS_NAME, null);

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

    private void savePreferences() {
        // Serialize the ArrayList of Notes into a JSON string
        Gson gson = new Gson();
        String notesJson = gson.toJson(dataManager.getAllNotes());

        // Save the JSON string to SharedPreferences
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(PREFS_NAME, notesJson);
        editor.apply();
    }

    private void showAddNoteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Note");

        final EditText input = new EditText(getContext());
        builder.setView(input);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String note = input.getText().toString();
            Note newNote = new Note(note, false);
            if (!note.isEmpty()) {
                dataManager.getAllNotes().add(newNote);
                mAdapter.updateList(dataManager.getAllNotes());
                savePreferences();

            } else {
                Toast.makeText(getContext(), "Note cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}