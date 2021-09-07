package com.cashmyproperty.app.View.Category;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Activities.Navigation_Seller;
import com.cashmyproperty.app.View.Adapter.SellerProperty_Recycler;
import com.cashmyproperty.app.View.Response.MypropertyDatum;
import com.cashmyproperty.app.View.Response.Result;
import com.cashmyproperty.app.View.ViewModel.DataViewModel;
import com.google.android.material.appbar.MaterialToolbar;


import java.util.List;

public class SellerPropertyFragment extends Fragment {
    View view;
    RecyclerView property_recycler;
    SellerProperty_Recycler sellerProperty_recycler;
    MaterialToolbar tool_bar;
    private ProgressDialog progress;
    DataViewModel dataViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_sellerproperty, container, false);

        property_recycler=view.findViewById(R.id.property_recycler);

        dataViewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);
        ((Navigation_Seller)getActivity()).setDrawer_Locked();
       // ((Navigation_Seller)getActivity()).setbottom_Locked();


        tool_bar=view.findViewById(R.id.tool_bar);
        tool_bar.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);

        property_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        tool_bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        getpropertyData();

        return view;
    }

    private void getpropertyData() {

        showprogressbar(true);

        dataViewModel.getmyproperty(getActivity()).observe(getActivity(), new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                //hideLoader();
                if (result != null) {
                    {
                        List<MypropertyDatum> mypropertyData = result.getMypropertyData();

                        if (mypropertyData == null) {
                            Toast.makeText(getActivity(), "No Data Found", Toast.LENGTH_LONG).show();
                        } else {
                            showprogressbar(false);
                            sellerProperty_recycler = new SellerProperty_Recycler(getActivity(), mypropertyData);
                            property_recycler.setAdapter(sellerProperty_recycler);
                        }

                    }
                }
            }


        });
    }

    @Override
    public void onStart() {
        super.onStart();
        ((Navigation_Seller)getActivity()).setDrawer_Locked();
       // ((Navigation_Seller)getActivity()).setbottom_Locked();
    }


    @Override
    public void onStop() {
        super.onStop();


        ((Navigation_Seller)getActivity()).setDrawer_UnLocked();
       // ((Navigation_Seller)getActivity()).setbottom_UnLocked();
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