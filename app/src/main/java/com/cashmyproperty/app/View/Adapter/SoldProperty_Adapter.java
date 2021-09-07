package com.cashmyproperty.app.View.Adapter;

import android.content.Context;
import android.content.Intent;
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
import com.cashmyproperty.app.View.Response.SellerSoldPropertyDetails;

import java.util.ArrayList;
import java.util.List;

public class SoldProperty_Adapter extends RecyclerView.Adapter<SoldProperty_Adapter.ViewHolder> {

    Context context;
    SellerSoldPropertyDetails soldPropertyData;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";

    public SoldProperty_Adapter(Context context, SellerSoldPropertyDetails soldPropertyData){
        this.context=context;
        this.soldPropertyData = soldPropertyData;

    }



    @Override
    public SoldProperty_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_photos,parent,false);
        return new SoldProperty_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SoldProperty_Adapter.ViewHolder holder, int position) {
        List<Image> list=soldPropertyData.getImages();
        Glide.with(context).load(image_base_url+list.get(position).getPropertyImage()).into(holder.cat_img);

        ArrayList<String> img=new ArrayList<>();

        for(int i=0;i<soldPropertyData.getImages().size();i++)
            img.add(soldPropertyData.getImages().get(i).getPropertyImage());


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
    public int getItemCount() {
        return soldPropertyData.getImages().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView cat_img;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cat_img = itemView.findViewById(R.id.cat_img);


        }
    }
}