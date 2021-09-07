package com.cashmyproperty.app.View.Steps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Activities.Navigation_Seller;
import com.cashmyproperty.app.View.Adapter.KindPropertyAdapter;
import com.cashmyproperty.app.View.Adapter.PropertyTypeAdapter;
import com.cashmyproperty.app.View.Response.KindOfProperty;
import com.cashmyproperty.app.View.Response.PropertyData;
import com.cashmyproperty.app.View.Response.PropertyType;
import com.cashmyproperty.app.View.Response.SellerData;
import com.cashmyproperty.app.View.Response.SignUpResponse;
import com.cashmyproperty.app.View.Utility.PreferenceUtils;
import com.cashmyproperty.app.View.ViewModel.DataViewModel;
import com.cashmyproperty.app.View.network.ApiService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddDetails_Fragment extends Fragment implements KindPropertyAdapter.OnclickListener,PropertyTypeAdapter.OnclickListener {
    Button btn_next;
    RecyclerView rec_kindproperty, rec_propertytype;
    View view;
    String cat_id="",sub_catid,prop="",category_name;

    DataViewModel dataViewModel;
    ImageView back;
    TextView kind_property,select_prop_type;
    private ProgressDialog progress;

    KindPropertyAdapter kindPropertyAdapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_add_details, container, false);


        ((Navigation_Seller) getActivity()).setDrawer_Locked();
        ((Navigation_Seller) getActivity()).setbottom_Locked();

        dataViewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);

        initviews();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        btn_next = view.findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sub_stepone();

            }
        });

        setStepview();



        return view;
    }

    private void initviews() {
        rec_kindproperty = view.findViewById(R.id.rec_kindproperty);
        rec_kindproperty.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        kind_property=view.findViewById(R.id.kind_property);
        select_prop_type=view.findViewById(R.id.select_prop_type);

        kind_property.setVisibility(View.GONE);
        select_prop_type.setVisibility(View.GONE);

        rec_propertytype = view.findViewById(R.id.rec_propertytype);
        rec_propertytype.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        back=view.findViewById(R.id.back);


    }

    private void sub_stepone() {

        showprogressbar(true);

        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(getActivity());

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(getActivity(),PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(getActivity(),PreferenceUtils.UserToken));
        params.put("kind_of_property",cat_id);
        params.put("property_type",sub_catid);

        Call<SignUpResponse> call = apiService.createFactoryApi().stepsubmit(headerMap, params);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.e("customerSignUpForm", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    SignUpResponse responseClass = response.body();
                    if (responseClass.getSuccess() == 1) {
                        PropertyData data = responseClass.getResult().getPropertyData();

                        PreferenceUtils.setStringValue(getActivity(), PreferenceUtils.PropertyID, String.valueOf(data.getPropertyId()));
                        showprogressbar(false);
                        Toast.makeText(getActivity(), responseClass.getMessage(), Toast.LENGTH_SHORT).show();
                       /* FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        Step_Second step_second = new Step_Second();
                        fragmentTransaction.replace(R.id.nav_host_fragment, step_second);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();*/
                        Intent intent=new Intent(getActivity(),Step_Second.class);
                        intent.putExtra("cat_name",category_name);
                        startActivity(intent);

                    }  else
                        Toast.makeText(getActivity(), responseClass.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                // hideLoader();
                // showSnackbar();
                Log.e("sub_stepone", " - > Error    " + t.getMessage());
            }
        });


    }

    private void setStepview() {

        showprogressbar(true);

        dataViewModel.getProductData(getActivity()).observe(getActivity(), new Observer<List<KindOfProperty>>() {
            @Override
            public void onChanged(List<KindOfProperty> products) {
                //hideLoader();
                if (products != null) {
                    {

                        showprogressbar(false);
                        kind_property.setVisibility(View.VISIBLE);
                        select_prop_type.setVisibility(View.VISIBLE);
                        kindPropertyAdapter = new KindPropertyAdapter(getActivity(),products,AddDetails_Fragment.this);
                        rec_kindproperty.setAdapter(kindPropertyAdapter);

                    }
                }
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        ((Navigation_Seller) getActivity()).setDrawer_Locked();
        ((Navigation_Seller)getActivity()).setbottom_Locked();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((Navigation_Seller) getActivity()).setDrawer_UnLocked();
        ((Navigation_Seller)getActivity()).setbottom_UnLocked();
    }



    @Override
    public void onClick(ArrayList<PropertyType> propertyType,String categoryid,String cat_name) {

        cat_id=categoryid;
        category_name=cat_name;

        PropertyTypeAdapter propertyTypeAdapter = new PropertyTypeAdapter(getActivity(),propertyType,AddDetails_Fragment.this);
        rec_propertytype.setAdapter(propertyTypeAdapter);

    }


    @Override
    public void onClick(String string) {
        sub_catid=string;
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



