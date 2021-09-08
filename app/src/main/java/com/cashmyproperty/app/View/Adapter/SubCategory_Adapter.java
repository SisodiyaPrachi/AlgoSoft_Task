package com.cashmyproperty.app.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Response.SubCategoryDatum;

import java.util.List;

public class SubCategory_Adapter extends RecyclerView.Adapter<SubCategory_Adapter.ViewHolder> {

    Context context;
    List<SubCategoryDatum> subCategoryData;
    OnclickListener onclickListener;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";


    public SubCategory_Adapter(Context context, List<SubCategoryDatum> subCategoryData,OnclickListener onclickListener){
        this.context=context;
        this.subCategoryData = subCategoryData;
        this.onclickListener=onclickListener;

    }



    @Override
    public SubCategory_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_resedential,parent,false);
        return new SubCategory_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategory_Adapter.ViewHolder holder, int position) {
        Glide.with(context).load(image_base_url+subCategoryData.get(position).getSubCategoryAppImage()).into(holder.cat_img);
        holder.property_name.setText(subCategoryData.get(position).getSubCategoryName());
        holder.propertycount.setText((String.valueOf(subCategoryData.get(position).getPropertyCount()))+" Properties");

        holder.cat_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickListener.onClick(subCategoryData.get(position).getSubCategoryId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return subCategoryData.size();
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

        public void onClick(String id);

    }
}
