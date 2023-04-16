package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.example.notesapp.db.Note;
import com.example.notesapp.db.NoteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView notesGridView;
    private NotesAdapter notesAdapter;
    private NoteDatabase noteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize the GridView and Adapter
        notesGridView = findViewById(R.id.notesGridView);
        notesAdapter = new NotesAdapter(this);
        notesGridView.setAdapter(notesAdapter);

        // Initialize the database
        noteDatabase = NoteDatabase.getInstance(this);

        // Handle click on note
        notesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ViewNoteActivity.class);
                Note note = (Note) adapterView.getItemAtPosition(position);
                intent.putExtra("note", note);
                startActivity(intent);
            }
        });

        // Add new note button
        TextView newNoteTile = (TextView) LayoutInflater.from(this).inflate(R.layout.new_note_tile, null);
//        notesGridView.addHeaderView(newNoteTile);
        newNoteTile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        // Load notes from the database
        new LoadNotesTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_settings) {
            // Handle settings click
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addNoteButtonClick(View view) {
        EditText titleEditText = findViewById(R.id.title_edit_text);
        EditText contentEditText = findViewById(R.id.content_edit_text);

        // Get the title and content from the EditText views
        String title = titleEditText.getText().toString().trim();
        String content = contentEditText.getText().toString().trim();

        // Check if the title or content is not empty
        if (!title.isEmpty() || !content.isEmpty()) {
            // Create a new Note object
            Note note = new Note();
            note.setTitle(title);
            note.setContent(content);
            note.setDate(new Date().getTime());

            // Insert the note into the database
            new InsertNoteTask().execute(note);

            // Clear the EditText views
            titleEditText.setText("");
            contentEditText.setText("");
        }
    }

    private class LoadNotesTask extends AsyncTask<Void, Void, List<Note>> {

        @Override
        protected List<Note> doInBackground(Void... voids) {
            return noteDatabase.getNoteDao().getAll();
        }

        @Override
        protected void onPostExecute(List<Note> notes) {
            super.onPostExecute(notes);
            notesAdapter.setNotes(notes);
            notesAdapter.notifyDataSetChanged();
        }
    }

    private class InsertNoteTask extends AsyncTask<Note, Void, Void> {

        @Override
        protected Void doInBackground(Note... notes) {
            NoteDatabase.getInstance(MainActivity.this).getNoteDao().insert(notes[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            notesAdapter.notifyDataSetChanged();
        }
    }
}