package com.example.israanotes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    Button btnBackToHome;
    ListView notesListView;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btnBackToHome = findViewById(R.id.btnBackToHome);
        notesListView = findViewById(R.id.notesListView);
        dbHelper = new DbHelper(this);

        loadNotes();

        btnBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, HomepageActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadNotes() {
        Cursor cursor = dbHelper.getAllNotes();
        ArrayList<String> notesList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_TITLE));
                String content = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_CONTENT));
                notesList.add("Title: " + title + "\nContent: " + content);
            } while (cursor.moveToNext());
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        notesListView.setAdapter(adapter);
    }
}
