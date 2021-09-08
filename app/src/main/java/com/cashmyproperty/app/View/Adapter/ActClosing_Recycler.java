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
import com.cashmyproperty.app.View.DetailPage.DetailsActivity;
import com.cashmyproperty.app.View.Response.AuctionclosingProperty;
import com.cashmyproperty.app.View.Response.Image__1;

import java.util.List;

public class ActClosing_Recycler extends RecyclerView.Adapter<ActClosing_Recycler.ViewHolder> {


    Context context;
    List<AuctionclosingProperty> auctionclosingProperties;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";



    public ActClosing_Recycler(Context context, List<AuctionclosingProperty> auctionclosingProperties){
        this.context=context;
        this.auctionclosingProperties = auctionclosingProperties;


    }



    @Override
    public ActClosing_Recycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_villa,parent,false);
        return new ActClosing_Recycler.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActClosing_Recycler.ViewHolder holder, int position) {

        Glide.with(context).load(image_base_url+auctionclosingProperties.get(position).getImages().get(0).getPropertyImage()).into(holder.cat_img);
        holder.area.setText("Area- "+String.valueOf(auctionclosingProperties.get(position).getTotalArea()) +" "+auctionclosingProperties.get(position).getAreaType());
        holder.propertyid.setText("Property ID #"+auctionclosingProperties.get(position).getPropertySequenceId());
        holder.img_des.setText(auctionclosingProperties.get(position).getPropertyName());
        holder.location.setText(auctionclosingProperties.get(position).getAddress());
        // holder.txt_startarea.setText(auctionclosingProperties.get(position).getStartAmount());
        holder.current_buil.setText(auctionclosingProperties.get(position).getCurrentBidAmount());

        holder.viewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("propertyid", String.valueOf(auctionclosingProperties.get(position).getPropertyId()));
                context.startActivity(intent);

            }
        });

             holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, DetailsActivity.class);
                    intent.putExtra("propertyid",String.valueOf(auctionclosingProperties.get(position).getPropertyId()));
                    context.startActivity(intent);
                }
            });


    }




    @Override
    public int getItemCount() {
        return auctionclosingProperties.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView cat_img;
        TextView img_des,area,location,txt_startarea,current_buil,propertyid;
        Button viewdetails;
        RelativeLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cat_img = itemView.findViewById(R.id.imageView);
            area = itemView.findViewById(R.id.area);
            location = itemView.findViewById(R.id.location);
            layout=itemView.findViewById(R.id.layout);
            // txt_startarea = itemView.findViewById(R.id.txt_startamt);
            current_buil = itemView.findViewById(R.id.current_bid);
            img_des = itemView.findViewById(R.id.img_des);
            propertyid=itemView.findViewById(R.id.propertyid);
            viewdetails=itemView.findViewById(R.id.viewdetails);

        }
    }


}

