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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.DetailPage.BidDetailsProperty_Activity;
import com.cashmyproperty.app.View.DetailPage.DetailsActivity;
import com.cashmyproperty.app.View.Response.Image;
import com.cashmyproperty.app.View.Response.PropertyDatum;

import java.util.List;

public class DatabyRecycler_Adapter extends RecyclerView.Adapter<DatabyRecycler_Adapter.ViewHolder> {


    Context context;
    List<PropertyDatum> restPropertyData;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";


    public DatabyRecycler_Adapter(Context context, List<PropertyDatum> restPropertyData) {
        this.context = context;
        this.restPropertyData = restPropertyData;


    }


    @Override
    public DatabyRecycler_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_villa, parent, false);
        return new DatabyRecycler_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatabyRecycler_Adapter.ViewHolder holder, int position) {

        Glide.with(context).load(image_base_url+restPropertyData.get(0).getImages().get(0).getPropertyImage()).into(holder.cat_img);
        holder.area.setText("Area- " + String.valueOf(restPropertyData.get(position).getTotalArea()+" "+
                restPropertyData.get(position).getAreaType()));
        holder.propertyid.setText("Property ID #" + restPropertyData.get(position).getPropertySequenceId());
        holder.location.setText(restPropertyData.get(position).getAddress());
        holder.img_des.setText(restPropertyData.get(position).getPropertyName());
        // holder.txt_startarea.setText("AED "+String.valueOf(restPropertyData.get(position).getStartAmount()));
        holder.current_buil.setText("AED " + String.valueOf(restPropertyData.get(position).getCurrentBiding()));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("propertyid", String.valueOf(restPropertyData.get(position).getPropertyId()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

        holder.viewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, BidDetailsProperty_Activity.class);
                intent.putExtra("propertyid",String.valueOf(restPropertyData.get(position).getPropertyId()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return restPropertyData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cat_img;
        TextView img_des, area, location, txt_startarea, current_buil, propertyid;
        RelativeLayout layout;
        Button viewdetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cat_img = itemView.findViewById(R.id.imageView);
            area = itemView.findViewById(R.id.area);
            location = itemView.findViewById(R.id.location);
            layout=itemView.findViewById(R.id.layout);
            //txt_startarea = itemView.findViewById(R.id.txt_startamt);
            current_buil = itemView.findViewById(R.id.current_bid);
            img_des = itemView.findViewById(R.id.img_des);
            propertyid = itemView.findViewById(R.id.propertyid);
            viewdetails = itemView.findViewById(R.id.viewdetails);


        }
    }
}
