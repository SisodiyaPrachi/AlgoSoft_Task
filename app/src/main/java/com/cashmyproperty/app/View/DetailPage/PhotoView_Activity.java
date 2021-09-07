package com.cashmyproperty.app.View.DetailPage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Adapter.ViewPagerAdapter;
import com.cashmyproperty.app.View.Response.Image;

import java.util.ArrayList;
import java.util.List;

public class PhotoView_Activity extends AppCompatActivity {
    ViewPager mViewPager;
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;
    ArrayList<String> list;
    ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_view);

        list=new ArrayList<>();

        Bundle i = getIntent().getExtras();
        list = i.getStringArrayList("Photos");

         //String url=getIntent().getStringExtra("Photos");

        mViewPager=findViewById(R.id.viewPagerMain);
        //scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        mViewPager = (ViewPager)findViewById(R.id.viewPagerMain);

        // Initializing the ViewPagerAdapter
        Log.d("list", "ffff"+list);
        mViewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), list);

        // Adding the Adapter to the ViewPager
        mViewPager.setAdapter(mViewPagerAdapter);

       // Glide.with(getApplicationContext()).load(url).into(image_view);

    }


}
