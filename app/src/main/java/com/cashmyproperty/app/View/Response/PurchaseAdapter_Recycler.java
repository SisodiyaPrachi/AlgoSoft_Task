package com.cashmyproperty.app.View.Response;

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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.DetailPage.BidDetailsProperty_Activity;
import com.cashmyproperty.app.View.DetailPage.PurchaseDetailsProperty_Activity;

import java.util.List;

public class PurchaseAdapter_Recycler extends RecyclerView.Adapter<PurchaseAdapter_Recycler.ViewHolder> {


    Context context;
    List<PurchasePropertyDatum> purchasePropertyData;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";



    public PurchaseAdapter_Recycler(Context context, List<PurchasePropertyDatum> purchasePropertyData){
        this.context=context;
        this.purchasePropertyData = purchasePropertyData;


    }



    @Override
    public PurchaseAdapter_Recycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_purchase,parent,false);
        return new PurchaseAdapter_Recycler.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseAdapter_Recycler.ViewHolder holder, int position) {

        Glide.with(context).load(image_base_url+purchasePropertyData.get(position).getImages().get(0).getPropertyImage()).into(holder.imageView);
        holder.propertyid.setText("Property ID #"+purchasePropertyData.get(position).getPropertySequenceId());
        holder.area.setText("Area- "+String.valueOf(purchasePropertyData.get(position).getTotalArea())+" "+purchasePropertyData.get(position).getAreaType());
        holder.location.setText(String.valueOf(purchasePropertyData.get(position).getAddress()));
        holder.txt_lastamt.setText(purchasePropertyData.get(position).getLastAmount());
        holder.img_des.setText(purchasePropertyData.get(position).getPropertyName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, PurchaseDetailsProperty_Activity.class);
                intent.putExtra("propertyid",String.valueOf(purchasePropertyData.get(position).getPropertyId()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.btn_viewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, PurchaseDetailsProperty_Activity.class);
                intent.putExtra("propertyid",String.valueOf(purchasePropertyData.get(position).getPropertyId()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return purchasePropertyData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        Button btn_viewdetails;
        RelativeLayout layout;
        TextView propertyid,area,location,txt_lastamt,img_des;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            btn_viewdetails=itemView.findViewById(R.id.btn_viewdetails);
            propertyid=itemView.findViewById(R.id.propertyid);
            area=itemView.findViewById(R.id.area);
            layout=itemView.findViewById(R.id.layout);
            location=itemView.findViewById(R.id.location);
            img_des=itemView.findViewById(R.id.img_des);
            txt_lastamt=itemView.findViewById(R.id.txt_lastamt);
        }
    }


}


