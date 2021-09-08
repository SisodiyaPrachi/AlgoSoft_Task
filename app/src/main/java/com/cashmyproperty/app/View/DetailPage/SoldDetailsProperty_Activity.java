package com.cashmyproperty.app.View.DetailPage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Adapter.SliderAdapter_Agent;
import com.cashmyproperty.app.View.Adapter.SoldProperty_Adapter;
import com.cashmyproperty.app.View.Response.Image;
import com.cashmyproperty.app.View.Response.Result;
import com.cashmyproperty.app.View.Response.SellerSoldPropertyDetails;
import com.cashmyproperty.app.View.ViewModel.DataViewModel;
import com.smarteist.autoimageslider.SliderView;


import java.util.ArrayList;
import java.util.List;

public class SoldDetailsProperty_Activity extends AppCompatActivity {

    RecyclerView rec_photos;
    SoldProperty_Adapter soldProperty_adapter;
    TextView txt_mybidamount,txt_note,bid_myammount,btn_submit,locat,amount,residential,
            property_type,city,layout,location,prop_id,name,txt_layout,txt_title;
   ImageView back,share;
   SliderView image_details;
   String propertyid;
    String link;
   ConstraintLayout con_details;
    private ProgressDialog progress;
    DataViewModel dataViewModel;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_solddetails);

        initviews();

        propertyid=getIntent().getStringExtra("propertyid");

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               onBackPressed();
           }
       });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = link;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        /*fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fav.isPressed()){
                    fav.setImageResource(R.drawable.ic_baseline_favorite_24);
                }
                else{
                    fav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                }
            }
        });*/

        get_solddetails();

    }

    private void get_solddetails() {

        showprogressbar(true);

        dataViewModel.getSoldPropertyDetails(getApplicationContext(),propertyid).observe(this, new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                //hideLoader();
                if (result != null) {


                    SellerSoldPropertyDetails soldPropertyData = result.getSellerSoldPropertyDetails();

                    if (soldPropertyData == null) {
                        Toast.makeText(getApplicationContext(), "No Data Found", Toast.LENGTH_LONG).show();
                        showprogressbar(false);
                    } else {
                        con_details.setVisibility(View.VISIBLE);

                        List<Image> list=soldPropertyData.getImages();

                        if(list.size()>0) {

                            ArrayList<String> slider = new ArrayList<>();

                            for (int i = 0; i < list.size(); i++) {
                                slider.add(list.get(i).getPropertyImage());
                            }

                            get_slider(slider);
                        }
                        else{

                        }

                        locat.setText(soldPropertyData.getAddress());
                        prop_id.setText("Property ID #"+soldPropertyData.getPropertySequenceId());
                        amount.setText("AED "+soldPropertyData.getLastAmount());
                        txt_title.setText(soldPropertyData.getPropertyName());
                        name.setText(soldPropertyData.getPropertyName());
                        residential.setText(soldPropertyData.getKindOfPropertyName());
                        property_type.setText(soldPropertyData.getPropertyTypeName());
                        city.setText(soldPropertyData.getAddress());
                        if(soldPropertyData.getKindOfPropertyName().equalsIgnoreCase("Commercial")){
                            txt_layout.setVisibility(View.GONE);
                            layout.setVisibility(View.GONE);
                        }
                        else{
                            txt_layout.setVisibility(View.VISIBLE);
                            layout.setVisibility(View.VISIBLE);
                            layout.setText(String.valueOf(soldPropertyData.getNoOfBed()+" bedroom"));
                        }
                        location.setText(soldPropertyData.getLocationName());
                        link=soldPropertyData.getWeblinkdetailPage();

                        showprogressbar(false);

                        get_photos(soldPropertyData);
                    }

                }
                     else{
                        Toast.makeText(getApplicationContext(),"No Data Found",Toast.LENGTH_SHORT).show();
                        showprogressbar(false);

                }
            }
        });
    }

    private void get_slider(ArrayList<String> slider) {
        SliderAdapter_Agent sliderAdapter_agent=new SliderAdapter_Agent(getApplicationContext(),slider);
        image_details.setSliderAdapter(sliderAdapter_agent);
        image_details.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        image_details.setScrollTimeInSec(3);
        image_details.setAutoCycle(true);
        image_details.startAutoCycle();
    }

    private void initviews() {

        txt_mybidamount=findViewById(R.id.txt_mybidamount);
        txt_note=findViewById(R.id.txt_note);
        bid_myammount=findViewById(R.id.bid_myammount);
        btn_submit=findViewById(R.id.btn_submit);
        back=findViewById(R.id.back);
        share=findViewById(R.id.share);
        //fav=findViewById(R.id.fav);
        prop_id=findViewById(R.id.prop_id);
        name=findViewById(R.id.name);
        txt_title=findViewById(R.id.txt_title);
        txt_layout=findViewById(R.id.txt_layout);
        con_details=findViewById(R.id.con_details);
        image_details=findViewById(R.id.image_details);
        locat=findViewById(R.id.locat);
        residential=findViewById(R.id.residential);
        property_type=findViewById(R.id.property_type);
        city=findViewById(R.id.city);
        layout=findViewById(R.id.layout);
        location=findViewById(R.id.location);
        amount=findViewById(R.id.amount);
        con_details.setVisibility(View.GONE);

        rec_photos=findViewById(R.id.rec_photos);

        RecyclerView.LayoutManager recyclerViewLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        rec_photos.setLayoutManager(recyclerViewLayoutManager);
    }

    private void get_photos(SellerSoldPropertyDetails soldPropertyData) {

        /*ArrayList<Integer> img= new ArrayList<>();
        ArrayList<String> text_category= new ArrayList<>();

        img.add(R.drawable.furnished);
        img.add(R.drawable.home);*/

        soldProperty_adapter = new SoldProperty_Adapter(getApplicationContext(),soldPropertyData);

        rec_photos.setAdapter(soldProperty_adapter);

    }


    private void showprogressbar(Boolean IS_SHOW) {
        if (IS_SHOW) {
            progress = ProgressDialog.show(SoldDetailsProperty_Activity.this, "", "");
            progress.setContentView(R.layout.loader);
            progress.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        } else {
            progress.dismiss();
        }
    }


}



