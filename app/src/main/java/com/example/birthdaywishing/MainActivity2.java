package com.example.birthdaywishing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    FloatingActionButton mCreateRem;
    RecyclerView mRecyclerview;
    ArrayList<Model> dataholder = new ArrayList<Model>();
    myAdapter adapter;
    dbManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        db = new dbManager(getApplicationContext());

        mRecyclerview = findViewById(R.id.recyclerView);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mCreateRem = findViewById(R.id.create_reminder);

        mCreateRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainActivity2.this, Reminder.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        loadReminders();

        adapter = new myAdapter(dataholder, new myAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(Model model) {
                editReminder(model);
            }

            @Override
            public void onDeleteClick(Model model) {
                deleteReminder(model);
            }
            @Override
            public void onMoveToMainClick(Model model) {
                // Handle the "Move to Main" button click action here
                // For example:
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
        mRecyclerview.setAdapter(adapter);
    }

    private void loadReminders() {
        Cursor cursor = db.readAllReminders();
        dataholder.clear();
        while (cursor.moveToNext()) {
            Model model = new Model(cursor.getString(1), cursor.getString(2), cursor.getString(3));
            dataholder.add(model);
        }
    }

    private void editReminder(Model model) {
        // Implement the edit functionality here
        // You can pass the reminder data to the editing activity
        Toast.makeText(this, "Edit: " + model.getTitle(), Toast.LENGTH_SHORT).show();
    }

    private void deleteReminder(Model model) {
        String result = db.deleteReminder(model.getTitle(), model.getDate(), model.getTime());
        if (result.equals("Successfully deleted reminder")) {
            loadReminders(); // Reload the list after deletion
            adapter.notifyDataSetChanged();
        }
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    }
}