package com.example.birthdaywishing;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationMessage extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);

        textView = findViewById(R.id.message);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String message = bundle.getString("message");
            if (message != null) {
                textView.setText(message);
            }
        }
    }
}
