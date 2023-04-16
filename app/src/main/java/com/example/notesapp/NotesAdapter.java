package com.example.notesapp;
import com.example.notesapp.db.Note;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends BaseAdapter {

    private Context context;
    private List<Note> notes;

    public NotesAdapter(Context context) {
        this.context = context;
        this.notes = new ArrayList<>();
    }

    public void setNotes(List<Note> notes) {
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

    public void add(Note note) {
        notes.add(note);
        return;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.note_layout, parent, false);
        }

        Note note = notes.get(position);

        TextView titleView = convertView.findViewById(R.id.note_title);
        TextView contentView = convertView.findViewById(R.id.note_content);
        TextView dateView = convertView.findViewById(R.id.note_date);

        titleView.setText(note.getTitle());
        contentView.setText(note.getContent());
//        dateView.setText(note.getDate());

        return convertView;
    }
}
