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
import com.cashmyproperty.app.View.Activities.Navigation_Activity;
import com.cashmyproperty.app.View.Adapter.BidAdapter_Recycler;
import com.cashmyproperty.app.View.Response.BidPropertyDatum;
import com.cashmyproperty.app.View.Response.Result;
import com.cashmyproperty.app.View.ViewModel.DataViewModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class BidsFragment extends Fragment {

    RecyclerView rec_bids;
    MaterialToolbar tool_bar;
    BidAdapter_Recycler bidAdapter_recycler;
    DataViewModel dataViewModel;
    private ProgressDialog progress;
    public BidsFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_bids, container, false);

        ((Navigation_Activity)getActivity()).setDrawer_Locked();
        ((Navigation_Activity)getActivity()).setbottom_Locked();

        dataViewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);

        rec_bids = view.findViewById(R.id.rec_bids);

        tool_bar=view.findViewById(R.id.tool_bar);
        tool_bar.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);

        rec_bids.setLayoutManager(new LinearLayoutManager(getActivity()));

        tool_bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        getbidData();

        return view;
    }

    private void getbidData() {

        showprogressbar(true);

        dataViewModel.getbidproperty(getActivity()).observe(getActivity(), new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                //hideLoader();
                if (result != null) {

                        List<BidPropertyDatum> bidPropertyData=result.getBidPropertyData();

                        if (bidPropertyData.size()==0) {
                            Toast.makeText(getActivity(),"No Data Found",Toast.LENGTH_LONG).show();
                            showprogressbar(false);
                        }
                        else {
                            showprogressbar(false);
                            bidAdapter_recycler = new BidAdapter_Recycler(getActivity(),bidPropertyData);
                            rec_bids.setAdapter(bidAdapter_recycler);
                        }

                }
                else{
                    Toast.makeText(getActivity(),"No Data Found",Toast.LENGTH_SHORT).show();
                    showprogressbar(false);
                }
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        ((Navigation_Activity)getActivity()).setDrawer_Locked();
        ((Navigation_Activity)getActivity()).setbottom_Locked();
    }


    @Override
    public void onStop() {
        super.onStop();


        ((Navigation_Activity)getActivity()).setDrawer_UnLocked();
        ((Navigation_Activity)getActivity()).setbottom_UnLocked();
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


