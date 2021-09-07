package com.cashmyproperty.app.View.Category;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Activities.Navigation_Activity;
import com.cashmyproperty.app.View.Adapter.SubCategory_Adapter;
import com.cashmyproperty.app.View.Response.CategoryData;
import com.cashmyproperty.app.View.Response.Result;
import com.cashmyproperty.app.View.Response.SubCategoryDatum;
import com.cashmyproperty.app.View.Utility.PreferenceUtils;
import com.cashmyproperty.app.View.ViewModel.DataViewModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class SubCategoryFragment extends Fragment implements SubCategory_Adapter.OnclickListener{

    RecyclerView rec_category;
    SubCategory_Adapter resedential_adapter;
    MaterialToolbar tool_bar;
    DataViewModel dataViewModel;
    private ProgressDialog progress;

    public SubCategoryFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_res,container,false);

        ((Navigation_Activity)getActivity()).setDrawer_Locked();

        dataViewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);

        rec_category = view.findViewById(R.id.rec_category);

        tool_bar=view.findViewById(R.id.tool_bar);
        tool_bar.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);

        RecyclerView.LayoutManager recyclerViewLayoutManager = new GridLayoutManager(getActivity(), 2);

        rec_category.setLayoutManager(recyclerViewLayoutManager);

        tool_bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        getresData();

        return view;
    }

    private void getresData() {

        showprogressbar(true);

        dataViewModel.getSubCategoryDetails(getActivity()).observe(getActivity(), new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                //hideLoader();
                if (result != null) {

                        CategoryData data=result.getCategoryData();
                       List<SubCategoryDatum> subCategoryData = result.getSubCategoryData();

                       tool_bar.setTitle(data.getCategoryName());

                        showprogressbar(false);

                        resedential_adapter = new SubCategory_Adapter(getActivity(), subCategoryData,SubCategoryFragment.this);

                        rec_category.setAdapter(resedential_adapter);



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
    }


   /* @Override
    public void onStop() {
        super.onStop();

        ((Navigation_Activity)getActivity()).setDrawer_UnLocked();
    }*/




    @Override
    public void onClick(String id) {

        PreferenceUtils.setStringValue(getActivity(), PreferenceUtils.SubCategory_ID, id);

        Intent intent=new Intent(getActivity(),DataByCategory_Activity.class);
        startActivity(intent);

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
