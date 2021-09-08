package com.cashmyproperty.app.View.DetailPage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.cashmyproperty.app.View.Response.BuyerPurchasePropertyDetails;
import com.cashmyproperty.app.View.Response.Example;
import com.cashmyproperty.app.View.Response.Image;
import com.cashmyproperty.app.View.Response.PurchaseProperty_Adapter;
import com.cashmyproperty.app.View.Response.Result;
import com.cashmyproperty.app.View.Response.WishlistData;
import com.cashmyproperty.app.View.Utility.PreferenceUtils;
import com.cashmyproperty.app.View.ViewModel.DataViewModel;
import com.cashmyproperty.app.View.network.ApiService;
import com.google.gson.Gson;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PurchaseDetailsProperty_Activity extends AppCompatActivity {

    RecyclerView rec_photos;
    PurchaseProperty_Adapter purchaseProperty_adapter;
    TextView txt_mybidamount,txt_note,bid_myammount,btn_submit,name,locat,amount
            ,residential,property_type,city,layout,location,prop_id,txt_layout,txt_title;
    ImageView back,share,fav;
    SliderView image_details;
    String propertyid="";
    ConstraintLayout con_details;
    int count=0;
    String link;
    DataViewModel dataViewModel;
    private ProgressDialog progress;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_purchasedetails);

        initviews();

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        propertyid=getIntent().getStringExtra("propertyid");


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if(PreferenceUtils.getStringValue(getApplicationContext(),PreferenceUtils.Status).equalsIgnoreCase("Success")) {

            fav.setImageResource(R.drawable.ic_baseline_favorite_24);
        }
        else {
            fav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        }

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

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_favlist();
            }
        });

        get_purchase();


    }

    private void get_purchase() {
        showprogressbar(true);
        dataViewModel.getpurchasepropertydetails(this, propertyid).observe(this, new Observer<Result>() {
            @Override
            public void onChanged(Result result) {

                //hideLoader();
                if (result != null) {
                    con_details.setVisibility(View.VISIBLE);
                    BuyerPurchasePropertyDetails buyerPurchasePropertyDetails = result.getBuyerPurchasePropertyDetails();
                    List<Image> list = buyerPurchasePropertyDetails.getImages();

                    if(list.size()>0) {

                        ArrayList<String> slider = new ArrayList<>();

                        for (int i = 0; i < list.size(); i++) {
                            slider.add(list.get(i).getPropertyImage());
                        }

                        get_slider(slider);
                    }
                    else{

                    }

                    name.setText(buyerPurchasePropertyDetails.getPropertyName());
                    locat.setText(buyerPurchasePropertyDetails.getAddress());
                    txt_title.setText(buyerPurchasePropertyDetails.getPropertyName());
                    amount.setText(buyerPurchasePropertyDetails.getLastBiding());
                    prop_id.setText("Property ID #"+buyerPurchasePropertyDetails.getPropertySequenceId());
                    residential.setText(buyerPurchasePropertyDetails.getKindOfPropertyName());
                    property_type.setText(buyerPurchasePropertyDetails.getPropertyTypeName());
                    city.setText(buyerPurchasePropertyDetails.getAddress());
                    link=buyerPurchasePropertyDetails.getWeblinkdetailPage();

                    if(buyerPurchasePropertyDetails.getKindOfPropertyName().equalsIgnoreCase("Commercial")){
                        txt_layout.setVisibility(View.GONE);
                        layout.setVisibility(View.GONE);
                    }
                    else{
                        txt_layout.setVisibility(View.VISIBLE);
                        layout.setVisibility(View.VISIBLE);
                        layout.setText(String.valueOf(buyerPurchasePropertyDetails.getNoOfBed()+" bedroom"));
                    }

                    location.setText(buyerPurchasePropertyDetails.getLocationName());



                    showprogressbar(false);

                    get_photos(buyerPurchasePropertyDetails);
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
        fav=findViewById(R.id.fav);
        prop_id=findViewById(R.id.prop_id);
        txt_layout=findViewById(R.id.txt_layout);
        image_details=findViewById(R.id.image_details);
        name=findViewById(R.id.name);
        txt_title=findViewById(R.id.txt_title);
        locat=findViewById(R.id.locat);
        amount=findViewById(R.id.amount);
        con_details=findViewById(R.id.con_details);
        residential=findViewById(R.id.residential);
        property_type=findViewById(R.id.property_type);
        city=findViewById(R.id.city);
        layout=findViewById(R.id.layout);
        location=findViewById(R.id.location);

        rec_photos=findViewById(R.id.rec_photos);
        con_details.setVisibility(View.GONE);

        RecyclerView.LayoutManager recyclerViewLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        rec_photos.setLayoutManager(recyclerViewLayoutManager);
    }

    private void get_photos(BuyerPurchasePropertyDetails buyerPurchasePropertyDetails) {

        purchaseProperty_adapter = new PurchaseProperty_Adapter(getApplicationContext(),buyerPurchasePropertyDetails);

        rec_photos.setAdapter(purchaseProperty_adapter);

    }

    private void add_favlist() {


        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(getApplicationContext());

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(getApplicationContext(),PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(getApplicationContext(),PreferenceUtils.UserToken));
        params.put("property_id",propertyid);

        Call<Example> call = apiService.createFactoryApi().setfav(headerMap, params);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.e("set_fav","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null){
                    if(response.body().getSuccess()==1){
                        count=1;
                        WishlistData wishlistData=response.body().getResult().getWishlistData();
                        fav.setImageResource(R.drawable.ic_baseline_favorite_24);
                        PreferenceUtils.setStringValue(getApplicationContext(),PreferenceUtils.Status,"Success");
                        Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }else{
                        count=0;
                        fav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                        PreferenceUtils.setStringValue(getApplicationContext(),PreferenceUtils.Status,"Fail");
                        Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e("set_fav"," - > Error    "+t.getMessage());
            }
        });

    }


    private void showprogressbar(Boolean IS_SHOW) {
        if (IS_SHOW) {
            progress = ProgressDialog.show(PurchaseDetailsProperty_Activity.this, "", "");
            progress.setContentView(R.layout.loader);
            progress.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        } else {
            progress.dismiss();
        }
    }



}

