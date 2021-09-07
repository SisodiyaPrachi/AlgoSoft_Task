package com.cashmyproperty.app.View.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cashmyproperty.app.R;

import com.cashmyproperty.app.View.Category.CategoryFragment;
import com.cashmyproperty.app.View.Helper.FilePath;
import com.cashmyproperty.app.View.Home.HomeFragment_Seller;
import com.cashmyproperty.app.View.Response.SellerData;
import com.cashmyproperty.app.View.Response.SignUpResponse;
import com.cashmyproperty.app.View.Utility.PreferenceUtils;
import com.cashmyproperty.app.View.network.ApiService;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    EditText username, password,repassword,email,mobile,edit_reraid;
    TextView txt_reera,edit_emir,btn_signup,txt_account,buyer,seller;
    String user="",emaill="",pass="",repass="",rrid="",imagePath="";
    String phone_no="",usertype="buyer";
    CheckBox chk_policy;
    private static final int PERMISSION_READ_STATE = 1;
    List<MultipartBody.Part> files;
    Dialog dialog;
    private ProgressDialog progress;
    ImageView plus_button,img_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initViews();



            seller.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    usertype="seller";
                    seller.setBackgroundResource(R.drawable.rounded_edittext_yellow);
                    buyer.setBackgroundResource(R.drawable.rounded_edittext_color);
                    txt_reera.setVisibility(View.GONE);
                    edit_reraid.setVisibility(View.GONE);
                    edit_emir.setVisibility(View.GONE);
                    plus_button.setVisibility(View.GONE);
                    img_signup.setVisibility(View.VISIBLE);
                    btn_signup.setVisibility(View.VISIBLE);
                    txt_account.setVisibility(View.VISIBLE);

                    setBlank();
                }

            });

            buyer.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                      usertype="buyer";
                    buyer.setBackgroundResource(R.drawable.rounded_edittext_yellow);
                    seller.setBackgroundResource(R.drawable.rounded_edittext_color);
                    txt_reera.setVisibility(View.VISIBLE);
                    edit_reraid.setVisibility(View.VISIBLE);
                    edit_emir.setVisibility(View.VISIBLE);
                    plus_button.setVisibility(View.VISIBLE);
                    img_signup.setVisibility(View.GONE);
                    btn_signup.setVisibility(View.GONE);
                    txt_account.setVisibility(View.GONE);

                    setBlank();
                }
            });

        plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                }

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);
            }
        });

        }

    private void setBlank() {
        username.setText(null);
        password.setText(null);
        repassword.setText(null);
        email.setText(null);
        mobile.setText(null);
        edit_reraid.setText(null);
        username.requestFocus();
    }



    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_READ_STATE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_READ_STATE: {
                if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    TelephonyManager telephonyManager = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
                } else {
                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getApplicationContext());
                    builder.setTitle("Permission Denied");
                    builder.setMessage("Do you want to deny this permission?");
                    builder.setNegativeButton("RE-TRY", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            requestPermission();
                        }
                    });
                    builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.show();

                }
            }
            break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {

                final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/*; charset=utf-8");
                files = new ArrayList<>();

                Uri selectedImage = data.getData();
                String selectedFilePath = FilePath.getPath(getApplicationContext(), selectedImage);
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getApplicationContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath = cursor.getString(columnIndex);
                if(imagePath!=null && !imagePath.isEmpty()) {
                    File panFile = new File(imagePath);
                    files.add(MultipartBody.Part.createFormData("emirates_id_password",
                            panFile.getName(),
                            RequestBody.create(panFile, MEDIA_TYPE_PNG)));


                    cursor.close();

                }

                else{

                }


            } // When an Video is picked
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }



    private void addTextChangeListeners() {

        boolean bool = true;

        user = username.getText().toString().trim();

        if(user.equals("")){
            bool=false;
            username.setError("Please enter username");
        }else if(user.length()<3){
            bool=false;
            username.setError("Please enter at least 3 characters");
        }else{
            username.setError(null);
        }

        emaill= email.getText().toString().trim();
        if(emaill.equals("")){
            bool=false;
            email.setError("Please enter your email id");

        }else if(validateEmail()){
            bool=false;
            email.setError("Email id is not valid!!");

        }else{
            email.setError(null);
        }

        phone_no=mobile.getText().toString();
        if(phone_no.equals("")){
            bool=false;
            mobile.setError("Please enter your mobile no");

        }else if(validateMobileNo()){
            bool=false;
            mobile.setError("Mobile no is not valid!!");

        }else{
            mobile.setError(null);
        }

       
        pass = password.getText().toString();
        if(pass.equals("")){
            bool=false;
            password.setError("Please enter a password");
        }else if(pass.length()>=0 && pass.charAt(0)=='-'){
            bool=false;
            password.setError("Password cannot start with -");
        }else{
            password.setError(null);
        }
        if(!pass.equals(repassword.getText().toString().trim())){
            bool=false;
            repassword.setError("Passwords do not match!!");
        }else{
            if(!password.equals("")){
                repassword.setError(null);
            }

        }

        if(usertype.equals("buyer")) {
            rrid = edit_reraid.getText().toString();
            if (rrid.equals("")) {
                bool = false;
                edit_reraid.setError("Please enter reraid");
            } else {
                edit_reraid.setError(null);
            }
        }

        if(!chk_policy.isChecked()){
            bool=false;
            Toast.makeText(getApplicationContext(),"You must agree to our Terms & Conditions for signing up with us.",Toast.LENGTH_LONG).show();
        }

        if(!bool) return;

        checkUserRegistration(files);
    }

    private void checkUserRegistration(List<MultipartBody.Part> imagetopath) {

        showprogressbar(true);

        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(getApplicationContext());

        HashMap<String, String> params = new HashMap<>();
        params.put("users_name", user);
        params.put("users_email", emaill);
        params.put("users_mobile", phone_no);
        params.put("users_password", pass);
        params.put("term_condition", "Y");
        params.put("rera_id_number",rrid);
        params.put("users_type",usertype);

        if(imagetopath==null || imagetopath.isEmpty()) {
            params.put("emirates_id_password", imagePath);
        }

        Call<SignUpResponse> call = apiService.createFactoryApi().sellerSignUpForm(headerMap, params,imagetopath);
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

                        if(data.getUsersType().equalsIgnoreCase("Seller"))
                        email_verification(data);
                        else{
                            dialog_verify();
                        }

                    } else {
                        if (responseClass.getMessage().equalsIgnoreCase("This Mobile No. already exists")) {
                            mobile.setText("");
                            mobile.requestFocus();
                            mobile.setError(responseClass.getMessage());
                            showprogressbar(false);

                        } else if (responseClass.getMessage().equalsIgnoreCase("This Email Id already exists")) {
                            email.setText("");
                            email.requestFocus();
                            showprogressbar(false);
                            email.setError(responseClass.getMessage());

                        } else
                            showprogressbar(false);
                            Toast.makeText(getApplicationContext(), responseClass.getMessage(), Toast.LENGTH_SHORT).show();
                    }
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

    private void dialog_verify() {
        dialog=new Dialog(SignupActivity.this);
        dialog.setContentView(R.layout.dialog_verify);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();
    }

    private boolean validateEmail() {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (emaill == null)
            return true;
        return !pat.matcher(emaill).matches();
    }

    private boolean validateMobileNo() {
        String mobileRegex = "(0/91)?[6-9][0-9]{9}";

        Pattern pat = Pattern.compile(mobileRegex);
        if (phone_no == null)
            return true;
        return !pat.matcher(phone_no).matches();
    }

    private void email_verification(SellerData data) {


       Intent intent=new Intent(getApplicationContext(),OTPVerification_Activity.class);
        intent.putExtra("AccountVerification","account_verify");
        intent.putExtra("Sellerid",data.getUsersId());
        intent.putExtra("EmailID",data.getUsersEmail());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }



    private void initViews() {

        username = findViewById(R.id.edit_fullname);
        email = findViewById(R.id.edit_email);
        mobile =  findViewById(R.id.edit_mobile);
        password=findViewById(R.id.edit_password);
        repassword=findViewById(R.id.edit_con_pwd);
        edit_reraid=findViewById(R.id.edit_reraid);
        txt_reera= findViewById(R.id.txt_reera);
        buyer=findViewById(R.id.buyer);
        seller=findViewById(R.id.seller);

        edit_emir=findViewById(R.id.edit_emir);
        chk_policy=findViewById(R.id.chk_policy);
        plus_button=findViewById(R.id.plus_button);
        btn_signup=findViewById(R.id.btn_signup);
        txt_account=findViewById(R.id.txt_account);
        img_signup=findViewById(R.id.img_signup);

        password.setHint("Password");
        repassword.setHint("Confirm Password");

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }


    public void sign_up(View view) {

        addTextChangeListeners();


    }

    private void showprogressbar(Boolean IS_SHOW) {
        if (IS_SHOW) {
            progress = ProgressDialog.show(SignupActivity.this, "", "");
            progress.setContentView(R.layout.loader);
            progress.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        } else {
            progress.dismiss();
        }
    }


}




