package com.example.notesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteTileAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> notes;

    public NoteTileAdapter(Context context, ArrayList<String> notes) {
        this.context = context;
        this.notes = notes;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.note_tile, parent, false);
        }

        String note = notes.get(position);

        TextView noteTextView = convertView.findViewById(R.id.noteTextView);
        noteTextView.setText(note);

        return convertView;
    }
}
