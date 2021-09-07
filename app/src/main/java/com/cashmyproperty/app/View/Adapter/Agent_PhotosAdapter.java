package com.cashmyproperty.app.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.DetailPage.PhotoView_Activity;
import com.cashmyproperty.app.View.Response.Image;
import com.cashmyproperty.app.View.Response.PropertyDetailsData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Agent_PhotosAdapter extends RecyclerView.Adapter<Agent_PhotosAdapter.ViewHolder> {

    Context context;
    PropertyDetailsData photo;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";


    public Agent_PhotosAdapter(Context context, PropertyDetailsData photo){
        this.context=context;
        this.photo = photo;

    }



    @Override
    public Agent_PhotosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_photos,parent,false);
        return new Agent_PhotosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Agent_PhotosAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(image_base_url+photo.getImages().get(position).getPropertyImage()).into(holder.cat_img);

          ArrayList<String> img=new ArrayList<>();

             for(int i=0;i<photo.getImages().size();i++)
             img.add(photo.getImages().get(i).getPropertyImage());


        holder.cat_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, PhotoView_Activity.class);
                intent.putExtra("Photos", img);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount()
    {
        return photo.getImages().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView cat_img;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cat_img = itemView.findViewById(R.id.cat_img);


        }
    }
}

