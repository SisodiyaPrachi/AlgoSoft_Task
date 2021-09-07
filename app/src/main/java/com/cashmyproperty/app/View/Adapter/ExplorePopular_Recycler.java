package com.cashmyproperty.app.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.DetailPage.DetailsActivity;
import com.cashmyproperty.app.View.Response.ExplorepopularArea;
import com.cashmyproperty.app.View.Response.Image__1;
import com.cashmyproperty.app.View.Response.Image__3;
import com.cashmyproperty.app.View.Response.RestPropertyDatum;

import java.util.List;

public class ExplorePopular_Recycler extends RecyclerView.Adapter<ExplorePopular_Recycler.ViewHolder> {

    Context context;
    List<ExplorepopularArea> explorepopularAreas;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";


    public ExplorePopular_Recycler(Context context,  List<ExplorepopularArea> explorepopularAreas) {
        this.context = context;
        this.explorepopularAreas = explorepopularAreas;
    }


    @Override
    public ExplorePopular_Recycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_buyrent, parent, false);
        return new ExplorePopular_Recycler.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExplorePopular_Recycler.ViewHolder holder, int position) {

        Glide.with(context).load(image_base_url+explorepopularAreas.get(0).getImages().get(0).getPropertyImage()).into(holder.imageView);
        holder.txt_img.setText(explorepopularAreas.get(position).getLocationName());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailsActivity.class);
                intent.putExtra("propertyid",String.valueOf(explorepopularAreas.get(position).getPropertyId()));
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return explorepopularAreas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txt_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            txt_img = itemView.findViewById(R.id.txt_img);

        }
    }
}
