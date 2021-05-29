package com.example.mindwind.User;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.widget.EditText;


import com.example.mindwind.R;

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {
    int noteID;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        editText = findViewById(R.id.multilineedit);
        Intent intent = getIntent();
        noteID = intent.getIntExtra("noteID", -1);
        if(noteID != -1)
        {
            editText.setText(JournalPage.notes.get(noteID));
        }
        else
        {
            JournalPage.notes.add("");                // as initially, the note is empty
            noteID = JournalPage.notes.size() - 1;
            JournalPage.arrayAdapter.notifyDataSetChanged();
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                JournalPage.notes.set(noteID, String.valueOf(s));
                JournalPage.arrayAdapter.notifyDataSetChanged();
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(JournalPage.notes);
                sharedPreferences.edit().putStringSet("notes", set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}