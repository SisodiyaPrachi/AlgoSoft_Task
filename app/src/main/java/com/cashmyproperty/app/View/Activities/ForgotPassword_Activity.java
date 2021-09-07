package com.cashmyproperty.app.View.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Response.SellerData;
import com.cashmyproperty.app.View.Response.SignUpResponse;
import com.cashmyproperty.app.View.Utility.PreferenceUtils;
import com.cashmyproperty.app.View.network.ApiService;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword_Activity extends AppCompatActivity {

    EditText txt_email;
    String email="";
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        txt_email=findViewById(R.id.txt_emailid);

    }

    public void send_code(View view) {

        email=txt_email.getText().toString().trim();

        if(email.equals("")){
            txt_email.setError("Please enter your email id");

        }else if(validateEmail()){
            txt_email.setError("Email id is not valid!!");

        }
        else{
             forgot_pass();

        }

    }

    private void forgot_pass() {

        showprogressbar(true);

        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(getApplicationContext());

        HashMap<String, String> params = new HashMap<>();

        params.put("users_email", email);

        Call<SignUpResponse> call = apiService.createFactoryApi().forgotpassword(headerMap, params);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.e("customerSignUpForm", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    SignUpResponse responseClass = response.body();
                    if (responseClass.getSuccess() == 1) {
                        SellerData data = responseClass.getResult().getUsersData();
                        showprogressbar(false);
                        Toast.makeText(getApplicationContext(), responseClass.getMessage(), Toast.LENGTH_SHORT).show();

                        show_otp(data);

                    }  else
                        showprogressbar(false);
                        Toast.makeText(getApplicationContext(), responseClass.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                // hideLoader();
                // showSnackbar();
                Log.e("customerSignUpForm", " - > Error    " + t.getMessage());
            }
        });

    }

    private void show_otp(SellerData data) {
        Intent intent=new Intent(getApplicationContext(),OTPVerification_Activity.class);
        intent.putExtra("AccountVerification","forgotpass");
        intent.putExtra("EmailID",data.getUsersEmail());
        intent.putExtra("Sellerid",data.getUsersId());
        intent.putExtra("verificationcode",String.valueOf(data.getVerificationCode()));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    private void reset_password() {

        Intent intent=new Intent(getApplicationContext(),ResetPassword_Activity.class);
        startActivity(intent);

    }

    private boolean validateEmail() {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return true;
        return !pat.matcher(email).matches();
    }

    private void showprogressbar(Boolean IS_SHOW) {
        if (IS_SHOW) {
            progress = ProgressDialog.show(ForgotPassword_Activity.this, "", "");
            progress.setContentView(R.layout.loader);
            progress.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        } else {
            progress.dismiss();
        }
    }

}
