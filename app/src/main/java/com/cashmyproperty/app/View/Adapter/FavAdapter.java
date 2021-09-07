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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.DetailPage.BidDetailsProperty_Activity;
import com.cashmyproperty.app.View.DetailPage.DetailsActivity;
import com.cashmyproperty.app.View.Response.Image;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    Context context;
    List<Image> images;
    String propid,area,areatype,address,curr_biding,prop_type,prop_seqid;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";

    public FavAdapter(Context context,  List<Image> images,String propid,String area,String areatype,
                      String address,String curr_biding,String prop_type,String prop_seqid){
        this.context=context;
        this.images = images;
        this.propid=propid;
        this.area=area;
        this.areatype=areatype;
        this.address=address;
       // this.amt=amt;
        this.curr_biding=curr_biding;
        this.prop_type=prop_type;
        this.prop_seqid=prop_seqid;
    }



    @Override
    public FavAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_bid,parent,false);
        return new FavAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapter.ViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return images.size();
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
