package com.cashmyproperty.app.View.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cashmyproperty.app.View.Repository.DataRepository;
import com.cashmyproperty.app.View.Response.DatabycatResult;
import com.cashmyproperty.app.View.Response.KindOfProperty;
import com.cashmyproperty.app.View.Response.LocationDatum;
import com.cashmyproperty.app.View.Response.Result;
import com.cashmyproperty.app.View.Response.SignUpResult;

import java.util.List;

public class DataViewModel extends AndroidViewModel {

    private DataRepository dataRepository;

    public DataViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new DataRepository(application);
    }

    public LiveData<List<KindOfProperty>> getProductData(Context context) {
        return dataRepository.getKindProperty(context);
    }

    public LiveData<SignUpResult> getSliderImage(Context context) {
        return dataRepository.getSliderImage(context);
    }

    public LiveData<SignUpResult> getDetails(Context context,String propid) {
        return dataRepository.getDetails(context,propid);
    }

    public LiveData<SignUpResult> getbuyerDetails(Context context,String propid) {
        return dataRepository.getbuyerDetails(context,propid);
    }


    public LiveData<SignUpResult> getCategoryDetails(Context context) {
        return dataRepository.getCategoryDetails(context);
    }

    public LiveData<Result> getSubCategoryDetails(Context context) {
        return dataRepository.getSubCategoryDetails(context);
    }

    public LiveData<DatabycatResult> getDatabyCategoryDetails(Context context) {
        return dataRepository.getDatabyCategoryDetails(context);
    }

    public LiveData<Result> getSearchDetails(Context context,String search_type) {
        return dataRepository.getSearchDetails(context,search_type);
    }

    public LiveData<SignUpResult> getTermsandCondition(Context context) {
        return dataRepository.getTermsandCondition(context);
    }

    public LiveData<Result> getSlider_Agent(Context context) {
        return dataRepository.getSlider_Agent(context);
    }

    public LiveData<Result> getSoldProperty(Context context) {
        return dataRepository.getSoldProperty(context);
    }
    public LiveData<Result> getSoldPropertyDetails(Context context,String propid) {
        return dataRepository.getSoldPropertyDetails(context,propid);
    }

    public LiveData<Result> getbidproperty(Context context) {
        return dataRepository.getbidproperty(context);
    }

    public LiveData<Result> getbidpropertydetails(Context context,String propid) {
        return dataRepository.getbidpropertydetails(context,propid);
    }

    public LiveData<Result> getmyproperty(Context context) {
        return dataRepository.getmyproperty(context);
    }

    public LiveData<Result> getsellerpropertydetails(Context context,String propid) {
        return dataRepository.getsellerpropertydetails(context,propid);
    }

    public LiveData<Result> getpurchaseproperty(Context context) {
        return dataRepository.getpurchaseproperty(context);
    }

    public LiveData<Result> getpurchasepropertydetails(Context context,String propid) {
        return dataRepository.getpurchasepropertydetails(context,propid);
    }

    public LiveData<Result> getbuyer_homepage(Context context) {
        return dataRepository.getbuyer_homepage(context);
    }

    public LiveData<Result> getfavlist(Context context) {
        return dataRepository.getfavlist(context);
    }

}
