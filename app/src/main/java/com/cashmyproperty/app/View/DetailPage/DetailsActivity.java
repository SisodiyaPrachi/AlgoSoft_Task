package com.cashmyproperty.app.View.DetailPage;

import android.app.Dialog;
import android.app.ProgressDialog;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Activities.Navigation_Activity;
import com.cashmyproperty.app.View.Adapter.Agent_PhotosAdapter;
import com.cashmyproperty.app.View.Adapter.PhotosAdapter;
import com.cashmyproperty.app.View.Adapter.SliderAdapter_Agent;
import com.cashmyproperty.app.View.Response.BidsubmitData;
import com.cashmyproperty.app.View.Response.Example;
import com.cashmyproperty.app.View.Response.Image;
import com.cashmyproperty.app.View.Response.PropertyDetailsData;

import com.cashmyproperty.app.View.Response.SignUpResult;
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

public class DetailsActivity extends AppCompatActivity {

    RecyclerView rec_photos;
    PhotosAdapter photosAdapter;
    Agent_PhotosAdapter agent_photosAdapter;
    DataViewModel dataViewModel;
    ConstraintLayout con_details;
    String bid_txt;
    String link;
    ImageView share,fav;
    Integer curr_bid;
    SliderView image_details;
    TextView name,locat,amount,bid_amount,residential,property_type,city,layout,location,prop_id,txt_layout,txt_bidamount,txt_amount;
    EditText bid_myammount;
    FrameLayout frame;
    TextView txt_mybidamount,txt_note,txt_title;
    ImageView back,plus,sub;
    Integer bid;
    Button btn_submit;
    Dialog dialog;
    private ProgressDialog progress;
    String propertyid="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_details);

