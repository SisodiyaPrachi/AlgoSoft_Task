package com.cashmyproperty.app.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Response.KindOfProperty;
import com.cashmyproperty.app.View.Response.LocationDatum;
import com.cashmyproperty.app.View.Response.PropertyHolder;
import com.cashmyproperty.app.View.Response.PropertyType;

import java.util.ArrayList;
import java.util.List;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder> {

    Context context;
    String property_holderid,property_holdername;
    List<PropertyHolder> propertyHolders;
    public OnclickListener onclickListener;
    int check_pos=0;


    public PropertyAdapter(Context context, List<PropertyHolder> propertyHolders, OnclickListener onclickListener){
        this.context=context;
        this.propertyHolders=propertyHolders;
        this.onclickListener=onclickListener;

    }



    @Override
    public PropertyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.propertyholder_adapter,parent,false);
        return new PropertyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyAdapter.ViewHolder holder, int position) {
        holder.txt_holder.setText(propertyHolders.get(position).getPropertyHolderName());


        if(position==0){
            property_holderid= String.valueOf(propertyHolders.get(position).getPropertyHolderId());
            property_holdername=propertyHolders.get(position).getPropertyHolderName();
            holder.txt_holder.setBackgroundResource(R.drawable.rounded_edittext_border);
            onclickListener.onClick(property_holderid,property_holdername);
        }

        if(check_pos==position){
            holder.txt_holder.setBackgroundResource(R.drawable.rounded_edittext_border);
            property_holderid= String.valueOf(propertyHolders.get(position).getPropertyHolderId());
            property_holdername=propertyHolders.get(position).getPropertyHolderName();
            onclickListener.onClick(property_holderid,property_holdername);

        }
        else{
            holder.txt_holder.setBackgroundResource(R.drawable.rounded_edittext);
        }



    }

    @Override
    public int getItemCount() {
        return propertyHolders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_holder;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_holder = itemView.findViewById(R.id.txt_holder);
            txt_holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    set_selection(getAdapterPosition());
                }
            });



        }
    }

    private void set_selection(int adapterPosition) {
        if(adapterPosition==RecyclerView.NO_POSITION)
            return;

        notifyItemChanged(check_pos);
        check_pos=adapterPosition;
        notifyItemChanged(check_pos);
    }

    public interface OnclickListener{

        public void onClick(String string,String name);

    }
}
