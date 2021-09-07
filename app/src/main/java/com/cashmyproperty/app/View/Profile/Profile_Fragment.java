package com.cashmyproperty.app.View.Profile;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Activities.Navigation_Activity;
import com.cashmyproperty.app.View.Activities.Navigation_Seller;
import com.cashmyproperty.app.View.Activities.OTPVerification_Activity;
import com.cashmyproperty.app.View.Helper.FilePath;
import com.cashmyproperty.app.View.Response.SellerData;
import com.cashmyproperty.app.View.Response.SignUpResponse;
import com.cashmyproperty.app.View.Utility.PreferenceUtils;
import com.cashmyproperty.app.View.network.ApiService;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class Profile_Fragment extends Fragment {

    MaterialToolbar tool_bar;
    TextView edit_fullname,edit_email,edit_mobile,edit_rera,txt_rera;
    String fullname="",email="",mobile="",reraid="",imagePath;
    View view;
    EditText editText;
    Button btn_profileupdate;
    private ProgressDialog progress;
    private static final int PERMISSION_READ_STATE = 1;
    CircularImageView profileupdate;
    ImageView img_camera;
    String image_base_url = "https://apkconnectlab.com/cmpdtest/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.profile_fragment, container, false);

         if(PreferenceUtils.getStringValue(getActivity(),PreferenceUtils.UserType).equalsIgnoreCase("Seller"))
        ((Navigation_Seller)getActivity()).setDrawer_Locked();
         else{
             ((Navigation_Activity)getActivity()).setDrawer_Locked();
         }

        tool_bar=view.findViewById(R.id.tool_bar);
        tool_bar.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24);

        tool_bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();

            }
        });

        initviews();


        return view;

    }

    private void initviews() {
        edit_fullname=view.findViewById(R.id.edit_fullname);
        edit_email=view.findViewById(R.id.edit_email);
        edit_mobile=view.findViewById(R.id.edit_mobile);
         edit_rera=view.findViewById(R.id.edit_rera);
        txt_rera=view.findViewById(R.id.txt_rera);
        profileupdate=view.findViewById(R.id.profileupdate);
        btn_profileupdate=view.findViewById(R.id.btn_profileupdate);
        img_camera=view.findViewById(R.id.img_camera);


        if(PreferenceUtils.getStringValue(getActivity(),PreferenceUtils.UserType).equalsIgnoreCase("buyer"))
        {
            txt_rera.setVisibility(View.VISIBLE);
            edit_rera.setVisibility(View.VISIBLE);
        }
        else{
            txt_rera.setVisibility(View.GONE);
            edit_rera.setVisibility(View.GONE);
        }


        edit_email.setText(PreferenceUtils.getStringValue(getActivity(),PreferenceUtils.UserEmail));
        edit_mobile.setText(PreferenceUtils.getStringValue(getActivity(), PreferenceUtils.UserMobile));
        edit_fullname.setText(PreferenceUtils.getStringValue(getActivity(), PreferenceUtils.UserName));
        edit_rera.setText(PreferenceUtils.getStringValue(getActivity(), PreferenceUtils.ReraId));
       // Toast.makeText(getActivity(),PreferenceUtils.getStringValue(getActivity(),PreferenceUtils.UserImage),Toast.LENGTH_SHORT).show();
        if( PreferenceUtils.getStringValue(getActivity(), PreferenceUtils.UserImage).equals("")){
            Glide.with(getActivity()).load(R.drawable.user).into(profileupdate);
        }
        else {
            Glide.with(getActivity()).load(image_base_url + PreferenceUtils.getStringValue(getActivity(),PreferenceUtils.UserImage)).into(profileupdate);
           // Glide.with(getActivity()).load(image_base_url+PreferenceUtils.getStringValue(getActivity(),PreferenceUtils.UserImage)).into(profileupdate);
        }


        edit_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edit_email.setCursorVisible(true);
                edit_email.setFocusableInTouchMode(true);
                edit_email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                edit_email.requestFocus();

            }

        });

       edit_mobile.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               edit_mobile.setCursorVisible(true);
               edit_mobile.setFocusableInTouchMode(true);
               edit_mobile.setInputType(InputType.TYPE_CLASS_PHONE);
               edit_mobile.requestFocus();


           }
       });

       edit_fullname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_fullname.setCursorVisible(true);
                edit_fullname.setFocusableInTouchMode(true);
                edit_fullname.setInputType(InputType.TYPE_CLASS_TEXT);
                edit_fullname.requestFocus();

            }
        });

        edit_rera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_rera.setCursorVisible(true);
                edit_rera.setFocusableInTouchMode(true);
                edit_rera.setInputType(InputType.TYPE_CLASS_TEXT);
                edit_rera.requestFocus();
            }
        });

        btn_profileupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get_profile();
            }
        });

        img_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                    return;
                }


                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, 0);

            }
        });

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_READ_STATE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_READ_STATE: {
                if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
                } else {
                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
                    builder.setTitle("Permission Denied");
                    builder.setMessage("Do you want to deny this permission?");
                    builder.setNegativeButton("RETRY", new DialogInterface.OnClickListener() {
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

    private void choose_option() {


        final Dialog alertdialogbuilder1 = new Dialog(getActivity());
        alertdialogbuilder1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams wmlp = alertdialogbuilder1.getWindow().getAttributes();
        alertdialogbuilder1.setContentView(R.layout.list_chooseoption);

        Button cancel = (Button) alertdialogbuilder1.findViewById(R.id.cancel);
        ((Button) alertdialogbuilder1.findViewById(R.id.camera)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);
            }
        });

        ((Button) alertdialogbuilder1.findViewById(R.id.gallery)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertdialogbuilder1.cancel();
            }
        });
        wmlp.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        wmlp.x = 0;
        wmlp.y = 100;
        // alertdialogbuilder1.setCancelable(false);
        alertdialogbuilder1.show();
    }

    private void get_profile() {

        showprogressbar(true);

        email=edit_email.getText().toString().trim();
        mobile=edit_mobile.getText().toString().trim();
        fullname=edit_fullname.getText().toString().trim();
        reraid=edit_rera.getText().toString().trim();


        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(getActivity());

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id",PreferenceUtils.getStringValue(getActivity(), PreferenceUtils.customer_id));
        params.put("users_token", PreferenceUtils.getStringValue(getActivity(), PreferenceUtils.UserToken));
        params.put("users_name",fullname);
        params.put("users_email", email);
        params.put("users_mobile",mobile);
        params.put("users_type", PreferenceUtils.getStringValue(getActivity(), PreferenceUtils.UserType));
        params.put("rera_id_number", reraid);


        Call<SignUpResponse> call = apiService.createFactoryApi().update_profile(headerMap, params);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.e("customerSignUpForm", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    SignUpResponse responseClass = response.body();
                    if (responseClass.getSuccess() == 1) {
                        SellerData data = responseClass.getResult().getUsersData();
                        Log.e("customerUserName", "Response: " + PreferenceUtils.getStringValue(getActivity(), PreferenceUtils.UserName));
                        Toast.makeText(getActivity(), responseClass.getMessage(), Toast.LENGTH_SHORT).show();

                        PreferenceUtils.setStringValue(getActivity(), PreferenceUtils.UserName, fullname);
                        PreferenceUtils.setStringValue(getActivity(), PreferenceUtils.ReraId, reraid);
                        PreferenceUtils.setStringValue(getActivity(), PreferenceUtils.UserMobile, mobile);
                        PreferenceUtils.setStringValue(getActivity(), PreferenceUtils.UserEmail, email);
                        showprogressbar(false);

                        Log.e("customerUserNamee", "Response: " + PreferenceUtils.getStringValue(getActivity(), PreferenceUtils.UserName));

                        if(data.getUsersEmailVerified().equalsIgnoreCase("N")){
                            profile_sendverification(data);
                        }


                        }

                      else {
                        Toast.makeText(getActivity(), responseClass.getMessage(), Toast.LENGTH_SHORT).show();
                        showprogressbar(false);
                    }
                }
                else{
                    Toast.makeText(getActivity(),"No Data Found",Toast.LENGTH_SHORT).show();
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

    private void profile_sendverification(SellerData data) {

        showprogressbar(true);

        String type="email";

        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(getActivity());

        HashMap<String, String> params = new HashMap<>();
        params.put("users_id", data.getUsersId());
        params.put("resend_type",type);
        params.put("users_mobile",mobile);
        params.put("users_email",email);


        Call<SignUpResponse> call = apiService.createFactoryApi().profile_sendverification(headerMap, params);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.e("customerVerification","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null) {
                    //  hideLoader();
                    SignUpResponse responseClass = response.body();
                    if(responseClass.getSuccess()==1){
                       SellerData data = responseClass.getResult().getUsersData();
                        showprogressbar(false);
                        Toast.makeText(getActivity(), responseClass.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getActivity(),OTPVerification_Activity.class);
                        intent.putExtra("AccountVerification","profile_emailverify");
                        intent.putExtra("Sellerid",data.getUsersId());
                        intent.putExtra("EmailID",data.getUsersEmail());
                        intent.putExtra("verificationcode",String.valueOf(data.getVerificationCode()));
                        startActivity(intent);

                    }else{
                        Toast.makeText(getActivity(),"OTP mismatch",Toast.LENGTH_LONG).show();
                        showprogressbar(false);

                    }
                }
                else{
                    Toast.makeText(getActivity(),"No Data Found",Toast.LENGTH_SHORT).show();
                    showprogressbar(false);
                }

            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

                Log.e("customerVerification"," - > Error    "+t.getMessage());
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {
                // Get the Image from data



                    final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/*; charset=utf-8");
                    List<MultipartBody.Part> files = new ArrayList<>();

                    Uri selectedImage = data.getData();
                    String selectedFilePath = FilePath.getPath(getActivity(), selectedImage);
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imagePath = cursor.getString(columnIndex);
                if(imagePath!=null && !imagePath.isEmpty()) {
                    File panFile = new File(imagePath);
                    files.add(MultipartBody.Part.createFormData("users_image",
                            panFile.getName(),
                            RequestBody.create(panFile, MEDIA_TYPE_PNG)));
                    profileupdate.setImageBitmap(BitmapFactory.decodeFile(selectedFilePath));
                    cursor.close();
                    uploadImage(files);
                }

                else{

                }


            } // When an Video is picked
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }



    private void uploadImage(List<MultipartBody.Part> imagetopath) {

        showprogressbar(true);

        ApiService apiService = ApiService.getInstance();


        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(getActivity());

        HashMap<String, String> params = new HashMap<>();
        params.put("users_id",PreferenceUtils.getStringValue(getActivity(), PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(getActivity(), PreferenceUtils.UserToken));


        if(imagetopath==null || imagetopath.isEmpty()) {
            params.put("users_image", imagePath);
        }

        Call<SignUpResponse> call = apiService.createFactoryApi().profile_uploadimage(headerMap, params,imagetopath);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.e("profileimg","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null) {
                    //  hideLoader();
                    SignUpResponse responseClass = response.body();
                    if(responseClass.getSuccess()==1){
                        SellerData data = responseClass.getResult().getUsersData();
                        Glide.with(getActivity()).load(image_base_url + data.getUsersImage())
                                .into(profileupdate);
                        PreferenceUtils.setStringValue(getActivity(),PreferenceUtils.UserImage,data.getUsersImage());
                        Toast.makeText(getActivity(), responseClass.getMessage(), Toast.LENGTH_SHORT).show();
                        showprogressbar(false);


                    }else{
                        Toast.makeText(getActivity(), responseClass.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }

            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

                Log.e("customerVerification"," - > Error    "+t.getMessage());
            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();
        if(PreferenceUtils.getStringValue(getActivity(),PreferenceUtils.UserType).equalsIgnoreCase("Seller")) {
            ((Navigation_Seller) getActivity()).setDrawer_Locked();
            if( PreferenceUtils.getStringValue(getActivity(), PreferenceUtils.UserImage).equals("")){
                Glide.with(getActivity()).load(R.drawable.user).into(profileupdate);
            }
            else {
                Glide.with(getActivity()).load(image_base_url + PreferenceUtils.getStringValue(getActivity(),PreferenceUtils.UserImage)).into(profileupdate);
            }
        }
        else{
            ((Navigation_Activity)getActivity()).setDrawer_Locked();
            if( PreferenceUtils.getStringValue(getActivity(), PreferenceUtils.UserImage).equals("")){
                Glide.with(getActivity()).load(R.drawable.user).into(profileupdate);
            }
            else {
                Glide.with(getActivity()).load(image_base_url + PreferenceUtils.getStringValue(getActivity(),PreferenceUtils.UserImage)).into(profileupdate);
            }
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if(PreferenceUtils.getStringValue(getActivity(),PreferenceUtils.UserType).equalsIgnoreCase("Seller"))
            ((Navigation_Seller)getActivity()).setDrawer_UnLocked();
        else{
            ((Navigation_Activity)getActivity()).setDrawer_UnLocked();

        }
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
