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
import com.cashmyproperty.app.View.Response.Image__2;
import com.cashmyproperty.app.View.Response.RecommendedProperty;

import java.util.List;

public class Recomm_Recycler extends RecyclerView.Adapter<Recomm_Recycler.ViewHolder> {


    Context context;
    List<RecommendedProperty> recommendedProperties;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";



    public Recomm_Recycler(Context context, List<RecommendedProperty> recommendedProperties){
        this.context=context;
        this.recommendedProperties = recommendedProperties;


    }



    @Override
    public Recomm_Recycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_home,parent,false);
        return new Recomm_Recycler.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Recomm_Recycler.ViewHolder holder, int position) {

        Glide.with(context).load(image_base_url+recommendedProperties.get(0).getImages().get(0).getPropertyImage()).into(holder.cat_img);
        holder.prop_id.setText("Property ID # "+recommendedProperties.get(position).getPropertySequenceId());
        holder.name.setText(recommendedProperties.get(position).getPropertyName());
        holder.locat.setText(String.valueOf(recommendedProperties.get(position).getAddress()));
        holder.bid.setText("Last Bid Amount-"+recommendedProperties.get(position).getLastBidAmount());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailsActivity.class);
                intent.putExtra("propertyid",String.valueOf(recommendedProperties.get(position).getPropertyId()));
                context.startActivity(intent);
            }
        });

        holder.view_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailsActivity.class);
                intent.putExtra("propertyid",String.valueOf(recommendedProperties.get(position).getPropertyId()));
                context.startActivity(intent);
            }
        });


    }




    @Override
    public int getItemCount() {
        return recommendedProperties.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView cat_img;
        Button view_details;
        RelativeLayout layout;
        TextView name,locat,bid,prop_id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cat_img = itemView.findViewById(R.id.cat_img);
            view_details=itemView.findViewById(R.id.view_details);
            name=itemView.findViewById(R.id.name);
            locat=itemView.findViewById(R.id.locat);
            layout=itemView.findViewById(R.id.layout);
            bid=itemView.findViewById(R.id.bid);
            prop_id=itemView.findViewById(R.id.prop_id);

        }
    }


}

