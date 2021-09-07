package com.cashmyproperty.app.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Response.Image;


import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private Context context;
    List<Image> images;
    String property_type,description;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";

    public DataAdapter(Context context, List<Image> images,String property_type,String description) {
        this.context = context;
        this.images=images;
        this.property_type=property_type;
        this.description=description;

    }


    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_home, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.text_imgName.setText(property_type);
        viewHolder.description.setText(description);
        Glide.with(context).load(image_base_url+images.get(i).getPropertyImage()).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }





    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView text_imgName,description;
        public ViewHolder(View view) {
            super(view);

            text_imgName = view.findViewById(R.id.text_imgName);
            imageView =  view.findViewById(R.id.imageView);
            description=view.findViewById(R.id.description);
        }
    }

}
