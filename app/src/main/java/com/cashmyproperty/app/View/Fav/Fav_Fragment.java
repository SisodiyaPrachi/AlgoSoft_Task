package com.cashmyproperty.app.View.Fav;

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
import com.cashmyproperty.app.View.Adapter.FavAdapter_Recycler;
import com.cashmyproperty.app.View.Response.BidPropertyDatum;
import com.cashmyproperty.app.View.Response.PropertywishlistDatum;
import com.cashmyproperty.app.View.Response.Result;
import com.cashmyproperty.app.View.ViewModel.DataViewModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class Fav_Fragment extends Fragment {

    View view;
    RecyclerView rec_fav;
    MaterialToolbar tool_bar;
    FavAdapter_Recycler favAdapter_recycler;
    DataViewModel dataViewModel;
    private ProgressDialog progress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_fav, container, false);

        ((Navigation_Activity)getActivity()).setDrawer_Locked();

        dataViewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);

        initviews();

        tool_bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        get_favlist();

        return view;
    }

    private void get_favlist() {

        showprogressbar(true);

        dataViewModel.getfavlist(getActivity()).observe(getActivity(), new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                //hideLoader();
                if (result != null) {

                    List<PropertywishlistDatum> propertywishlistData=result.getPropertywishlistData();

                    if (propertywishlistData.size()>0) {
                        showprogressbar(false);
                        favAdapter_recycler = new FavAdapter_Recycler(getActivity(),propertywishlistData);
                        rec_fav.setAdapter(favAdapter_recycler);

                    }
                    else {
                        Toast.makeText(getActivity(),"No Data Found",Toast.LENGTH_LONG).show();
                        showprogressbar(false);
                    }

                }
                else{
                    Toast.makeText(getActivity(),"No Data Found",Toast.LENGTH_SHORT).show();
                    showprogressbar(false);
                }
            }
        });


    }

    private void initviews() {
        rec_fav = view.findViewById(R.id.rec_fav);

        tool_bar=view.findViewById(R.id.tool_bar);
        tool_bar.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);

        rec_fav.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onStart() {
        super.onStart();
        ((Navigation_Activity) getActivity()).setDrawer_Locked();
    }


    @Override
    public void onStop() {
        super.onStop();
        ((Navigation_Activity) getActivity()).setDrawer_UnLocked();

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
