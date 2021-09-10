package com.cashmyproperty.app.View.Search;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Activities.Navigation_Activity;
import com.cashmyproperty.app.View.Activities.Navigation_Seller;
import com.cashmyproperty.app.View.Adapter.ActClosing_Recycler;
import com.cashmyproperty.app.View.Adapter.ExplorePopular_Recycler;
import com.cashmyproperty.app.View.Adapter.Recomm_Recycler;
import com.cashmyproperty.app.View.Adapter.SliderAdapter_Agent;
import com.cashmyproperty.app.View.Adapter.SliderAdapter_Recyler;
import com.cashmyproperty.app.View.DetailPage.DetailsActivity;
import com.cashmyproperty.app.View.Response.AuctionclosingProperty;
import com.cashmyproperty.app.View.Response.ExplorepopularArea;
import com.cashmyproperty.app.View.Response.HometopProperty;
import com.cashmyproperty.app.View.Response.Image;
import com.cashmyproperty.app.View.Response.RecommendedProperty;
import com.cashmyproperty.app.View.Response.Result;
import com.cashmyproperty.app.View.Utility.PreferenceUtils;
import com.cashmyproperty.app.View.ViewModel.DataViewModel;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class Search extends Fragment {

    RecyclerView rec_cat_agent, rec_buy_rent, rec_recommended;
    ActClosing_Recycler actClosing_adapter;
    ExplorePopular_Recycler explorePopular_recycler;

    SliderView sliderView;
    SliderAdapter_Recyler sliderAdapter_recyler;
    Recomm_Recycler recomm_recycler;
    CardView cv_one_login;

    View view;
    RecyclerView recyclerView;
    ImageView img_back;
    SearchView searchbar;
    DataViewModel dataViewModel;
    String search_type;
    NestedScrollView nested_scroll;
    private ProgressDialog progress;
    TextView name, locat, lastbid,txt_amount, txt_action, act_des, txt_exp, exp_des, txt_rec, rec_text, amount, bid_amount,chk_offer;

    public Search() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_homeagent, container, false);

        if (PreferenceUtils.getStringValue(getActivity(), PreferenceUtils.UserType).equalsIgnoreCase("Seller"))
            ((Navigation_Seller) getActivity()).setDrawer_Locked();
        else
            ((Navigation_Activity) getActivity()).setDrawer_Locked();
        dataViewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);

        initviews();

        getSliderimg();

        /*search_bar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SearchFragment searchFragment = new SearchFragment();
                fragmentTransaction.replace(R.id.nav_host_fragment, searchFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return false;
            }
        });*/

        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search_type = query;
                if (search_type.length()>0) {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    SearchFragment searchFragment = new SearchFragment();
                    fragmentTransaction.replace(R.id.nav_host_fragment, searchFragment);
                    Bundle bundle=new Bundle();
                    bundle.putString("Search_type",search_type);
                    searchFragment.setArguments(bundle);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    return false;

                } else {
                    Toast.makeText(getActivity(), "Please Search the city", Toast.LENGTH_LONG).show();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter.getFilter().filter(newText);
                return false;
            }
        });

      /*  search_bar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SearchFragment searchFragment = new SearchFragment();
                fragmentTransaction.replace(R.id.nav_host_fragment, searchFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return false;
            }
        });*/


        return view;
    }

    private void initviews() {

        rec_cat_agent = view.findViewById(R.id.rec_cat_agent);
        rec_buy_rent = view.findViewById(R.id.rec_buy_rent);
        rec_recommended = view.findViewById(R.id.rec_recommended);
        sliderView = view.findViewById(R.id.slider);
        //search_bar = view.findViewById(R.id.search_bar);
        name = view.findViewById(R.id.name);
        locat = view.findViewById(R.id.locat);
        lastbid = view.findViewById(R.id.lastbid);
        amount = view.findViewById(R.id.amount);
        chk_offer=view.findViewById(R.id.chk_offer);
        txt_amount=view.findViewById(R.id.txt_amount);
        //agent_homelyt=view.findViewById(R.id.agent_homelyt);
        bid_amount = view.findViewById(R.id.bid_amount);
        cv_one_login = view.findViewById(R.id.cv_one_login);
        txt_action = view.findViewById(R.id.txt_action);
        act_des = view.findViewById(R.id.act_des);
        txt_exp = view.findViewById(R.id.txt_exp);
        nested_scroll = view.findViewById(R.id.nested_scroll);
        exp_des = view.findViewById(R.id.exp_des);
        txt_rec = view.findViewById(R.id.txt_rec);
//        rec_text=view.findViewById(R.id.rec_text);
        rec_cat_agent.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rec_buy_rent.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rec_recommended.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        cv_one_login.setVisibility(View.GONE);

        txt_amount.setVisibility(View.GONE);
        amount.setVisibility(View.GONE);

        searchbar = view.findViewById(R.id.search_bar);
        img_back = view.findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

    }

    private void getSliderimg() {

        showprogressbar(true);

        dataViewModel.getSlider_Agent(getContext()).observe(getViewLifecycleOwner(), new Observer<Result>() {
            @Override
            public void onChanged(Result result) {

                //hideLoader();
                if (result != null) {


                    List<HometopProperty> hometopProperty = result.getHometopProperty();
                    //List<Image> images = hometopProperty.get(0).getImages();
                    List<AuctionclosingProperty> auctionclosingProperties = result.getAuctionclosingProperty();
                    List<ExplorepopularArea> explorepopularAreas = result.getExplorepopularArea();
                    List<RecommendedProperty> recommendedProperties = result.getRecommendedProperty();

                    if (hometopProperty.size() > 0) {
                        cv_one_login.setVisibility(View.VISIBLE);
                        name.setText(hometopProperty.get(0).getPropertyName());
                        locat.setText(hometopProperty.get(0).getAddress());
                        lastbid.setText("Last Bid Amount- AED " + hometopProperty.get(0).getLastBidAmount());
                        amount.setText(hometopProperty.get(0).getStartAmount());
                        bid_amount.setText("AED "+hometopProperty.get(0).getCurrentBidAmount());

                        cv_one_login.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                                intent.putExtra("propertyid", String.valueOf(hometopProperty.get(0).getPropertyId()));
                                startActivity(intent);
                            }
                        });

                    } else {
                        cv_one_login.setVisibility(View.GONE);
                    }


                    List<String> slider = new ArrayList<>();
                    List<String> integers = new ArrayList<>();

                    for (int i = 0; i < hometopProperty.size(); i++) {
                        for (int j = 0; j < hometopProperty.get(i).getImages().size(); j++) {
                            integers.add(String.valueOf(hometopProperty.get(i).getImages().get(j).getPropertyImage()));

                        }
                    }

                    int count = integers.size();

                    for (int k = 0; k < count; k++) {
                        if (k < 5)
                            slider.add(integers.get(k));
                        else
                            break;
                    }

                    showprogressbar(false);
                    SliderAdapter_Agent sliderAdapter_agent = new SliderAdapter_Agent(getActivity(), slider);
                    sliderView.setSliderAdapter(sliderAdapter_agent);

                    sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
                    sliderView.setScrollTimeInSec(3);
                    sliderView.setAutoCycle(true);
                    sliderView.startAutoCycle();

                    getact_closingsoon(auctionclosingProperties);

                    get_explorepopulararea(explorepopularAreas);

                    getrecommended_Data(recommendedProperties);

                } else {
                    Toast.makeText(getActivity(), "No Data Found", Toast.LENGTH_SHORT).show();
                    showprogressbar(false);
                }

            }


        });


    }

    private void get_explorepopulararea(List<ExplorepopularArea> explorepopularAreas) {
        showprogressbar(true);
        txt_exp.setVisibility(View.VISIBLE);
        exp_des.setVisibility(View.VISIBLE);

        showprogressbar(false);
        explorePopular_recycler = new ExplorePopular_Recycler(getActivity(), explorepopularAreas);

        rec_buy_rent.setAdapter(explorePopular_recycler);
    }

    private void getact_closingsoon(List<AuctionclosingProperty> auctionclosingProperties) {

        showprogressbar(true);

        txt_action.setVisibility(View.VISIBLE);
        act_des.setVisibility(View.VISIBLE);

        showprogressbar(false);
        actClosing_adapter = new ActClosing_Recycler(getActivity(), auctionclosingProperties);

        rec_cat_agent.setAdapter(actClosing_adapter);

    }


    private void getrecommended_Data(List<RecommendedProperty> recommendedProperties) {

        showprogressbar(true);

        txt_rec.setVisibility(View.VISIBLE);
        //rec_text.setVisibility(View.VISIBLE);

        showprogressbar(false);
        recomm_recycler = new Recomm_Recycler(getActivity(), recommendedProperties);

        rec_recommended.setAdapter(recomm_recycler);
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

    @Override
    public void onStart() {
        super.onStart();
        if (PreferenceUtils.getStringValue(getActivity(), PreferenceUtils.UserType).equalsIgnoreCase("Seller"))
            ((Navigation_Seller) getActivity()).setDrawer_Locked();
        else
            ((Navigation_Activity) getActivity()).setDrawer_Locked();

    }

    @Override
    public void onStop() {
        super.onStop();
        if (PreferenceUtils.getStringValue(getActivity(), PreferenceUtils.UserType).equalsIgnoreCase("Seller"))
            ((Navigation_Seller) getActivity()).setDrawer_UnLocked();
        else
            ((Navigation_Activity) getActivity()).setDrawer_UnLocked();

    }
}
