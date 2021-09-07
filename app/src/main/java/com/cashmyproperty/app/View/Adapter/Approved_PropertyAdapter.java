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
import com.cashmyproperty.app.View.Response.Image__1;
import com.cashmyproperty.app.View.Response.RestPropertyDatum;

import java.util.List;

import static android.media.CamcorderProfile.get;

public class Approved_PropertyAdapter extends RecyclerView.Adapter<Approved_PropertyAdapter.ViewHolder> {

    Context context;
    List<RestPropertyDatum> restPropertyData;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";


    public Approved_PropertyAdapter(Context context,  List<RestPropertyDatum> restPropertyData) {
        this.context = context;
        this.restPropertyData = restPropertyData;
    }


    @Override
    public Approved_PropertyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_home, parent, false);
        return new Approved_PropertyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Approved_PropertyAdapter.ViewHolder holder, int position) {


        String address=  restPropertyData.get(position).getAddress();

        holder.name.setText(restPropertyData.get(position).getPropertyName());
        holder.bid.setText("Last Bid Amount- "+String.valueOf(restPropertyData.get(position).getLastBid()));
        String upperString = address.substring(0, 1).toUpperCase() + address.substring(1).toLowerCase();
        holder.locat.setText(address);
        holder.prop_id.setText(" Property ID # "+restPropertyData.get(position).getPropertySequenceId());

        Glide.with(context).load(image_base_url+restPropertyData.get(0).getImages().get(0).getPropertyImage()).into(holder.cat_img);
        holder.view_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailsActivity.class);
                intent.putExtra("propertyid",String.valueOf(restPropertyData.get(position).getPropertyId()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailsActivity.class);
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
        Button view_details;
        RelativeLayout layout;
        TextView name,locat,bid,prop_id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cat_img = itemView.findViewById(R.id.cat_img);
            view_details=itemView.findViewById(R.id.view_details);
            name=itemView.findViewById(R.id.name);
            layout=itemView.findViewById(R.id.layout);
            locat=itemView.findViewById(R.id.locat);
            bid=itemView.findViewById(R.id.bid);
            prop_id=itemView.findViewById(R.id.prop_id);

        }
    }
}

