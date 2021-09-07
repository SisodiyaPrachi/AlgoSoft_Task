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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Activities.Navigation_Activity;
import com.cashmyproperty.app.View.Adapter.Category_Adapter;
import com.cashmyproperty.app.View.Response.CategoryDatum;
import com.cashmyproperty.app.View.Response.SignUpResult;
import com.cashmyproperty.app.View.Utility.PreferenceUtils;
import com.cashmyproperty.app.View.ViewModel.DataViewModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class CategoryFragment extends Fragment implements Category_Adapter.OnclickListener {

    RecyclerView rec_category;
    Category_Adapter category_adapter;
    MaterialToolbar tool_bar;
    private ProgressDialog progress;
    DataViewModel dataViewModel;

    public CategoryFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nav_fragment, container, false);

        ((Navigation_Activity) getActivity()).setDrawer_Locked();

        dataViewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);

        rec_category = view.findViewById(R.id.rec_category);
        RecyclerView.LayoutManager recyclerViewLayoutManager = new GridLayoutManager(getActivity(), 2);
        rec_category.setLayoutManager(recyclerViewLayoutManager);

        //Change 2 to your choice because here 2 is the number of Grid layout Columns in each row.

        tool_bar = view.findViewById(R.id.tool_bar);
        tool_bar.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);


        tool_bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        getcatData();

        return view;
    }

    private void getcatData() {

        showprogressbar(true);

        dataViewModel.getCategoryDetails(getActivity()).observe(getActivity(), new Observer<SignUpResult>() {
            @Override
            public void onChanged(SignUpResult result) {
                //hideLoader();
                if (result != null) {
                    {

                        List<CategoryDatum> categoryDatum = result.getCategoryData();

                        showprogressbar(false);
                        category_adapter = new Category_Adapter(getActivity(), categoryDatum, CategoryFragment.this);

                        rec_category.setAdapter(category_adapter);

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
        ((Navigation_Activity) getActivity()).setDrawer_Locked();
    }


    @Override
    public void onStop() {
            super.onStop();
            ((Navigation_Activity) getActivity()).setDrawer_UnLocked();

    }

    @Override
    public void onClick(String categoryName, String categoryID) {

        PreferenceUtils.setStringValue(getActivity(), PreferenceUtils.Category_ID, categoryID);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SubCategoryFragment resedentialFragment = new SubCategoryFragment();
        fragmentTransaction.replace(R.id.nav_host_fragment, resedentialFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

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
