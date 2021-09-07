package com.cashmyproperty.app.View.Home;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Adapter.DatabyRecycler_Adapter;
import com.cashmyproperty.app.View.Category.DataByCategory_Activity;
import com.cashmyproperty.app.View.Response.DatabycatResult;
import com.cashmyproperty.app.View.Response.PropertyDatum;
import com.cashmyproperty.app.View.Response.Result;
import com.cashmyproperty.app.View.Response.SubCategoryData;
import com.cashmyproperty.app.View.ViewModel.DataViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Home_Agent extends Fragment {

    View view;
    RecyclerView rec_villa;
    DatabyRecycler_Adapter databyRecycler_adapter;
    Toolbar tool_bar;
    DataViewModel dataViewModel;
    String location_id="";
    private  boolean ascending= true;
    private ProgressDialog progress;
    ArrayList<String> location;
    List<PropertyDatum> propertyData;
    TextView price,location_place,time,bids;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_villa, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar();
        dataViewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);

        rec_villa = view.findViewById(R.id.rec_category);
        price=view.findViewById(R.id.price);
        location_place=view.findViewById(R.id.location_place);
        time=view.findViewById(R.id.time);
        bids=view.findViewById(R.id.bids);
        tool_bar = view.findViewById(R.id.tool_bar);
        tool_bar.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);

        rec_villa.setLayoutManager(new LinearLayoutManager(getActivity()));

        tool_bar.setVisibility(View.GONE);

      /*  tool_bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });*/


        getcatData();

        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showprogressbar(true);

                sortPrice(ascending);
                ascending=!ascending;

                showprogressbar(false);
            }
        });

        bids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showprogressbar(true);

                sortBids(ascending);
                ascending=!ascending;

                showprogressbar(false);
            }
        });

       /* time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showprogressbar(true);

                sortTime(ascending);
                ascending=!ascending;

                showprogressbar(false);
            }
        });*/

        location_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showprogressbar(true);

                sortData(ascending);
                ascending=!ascending;


                showprogressbar(false);

            }
        });

        return view;

    }

    private void sortBids(boolean ascending) {
        if(ascending){
            Collections.sort(propertyData, new Comparator<PropertyDatum>() {
                @Override
                public int compare(PropertyDatum propertyDatum, PropertyDatum t1) {
                    return propertyDatum.getCurrentBiding().compareTo(t1.getCurrentBiding());
                }
            });
        }
        else{
            Collections.reverse(propertyData);
        }

        databyRecycler_adapter = new DatabyRecycler_Adapter(getActivity(), propertyData);
        rec_villa.setAdapter(databyRecycler_adapter);
        databyRecycler_adapter.notifyDataSetChanged();
    }

    private void sortTime(boolean ascending) {
        if(ascending){
            Collections.sort(propertyData, new Comparator<PropertyDatum>() {
                @Override
                public int compare(PropertyDatum propertyDatum, PropertyDatum t1) {
                    return propertyDatum.getCurrentBiding().compareTo(t1.getCurrentBiding());
                }
            });
        }
        else{
            Collections.reverse(propertyData);
        }

        databyRecycler_adapter = new DatabyRecycler_Adapter(getActivity(), propertyData);
        rec_villa.setAdapter(databyRecycler_adapter);
        databyRecycler_adapter.notifyDataSetChanged();
    }

    private void sortPrice(boolean ascending) {
        if(ascending){
            Collections.sort(propertyData, new Comparator<PropertyDatum>() {
                @Override
                public int compare(PropertyDatum propertyDatum, PropertyDatum t1) {
                    return propertyDatum.getCurrentBiding().compareTo(t1.getCurrentBiding());
                }
            });
        }
        else{
            Collections.reverse(propertyData);
        }

        databyRecycler_adapter = new DatabyRecycler_Adapter(getActivity(), propertyData);
        rec_villa.setAdapter(databyRecycler_adapter);
        databyRecycler_adapter.notifyDataSetChanged();
    }

    private void sortData(boolean ascending) {
        if(ascending){
            Collections.sort(propertyData, new Comparator<PropertyDatum>() {
                @Override
                public int compare(PropertyDatum propertyDatum, PropertyDatum t1) {
                    //Toast.makeText(getActivity(),t1.getAddress(),Toast.LENGTH_SHORT).show();
                    return String.valueOf(propertyDatum.getAddress()).compareToIgnoreCase(String.valueOf(t1.getAddress()));

                }
            });
        }
        else{
            Collections.reverse(propertyData);
        }

        databyRecycler_adapter = new DatabyRecycler_Adapter(getActivity(), propertyData);
        rec_villa.setAdapter(databyRecycler_adapter);
        databyRecycler_adapter.notifyDataSetChanged();
    }


    private void getcatData() {

        showprogressbar(true);

        dataViewModel.getbuyer_homepage(getActivity()).observe(getActivity(), new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                //hideLoader();
                if (result != null) {

                    propertyData=result.getPropertyData();
                   // tool_bar.setTitle(sub_categoryData.getSubCategoryName());

                    if (propertyData.size()>0) {
                        showprogressbar(false);

                            /*Collections.sort(propertyData, new Comparator<PropertyDatum>() {
                                @Override
                                public int compare(PropertyDatum propertyDatum, PropertyDatum t1) {
                                    return propertyDatum.getCurrentBiding().compareTo(t1.getCurrentBiding());
                                }
                            });*/
                        databyRecycler_adapter = new DatabyRecycler_Adapter(getActivity(), propertyData);
                        rec_villa.setAdapter(databyRecycler_adapter);
                        // databyRecycler_adapter.notifyDataSetChanged();

                    }
                    else {
                        Toast.makeText(getActivity(),"No Data Found",Toast.LENGTH_LONG).show();
                        showprogressbar(false);

                    }
                }
                else{
                    Toast.makeText(getActivity(),"No Data Found",Toast.LENGTH_SHORT).show();
                    showprogressbar(false);
                }
            }
        });
    }


    private void showprogressbar(Boolean IS_SHOW) {
        if (IS_SHOW) {
            progress = ProgressDialog.show(getActivity(), "", "");
            progress.setContentView(R.layout.loader);
            progress.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        } else {
            progress.dismiss();
        }
    }
}