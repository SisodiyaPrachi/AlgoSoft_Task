package com.cashmyproperty.app.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Response.Image;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewPagerAdapter extends PagerAdapter {

    // Context object
    Context context;
    private float mScaleFactor = 1.0f;
    private ScaleGestureDetector scaleGestureDetector;
    ArrayList<String> images;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";
    // Layout Inflater
    LayoutInflater mLayoutInflater;
    PhotoView imageView;



    // Viewpager Constructor
    public ViewPagerAdapter(Context context,  ArrayList<String> images) {
        this.context = context;
        this.images = images;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {
        // return the number of images
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        // inflating the item.xml
        View itemView = mLayoutInflater.inflate(R.layout.view_image, container, false);

        // referencing the image view from the item.xml file
         imageView = (PhotoView) itemView.findViewById(R.id.image);

        // setting the image in the imageView
        Glide.with(context).load(image_base_url+images.get(position)).into(imageView);
       // imageView.setImageResource(Integer.parseInt(image_base_url+Integer.valueOf(images.get(position))));

        // Adding the View
        Objects.requireNonNull(container).addView(itemView);


        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);
    }




}
