package com.example.common;

import java.util.ArrayList;

public class DataManager {
    private ArrayList<Note> allNotes;

    public DataManager() {
        this.allNotes = new ArrayList<>();
    }

    public ArrayList<Note> getAllNotes() {
        return allNotes;
    }

    public DataManager setAllNotes(ArrayList<Note> allNotes) {
        this.allNotes = allNotes;
        return this;
    }
}




