package com.example.birthdaywishing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.birthdaywishing.databinding.ActivityWishingScreenBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class wishingScreen extends AppCompatActivity {

    // View Binding
    ActivityWishingScreenBinding binding;
    String name;
    String content;
    int[] imagearray;
    int position;
    ImageView imageView;
    TextView wishname,wishcontent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWishingScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        content = intent.getStringExtra("content");
        position = intent.getIntExtra("position", 0); // Retrieve image resource ID

        imageView = findViewById(R.id.imageView);
        imageView.setBackgroundResource(position);
        wishname=findViewById(R.id.name);
        wishcontent=findViewById(R.id.content);
        wishname.setText(name);
        wishcontent.setText(content);

        binding.saveToGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap image = getBitmapFormView(binding.finalimage);
                saveImageToGallery(image);
            }
        });

        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap image = getBitmapFormView(binding.finalimage);

                ShareImageAndText(image);

            }
        });
    }
    private void saveImageToGallery(Bitmap image) {
        String savedImageURL = MediaStore.Images.Media.insertImage(
                getContentResolver(),
                image,
                "BirthdayImage",
                "Birthday wish image"
        );

        if (savedImageURL != null) {
            Toast.makeText(this, "Image saved to Gallery", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to save image", Toast.LENGTH_SHORT).show();
        }
    }


    private void ShareImageAndText(Bitmap image) {
        // Save the image to the gallery
        String savedImageURL = MediaStore.Images.Media.insertImage(
                getContentResolver(),
                image,
                "BirthdayImage",
                "Birthday wish image"
        );

        if (savedImageURL != null) {
            // Image saved successfully, now create a share Intent
            Uri imageUri = Uri.parse(savedImageURL);

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/*");
            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            // Start the share action
            startActivity(Intent.createChooser(shareIntent, "Share Image Via:"));
        } else {
            // Failed to save the image
            Toast.makeText(this, "Failed to save image", Toast.LENGTH_SHORT).show();
        }
    }



    private Uri getImageToShare(Bitmap image) {
        File imageFolder =new File(getCacheDir(),"images");
        Uri uri = null;
        try {

            imageFolder.mkdirs();
            File file = new File(imageFolder,"birthday_image.png");
            FileOutputStream outputStream = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG,100,outputStream);
            outputStream.flush();
            outputStream.close();
            uri = FileProvider.getUriForFile(this,"com.group7.shareImage.FileProvider",file);



        }catch (Exception e){
            Toast.makeText(this, ""+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return uri;

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

        //draw the view on canvas
        view.draw(canvas);

        return returnedBitmap;

    }
}

