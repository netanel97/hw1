package com.example.common;

public class Note {
    private String text;
    private boolean isDone;

    public Note(String text, boolean isDone) {
        this.text = text;
        this.isDone = isDone;
    }

    public Note() {
    }

    public String getText() {
        return text;
    }

    public Note setText(String text) {
        this.text = text;
        return this;
    }

    public boolean isDone() {
        return isDone;
    }

    public Note setDone(boolean done) {
        isDone = done;
        return this;
    }
}
