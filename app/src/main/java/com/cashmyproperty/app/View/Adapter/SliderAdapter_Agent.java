package com.cashmyproperty.app.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Response.FirstPropertyData;
import com.cashmyproperty.app.View.Response.HometopProperty;
import com.cashmyproperty.app.View.Response.Image;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter_Agent extends SliderViewAdapter<SliderAdapter_Agent.ViewHolder> {

    Context context;
    List<String> slider;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";


    public SliderAdapter_Agent(Context context, List<String> slider) {
        this.context = context;
        this.slider=slider;
    }


    @Override
    public SliderAdapter_Agent.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.slider_img, parent, false);
        return new SliderAdapter_Agent.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapter_Agent.ViewHolder holder, int position) {


            Glide.with(context).load(image_base_url + slider.get(position)).into(holder.slider_img);



    }

    @Override
    public int getCount() {
        return slider.size();
    }

    public class ViewHolder extends SliderViewAdapter.ViewHolder {

        ImageView slider_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            slider_img = itemView.findViewById(R.id.slider_img);

        }
    }

}

