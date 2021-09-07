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
import com.cashmyproperty.app.View.Response.BidPropertyDatum;
import com.cashmyproperty.app.View.Response.Image;
import com.cashmyproperty.app.View.Response.PropertywishlistDatum;

import java.util.List;

public class FavAdapter_Recycler extends RecyclerView.Adapter<FavAdapter_Recycler.ViewHolder> {


    Context context;
    List<PropertywishlistDatum> propertywishlistData;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";



    public FavAdapter_Recycler(Context context, List<PropertywishlistDatum> propertywishlistData){
        this.context=context;
        this.propertywishlistData = propertywishlistData;


    }



    @Override
    public FavAdapter_Recycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_bid,parent,false);
        return new FavAdapter_Recycler.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapter_Recycler.ViewHolder holder, int position) {


        Glide.with(context).load(image_base_url+propertywishlistData.get(0).getImages().get(0).getPropertyImage()).into(holder.imageView);
        holder.propertyid.setText("Property ID # "+propertywishlistData.get(position).getPropertySequenceId());
        holder.area.setText("Area- "+String.valueOf(propertywishlistData.get(position).getTotalArea())+" "+propertywishlistData.get(position).getAreaType());
        holder.location.setText(String.valueOf(propertywishlistData.get(position).getAddress()));
        //holder.txt_startamt.setText(propertywishlistData.get(position).getStartAmount());
        holder.current_bid.setText(String.valueOf(propertywishlistData.get(position).getCurrentBidAmount()));
        holder.img_des.setText(propertywishlistData.get(position).getPropertyName());
        holder.viewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailsActivity.class);
                intent.putExtra("propertyid",String.valueOf(propertywishlistData.get(position).getPropertyId()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailsActivity.class);
                intent.putExtra("propertyid",String.valueOf(propertywishlistData.get(position).getPropertyId()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });



    }




    @Override
    public int getItemCount() {
        return propertywishlistData.size();
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
            // txt_startamt=itemView.findViewById(R.id.txt_startamt);
            current_bid=itemView.findViewById(R.id.current_bid);

        }
    }


}
