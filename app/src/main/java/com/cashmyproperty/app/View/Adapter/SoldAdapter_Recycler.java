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
import com.cashmyproperty.app.View.DetailPage.SoldDetailsProperty_Activity;
import com.cashmyproperty.app.View.Response.Image;
import com.cashmyproperty.app.View.Response.PropertyDatum;
import com.cashmyproperty.app.View.Response.SoldPropertyDatum;

import java.util.List;

public class SoldAdapter_Recycler extends RecyclerView.Adapter<SoldAdapter_Recycler.ViewHolder> {


    Context context;
    List<SoldPropertyDatum> soldPropertyData;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";



    public SoldAdapter_Recycler(Context context, List<SoldPropertyDatum> soldPropertyData){
        this.context=context;
        this.soldPropertyData = soldPropertyData;

    }



    @Override
    public SoldAdapter_Recycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_soldproperty,parent,false);
        return new SoldAdapter_Recycler.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SoldAdapter_Recycler.ViewHolder holder, int position) {

        Glide.with(context).load(image_base_url+soldPropertyData.get(0).getImages().get(0).getPropertyImage()).into(holder.imageView);
        holder.propertyid.setText("Property ID #"+soldPropertyData.get(position).getPropertySequenceId());
        holder.area.setText("Area- "+String.valueOf(soldPropertyData.get(position).getTotalArea())+" "+soldPropertyData.get(position).getAreaType());
        holder.location.setText(String.valueOf(soldPropertyData.get(position).getAddress()));
        holder.txt_lastbid.setText(soldPropertyData.get(position).getLastBid());
        holder.img_des.setText(String.valueOf(soldPropertyData.get(position).getPropertyName()));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, SoldDetailsProperty_Activity.class);
                intent.putExtra("propertyid",String.valueOf(soldPropertyData.get(position).getPropertyId()));
                context.startActivity(intent);
            }
        });

        holder.btn_viewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, SoldDetailsProperty_Activity.class);
                intent.putExtra("propertyid",String.valueOf(soldPropertyData.get(position).getPropertyId()));
                context.startActivity(intent);
            }
        });

    }




    @Override
    public int getItemCount() {
        return soldPropertyData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        Button btn_viewdetails;
        RelativeLayout layout;
        TextView propertyid,area,location,txt_lastbid,img_des;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            btn_viewdetails=itemView.findViewById(R.id.btn_viewdetails);
            propertyid=itemView.findViewById(R.id.propertyid);
            area=itemView.findViewById(R.id.area);
            layout=itemView.findViewById(R.id.layout);
            location=itemView.findViewById(R.id.location);
            img_des=itemView.findViewById(R.id.img_des);
            txt_lastbid=itemView.findViewById(R.id.txt_lastbid);

        }
    }


}
