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
import com.cashmyproperty.app.View.Response.BuyerBidPropertyDetails;
import com.cashmyproperty.app.View.Response.Image;
import com.cashmyproperty.app.View.Response.PropertyDetailsData;

import java.util.ArrayList;
import java.util.List;

public class BidProperty_Adapter extends RecyclerView.Adapter<BidProperty_Adapter.ViewHolder> {

    Context context;
    BuyerBidPropertyDetails buyerBidPropertyDetails;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";


    public BidProperty_Adapter(Context context, BuyerBidPropertyDetails buyerBidPropertyDetails){
        this.context=context;
        this.buyerBidPropertyDetails = buyerBidPropertyDetails;

    }



    @Override
    public BidProperty_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_photos,parent,false);
        return new BidProperty_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BidProperty_Adapter.ViewHolder holder, int position) {

        Glide.with(context).load(image_base_url+buyerBidPropertyDetails.getImages().get(position).getPropertyImage())
                .into(holder.cat_img);

        ArrayList<String> img=new ArrayList<>();

        for(int i=0;i<buyerBidPropertyDetails.getImages().size();i++)
            img.add(buyerBidPropertyDetails.getImages().get(i).getPropertyImage());


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
        return buyerBidPropertyDetails.getImages().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView cat_img;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cat_img = itemView.findViewById(R.id.cat_img);


        }
    }
}
