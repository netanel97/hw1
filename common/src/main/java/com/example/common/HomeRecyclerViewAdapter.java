package com.example.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeViewHolder> {

    private Context context;
    private ArrayList<Note> allNotes;


    public HomeRecyclerViewAdapter(Context context) {
        this.context = context;
        this.allNotes = new ArrayList<>();
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_card, parent, false);

        // Return a new holder instance
        HomeViewHolder viewHolder = new HomeViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Note note = getItem(position);
        holder.homeFragment_TXT_Note.setText(note.getText());
        holder.homeFragment_BOX_IsDone.setAllCaps(note.isDone());
    }
    public void updateList(ArrayList<Note> notes) {
        this.allNotes = notes;
        notifyDataSetChanged();
    }
    private Note getItem(int position) {
        ArrayList<Note> notes = new ArrayList<>(allNotes);
        return notes.get(position);
    }

    @Override
    public int getItemCount() {
        return allNotes == null ? 0 : allNotes.size();

    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        private TextView homeFragment_TXT_Note;
        private TextView homeFragment_BOX_IsDone;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            homeFragment_TXT_Note = itemView.findViewById(R.id.homeFragment_TXT_Note);
            homeFragment_BOX_IsDone = itemView.findViewById(R.id.homeFragment_BOX_IsDone);
        }
    }
}
