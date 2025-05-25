package com.example.israanotes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class NoteActivity extends AppCompatActivity {

    EditText etNoteTitle, etNoteContent;
    Button btnSaveNote, btnBackToHomeFromNote;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        etNoteTitle = findViewById(R.id.etNoteTitle);
        etNoteContent = findViewById(R.id.etNoteContent);
        btnSaveNote = findViewById(R.id.btnSaveNote);
        btnBackToHomeFromNote = findViewById(R.id.btnBackToHomeFromNote);

        dbHelper = new DbHelper(this);

        btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etNoteTitle.getText().toString();
                String content = etNoteContent.getText().toString();

                if (title.isEmpty() || content.isEmpty()) {
                    Toast.makeText(NoteActivity.this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean inserted = dbHelper.insertNote(title, content);
                    if (inserted) {
                        Toast.makeText(NoteActivity.this, "Note Saved", Toast.LENGTH_SHORT).show();
                        etNoteTitle.setText("");
                        etNoteContent.setText("");
                    } else {
                        Toast.makeText(NoteActivity.this, "Failed to save note", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnBackToHomeFromNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteActivity.this, HomepageActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
