package com.cashmyproperty.app.View.Search;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cashmyproperty.app.View.Activities.Navigation_Activity;
import com.cashmyproperty.app.View.Activities.Navigation_Seller;
import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Activities.Search_Activity;
import com.cashmyproperty.app.View.Category.SubCategoryFragment;
import com.cashmyproperty.app.View.Home.HomeFragment_Agent;
import com.cashmyproperty.app.View.Response.PropertyDatum;
import com.cashmyproperty.app.View.Response.Result;
import com.cashmyproperty.app.View.Adapter.SearchRecycler_Adapter;
import com.cashmyproperty.app.View.Utility.PreferenceUtils;
import com.cashmyproperty.app.View.ViewModel.DataViewModel;

import java.util.List;

public class SearchFragment extends Fragment {

    RecyclerView recyclerView;
    ImageView img_back;
    SearchView searchbar;
    DataViewModel dataViewModel;
    String search_type="";
    private ProgressDialog progress;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_homerec,container,false);




        if(PreferenceUtils.getStringValue(getActivity(),PreferenceUtils.UserType).equalsIgnoreCase("Seller"))
            ((Navigation_Seller) getActivity()).setDrawer_Locked();
          else {
            Bundle bundle = this.getArguments();
            search_type= bundle.getString("Search_type");
            ((Navigation_Activity) getActivity()).setDrawer_Locked();
        }

      //  Toast.makeText(getActivity(),"No Data Found",Toast.LENGTH_SHORT).show();

        dataViewModel = ViewModelProviders.of(getActivity()).get(DataViewModel.class);


        recyclerView = view.findViewById(R.id.rec_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchbar=view.findViewById(R.id.search_bar);
        img_back=view.findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        if(search_type.length()>0){
            searchViews();
        }
        else{
            Toast.makeText(getActivity(),"Please Enter the city",Toast.LENGTH_LONG).show();
        }
        searchbar.setQuery(search_type,true);
        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search_type=query;
                if(search_type.length()>0){
                    searchViews();
                }else{
                    Toast.makeText(getActivity(),"Please Enter the city",Toast.LENGTH_LONG).show();
                }
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter.getFilter().filter(newText);
                return false;
            }
        });

       /* searchbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (searchbar.getRight() - searchbar.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        search_type=searchbar.getText().toString();
                        if(search_type.equals("")){
                            searchbar.setError("Please search the city");
                        }
                        else {
                            searchViews();
                        }
                        return true;
                    }
                }
                return false;
            }
        });
*/


    return view ;
    }


    private void searchViews() {

        showprogressbar(true);

        dataViewModel.getSearchDetails(getActivity(),search_type).observe(getActivity(), new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                //hideLoader();
                if (result != null) {


                    List<PropertyDatum> propertyData = result.getPropertyData();

                    if (propertyData.size()>0) {

                        SearchRecycler_Adapter adapter = new SearchRecycler_Adapter(getActivity(), propertyData);
                        recyclerView.setAdapter(adapter);
                        showprogressbar(false);
                       // Toast.makeText(getActivity(), "hhhhhh", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getActivity(), "No Data Found", Toast.LENGTH_LONG).show();
                        recyclerView.setAdapter(null);
                        showprogressbar(false);
                    }
                }
                    else {
                        Toast.makeText(getActivity(),"No Data Found",Toast.LENGTH_SHORT).show();
                    showprogressbar(false);
                    }
                }

        });
        /*ArrayList<String> image_name=new ArrayList<>();
        ArrayList<String> image_description=new ArrayList<>();
        ArrayList<Integer> image_urls=new ArrayList<>();

        image_name.add("Flat");
        image_name.add("Home");
        image_name.add("Villa");
        image_name.add("Furnished Flat/Home");

        image_description.add("Citadel Apartments boasts manicured landscaping and an ample amenity package.  ");

        image_description.add(   "This three-bedroom home was built in 1956 by noted local architect.  ");
                image_description.add(     "A villa is a type of house that was originally an ancient Roman .");
                image_description.add(    "The term furnished apartment means different things.");



        image_urls.add(    R.drawable.store);
        image_urls.add(    R.drawable.villa_search);
                image_urls.add(      R.drawable.home_search);
        image_urls.add(    R.drawable.furnished);*/






    }


    @Override
    public void onStart() {
        super.onStart();
        if(PreferenceUtils.getStringValue(getActivity(),PreferenceUtils.UserType).equalsIgnoreCase("Seller"))
            ((Navigation_Seller) getActivity()).setDrawer_Locked();
        else
            ((Navigation_Activity)getActivity()).setDrawer_Locked();

    }

    @Override
    public void onStop() {
        super.onStop();
        if(PreferenceUtils.getStringValue(getActivity(),PreferenceUtils.UserType).equalsIgnoreCase("Seller"))
            ((Navigation_Seller) getActivity()).setDrawer_UnLocked();
        else
            ((Navigation_Activity)getActivity()).setDrawer_UnLocked();


    }

    private void showprogressbar(Boolean IS_SHOW) {
        if (IS_SHOW) {
            progress = ProgressDialog.show(getActivity(), "", "PLEASE WAIT...", true);
        } else {
            progress.dismiss();
        }
    }

}

