package com.cashmyproperty.app.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Response.KindOfProperty;
import com.cashmyproperty.app.View.Response.PropertyType;

import java.util.ArrayList;
import java.util.List;

public class PropertyTypeAdapter extends RecyclerView.Adapter<PropertyTypeAdapter.ViewHolder> {

    Context context;
    ArrayList<PropertyType> propertyType;
    String sub_catid;
    int check_pos=0;
    public OnclickListener onclickListener;


    public PropertyTypeAdapter(Context context, ArrayList<PropertyType> propertyType,OnclickListener onclickListener){
        this.context=context;
        this.propertyType=propertyType;
        this.onclickListener=onclickListener;

    }



    @Override
    public PropertyTypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.propertytype_adapter,parent,false);
        return new PropertyTypeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyTypeAdapter.ViewHolder holder, int position) {


        holder.txt_propertytype.setText(propertyType.get(position).getSubCategoryName());

        if(position==0){
            sub_catid= String.valueOf(propertyType.get(position).getSubCategoryId());
           holder.txt_propertytype.setBackgroundResource(R.drawable.rounded_edittext_border);
            onclickListener.onClick(sub_catid);
        }

        if(check_pos==position){
            holder.txt_propertytype.setBackgroundResource(R.drawable.rounded_edittext_border);
            sub_catid= String.valueOf(propertyType.get(position).getSubCategoryId());
            onclickListener.onClick(sub_catid);

        }
        else{
            holder.txt_propertytype.setBackgroundResource(R.drawable.rounded_edittext);
        }



    }

    @Override
    public int getItemCount() {
        return propertyType.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_propertytype;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_propertytype = itemView.findViewById(R.id.txt_propertytype);
            txt_propertytype.setOnClickListener(new View.OnClickListener() {
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

        public void onClick(String string);

    }
}
