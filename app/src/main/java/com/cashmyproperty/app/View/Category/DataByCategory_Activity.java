package com.cashmyproperty.app.View.Category;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

import com.cashmyproperty.app.View.Activities.Navigation_Activity;
import com.cashmyproperty.app.View.Adapter.DatabyRecycler_Adapter;
import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Response.DatabycatResult;
import com.cashmyproperty.app.View.Response.LocationDatum;
import com.cashmyproperty.app.View.Response.PropertyDatum;
import com.cashmyproperty.app.View.Response.SubCategoryData;
import com.cashmyproperty.app.View.ViewModel.DataViewModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DataByCategory_Activity extends AppCompatActivity {

    RecyclerView rec_villa;
    DatabyRecycler_Adapter databyRecycler_adapter;
    MaterialToolbar tool_bar;
    DataViewModel dataViewModel;
    String location_id="";
    private  boolean ascending= true;
    private ProgressDialog progress;
    ArrayList<String> location;
    List<PropertyDatum> propertyData;
    TextView price,location_place,time,bids;

    public DataByCategory_Activity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_villa);

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        rec_villa = findViewById(R.id.rec_category);
        price=findViewById(R.id.price);
        location_place=findViewById(R.id.location_place);
        time=findViewById(R.id.time);
        bids=findViewById(R.id.bids);
        tool_bar = findViewById(R.id.tool_bar);
        tool_bar.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);

        rec_villa.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        tool_bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


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

        databyRecycler_adapter = new DatabyRecycler_Adapter(getApplicationContext(), propertyData);
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

        databyRecycler_adapter = new DatabyRecycler_Adapter(getApplicationContext(), propertyData);
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

        databyRecycler_adapter = new DatabyRecycler_Adapter(getApplicationContext(), propertyData);
        rec_villa.setAdapter(databyRecycler_adapter);
        databyRecycler_adapter.notifyDataSetChanged();
    }

    private void sortData(boolean ascending) {
        if(ascending){
            Collections.sort(propertyData, new Comparator<PropertyDatum>() {
                @Override
                public int compare(PropertyDatum propertyDatum, PropertyDatum t1) {
                    return String.valueOf(propertyDatum.getAddress()).compareToIgnoreCase(String.valueOf(t1.getAddress()));
                }
            });
        }
        else{
            Collections.reverse(propertyData);
        }

        databyRecycler_adapter = new DatabyRecycler_Adapter(getApplicationContext(), propertyData);
        rec_villa.setAdapter(databyRecycler_adapter);
        databyRecycler_adapter.notifyDataSetChanged();
    }


    private void getcatData() {

        showprogressbar(true);

        dataViewModel.getDatabyCategoryDetails(getApplicationContext()).observe(this, new Observer<DatabycatResult>() {
            @Override
            public void onChanged(DatabycatResult result) {
                //hideLoader();
                if (result != null) {

                        propertyData=result.getPropertyData();
                        SubCategoryData sub_categoryData = result.getSubCategoryData();
                        tool_bar.setTitle(sub_categoryData.getSubCategoryName());

                        if (propertyData.size()>0) {
                            showprogressbar(false);

                            /*Collections.sort(propertyData, new Comparator<PropertyDatum>() {
                                @Override
                                public int compare(PropertyDatum propertyDatum, PropertyDatum t1) {
                                    return propertyDatum.getCurrentBiding().compareTo(t1.getCurrentBiding());
                                }
                            });*/
                            databyRecycler_adapter = new DatabyRecycler_Adapter(getApplicationContext(), propertyData);
                            rec_villa.setAdapter(databyRecycler_adapter);
                           // databyRecycler_adapter.notifyDataSetChanged();

                        }
                        else {
                            Toast.makeText(getApplicationContext(),"No Data Found",Toast.LENGTH_LONG).show();
                            showprogressbar(false);

                        }
                }
                else{
                    Toast.makeText(getApplicationContext(),"No Data Found",Toast.LENGTH_SHORT).show();
                    showprogressbar(false);
                }
            }
        });
    }


    private void showprogressbar(Boolean IS_SHOW) {
        if (IS_SHOW) {
            progress = ProgressDialog.show(DataByCategory_Activity.this, "", "");
            progress.setContentView(R.layout.loader);
            progress.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        } else {
            progress.dismiss();
        }
    }
}