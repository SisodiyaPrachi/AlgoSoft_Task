package com.cashmyproperty.app.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.DetailPage.BidDetailsProperty_Activity;
import com.cashmyproperty.app.View.Response.BidPropertyDatum;
import com.cashmyproperty.app.View.Response.Image;
import com.cashmyproperty.app.View.Response.SoldPropertyDatum;

import java.util.List;

public class BidAdapter_Recycler extends RecyclerView.Adapter<BidAdapter_Recycler.ViewHolder> {


    Context context;
    List<BidPropertyDatum> bidPropertyData;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";



    public BidAdapter_Recycler(Context context, List<BidPropertyDatum> bidPropertyData){
        this.context=context;
        this.bidPropertyData = bidPropertyData;


    }



    @Override
    public BidAdapter_Recycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_bid,parent,false);
        return new BidAdapter_Recycler.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BidAdapter_Recycler.ViewHolder holder, int position) {

        Glide.with(context).load(image_base_url+bidPropertyData.get(position).getImages().get(0).getPropertyImage()).into(holder.imageView);
        holder.propertyid.setText("Property ID #"+bidPropertyData.get(position).getPropertySequenceId());
        holder.area.setText("Area- "+String.valueOf(bidPropertyData.get(position).getTotalArea()+" "+bidPropertyData.get(position).getAreaType()));
        holder.location.setText(String.valueOf(bidPropertyData.get(position).getAddress()));
        holder.txt_startamt.setText(bidPropertyData.get(position).getStartAmount());
        holder.current_bid.setText("AED "+bidPropertyData.get(position).getCurrentBiding());
        holder.img_des.setText(bidPropertyData.get(position).getPropertyName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, BidDetailsProperty_Activity.class);
                intent.putExtra("propertyid",String.valueOf(bidPropertyData.get(position).getPropertyId()));
                context.startActivity(intent);
            }
        });

        holder.viewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, BidDetailsProperty_Activity.class);
                intent.putExtra("propertyid",String.valueOf(bidPropertyData.get(position).getPropertyId()));
                context.startActivity(intent);
            }
        });

    }




    @Override
    public int getItemCount() {
        return bidPropertyData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        Button viewdetails;
        RelativeLayout layout;
        TextView propertyid,area,location,txt_startamt,current_bid,img_des;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            viewdetails=itemView.findViewById(R.id.viewdetails);
            propertyid=itemView.findViewById(R.id.propertyid);
            area=itemView.findViewById(R.id.area);
            layout=itemView.findViewById(R.id.layout);
            img_des=itemView.findViewById(R.id.img_des);
            location=itemView.findViewById(R.id.location);
             txt_startamt=itemView.findViewById(R.id.txt_startamt);
            current_bid=itemView.findViewById(R.id.current_bid);
            txt_startamt.setVisibility(View.GONE);

        }
    }


}

