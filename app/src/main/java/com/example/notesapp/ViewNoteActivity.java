package com.example.notesapp;
import com.example.notesapp.db.Note;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ViewNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView noteTitleTextView = findViewById(R.id.note_title_text_view);
        TextView noteContentTextView = findViewById(R.id.note_content_text_view);
        TextView noteDateTextView = findViewById(R.id.note_date_text_view);

        Note note = getIntent().getParcelableExtra("note");

        if (note != null) {
            noteTitleTextView.setText(note.getTitle());
            noteContentTextView.setText(note.getContent());

            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy, hh:mm aaa", Locale.getDefault());
            noteDateTextView.setText(sdf.format(note.getDate()));
        }
    }

    public void getSerializableExtra() {
        Note note = (Note) getIntent().getSerializableExtra("note");

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