        initviews();
        propertyid=getIntent().getStringExtra("propertyid");
       // Toast.makeText(getApplicationContext(),propertyid,Toast.LENGTH_SHORT).show();
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

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bid_txt=bid_myammount.getText().toString();
                if((Integer.valueOf(bid_txt)%5000==0) && (Integer.valueOf(bid_txt)>=(curr_bid+5000))){
                    submit_bid();
                }
                else{
                    show_bidalert();
                }
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

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add_favlist();
            }
        });

        if(PreferenceUtils.getStringValue(getApplicationContext(),PreferenceUtils.UserType).equalsIgnoreCase("Seller")) {
            txt_mybidamount.setVisibility(View.GONE);
            txt_note.setVisibility(View.GONE);
            frame.setVisibility(View.GONE);
            btn_submit.setVisibility(View.GONE);
            plus.setVisibility(View.GONE);
            fav.setVisibility(View.GONE);
            sub.setVisibility(View.GONE);
        }
        else {
            txt_mybidamount.setVisibility(View.VISIBLE);
            txt_note.setVisibility(View.VISIBLE);
            bid_myammount.setVisibility(View.VISIBLE);
            btn_submit.setVisibility(View.VISIBLE);
            plus.setVisibility(View.VISIBLE);
            fav.setVisibility(View.VISIBLE);
            sub.setVisibility(View.VISIBLE);
        }

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        if(PreferenceUtils.getStringValue(getApplicationContext(),PreferenceUtils.UserType).equalsIgnoreCase("Seller")) {

            get_details();
        }
        else{

            get_buyerdetails();
        }

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
                            WishlistData wishlistData=response.body().getResult().getWishlistData();
                            fav.setImageResource(R.drawable.ic_baseline_favorite_24);
                            PreferenceUtils.setStringValue(getApplicationContext(),PreferenceUtils.Status,"Success");
                            Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                        }else{
                            PreferenceUtils.setStringValue(getApplicationContext(),PreferenceUtils.Status,"Fail");
                            fav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
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



    private void submit_bid() {

        showprogressbar(true);


        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(getApplicationContext());

        HashMap<String, String> params = new HashMap<>();
        params.put("users_id", PreferenceUtils.getStringValue(getApplicationContext(),PreferenceUtils.UserID));
        params.put("users_token", PreferenceUtils.getStringValue(getApplicationContext(),PreferenceUtils.UserToken));
        params.put("property_id", propertyid);
        params.put("bid_amount", bid_txt);

        Call<Example> call = apiService.createFactoryApi().submit_bid(headerMap, params);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.e("submitbid", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    Example responseClass = response.body();
                    if (responseClass.getSuccess() == 1) {
                        BidsubmitData data=responseClass.getResult().getBidsubmitData();
                        showprogressbar(false);
                        Toast.makeText(getApplicationContext(), responseClass.getMessage(), Toast.LENGTH_SHORT).show();
                        show_policyalert();

                    }
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                // hideLoader();
                // showSnackbar();
                Log.e("submitbid", " - > Error    " + t.getMessage());
                showprogressbar(false);
            }
        });

    }

    private void show_policyalert() {
        dialog.setContentView(R.layout.app_alert);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView title=dialog.findViewById(R.id.title);
        title.setText("Alert!!!");
        title.setTextColor(getResources().getColor(R.color.red));
        TextView view=dialog.findViewById(R.id.view);
        view.setText("Accepting the Bidding Policies & Acknowledging to prosecute in the UAE Court of Law if falling to meet Bidding norms.");
        Button btn_ok=dialog.findViewById(R.id.btn_ok);
        btn_ok.setText("Submit");
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Navigation_Activity.class);
                startActivity(intent);
            }
        });
        ImageView cancel=dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void show_bidalert() {
        dialog.setContentView(R.layout.alert_bid);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView cancel=dialog.findViewById(R.id.cancel);
        TextView title=dialog.findViewById(R.id.title);
        TextView view=dialog.findViewById(R.id.view);
        TextView view_msg=dialog.findViewById(R.id.view_msg);
        view_msg.setText("Minimum Bid Criteria should be AED5000 & Above");
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void get_buyerdetails() {

        showprogressbar(true);


        dataViewModel.getbuyerDetails(this,propertyid).observe(this, new Observer<SignUpResult>() {
            @Override
            public void onChanged(SignUpResult result) {

                //hideLoader();
                if (result != null) {
                    con_details.setVisibility(View.VISIBLE);
                    PropertyDetailsData propertyDetailsData=result.getPropertyDetailsData();
                    List<Image> list=propertyDetailsData.getImages();

                    if(list.size()>0) {

                        ArrayList<String> slider = new ArrayList<>();

                        for (int i = 0; i < list.size(); i++) {
                            slider.add(list.get(i).getPropertyImage());
                        }

                        get_slider(slider);
                    }
                    else{

                    }
                    name.setText(propertyDetailsData.getPropertyName());
                    locat.setText(propertyDetailsData.getAddress());
                    txt_title.setText(propertyDetailsData.getPropertyName());
                    prop_id.setText("Property ID # "+propertyDetailsData.getPropertySequenceId());
                    amount.setText("AED "+propertyDetailsData.getStartAmount());
                    curr_bid= Integer.valueOf(propertyDetailsData.getCurrentBidAmount());
                    bid_amount.setText("AED "+propertyDetailsData.getCurrentBidAmount());
                    bid=propertyDetailsData.getMyBidAmount();
                    bid_myammount.setText(String.valueOf(propertyDetailsData.getMyBidAmount()));
                    residential.setText(propertyDetailsData.getKindOfPropertyName());
                    property_type.setText(propertyDetailsData.getPropertyTypeName());
                    city.setText(propertyDetailsData.getAddress());
                    link=propertyDetailsData.getWeblinkdetailPage();

                    if(propertyDetailsData.getKindOfPropertyName().equalsIgnoreCase("Commercial")){
                        txt_layout.setVisibility(View.GONE);
                        layout.setVisibility(View.GONE);
                    }
                    else{
                        txt_layout.setVisibility(View.VISIBLE);
                        layout.setVisibility(View.VISIBLE);
                        layout.setText(String.valueOf(propertyDetailsData.getNoOfBed()+" bedroom"));
                    }

                    location.setText(propertyDetailsData.getLocationName());

                    showprogressbar(false);

                    get_agentphotos(propertyDetailsData);

                }
                else{
                    Toast.makeText(getApplicationContext(),"No Data Found",Toast.LENGTH_SHORT).show();
                    showprogressbar(false);
                }
            }
        });

    }

    private void get_agentphotos(PropertyDetailsData propertyDetailsData) {

        agent_photosAdapter = new Agent_PhotosAdapter(getApplicationContext(),propertyDetailsData);

        rec_photos.setAdapter(agent_photosAdapter);
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
        name=findViewById(R.id.name);
        locat=findViewById(R.id.locat);
        dialog=new Dialog(DetailsActivity.this);
        amount=findViewById(R.id.amount);
        bid_amount=findViewById(R.id.bid_amount);
        residential=findViewById(R.id.residential);
        frame=findViewById(R.id.frame);
        property_type=findViewById(R.id.property_type);
        city=findViewById(R.id.city);
        back=findViewById(R.id.back);
        txt_title=findViewById(R.id.txt_title);
        txt_amount=findViewById(R.id.txt_amount);
        txt_bidamount=findViewById(R.id.txt_bidamount);
        txt_layout=findViewById(R.id.txt_layout);
        layout=findViewById(R.id.layout);
        plus=findViewById(R.id.plus);
        con_details=findViewById(R.id.con_details);
        prop_id=findViewById(R.id.prop_id);
        sub=findViewById(R.id.sub);
        image_details=findViewById(R.id.image_details);
        location =findViewById(R.id.location);
        txt_mybidamount=findViewById(R.id.txt_mybidamount);
        txt_note=findViewById(R.id.txt_note);
        bid_myammount=findViewById(R.id.bid_myammount);
        btn_submit=findViewById(R.id.btn_submit);
        back=findViewById(R.id.back);
        share=findViewById(R.id.share);
        fav=findViewById(R.id.fav);
        con_details.setVisibility(View.GONE);

        rec_photos=findViewById(R.id.rec_photos);

        RecyclerView.LayoutManager recyclerViewLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        rec_photos.setLayoutManager(recyclerViewLayoutManager);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bid_txt=bid_myammount.getText().toString();
                bid_txt= String.valueOf(Integer.valueOf(bid_txt)+5000);
              bid_myammount.setText(String.valueOf(bid_txt));
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bid_txt = bid_myammount.getText().toString();

                if(Integer.valueOf(bid_txt)>=(curr_bid+5000)) {
                    bid_txt = String.valueOf(Integer.valueOf(bid_txt) - 5000);
                    bid_myammount.setText(String.valueOf(bid_txt));
                }
                else{
                  //  Toast.makeText(getApplicationContext(),"no",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void get_details() {
        showprogressbar(true);

        dataViewModel.getDetails(this,propertyid).observe(this, new Observer<SignUpResult>() {
            @Override
            public void onChanged(SignUpResult result) {

                //hideLoader();
                if (result != null) {
                    con_details.setVisibility(View.VISIBLE);
                    PropertyDetailsData propertyDetailsData=result.getPropertyDetailsData();
                    List<Image> list=propertyDetailsData.getImages();

                    if(list.size()>0) {

                        ArrayList<String> slider = new ArrayList<>();

                        for (int i = 0; i < list.size(); i++) {
                            slider.add(list.get(i).getPropertyImage());
                        }

                        get_slider(slider);
                    }
                    else{

                    }

                    name.setText(propertyDetailsData.getPropertyName());
                    prop_id.setText("Property ID # "+propertyDetailsData.getPropertySequenceId());
                    locat.setText(propertyDetailsData.getAddress());
                    txt_title.setText(propertyDetailsData.getPropertyName());
                    amount.setText(propertyDetailsData.getStartAmount());
                    bid_amount.setText("AED "+String.valueOf(propertyDetailsData.getCurrentBidAmount()));
//                    Toast.makeText(getApplicationContext(), propertyDetailsData.getCurrentBidAmount(), Toast.LENGTH_SHORT).show();
                    residential.setText(propertyDetailsData.getKindOfPropertyName());
                    property_type.setText(propertyDetailsData.getPropertyTypeName());
                    city.setText(propertyDetailsData.getAddress());
                    link=propertyDetailsData.getWeblinkdetailPage();

                    if(propertyDetailsData.getKindOfPropertyName().equalsIgnoreCase("Commercial")){
                        txt_layout.setVisibility(View.GONE);
                        layout.setVisibility(View.GONE);
                    }
                    else{
                        txt_layout.setVisibility(View.VISIBLE);
                        layout.setVisibility(View.VISIBLE);
                        layout.setText(String.valueOf(propertyDetailsData.getNoOfBed()+" bedroom"));
                    }

                     location.setText(propertyDetailsData.getLocationName());
                     showprogressbar(false);
                    get_photos(propertyDetailsData);

                }
            }
        });
    }

    private void get_photos(PropertyDetailsData propertyDetailsData) {


        photosAdapter = new PhotosAdapter(getApplicationContext(),propertyDetailsData);

        rec_photos.setAdapter(photosAdapter);

    }


    private void showprogressbar(Boolean IS_SHOW) {
        if (IS_SHOW) {
            progress = ProgressDialog.show(DetailsActivity.this, "", "");
            progress.setContentView(R.layout.loader);
            progress.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        } else {
            progress.dismiss();
        }
    }

    }





