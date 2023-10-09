package com.example.birthdaywishing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import com.example.birthdaywishing.databinding.ActivityWishingScreenBinding;

public class wishingScreen extends AppCompatActivity {

    // View Binding
    ActivityWishingScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWishingScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        String name = intent.getStringExtra("Name");
        String content = intent.getStringExtra("Content");

        binding.name.setText(name);
        binding.content.setText(content);

        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap image = getBitmapFormView(binding.finalimage);

            }
        });
    }

    private Bitmap getBitmapFormView(View view) {
        //define a bitmap with same width and height
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(),view.getHeight(),Bitmap.Config.ARGB_8888);

        //binding canva to it
        Canvas canvas = new Canvas(returnedBitmap);

        //get the background view of the layout
        Drawable background = view.getBackground();
        if (background != null){

            background.draw(canvas);
        }else{
            canvas.drawColor(Color.WHITE);
        }

        view.draw(canvas);

        return returnedBitmap;

    }
}

