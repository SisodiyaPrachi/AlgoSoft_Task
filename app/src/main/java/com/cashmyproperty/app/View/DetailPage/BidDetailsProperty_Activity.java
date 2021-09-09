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
import com.cashmyproperty.app.View.Activities.Navigation_Activity;
import com.cashmyproperty.app.View.Activities.Navigation_Seller;
import com.cashmyproperty.app.View.Adapter.BidProperty_Adapter;
import com.cashmyproperty.app.View.Adapter.SliderAdapter;
import com.cashmyproperty.app.View.Adapter.SliderAdapter_Agent;
import com.cashmyproperty.app.View.Adapter.SoldProperty_Adapter;
import com.cashmyproperty.app.View.Response.BidsubmitData;
import com.cashmyproperty.app.View.Response.BuyerBidPropertyDetails;
import com.cashmyproperty.app.View.Response.Example;
import com.cashmyproperty.app.View.Response.Image;
import com.cashmyproperty.app.View.Response.PropertyDetailsData;
import com.cashmyproperty.app.View.Response.Result;
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

public class BidDetailsProperty_Activity extends AppCompatActivity {

    RecyclerView rec_photos;
    BidProperty_Adapter bidProperty_adapter;
    TextView txt_mybidamount,txt_note,bid_myammount,name,locat,amount,txt_layout,txt_amount
            ,bid_amount,residential,property_type,city,layout,location,prop_id,txt_title;
    Button btn_submit;
    ImageView back,share,fav,plus,sub;
    String propertyid;
    SliderAdapter sliderAdapter;
    SliderView image_details;
    Integer bid;
    Integer curr_bid;
     int count=0;
    Dialog dialog;
    String bid_txt;
    String link;
    ConstraintLayout con_details;
    private ProgressDialog progress;
    DataViewModel dataViewModel;
    String wishlist="N";
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_biddetails);

        propertyid = getIntent().getStringExtra("propertyid");

        initviews();
        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);
        get_biddetails();


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

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_favlist();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bid_txt = bid_myammount.getText().toString();
                if ((Integer.valueOf(bid_txt) % 5000 == 0) && (Integer.valueOf(bid_txt)>=(curr_bid+5000))) {
                        submit_bid();
                } else {
                    show_bidalert();
                }
            }
        });
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
                        //PreferenceUtils.setStringValue(getApplicationContext(), PreferenceUtils.UserToken, data.getUsersToken());
                        Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }else{
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
                Intent intent=new Intent(getApplicationContext(), Navigation_Activity.class);
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

    private void get_biddetails() {

        showprogressbar(true);
        dataViewModel.getbidpropertydetails(this,propertyid).observe(this, new Observer<Result>() {
            @Override
            public void onChanged(Result result) {

                //hideLoader();
                if (result != null) {

                    con_details.setVisibility(View.VISIBLE);
                    BuyerBidPropertyDetails buyerBidPropertyDetails=result.getBuyerBidPropertyDetails();
                    List<Image> list=buyerBidPropertyDetails.getImages();

                    if(list.size()>0) {

                        ArrayList<String> slider = new ArrayList<>();

                        for (int i = 0; i < list.size(); i++) {
                            slider.add(list.get(i).getPropertyImage());
                        }

                        get_slider(slider);
                    }
                    else{

                    }

                    name.setText(buyerBidPropertyDetails.getPropertyName());
                    locat.setText(buyerBidPropertyDetails.getAddress());
                    txt_title.setText(buyerBidPropertyDetails.getPropertyName());
                    prop_id.setText("Property ID #"+buyerBidPropertyDetails.getPropertySequenceId());
                    amount.setText("AED "+buyerBidPropertyDetails.getStartAmount());
                    curr_bid=buyerBidPropertyDetails.getCurrentBiding();
                    bid_amount.setText("AED "+buyerBidPropertyDetails.getCurrentBiding());
                    bid=buyerBidPropertyDetails.getMyBidAmount();
                    bid_myammount.setText(String.valueOf(buyerBidPropertyDetails.getMyBidAmount()));
                    residential.setText(buyerBidPropertyDetails.getKindOfPropertyName());
                    property_type.setText(buyerBidPropertyDetails.getPropertyTypeName());
                    city.setText(buyerBidPropertyDetails.getAddress());
                    link=buyerBidPropertyDetails.getWeblinkdetailPage();
                    wishlist=buyerBidPropertyDetails.getPropertywishlisted();

                    if(wishlist.equals("Y")) {
                        fav.setImageResource(R.drawable.ic_baseline_favorite_24);
                    }
                    else {
                        fav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    }

                    if(buyerBidPropertyDetails.getKindOfPropertyName().equalsIgnoreCase("Commercial")){
                        txt_layout.setVisibility(View.GONE);
                        layout.setVisibility(View.GONE);
                    }
                    else{
                        txt_layout.setVisibility(View.VISIBLE);
                        layout.setVisibility(View.VISIBLE);
                        layout.setText(String.valueOf(buyerBidPropertyDetails.getNoOfBed()+" bedroom"));
                    }

                    location.setText(buyerBidPropertyDetails.getLocationName());

                    showprogressbar(false);

                    get_photos(buyerBidPropertyDetails);

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
        txt_amount=findViewById(R.id.txt_amount);
        con_details=findViewById(R.id.con_details);
        name=findViewById(R.id.name);
        txt_title=findViewById(R.id.txt_title);
        locat=findViewById(R.id.locat);
        plus =findViewById(R.id.plus);
        sub=findViewById(R.id.sub);
        txt_layout=findViewById(R.id.txt_layout);
        prop_id=findViewById(R.id.prop_id);
        amount=findViewById(R.id.amount);
        bid_amount=findViewById(R.id.bid_amount);
        residential=findViewById(R.id.residential);
        property_type=findViewById(R.id.property_type);
        city=findViewById(R.id.city);
        layout=findViewById(R.id.layout);
        location=findViewById(R.id.location);
        image_details=findViewById(R.id.image_details);
        con_details.setVisibility(View.GONE);
        dialog=new Dialog(BidDetailsProperty_Activity.this);

        rec_photos=findViewById(R.id.rec_photos);

        txt_amount.setVisibility(View.GONE);
        amount.setVisibility(View.GONE);

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

                if(Integer.valueOf(bid_txt)>(curr_bid+5000)) {
                    bid_txt = String.valueOf(Integer.valueOf(bid_txt) - 5000);
                    bid_myammount.setText(String.valueOf(bid_txt));
                }
                else{
                    //Toast.makeText(getApplicationContext(),"no",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void get_photos(BuyerBidPropertyDetails buyerBidPropertyDetails) {


        bidProperty_adapter = new BidProperty_Adapter(getApplicationContext(),buyerBidPropertyDetails);

        rec_photos.setAdapter(bidProperty_adapter);

    }

    private void showprogressbar(Boolean IS_SHOW) {
        if (IS_SHOW) {
            progress = ProgressDialog.show(BidDetailsProperty_Activity.this, "", "");
            progress.setContentView(R.layout.loader);
            progress.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        } else {
            progress.dismiss();
        }
    }



}
