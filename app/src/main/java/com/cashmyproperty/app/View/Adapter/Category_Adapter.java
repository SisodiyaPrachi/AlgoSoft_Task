package com.cashmyproperty.app.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Response.CategoryDatum;
import com.cashmyproperty.app.View.Response.PropertyType;


import java.util.ArrayList;
import java.util.List;

   public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.ViewHolder> {

   Context context;
    List<CategoryDatum> categoryData;
    OnclickListener onclickListener;
       String image_base_url = "https://apkconnectlab.com/cmpdtest/";


    public Category_Adapter(Context context,  List<CategoryDatum> categoryData,OnclickListener onclickListener){
        this.context=context;
        this.categoryData = categoryData;
        this.onclickListener=onclickListener;
    }



    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_category,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Glide.with(context).load(image_base_url+categoryData.get(position).getCategoryAppImage()).into(holder.cat_img);
        holder.property_name.setText(categoryData.get(position).getCategoryName());
        holder.propertycount.setText((String.valueOf(categoryData.get(position).getPropertyCount()))+"+ Properties");

        holder.cat_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickListener.onClick(categoryData.get(position).getCategoryName(),categoryData.get(position).getCategoryId());
            }
        });


        

    }

    @Override
    public int getItemCount() {
        return categoryData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView cat_img;
        TextView property_name,propertycount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cat_img = itemView.findViewById(R.id.cat_img);
            propertycount=itemView.findViewById(R.id.propertycount);
            property_name=itemView.findViewById(R.id.property_name);

        }
    }

       public interface OnclickListener{

           public void onClick(String categoryName,String categoryid);

       }
}
