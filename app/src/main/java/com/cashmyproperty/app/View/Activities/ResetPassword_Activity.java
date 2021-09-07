package com.cashmyproperty.app.View.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Response.SellerData;
import com.cashmyproperty.app.View.Response.SignUpResponse;
import com.cashmyproperty.app.View.Utility.PreferenceUtils;
import com.cashmyproperty.app.View.network.ApiService;
import com.google.gson.Gson;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPassword_Activity extends AppCompatActivity {

    EditText edit_pwd,edit_con_pwd;
    String pass="",con_pass="",sellerid,code;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        sellerid= getIntent().getStringExtra("Sellerid");
        code= getIntent().getStringExtra("verificationcode");

        initviews();

    }

    private void initviews() {
        edit_pwd=findViewById(R.id.edit_pwd);
        edit_con_pwd=findViewById(R.id.edit_con_pwd);
        edit_pwd.setHint("Password");
        edit_con_pwd.setHint("Confirm Password");
    }

    public void save_data(View view) {

        pass=edit_pwd.getText().toString().trim();
        con_pass=edit_con_pwd.getText().toString().trim();

        if(pass.equals("")|| con_pass.equals("")){
            Toast.makeText(getApplicationContext(),"Please enter password",Toast.LENGTH_LONG).show();

        }else {
            if(pass.charAt(0) == '-'){
                Toast.makeText(getApplicationContext(),"Password cannot start with -",Toast.LENGTH_LONG).show();
                return;
            }else{

            }
        }

        if(!pass.equals(con_pass)){
            Toast.makeText(getApplicationContext(),"Passwords do not match!!",Toast.LENGTH_LONG).show();
            return;
        }else{

            }

             call_resetpass();

        }

    private void call_resetpass() {

        showprogressbar(true);

        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(getApplicationContext());

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id",sellerid);
        params.put("users_forgot_password_code", code);
        params.put("users_password", pass);

        Call<SignUpResponse> call = apiService.createFactoryApi().resetpassword(headerMap, params);
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
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);


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

    private void showprogressbar(Boolean IS_SHOW) {
        if (IS_SHOW) {
            progress = ProgressDialog.show(ResetPassword_Activity.this, "","" );
            progress.setContentView(R.layout.loader);
            progress.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        } else {
            progress.dismiss();
        }
    }

}


