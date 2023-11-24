package com.example.birthdaywishing;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.birthdaywishing.R;
import com.example.birthdaywishing.wishingScreen;

import java.util.ArrayList;

public class BgViewActivity extends AppCompatActivity {
    private GridView mGridView;
    private ImageAdapter mImageAdapter;

    String name;
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bgview_activity);
        Intent intent=getIntent();
        name=intent.getStringExtra("name");
        content=intent.getStringExtra("content");
        mGridView = findViewById(R.id.gridView);

        // Array of image resource IDs from your drawable folder
        int[] images = {R.drawable.wish, R.drawable.bg3, R.drawable.bg2, R.drawable.bg4, R.drawable.bg5,
                R.drawable.bg16, R.drawable.bg17, R.drawable.bg21, R.drawable.bg22, R.drawable.bg23, R.drawable.bg25,
                R.drawable.bg27, R.drawable.bg6 ,R.drawable.bg10, R.drawable.bg11, R.drawable.bg12, R.drawable.bg13,
                R.drawable.bg14, R.drawable.bg19/*  Add more image references */};

        mImageAdapter = new ImageAdapter(this, images,name,content);
        mGridView.setAdapter(mImageAdapter);

    }
}
