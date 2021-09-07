package com.cashmyproperty.app.View.network;

import com.cashmyproperty.app.View.Response.DatabycatResult;
import com.cashmyproperty.app.View.Response.DatabycategoryResponse;
import com.cashmyproperty.app.View.Response.Example;
import com.cashmyproperty.app.View.Response.SignUpResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface FactoryApi {

    @Multipart
    @POST("users/putSignupForm")
    Call<SignUpResponse> sellerSignUpForm(@HeaderMap Map<String,String> headers,@PartMap Map<String, String> map, @Part List<MultipartBody.Part> image);

    @POST("users/putSignupVerificationForm")
    @FormUrlEncoded
    Call<SignUpResponse> sellerAccountVerification(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/setForgotPassword")
    @FormUrlEncoded
    Call<SignUpResponse> forgotpassword(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/setResetPassword")
    @FormUrlEncoded
    Call<SignUpResponse> resetpassword(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/resendVerificationCode")
    @FormUrlEncoded
    Call<SignUpResponse> resendverification(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/setLogin")
    @FormUrlEncoded
    Call<SignUpResponse> login(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/updateProfileData")
    @FormUrlEncoded
    Call<SignUpResponse> update_profile(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/resendEditVerificationCode")
    @FormUrlEncoded
    Call<SignUpResponse> profile_sendverification(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/editEmailVerification")
    @FormUrlEncoded
    Call<SignUpResponse> profile_otp(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @Multipart
    @POST("users/updateProfileImage")

    Call<SignUpResponse> profile_uploadimage(@HeaderMap Map<String,String> headers, @PartMap Map<String, String> map, @Part List<MultipartBody.Part> image);


    @POST("users/getPropertyStepOneData")
    @FormUrlEncoded
    Call<SignUpResponse> stepfirst(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/getPropertyStepTwoData")
    @FormUrlEncoded
    Call<SignUpResponse> steptwo(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/addPropertyStepOneData")
    @FormUrlEncoded
    Call<SignUpResponse> stepsubmit(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @Multipart
    @POST("users/addPropertyStepFourData")
    Call<SignUpResponse> fourstepsubmit(@HeaderMap Map<String,String> headers, @PartMap Map<String, String> map, @Part List<MultipartBody.Part> image);

    @Multipart
    @POST("users/addPropertyStepTwoData")
    Call<SignUpResponse> addsecond_step(@HeaderMap Map<String,String> headers, @PartMap Map<String, String> params,
                                        @Part List<MultipartBody.Part> deed, @Part List<MultipartBody.Part> eidpass,
                                        @Part List<MultipartBody.Part> tradelic, @Part List<MultipartBody.Part> eid, @Part List<MultipartBody.Part> poa);

    @Multipart
    @POST("users/addPropertyImageData")
    Call<SignUpResponse> addpropertyimage(@HeaderMap Map<String,String> headers, @PartMap Map<String, String> map, @Part List<MultipartBody.Part> image);

    @POST("users/addPropertyStepThreeData")
    @Multipart
    Call<SignUpResponse> add_thirdstep(@HeaderMap Map<String,String> headers, @PartMap Map<String, String> map/*, @Part List<MultipartBody.Part> image*/);

    @POST("users/getSellerHomePagePropertyData")
    @FormUrlEncoded
    Call<SignUpResponse> get_sliderhome(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/getSellerPropertyDetails")
    @FormUrlEncoded
    Call<SignUpResponse> get_details(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/getBuyerPropertyDetails")
    @FormUrlEncoded
    Call<SignUpResponse> get_buyerdetails(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/setLogout")
    @FormUrlEncoded
    Call<SignUpResponse> logout(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/getPropertyCategoryData")
    @FormUrlEncoded
    Call<SignUpResponse> get_categoryData(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/getPropertySubCategoryData")
    @FormUrlEncoded
    Call<Example> get_subcategoryData(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/getPropertydataByCategory")
    @FormUrlEncoded
    Call<DatabycategoryResponse> get_databycategoryData(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/searchPropertyData")
    @FormUrlEncoded
    Call<Example> get_searchData(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("common/getTermsConditionData")
    @FormUrlEncoded
    Call<SignUpResponse> termsandcond(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/buyerhomepagePropertyData")
    @FormUrlEncoded
    Call<Example> get_agenthome(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/getSellerSoldPropertyData")
    @FormUrlEncoded
    Call<Example> get_soldproperty(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/getSellerSoldPropertyDetails")
    @FormUrlEncoded
    Call<Example> get_soldpropertydetails(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/getBuyerBidPropertyData")
    @FormUrlEncoded
    Call<Example> get_biddetails(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/getBuyerBidPropertyDetails")
    @FormUrlEncoded
    Call<Example> get_bidpropdetails(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/getSellerMyPropertyData")
    @FormUrlEncoded
    Call<Example> get_myProp(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/getBuyerPurchasePropertyData")
    @FormUrlEncoded
    Call<Example> get_purchaseProp(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/getSellerMyPropertyDetails")
    @FormUrlEncoded
    Call<Example> get_myPropdetails(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/getBuyerPurchasePropertyDetails")
    @FormUrlEncoded
    Call<Example> get_myPurchasedetails(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/buyerPropertyBidsubmit")
    @FormUrlEncoded
    Call<Example> submit_bid(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/buyerhomepagePropertyDataOne")
    @FormUrlEncoded
    Call<Example> agenthome(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/addTowishlistproperty")
    @FormUrlEncoded
    Call<Example> setfav(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);

    @POST("users/getwishlistPropertyData")
    @FormUrlEncoded
    Call<Example> fav_list(@HeaderMap Map<String,String> headers, @FieldMap Map<String, String> params);
}

