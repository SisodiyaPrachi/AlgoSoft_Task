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
import com.cashmyproperty.app.View.Adapter.BidProperty_Adapter;
import com.cashmyproperty.app.View.Adapter.SliderAdapter_Agent;
import com.cashmyproperty.app.View.Adapter.SoldProperty_Adapter;
import com.cashmyproperty.app.View.Response.BuyerBidPropertyDetails;
import com.cashmyproperty.app.View.Response.Image;
import com.cashmyproperty.app.View.Response.MypropertyDetailData;
import com.cashmyproperty.app.View.Response.PropertyDetails_Adapter;
import com.cashmyproperty.app.View.Response.Result;
import com.cashmyproperty.app.View.ViewModel.DataViewModel;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class SellerProp_DetailsActivity extends AppCompatActivity {

    RecyclerView rec_photos;
    PropertyDetails_Adapter propertyDetails_adapter;
    TextView txt_mybidamount,txt_note,bid_myammount,btn_submit,name,locat,amount,bid_amount
            ,residential,property_type,city,layout,location,prop_id,txt_layout,txt_amount,txt_title;
    ImageView back,share;
    SliderView image_details;
    DataViewModel dataViewModel;
    ConstraintLayout con_details;
    private ProgressDialog progress;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";
    String propertyid="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sellerpropdetails);

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
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

       /* fav.setOnClickListener(new View.OnClickListener() {
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

        get_sellerdetails();


    }

    private void get_sellerdetails() {

        showprogressbar(true);

        dataViewModel.getsellerpropertydetails(this,propertyid).observe(this, new Observer<Result>() {
            @Override
            public void onChanged(Result result) {

                //hideLoader();
                if (result != null) {
                    con_details.setVisibility(View.VISIBLE);
                    MypropertyDetailData mypropertyDetailData=result.getMypropertyDetailData();
                    List<Image> list=mypropertyDetailData.getImages();

                    ArrayList<String> slider=new ArrayList<>();

                    for(int i=0;i<list.size();i++){
                        slider.add(list.get(i).getPropertyImage());
                    }

                    get_slider(slider);

                    name.setText(mypropertyDetailData.getPropertyName());
                    locat.setText(mypropertyDetailData.getAddress());
                    amount.setText(mypropertyDetailData.getStartAmount());
                    txt_title.setText(mypropertyDetailData.getPropertyName());
                    bid_amount.setText(mypropertyDetailData.getCurrentBidAmount());
                    prop_id.setText("Property ID #"+mypropertyDetailData.getPropertySequenceId());
                    residential.setText(mypropertyDetailData.getKindOfPropertyName());
                    property_type.setText(mypropertyDetailData.getPropertyTypeName());
                    city.setText(mypropertyDetailData.getAddress());

                    if(mypropertyDetailData.getKindOfPropertyName().equalsIgnoreCase("Commercial")){
                        txt_layout.setVisibility(View.GONE);
                        layout.setVisibility(View.GONE);
                    }
                    else{
                        txt_layout.setVisibility(View.VISIBLE);
                        layout.setVisibility(View.VISIBLE);
                        layout.setText(String.valueOf(mypropertyDetailData.getNoOfBed()+" bedroom"));
                    }

                    location.setText(mypropertyDetailData.getLocationName());

                    showprogressbar(false);

                    get_photos(mypropertyDetailData);

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
       // fav=findViewById(R.id.fav);
        txt_title=findViewById(R.id.txt_title);
        txt_layout=findViewById(R.id.txt_layout);
        prop_id=findViewById(R.id.prop_id);
        txt_amount=findViewById(R.id.txt_amount);
        con_details=findViewById(R.id.con_details);
        image_details=findViewById(R.id.image_details);
        name=findViewById(R.id.name);
        locat=findViewById(R.id.locat);
        amount=findViewById(R.id.amount);
        bid_amount=findViewById(R.id.bid_amount);
        residential=findViewById(R.id.residential);
        property_type=findViewById(R.id.property_type);
        city=findViewById(R.id.city);
        layout=findViewById(R.id.layout);
        location=findViewById(R.id.location);

        txt_amount.setVisibility(View.GONE);
        amount.setVisibility(View.GONE);

        rec_photos=findViewById(R.id.rec_photos);
        con_details.setVisibility(View.GONE);

        RecyclerView.LayoutManager recyclerViewLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        rec_photos.setLayoutManager(recyclerViewLayoutManager);
    }

    private void get_photos(MypropertyDetailData mypropertyDetailData) {



        propertyDetails_adapter = new PropertyDetails_Adapter(getApplicationContext(),mypropertyDetailData);

        rec_photos.setAdapter(propertyDetails_adapter);

    }

    private void showprogressbar(Boolean IS_SHOW) {
        if (IS_SHOW) {
            progress = ProgressDialog.show(SellerProp_DetailsActivity.this, "", "");
            progress.setContentView(R.layout.loader);
            progress.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        } else {
            progress.dismiss();
        }
    }



}
