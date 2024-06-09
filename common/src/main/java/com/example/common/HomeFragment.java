package com.example.common;

import android.app.AlertDialog;
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

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private FragmentMainBinding binding;

    private HomeRecyclerViewAdapter mAdapter;
    private RecyclerView resultRV;
    private ArrayList<Note> notesList;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        notesList = new ArrayList<>(); // Initialize notes list

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
            } else {
                Toast.makeText(getContext(), "Note cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}