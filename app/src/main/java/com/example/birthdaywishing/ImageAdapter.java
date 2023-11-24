package com.example.birthdaywishing;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private int[] mImages; // Array of image resource IDs
    String mname;
    String mcontent;

    public ImageAdapter(Context context, int[] images,String name ,String content) {
        mContext = context;
        mImages = images;
        mname= name;
        mcontent=content;


    }

    @Override
    public int getCount() {
        return mImages.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300)); // Adjust size as needed
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mImages[position]);

        // Set click listener for each image
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, wishingScreen.class);
                intent.putExtra("name", mname);
                intent.putExtra("content", mcontent);
                intent.putExtra("position", mImages[position]); // Pass image resource ID
                mContext.startActivity(intent);
            }
        });
        return imageView;
    }
}
