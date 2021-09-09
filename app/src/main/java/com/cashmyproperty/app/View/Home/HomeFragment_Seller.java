package com.cashmyproperty.app.View.Home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cashmyproperty.app.View.Adapter.SliderAdapter;
import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Adapter.Approved_PropertyAdapter;
import com.cashmyproperty.app.View.DetailPage.DetailsActivity;
import com.cashmyproperty.app.View.Response.FirstPropertyData;
import com.cashmyproperty.app.View.Response.Image;
import com.cashmyproperty.app.View.Response.RestPropertyDatum;
import com.cashmyproperty.app.View.Response.SignUpResult;
import com.cashmyproperty.app.View.ViewModel.DataViewModel;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment_Seller extends Fragment {

    RecyclerView rec_biding;
    Approved_PropertyAdapter approved_propertyAdapter;
    SliderView sliderView;
    TextView name,locat,area,amount,bid_amount,lastbid;
    SliderAdapter sliderAdapter;
    ImageView navigation_view;
    DataViewModel dataViewModel;
    View view;
    private ProgressDialog progress;
    CardView cv_one_login;
    TextView app_des,app_txt;
    List<RestPropertyDatum> restPropertyDatum;

    public HomeFragment_Seller() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_home, container, false);

        initviews();

        dataViewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);

        getSliderimg();

      //  Long tsLong = System.currentTimeMillis()/1000;
      //  String ts = tsLong.toString();

       // Toast.makeText(getActivity(),ts,Toast.LENGTH_SHORT).show();


        return view;
    }

    private void initviews() {
        sliderView = view.findViewById(R.id.slider);
        rec_biding = view.findViewById(R.id.rec_biding);
        rec_biding.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        name=view.findViewById(R.id.name);
        locat=view.findViewById(R.id.locat);
        area=view.findViewById(R.id.area);
        amount=view.findViewById(R.id.amount);
        lastbid=view.findViewById(R.id.lastbid);
        bid_amount=view.findViewById(R.id.bid_amount);
        cv_one_login=view.findViewById(R.id.cv_one_login);
        app_des=view.findViewById(R.id.app_des);
        app_txt=view.findViewById(R.id.app_txt);
        sliderView.setVisibility(View.GONE);
        cv_one_login.setVisibility(View.GONE);
        app_des.setVisibility(View.GONE);
        app_txt.setVisibility(View.GONE);
        //amount.setVisibility(View.GONE);
    }

    private void getSliderimg() {

        showprogressbar(true);

            dataViewModel.getSliderImage(getContext()).observe(getViewLifecycleOwner(), new Observer<SignUpResult>() {
                @Override
                public void onChanged(SignUpResult result) {

                    //hideLoader();
                    if(result!=null) {


                        FirstPropertyData firstPropertyData = result.getFirstPropertyData();

                        if (firstPropertyData==null) {
                            sliderView.setVisibility(View.GONE);
                            cv_one_login.setVisibility(View.GONE);
                            app_des.setVisibility(View.GONE);
                            app_txt.setVisibility(View.GONE);
                            showprogressbar(false);
                            Toast.makeText(getActivity(), "No Data Found", Toast.LENGTH_SHORT).show();
                        } else {

                            sliderView.setVisibility(View.VISIBLE);
                            cv_one_login.setVisibility(View.VISIBLE);
                            app_des.setVisibility(View.VISIBLE);
                            app_txt.setVisibility(View.VISIBLE);

                            restPropertyDatum = result.getRestPropertyData();

                            name.setText(firstPropertyData.getPropertyName());
                             locat.setText(firstPropertyData.getAddress());
                            lastbid.setText("Last Bid Amount- AED " + firstPropertyData.getLastBid());
                            amount.setText("AED "+firstPropertyData.getStartAmount());
                            bid_amount.setText("AED "+firstPropertyData.getCurrentBidAmount());

                            cv_one_login.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent=new Intent(getActivity(), DetailsActivity.class);
                                    intent.putExtra("propertyid",String.valueOf(firstPropertyData.getPropertyId()));
                                    startActivity(intent);
                                }
                            });

                            List<Image> images= firstPropertyData.getImages();

                            List<String> slider=new ArrayList<>();

                            for(int i=0;i<images.size();i++){
                                if(i<5)
                                slider.add(images.get(i).getPropertyImage());
                                else
                                    break;
                            }
                            showprogressbar(false);
                            sliderAdapter = new SliderAdapter(getActivity(), slider);
                            sliderView.setSliderAdapter(sliderAdapter);
                            sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

                            sliderView.setScrollTimeInSec(3);
                            sliderView.setAutoCycle(true);
                            sliderView.startAutoCycle();

                            if(restPropertyDatum.size()==0){
                                app_des.setVisibility(View.GONE);
                                app_txt.setVisibility(View.GONE);
                            }
                            else{
                                showprogressbar(true);
                                getcatData(restPropertyDatum);
                            }

                        }
                    }
                    else{
                    Toast.makeText(getActivity(),"No Data Found",Toast.LENGTH_SHORT).show();
                        showprogressbar(false);
                    }

                }


            });

    }

    private void getcatData(List<RestPropertyDatum> restPropertyDatum) {

        showprogressbar(false);

        approved_propertyAdapter = new Approved_PropertyAdapter(getActivity(), restPropertyDatum);

        rec_biding.setAdapter(approved_propertyAdapter);
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






