package com.cashmyproperty.app.View.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Home.HomeFragment_Seller;
import com.cashmyproperty.app.View.Response.SellerData;
import com.cashmyproperty.app.View.Response.SignUpResponse;
import com.cashmyproperty.app.View.Search.SearchFragment;
import com.cashmyproperty.app.View.Utility.PreferenceUtils;
import com.cashmyproperty.app.View.network.ApiService;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    TextView btn_signup,buyer,seller;;
    Button btnlogin;
    private static final int PERMISSION_READ_STATE = 1;
    String user="",pass="";
    String usertype="buyer";
     SwitchCompat switch_btn;
    private ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if ( ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) !=PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE) !=PackageManager.PERMISSION_GRANTED) {
            requestPermission();
        }

        initviews();

        switch_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

              if(isChecked){
                  usertype="seller";
            }
              else{
                  usertype="buyer";
              }
        }

    });

       /* seller.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                usertype="seller";
                seller.setBackgroundResource(R.drawable.rounded_edittext_yellow);
                buyer.setBackgroundResource(R.drawable.rounded_edittext_color);

            }
        });

        buyer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                usertype="buyer";
                buyer.setBackgroundResource(R.drawable.rounded_edittext_yellow);
                seller.setBackgroundResource(R.drawable.rounded_edittext_color);

            }
        });*/



        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean bool = true;

                user = username.getText().toString();
                if (user.equals("")) {
                    bool = false;
                    username.setError("Please Enter Username");
                }
                else{
                    username.setError(null);
                }

                pass = password.getText().toString();
                if(pass.equals("")) {
                    bool=false;
                    password.setError("Please Enter Password");
                }
                else
                    password.setError(null);

                if(!bool) return;

                login();
            }
        });

    }

    private void initviews() {

        username = findViewById(R.id.email);
        password = findViewById(R.id.edit_pwd);
        btnlogin = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signup);
        switch_btn=findViewById(R.id.switch_btn);
        //buyer=findViewById(R.id.buyer);
       // seller=findViewById(R.id.seller);

        password.setHint("Password");

    }

    public void forgot_pass(View view) {

        Intent intent = new Intent(getApplicationContext(), ForgotPassword_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }


    private void login() {

        showprogressbar(true);

        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(getApplicationContext());

        HashMap<String, String> params = new HashMap<>();

        params.put("users_email",user);
        params.put("users_password", pass);
        params.put("users_type",usertype);


        Call<SignUpResponse> call = apiService.createFactoryApi().login(headerMap, params);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.e("customerSignUpForm", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    SignUpResponse responseClass = response.body();
                    if (responseClass.getSuccess() == 1) {
                        SellerData data = responseClass.getResult().getUsersData();
                        Toast.makeText(getApplicationContext(), responseClass.getMessage(), Toast.LENGTH_SHORT).show();

                        PreferenceUtils.setStringValue(getApplicationContext(), PreferenceUtils.UserToken, data.getUsersToken());
                        PreferenceUtils.setStringValue(getApplicationContext(), PreferenceUtils.UserID, data.getUsersId());
                        PreferenceUtils.setStringValue(getApplicationContext(),PreferenceUtils.USER_SEQ_ID,data.getUsersSequenceId());
                        PreferenceUtils.setStringValue(getApplicationContext(), PreferenceUtils.UserName, data.getUsersName());
                        PreferenceUtils.setStringValue(getApplicationContext(), PreferenceUtils.UserType, data.getUsersType());
                        PreferenceUtils.setStringValue(getApplicationContext(), PreferenceUtils.ReraId, data.getReraIdNumber());
                        PreferenceUtils.setStringValue(getApplicationContext(), PreferenceUtils.customer_id, data.getUsersId());
                        PreferenceUtils.setStringValue(getApplicationContext(), PreferenceUtils.UserMobile, data.getUsersMobile());
                        PreferenceUtils.setStringValue(getApplicationContext(), PreferenceUtils.UserEmail, data.getUsersEmail());
                        PreferenceUtils.setStringValue(getApplicationContext(),PreferenceUtils.UserImage,data.getUsersImage());

                        if(data.getUsersType().equalsIgnoreCase("seller")&& usertype.equalsIgnoreCase("seller")) {

                            setLoginSeller(true); 
                            setLoggedIn();
                            showprogressbar(false);
                            Intent intent = new Intent(getApplicationContext(), Navigation_Seller.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        }
                        else if(data.getUsersType().equalsIgnoreCase("buyer")&& usertype.equalsIgnoreCase("buyer")){
                            setLoginSeller(false);
                            setLoggedIn();
                            showprogressbar(false);
                            Intent intent=new Intent(getApplicationContext(),Navigation_Activity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }


                    }  else
                        Toast.makeText(getApplicationContext(), responseClass.getMessage(), Toast.LENGTH_SHORT).show();
                    showprogressbar(false);
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

    private void setLoggedIn() {
        PreferenceUtils.setBoolValue(this, PreferenceUtils.Login, true);
    }

    private void setLoginSeller(boolean bool) {
        PreferenceUtils.setBoolValue(this, PreferenceUtils.LoginTypeSeller, bool);
    }


    private void showprogressbar(Boolean IS_SHOW) {
        if (IS_SHOW) {
            progress = ProgressDialog.show(MainActivity.this, "","");
            progress.setContentView(R.layout.loader);
            progress.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        } else {
            progress.dismiss();
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_READ_STATE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_READ_STATE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED && grantResults[3] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[4]==PackageManager.PERMISSION_GRANTED) {

                } else {

                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
                    builder.setTitle("Permission Denied");
                    builder.setMessage("Are you sure you want to deny this permission?");
                    builder.setNegativeButton("RETRY", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            requestPermission();
                        }
                    });
                    builder.setCancelable(false);
                    builder.show();


                }
            }
            break;
        }

    }

}



