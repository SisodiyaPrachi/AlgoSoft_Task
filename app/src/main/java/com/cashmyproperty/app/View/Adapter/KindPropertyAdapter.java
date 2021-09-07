package com.cashmyproperty.app.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Response.KindOfProperty;
import com.cashmyproperty.app.View.Response.PropertyType;

import java.util.ArrayList;
import java.util.List;

public class KindPropertyAdapter extends RecyclerView.Adapter<KindPropertyAdapter.ViewHolder> {

   Context context;
    String cat_id;
    String cat_name;
    List<KindOfProperty> kindOfProperties;
    ArrayList<PropertyType> propertyType;
    OnclickListener onclickListener;
    int check_pos=0;


    public KindPropertyAdapter(Context context,List<KindOfProperty> kindOfProperties, OnclickListener onclickListener){
      this.context=context;
        this.kindOfProperties=kindOfProperties;
        this.onclickListener=onclickListener;

    }



    @Override
    public KindPropertyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.kindproperty_adapter,parent,false);
        return new KindPropertyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KindPropertyAdapter.ViewHolder holder, int position) {

       holder.txt_type.setText(kindOfProperties.get(position).getCategoryName());
      propertyType = kindOfProperties.get(position).getPropertyType();

       if(position==0){
            cat_id= String.valueOf(kindOfProperties.get(position).getCategoryId());
             cat_name=kindOfProperties.get(position).getCategoryName();
            holder.txt_type.setBackgroundResource(R.drawable.rounded_edittext_border);
               onclickListener.onClick(propertyType,cat_id,cat_name);
           }

        if(check_pos==position){
            holder.txt_type.setBackgroundResource(R.drawable.rounded_edittext_border);
            cat_id= String.valueOf(kindOfProperties.get(position).getCategoryId());
            cat_name=kindOfProperties.get(position).getCategoryName();
            onclickListener.onClick(propertyType,cat_id,cat_name);
        }
        else{
            holder.txt_type.setBackgroundResource(R.drawable.rounded_edittext);
        }


    }

    @Override
    public int getItemCount() {
        return kindOfProperties.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_type;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_type = itemView.findViewById(R.id.txt_type);

            txt_type.setOnClickListener(new View.OnClickListener() {
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

       public void onClick(ArrayList<PropertyType> propertyType,String string,String cat_name);

    }

}
