package com.cashmyproperty.app.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Response.AuctionclosingProperty;
import com.cashmyproperty.app.View.Response.HometopProperty;
import com.cashmyproperty.app.View.Response.Image;
import com.cashmyproperty.app.View.Response.Image__1;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class SliderAdapter_Recyler extends RecyclerView.Adapter<SliderAdapter_Recyler.ViewHolder> {


    Context context;
    List<HometopProperty> hometopProperties;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";



    public SliderAdapter_Recyler(Context context,  List<HometopProperty> hometopProperties){
        this.context=context;
        this.hometopProperties = hometopProperties;


    }



    @Override
    public SliderAdapter_Recyler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.slider_recagent,parent,false);
        return new SliderAdapter_Recyler.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapter_Recyler.ViewHolder holder, int position) {
        List<Image> image__1s= hometopProperties.get(position).getImages();


       /* SliderAdapter_Agent sliderAdapter_agent=new SliderAdapter_Agent(context,image__1s);
        holder.rec_slideragent.setSliderAdapter(sliderAdapter_agent);*/



    }




    @Override
    public int getItemCount() {
        return hometopProperties.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        SliderView rec_slideragent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rec_slideragent=itemView.findViewById(R.id.rec_slideragent);


        }
    }


}

