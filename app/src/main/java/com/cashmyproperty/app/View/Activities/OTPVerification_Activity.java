package com.cashmyproperty.app.View.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Repository.DataRepository;
import com.cashmyproperty.app.View.Response.SellerData;
import com.cashmyproperty.app.View.Response.SignUpResponse;
import com.cashmyproperty.app.View.Utility.PreferenceUtils;
import com.cashmyproperty.app.View.network.ApiService;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPVerification_Activity extends AppCompatActivity {

    TextInputEditText et_email_1, et_email_2, et_email_3, et_email_4;
    String e1="",e2="",e3="",e4="";
    String code;
     TextView tv_resendOTP,tv_error_msg,txt_heading;
    TextInputLayout text_email_1, text_email_2, text_email_3, text_email_4;
    Button btn_submit;
    private ProgressDialog progress;
    String sellerid,emailid,key,mobile;
    SellerData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailverification);

       key=getIntent().getStringExtra("AccountVerification");
       sellerid= getIntent().getStringExtra("Sellerid");
       emailid=getIntent().getStringExtra("EmailID");
       code= getIntent().getStringExtra("verificationcode");

        initviews();
        addListeners();
        startOTPCountdown();
    }


    CountDownTimer cd;
    private void startOTPCountdown(){
        if(cd!=null) cd.cancel();
        cd = new CountDownTimer(15000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                try {
                    findViewById(R.id.resend_otp).setVisibility(View.VISIBLE);
                }catch (Exception e){}
            }
        }.start();
    }


    private void addListeners() {

        et_email_1.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                e1 = s.toString();
                if(e1.length()==1)
                    et_email_2.requestFocus();
                checkForBlanks();
            }

            @Override
            public void afterTextChanged(Editable s) {}

        });

        et_email_2.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                e2 = s.toString();
                if(e2.length()==1)
                    et_email_3.requestFocus();
                checkForBlanks();
            }

            @Override
            public void afterTextChanged(Editable s) {}

        });

        et_email_3.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                e3 = s.toString();
                if(e3.length()==1)
                    et_email_4.requestFocus();
                checkForBlanks();
            }

            @Override
            public void afterTextChanged(Editable s) {}

        });

        et_email_4.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                e4 = s.toString();
                checkForBlanks();
                if(e4.length()==1)
                    hideKeyboardFrom(getApplicationContext(), et_email_4);
            }

            @Override
            public void afterTextChanged(Editable s) {}

        });


    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void checkForBlanks() {
        if(e1.equals("")||e2.equals("")||e3.equals("")||e4.equals("")){
            findViewById(R.id.btn_submit).setVisibility(View.GONE);
       }else{

        findViewById(R.id.btn_submit).setVisibility(View.VISIBLE);
       }

    }


    private void initviews() {
        text_email_1 = findViewById(R.id.text_email_1);
        text_email_2 = findViewById(R.id.text_email_2);
        text_email_3 = findViewById(R.id.text_email_3);
        text_email_4 = findViewById(R.id.text_email_4);


        et_email_1 =   findViewById(R.id.et_email_1);
        et_email_2 =   findViewById(R.id.et_email_2);
        et_email_3 =   findViewById(R.id.et_email_3);
        et_email_4 =   findViewById(R.id.et_email_4);

        tv_error_msg=findViewById(R.id.tv_error_msg);


        txt_heading=findViewById(R.id.txt_heading);
        txt_heading.setText("Verify your account");


    }

    public void verify_otp(View view) {

        String e = e1+e2+e3+e4;

        Log.e("Email Otp", e);

           if(key.equals("account_verify"))
            checkOTP(e);
           else if(key.equals("profile_emailverify"))
               profile_otp(e);
           else{
               forgot_checkOtp(e);
           }


    }

    private void profile_otp(String e) {

        showprogressbar(true);

        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(getApplicationContext());

        HashMap<String, String> params = new HashMap<>();
        params.put("users_id", sellerid);
        params.put("users_email", emailid);
        params.put("users_email_verification_code",e);


        Call<SignUpResponse> call = apiService.createFactoryApi().profile_otp(headerMap, params);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.e("customerVerification","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null) {
                    //  hideLoader();
                    SignUpResponse responseClass = response.body();
                    if(responseClass.getSuccess()==1){
                        data = responseClass.getResult().getUsersData();
                        showprogressbar(false);
                        Toast.makeText(getApplicationContext(), responseClass.getMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        showprogressbar(false);
                        Toast.makeText(getApplicationContext(), responseClass.getMessage(), Toast.LENGTH_SHORT).show();
                      //  Toast.makeText(getApplicationContext(),"OTP mismatch",Toast.LENGTH_LONG).show();
                        //setError();
                    }
                }

            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

                Log.e("customerVerification"," - > Error    "+t.getMessage());
            }
        });

    }


    private void forgot_checkOtp(String e) {
        if(e.equals(code)){
          //  Toast.makeText(getApplicationContext(),e,Toast.LENGTH_LONG).show();
            Intent intent=new Intent(getApplicationContext(),ResetPassword_Activity.class);
            intent.putExtra("Sellerid",sellerid);
            intent.putExtra("verificationcode",e);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),e,Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),code,Toast.LENGTH_LONG).show();
            setError();
        }
    }

    private void checkOTP(String emailOTP) {

        showprogressbar(true);

        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(getApplicationContext());

        HashMap<String, String> params = new HashMap<>();
        params.put("users_id", sellerid);
        params.put("email_verification_code", emailOTP);


        Call<SignUpResponse> call = apiService.createFactoryApi().sellerAccountVerification(headerMap, params);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.e("customerVerification","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null) {
                    //  hideLoader();
                    SignUpResponse responseClass = response.body();
                    if(responseClass.getSuccess()==1){
                         data = responseClass.getResult().getUsersData();
                        verificationSuccess(data);
                        showprogressbar(false);
                        Toast.makeText(getApplicationContext(), responseClass.getMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        showprogressbar(false);
                          Toast.makeText(getApplicationContext(),"OTP mismatch",Toast.LENGTH_LONG).show();
                          setError();
                        }
                    }

            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

                Log.e("customerVerification"," - > Error    "+t.getMessage());
            }
        });

    }

    private void verificationSuccess(SellerData data) {
       setLogin(data);

    }

    public void setLogin(SellerData data) {

        PreferenceUtils.setStringValue(getApplicationContext(), PreferenceUtils.UserToken, data.getUsersToken());
        PreferenceUtils.setStringValue(getApplicationContext(), PreferenceUtils.UserName, data.getUsersName());
        PreferenceUtils.setStringValue(getApplicationContext(), PreferenceUtils.customer_id, data.getUsersId());
        PreferenceUtils.setStringValue(getApplicationContext(), PreferenceUtils.UserMobile, data.getUsersMobile());
        PreferenceUtils.setStringValue(getApplicationContext(), PreferenceUtils.UserEmail, data.getUsersEmail());
        PreferenceUtils.setStringValue(getApplicationContext(), PreferenceUtils.UserType, data.getUsersType());
        PreferenceUtils.setStringValue(getApplicationContext(), PreferenceUtils.ReraId, data.getReraIdNumber());
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        finish();

    }


    public void resend_otp(View view) {

     DataRepository dataRepository=new DataRepository(getApplicationContext());

     if(key.equals("account_verify")) {
         showprogressbar(true);
         String type = "email";
         showprogressbar(false);
         dataRepository.resend_verification(getApplicationContext(), sellerid, type, emailid);
     }
     else{
         showprogressbar(true);
         String type="forgotPassword";
         showprogressbar(false);
         dataRepository.resend_verification(getApplicationContext(),sellerid,type,emailid);
     }

    }

    private void setError(){
        et_email_1.setText("");
        et_email_2.setText("");
        et_email_3.setText("");
        et_email_4.setText("");

        text_email_1.setError(" ");
        text_email_2.setError(" ");
        text_email_3.setError(" ");
        text_email_4.setError(" ");


        et_email_1.requestFocus();

        tv_error_msg.setVisibility(View.VISIBLE);

    }

    @Override
    public void onBackPressed() {
       Toast.makeText(getApplicationContext(),"Please Enter OTP",Toast.LENGTH_LONG).show();
    }

    private void showprogressbar(Boolean IS_SHOW) {
        if (IS_SHOW) {
            progress = ProgressDialog.show(OTPVerification_Activity.this, "", "PLEASE WAIT...", true);
        } else {
            progress.dismiss();
        }
    }
}
