package com.cashmyproperty.app.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Response.Image;
import com.cashmyproperty.app.View.Response.PropertyDatum;

import java.util.List;

public class SearchRecycler_Adapter extends RecyclerView.Adapter<SearchRecycler_Adapter.ViewHolder> {


    Context context;
    List<PropertyDatum> propertyData;




    public SearchRecycler_Adapter(Context context, List<PropertyDatum> propertyData){
        this.context=context;
        this.propertyData = propertyData;


    }



    @Override
    public SearchRecycler_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_recsearch,parent,false);
        return new SearchRecycler_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecycler_Adapter.ViewHolder holder, int position) {
        List<Image> images= propertyData.get(position).getImages();
        String property_type=propertyData.get(position).getPropertyTypeName();
        String description=propertyData.get(position).getDescription();

        DataAdapter dataAdapter=new DataAdapter(context,images,property_type,description);
        holder.rec_search.setAdapter(dataAdapter);



    }




    @Override
    public int getItemCount() {
        return propertyData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RecyclerView rec_search;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rec_search=itemView.findViewById(R.id.rec_search);
            rec_search.setLayoutManager(new LinearLayoutManager(context));

        }
    }


}