package com.example.birthdaywishing;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.birthdaywishing.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // View Binding
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.nameInput.getText().toString();
                String content = binding.contentInput.getText().toString();

                if (!name.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, wishingScreen.class);
                    intent.putExtra("Name", name);
                    intent.putExtra("Content", content); // Pass both Name and Content here
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter the name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
