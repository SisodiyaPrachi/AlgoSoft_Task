package com.cashmyproperty.app.View.Category;

import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Activities.Navigation_Activity;
import com.cashmyproperty.app.View.Activities.Navigation_Seller;
import com.cashmyproperty.app.View.Adapter.SoldAdapter_Recycler;
import com.cashmyproperty.app.View.Response.Result;
import com.cashmyproperty.app.View.Response.SoldPropertyDatum;
import com.cashmyproperty.app.View.Utility.PreferenceUtils;
import com.cashmyproperty.app.View.ViewModel.DataViewModel;
import com.google.android.material.appbar.MaterialToolbar;


import java.util.List;

public class SoldPropertyFragment extends Fragment {

    RecyclerView rec_soldproperty;
    SoldAdapter_Recycler soldAdapter_recycler;
    DataViewModel dataViewModel;
    MaterialToolbar tool_bar;
    private ProgressDialog progress;
    public SoldPropertyFragment(){

    }

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frag_soldproperty, container, false);

        dataViewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);

        if (PreferenceUtils.getStringValue(getActivity(), PreferenceUtils.UserType).equalsIgnoreCase("Seller"))
        {  ((Navigation_Seller) getActivity()).setDrawer_Locked();
        ((Navigation_Seller) getActivity()).setbottom_Locked();
    }
        else{
            ((Navigation_Activity)getActivity()).setDrawer_Locked();
            ((Navigation_Activity)getActivity()).setbottom_Locked();
        }


        rec_soldproperty = view.findViewById(R.id.rec_soldproperty);

        tool_bar=view.findViewById(R.id.tool_bar);
        tool_bar.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);

        rec_soldproperty.setLayoutManager(new LinearLayoutManager(getActivity()));

        tool_bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        getsoldprop_Data();

        return view;
    }

    private void getsoldprop_Data() {

        showprogressbar(true);

        dataViewModel.getSoldProperty(getActivity()).observe(getActivity(), new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                //hideLoader();
                if (result != null) {
                    {
                        List<SoldPropertyDatum> soldPropertyData=result.getSoldPropertyData();

                        if (soldPropertyData.size()==0) {
                            Toast.makeText(getActivity(),"No Data Found",Toast.LENGTH_LONG).show();
                        }
                        else {
                            showprogressbar(false);
                            soldAdapter_recycler = new SoldAdapter_Recycler(getActivity(),soldPropertyData);
                            rec_soldproperty.setAdapter(soldAdapter_recycler);
                        }

                    }
                }
            }
        });

       /* ArrayList<Integer> img= new ArrayList<>();


        img.add(R.drawable.img1_villa);
        img.add(R.drawable.img2_villa);
        img.add(R.drawable.img3_villa);
        img.add(R.drawable.img4_villa);
        img.add(R.drawable.img5_villa);*/




    }

    @Override
    public void onStart() {
        super.onStart();
        if (PreferenceUtils.getStringValue(getActivity(), PreferenceUtils.UserType).equalsIgnoreCase("Seller"))
        {
            ((Navigation_Seller) getActivity()).setDrawer_Locked();
        ((Navigation_Seller) getActivity()).setbottom_Locked();
    }
        else{
            ((Navigation_Activity)getActivity()).setDrawer_Locked();
            ((Navigation_Activity)getActivity()).setbottom_Locked();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if(PreferenceUtils.getStringValue(getActivity(),PreferenceUtils.UserType).equalsIgnoreCase("Seller")) {
            ((Navigation_Seller) getActivity()).setDrawer_UnLocked();
            ((Navigation_Seller) getActivity()).setbottom_UnLocked();
        }
        else{
            ((Navigation_Activity)getActivity()).setDrawer_UnLocked();
            ((Navigation_Activity)getActivity()).setbottom_UnLocked();
        }
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