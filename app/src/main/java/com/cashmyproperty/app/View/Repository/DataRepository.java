package com.cashmyproperty.app.View.Repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cashmyproperty.app.View.Response.DatabycatResult;
import com.cashmyproperty.app.View.Response.DatabycategoryResponse;
import com.cashmyproperty.app.View.Response.Example;
import com.cashmyproperty.app.View.Response.KindOfProperty;
import com.cashmyproperty.app.View.Response.LocationDatum;
import com.cashmyproperty.app.View.Response.PropertyType;
import com.cashmyproperty.app.View.Response.Result;
import com.cashmyproperty.app.View.Response.SellerData;
import com.cashmyproperty.app.View.Response.SignUpResponse;
import com.cashmyproperty.app.View.Response.SignUpResult;
import com.cashmyproperty.app.View.Utility.PreferenceUtils;
import com.cashmyproperty.app.View.network.ApiService;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {


    public DataRepository(Context applicationContext) {
    }

    public static void setLogout(Context context) {

        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(context);

        HashMap<String, String> params = new HashMap<>();
        params.put("users_id", PreferenceUtils.getStringValue(context,PreferenceUtils.customer_id));
        params.put("users_token", PreferenceUtils.getStringValue(context,PreferenceUtils.UserToken));

        Call<SignUpResponse> call = apiService.createFactoryApi().logout(headerMap, params);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.e("customerVerification", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    SignUpResponse responseClass = response.body();
                    if (responseClass.getSuccess() == 1) {
                        SellerData data = responseClass.getResult().getUsersData();
                        //   Toast.makeText(applicationContext,resendtype,Toast.LENGTH_LONG).show();
                        Toast.makeText(context, responseClass.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, responseClass.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }



            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Log.e("customerVerification", " - > Error    " + t.getMessage());
            }
        });

    }

    public void resend_verification(Context applicationContext,String sellerid,String resendtype,String emailid) {
        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(applicationContext);

        HashMap<String, String> params = new HashMap<>();
        params.put("users_id", sellerid);
        params.put("resend_type", resendtype);
        params.put("users_email", emailid);


        Call<SignUpResponse> call = apiService.createFactoryApi().resendverification(headerMap, params);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.e("customerVerification", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    SignUpResponse responseClass = response.body();
                    if (responseClass.getSuccess() == 1) {
                        SellerData data = responseClass.getResult().getUsersData();
                     //   Toast.makeText(applicationContext,resendtype,Toast.LENGTH_LONG).show();
                        Toast.makeText(applicationContext, responseClass.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(applicationContext, responseClass.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }



            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Log.e("customerVerification", " - > Error    " + t.getMessage());
            }
        });
    }

    private MutableLiveData<List<KindOfProperty>> productData = new MutableLiveData<>();
    public MutableLiveData<List<KindOfProperty>> getKindProperty(Context context) {
        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(context);

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(context,PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(context,PreferenceUtils.UserToken));

        Call<SignUpResponse> call = apiService.createFactoryApi().stepfirst(headerMap, params);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.e("partnerProductData","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null){
                    if(response.body().getSuccess().equals(1)){
                        productData.setValue(response.body().getResult().getKindOfProperty());
                    }else{
                        productData.setValue(null);
//                        Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    productData.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                productData.setValue(null);
                Log.e("partnerProductData"," - > Error    "+t.getMessage());
            }
        });
        return productData;
    }

    private MutableLiveData<SignUpResult> getSliderImage = new MutableLiveData<>();
    public MutableLiveData<SignUpResult> getSliderImage(Context context) {
        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(context);

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(context,PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(context,PreferenceUtils.UserToken));

        Call<SignUpResponse> call = apiService.createFactoryApi().get_sliderhome(headerMap, params);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.e("partnerProductData","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null){
                    if(response.body().getSuccess()==1){
                        getSliderImage.setValue(response.body().getResult());
                    }else{
                        getSliderImage.setValue(null);
//                        Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getSliderImage.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                getSliderImage.setValue(null);
                Log.e("partnerProductData"," - > Error    "+t.getMessage());
            }
        });
        return getSliderImage;
    }

    private MutableLiveData<SignUpResult> getDetails = new MutableLiveData<>();
    public MutableLiveData<SignUpResult> getDetails(Context context,String propid) {
        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(context);

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(context,PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(context,PreferenceUtils.UserToken));
        params.put("property_id",propid);

        Call<SignUpResponse> call = apiService.createFactoryApi().get_details(headerMap, params);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.e("partnerProductData","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null){
                    if(response.body().getSuccess()==1){
                        getDetails.setValue(response.body().getResult());
                    }else{
                        getDetails.setValue(null);
//                        Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getDetails.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                getDetails.setValue(null);
                Log.e("partnerProductData"," - > Error    "+t.getMessage());
            }
        });
        return getDetails;
    }

    private MutableLiveData<SignUpResult> getbuyerDetails = new MutableLiveData<>();
    public MutableLiveData<SignUpResult> getbuyerDetails(Context context,String propid) {
        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(context);

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(context,PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(context,PreferenceUtils.UserToken));
        params.put("property_id",propid);

        Call<SignUpResponse> call = apiService.createFactoryApi().get_buyerdetails(headerMap, params);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.e("buyer_detailsdata","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null){
                    if(response.body().getSuccess()==1){
                        getbuyerDetails.setValue(response.body().getResult());
                    }else{
                        getbuyerDetails.setValue(null);
                       Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getbuyerDetails.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                getbuyerDetails.setValue(null);
                Log.e("buyer_detailsdata"," - > Error    "+t.getMessage());
            }
        });
        return getbuyerDetails;
    }


    private MutableLiveData<SignUpResult> getCategoryDetails = new MutableLiveData<>();
    public MutableLiveData<SignUpResult> getCategoryDetails(Context context) {
        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(context);

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(context,PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(context,PreferenceUtils.UserToken));

        Call<SignUpResponse> call = apiService.createFactoryApi().get_categoryData(headerMap, params);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.e("partnerProductData","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null){
                    if(response.body().getSuccess()==1){
                        getCategoryDetails.setValue(response.body().getResult());
                    }else{
                        getCategoryDetails.setValue(null);
//                        Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getCategoryDetails.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                getCategoryDetails.setValue(null);
                Log.e("partnerProductData"," - > Error    "+t.getMessage());
            }
        });
        return getCategoryDetails;
    }

    private MutableLiveData<Result> getSubCategoryDetails = new MutableLiveData<>();
    public MutableLiveData<Result> getSubCategoryDetails(Context context) {
        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(context);

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(context,PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(context,PreferenceUtils.UserToken));
        params.put("category_id",PreferenceUtils.getStringValue(context,PreferenceUtils.Category_ID));

        Call<Example> call = apiService.createFactoryApi().get_subcategoryData(headerMap, params);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.e("partnerProductData","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null){
                    if(response.body().getSuccess()==1){
                        getSubCategoryDetails.setValue(response.body().getResult());
                    }else{
                        getSubCategoryDetails.setValue(null);
                        Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getSubCategoryDetails.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                getSubCategoryDetails.setValue(null);
                Log.e("partnerProductData"," - > Error    "+t.getMessage());
            }
        });
        return getSubCategoryDetails;
    }

    private MutableLiveData<DatabycatResult> getDatabyCategoryDetails = new MutableLiveData<>();
    public MutableLiveData<DatabycatResult> getDatabyCategoryDetails(Context context) {
        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(context);

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(context,PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(context,PreferenceUtils.UserToken));
        params.put("category_id",PreferenceUtils.getStringValue(context,PreferenceUtils.Category_ID));
        params.put("sub_category_id",PreferenceUtils.getStringValue(context,PreferenceUtils.SubCategory_ID));

        Call<DatabycategoryResponse> call = apiService.createFactoryApi().get_databycategoryData(headerMap, params);
        call.enqueue(new Callback<DatabycategoryResponse>() {
            @Override
            public void onResponse(Call<DatabycategoryResponse> call, Response<DatabycategoryResponse> response) {
                Log.e("databycat","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null){
                    if(response.body().getSuccess()==1){
                        getDatabyCategoryDetails.setValue(response.body().getResult());
                    }else{
                        getDatabyCategoryDetails.setValue(null);
                        Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getDatabyCategoryDetails.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DatabycategoryResponse> call, Throwable t) {
                getDatabyCategoryDetails.setValue(null);
                Log.e("databycat"," - > Error    "+t.getMessage());
            }
        });
        return getDatabyCategoryDetails;
    }

    private MutableLiveData<Result> getSearchDetails = new MutableLiveData<>();
    public MutableLiveData<Result> getSearchDetails(Context context,String search_type) {
        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(context);

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(context,PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(context,PreferenceUtils.UserToken));
        params.put("search_text",search_type);

        Call<Example> call = apiService.createFactoryApi().get_searchData(headerMap, params);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.e("Search","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null){
                    if(response.body().getSuccess()==1){
                        getSearchDetails.setValue(response.body().getResult());
                    }else{
                        getSearchDetails.setValue(null);
//                        Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getSearchDetails.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                getSearchDetails.setValue(null);
                Log.e("Search"," - > Error    "+t.getMessage());
            }
        });
        return getSearchDetails;
    }


    private MutableLiveData<SignUpResult> getTermsandCondition = new MutableLiveData<>();
    public MutableLiveData<SignUpResult> getTermsandCondition(Context context) {
        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(context);

        HashMap<String, String> params = new HashMap<>();

        Call<SignUpResponse> call = apiService.createFactoryApi().termsandcond(headerMap, params);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.e("partnerProductData","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null){
                    if(response.body().getSuccess()==1){
                        getTermsandCondition.setValue(response.body().getResult());
                    }else{
                        getTermsandCondition.setValue(null);
//                        Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getTermsandCondition.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                getTermsandCondition.setValue(null);
                Log.e("partnerProductData"," - > Error    "+t.getMessage());
            }
        });
        return getTermsandCondition;
    }


    private MutableLiveData<Result> getSlider_Agent = new MutableLiveData<>();
    public MutableLiveData<Result> getSlider_Agent(Context context) {
        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(context);

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(context,PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(context,PreferenceUtils.UserToken));

        Call<Example> call = apiService.createFactoryApi().get_agenthome(headerMap, params);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.e("partnerProductData","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null){
                    if(response.body().getSuccess()==1){
                        getSlider_Agent.setValue(response.body().getResult());
                    }else{
                        getSlider_Agent.setValue(null);
//                        Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getSlider_Agent.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                getSlider_Agent.setValue(null);
                Log.e("partnerProductData"," - > Error    "+t.getMessage());
            }
        });
        return getSlider_Agent;
    }

    private MutableLiveData<Result> getSoldProperty = new MutableLiveData<>();
    public MutableLiveData<Result> getSoldProperty(Context context) {
        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(context);

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(context,PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(context,PreferenceUtils.UserToken));

        Call<Example> call = apiService.createFactoryApi().get_soldproperty(headerMap, params);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.e("partnerProductData","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null){
                    if(response.body().getSuccess()==1){
                        getSoldProperty.setValue(response.body().getResult());
                    }else{
                        getSoldProperty.setValue(null);
//                        Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getSoldProperty.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                getSoldProperty.setValue(null);
                Log.e("soldprop_data"," - > Error    "+t.getMessage());
            }
        });
        return getSoldProperty;
    }

    private MutableLiveData<Result> getSoldPropertyDetails = new MutableLiveData<>();
    public MutableLiveData<Result> getSoldPropertyDetails(Context context,String propid) {
        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(context);

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(context,PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(context,PreferenceUtils.UserToken));
        params.put("property_id",propid);

        Call<Example> call = apiService.createFactoryApi().get_soldpropertydetails(headerMap, params);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.e("soldprop_Detailsdata","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null){
                    if(response.body().getSuccess()==1){
                        getSoldPropertyDetails.setValue(response.body().getResult());
                    }else{
                        getSoldPropertyDetails.setValue(null);
//                        Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getSoldPropertyDetails.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                getSoldPropertyDetails.setValue(null);
                Log.e("soldprop_detailsdata"," - > Error    "+t.getMessage());
            }
        });
        return getSoldPropertyDetails;
    }

    private MutableLiveData<Result> getbidproperty = new MutableLiveData<>();
    public MutableLiveData<Result> getbidproperty(Context context) {
        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(context);

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(context,PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(context,PreferenceUtils.UserToken));

        Call<Example> call = apiService.createFactoryApi().get_biddetails(headerMap, params);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.e("soldprop_Detailsdata","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null){
                    if(response.body().getSuccess()==1){
                        getbidproperty.setValue(response.body().getResult());
                    }else{
                        getbidproperty.setValue(null);
//                        Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getbidproperty.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                getbidproperty.setValue(null);
                Log.e("soldprop_detailsdata"," - > Error    "+t.getMessage());
            }
        });
        return getbidproperty;
    }

    private MutableLiveData<Result> getbidpropertydetails = new MutableLiveData<>();
    public MutableLiveData<Result> getbidpropertydetails(Context context,String propid) {
        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(context);

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(context,PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(context,PreferenceUtils.UserToken));
        params.put("property_id",propid);

        Call<Example> call = apiService.createFactoryApi().get_bidpropdetails(headerMap, params);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.e("getbidpropertydetails","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null){
                    if(response.body().getSuccess()==1){
                        getbidpropertydetails.setValue(response.body().getResult());
                    }else{
                        getbidpropertydetails.setValue(null);
//                        Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getbidpropertydetails.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                getbidpropertydetails.setValue(null);
                Log.e("getbidpropertydetails"," - > Error    "+t.getMessage());
            }
        });
        return getbidpropertydetails;
    }

    private MutableLiveData<Result> getmyproperty = new MutableLiveData<>();
    public MutableLiveData<Result> getmyproperty(Context context) {
        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(context);

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(context,PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(context,PreferenceUtils.UserToken));

        Call<Example> call = apiService.createFactoryApi().get_myProp(headerMap, params);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.e("soldprop_Detailsdata","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null){
                    if(response.body().getSuccess()==1){
                        getmyproperty.setValue(response.body().getResult());
                    }else{
                        getmyproperty.setValue(null);
                        Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getmyproperty.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                getmyproperty.setValue(null);
                Log.e("myproperty_data"," - > Error    "+t.getMessage());
            }
        });
        return getmyproperty;
    }

    private MutableLiveData<Result> getpurchaseproperty = new MutableLiveData<>();
    public MutableLiveData<Result> getpurchaseproperty(Context context) {
        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(context);

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(context,PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(context,PreferenceUtils.UserToken));

        Call<Example> call = apiService.createFactoryApi().get_purchaseProp(headerMap, params);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.e("purchase_data","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null){
                    if(response.body().getSuccess()==1){
                        getpurchaseproperty.setValue(response.body().getResult());
                    }else{
                        getpurchaseproperty.setValue(null);
                        Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getpurchaseproperty.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                getpurchaseproperty.setValue(null);
                Log.e("purchase_data"," - > Error    "+t.getMessage());
            }
        });
        return getpurchaseproperty;
    }

    private MutableLiveData<Result> getsellerpropertydetails = new MutableLiveData<>();
    public MutableLiveData<Result> getsellerpropertydetails(Context context,String propid) {
        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(context);

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(context,PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(context,PreferenceUtils.UserToken));
        params.put("property_id",propid);

        Call<Example> call = apiService.createFactoryApi().get_myPropdetails(headerMap, params);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.e("myprop_Detailsdata","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null){
                    if(response.body().getSuccess()==1){
                        getsellerpropertydetails.setValue(response.body().getResult());
                    }else{
                        getsellerpropertydetails.setValue(null);
//                        Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getsellerpropertydetails.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                getsellerpropertydetails.setValue(null);
                Log.e("myprop_detailsdata"," - > Error    "+t.getMessage());
            }
        });
        return getsellerpropertydetails;
    }

    private MutableLiveData<Result> getpurchasepropertydetails = new MutableLiveData<>();
    public MutableLiveData<Result> getpurchasepropertydetails(Context context,String propid) {
        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(context);

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(context,PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(context,PreferenceUtils.UserToken));
        params.put("property_id",propid);

        Call<Example> call = apiService.createFactoryApi().get_myPurchasedetails(headerMap, params);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.e("purchase_detailsdata","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null){
                    if(response.body().getSuccess()==1){
                        getpurchasepropertydetails.setValue(response.body().getResult());
                    }else{
                        getpurchasepropertydetails.setValue(null);
//                        Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getpurchasepropertydetails.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                getpurchasepropertydetails.setValue(null);
                Log.e("purchase_detailsdata"," - > Error    "+t.getMessage());
            }
        });
        return getpurchasepropertydetails;
    }

    private MutableLiveData<Result> getbuyer_homepage = new MutableLiveData<>();
    public MutableLiveData<Result> getbuyer_homepage(Context context) {
        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(context);

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(context,PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(context,PreferenceUtils.UserToken));

        Call<Example> call = apiService.createFactoryApi().agenthome(headerMap, params);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.e("buyer_homepage","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null){
                    if(response.body().getSuccess()==1){
                        getbuyer_homepage.setValue(response.body().getResult());
                    }else{
                        getbuyer_homepage.setValue(null);
                        Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getbuyer_homepage.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                getbuyer_homepage.setValue(null);
                Log.e("buyer_homepage"," - > Error    "+t.getMessage());
            }
        });
        return getbuyer_homepage;
    }

    private MutableLiveData<Result> getfavlist = new MutableLiveData<>();
    public MutableLiveData<Result> getfavlist(Context context) {
        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(context);

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(context,PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(context,PreferenceUtils.UserToken));

        Call<Example> call = apiService.createFactoryApi().fav_list(headerMap, params);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.e("fav_list","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null){
                    if(response.body().getSuccess()==1){
                        getfavlist.setValue(response.body().getResult());
                    }else{
                        getfavlist.setValue(null);
                        Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    getfavlist.setValue(null);
                    Toast.makeText(context, "Cannot Fetch Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                getpurchaseproperty.setValue(null);
                Log.e("fav_list"," - > Error    "+t.getMessage());
            }
        });
        return getfavlist;
    }

}
