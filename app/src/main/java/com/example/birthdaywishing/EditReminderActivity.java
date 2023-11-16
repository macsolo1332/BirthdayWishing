package com.example.birthdaywishing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditReminderActivity extends AppCompatActivity {
    private EditText editTitle, editDate, editTime;
    private Button saveButton;
    private String oldTitle; // Store the original title for updating

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reminder);

        editTitle = findViewById(R.id.editTitle);
        editDate = findViewById(R.id.editDate);
        editTime = findViewById(R.id.editTime);
        saveButton = findViewById(R.id.saveButton);

        // Retrieve the reminder data from the intent
        Intent intent = getIntent();
        oldTitle = intent.getStringExtra("title");
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        editTitle.setText(oldTitle); // Display the original title
        editDate.setText(date);
        editTime.setText(time);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = editTitle.getText().toString().trim();
                String newDate = editDate.getText().toString().trim();
                String newTime = editTime.getText().toString().trim();

                if (newTitle.isEmpty() || newDate.isEmpty() || newTime.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    updateReminder(oldTitle, newTitle, newDate, newTime);
                }
            }
        });
    }

    private void updateReminder(String oldTitle, String newTitle, String date, String time) {
        String result = new dbManager(this).updateReminder(oldTitle, newTitle, date, time);
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

        // Return to the MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
