package com.cashmyproperty.app.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.DetailPage.SellerProp_DetailsActivity;
import com.cashmyproperty.app.View.Response.BidPropertyDatum;
import com.cashmyproperty.app.View.Response.Image;
import com.cashmyproperty.app.View.Response.MypropertyDatum;

import java.util.List;

public class SellerProperty_Recycler extends RecyclerView.Adapter<SellerProperty_Recycler.ViewHolder> {


    Context context;
    List<MypropertyDatum> mypropertyData;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";



    public SellerProperty_Recycler(Context context, List<MypropertyDatum> mypropertyData){
        this.context=context;
        this.mypropertyData = mypropertyData;


    }



    @Override
    public SellerProperty_Recycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_propertyseller,parent,false);
        return new SellerProperty_Recycler.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SellerProperty_Recycler.ViewHolder holder, int position) {

        String prop_ver=mypropertyData.get(position).getPropertyVerified();

        Glide.with(context).load(image_base_url+mypropertyData.get(0).getImages().get(0).getPropertyImage()).into(holder.imageView);
        if(prop_ver.equalsIgnoreCase("IR") || prop_ver.equalsIgnoreCase("AR")){
            holder.img_verify.setVisibility(View.VISIBLE);
            holder.img_verify.setBackgroundResource(R.drawable.reject);
        }
        else if(prop_ver.equalsIgnoreCase("IV") || prop_ver.equalsIgnoreCase("AV")){
            holder.img_verify.setVisibility(View.VISIBLE);
            holder.img_verify.setBackgroundResource(R.drawable.verified);
        }
        else {
            holder.img_verify.setVisibility(View.GONE);
        }

        holder.prop_id.setText("Property ID #"+String.valueOf(mypropertyData.get(position).getPropertySequenceId()));
        holder.curr_loc.setText(String.valueOf(mypropertyData.get(position).getAddress()));
        holder.area.setText("Area- "+String.valueOf(mypropertyData.get(position).getTotalArea())+" "+mypropertyData.get(position).getAreaType());
        holder.txt_name.setText(String.valueOf(mypropertyData.get(position).getPropertyName()));

        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, SellerProp_DetailsActivity.class);
                intent.putExtra("propertyid", String.valueOf(mypropertyData.get(position).getPropertyId()));
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return mypropertyData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        ImageView imageView,img_verify;
        TextView prop_id,curr_loc,area,txt_name;
        CardView card_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView);
            img_verify=itemView.findViewById(R.id.img_verify);
            card_view=itemView.findViewById(R.id.card_view);
            prop_id=itemView.findViewById(R.id.prop_id);
            curr_loc=itemView.findViewById(R.id.curr_loc);
            area=itemView.findViewById(R.id.area);
            txt_name=itemView.findViewById(R.id.txt_name);

        }
    }


}

