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
import com.cashmyproperty.app.View.Response.Image;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.ViewHolder> {

    Context context;
    List<String> firstPropertyData;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";


    public SliderAdapter(Context context, List<String> firstPropertyData) {
        this.context = context;
        this.firstPropertyData=firstPropertyData;
    }


    @Override
    public SliderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.slider_img, parent, false);
        return new SliderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapter.ViewHolder holder, int position) {
      //  List<Image> images=firstPropertyData.getImages();
        Glide.with(context).load(image_base_url+firstPropertyData.get(position)).into(holder.slider_img);


    }

    @Override
    public int getCount() {
        return firstPropertyData.size();
    }

    public class ViewHolder extends SliderViewAdapter.ViewHolder {

        ImageView slider_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            slider_img = itemView.findViewById(R.id.slider_img);

        }
    }

}
