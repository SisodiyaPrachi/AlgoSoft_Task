package com.cashmyproperty.app.View.Steps;

import android.Manifest;
import android.app.AlertDialog;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Activities.Navigation_Seller;
import com.cashmyproperty.app.View.Helper.FilePath;
import com.cashmyproperty.app.View.Response.PropertyData;
import com.cashmyproperty.app.View.Response.SignUpResponse;
import com.cashmyproperty.app.View.Utility.PreferenceUtils;
import com.cashmyproperty.app.View.network.ApiService;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

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

public class ThirdStep_Activity extends AppCompatActivity {

    Button nxt_btn;

    TextView txt_img1,txt_img2,txt_img3,txt_img4,txt_img5,txt_img6,txt_img7,txt_img8,txt_img9,txt_img10,tutorial,tutorial_img;
    private static final int PERMISSION_READ_STATE = 1;
    ImageView imageView1,select_img1,imageView2,select_img2,imageView3,select_img3,imageView4,select_img4,
            imageView5,select_img5,imageView6,select_img6,imageView7,select_img7,imageView8,select_img8,
            imageView9,select_img9,imageView10,select_img10;
    String imagePath="",description="",videopath="";
    TextInputEditText txt_extrainfo;
    Integer select_image=0;
    List<MultipartBody.Part> video;
    private ProgressDialog progress;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_stepthird);


        initviews();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });


        nxt_btn=findViewById(R.id.nxt_btn);
        nxt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                description=txt_extrainfo.getText().toString();
                if(description.equals("")){
                    txt_extrainfo.setError("Please Enter Description");
                }
                else {
                    sub_thirdstep();

                }
            }
        });

    }

    private void sub_thirdstep() {

        showprogressbar(true);

        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(getApplicationContext());

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(getApplicationContext(),PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(getApplicationContext(),PreferenceUtils.UserToken));
        params.put("property_id",PreferenceUtils.getStringValue(getApplicationContext(),PreferenceUtils.PropertyID));
        params.put("description",description);


        Call<SignUpResponse> call = apiService.createFactoryApi().add_thirdstep(headerMap, params/*,video*/);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.e("customerSignUpForm", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    SignUpResponse responseClass = response.body();
                    if (responseClass.getSuccess() == 1) {
                        PropertyData data = responseClass.getResult().getPropertyData();
                        showprogressbar(false);
                        Toast.makeText(getApplicationContext(), responseClass.getMessage(), Toast.LENGTH_SHORT).show();


                        Intent intent=new Intent(getApplicationContext(),FourthStep_Activity.class);
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
                Log.e("sub_stepone", " - > Error    " + t.getMessage());
            }
        });

    }

    private void initviews() {

        imageView1=findViewById(R.id.imageView1);
        select_img1=findViewById(R.id.select_img1);
        imageView2=findViewById(R.id.imageView2);
        select_img2=findViewById(R.id.select_img2);
        imageView3=findViewById(R.id.imageView3);
        select_img3=findViewById(R.id.select_img3);
        imageView4=findViewById(R.id.imageView4);
        select_img4=findViewById(R.id.select_img4);
        imageView5=findViewById(R.id.imageView5);
        select_img5=findViewById(R.id.select_img5);
        imageView6=findViewById(R.id.imageView6);
        back=findViewById(R.id.back);
        tutorial=findViewById(R.id.tutorial);
        tutorial_img=findViewById(R.id.tutorial_img);
        select_img6=findViewById(R.id.select_img6);
        imageView7=findViewById(R.id.imageView7);
        select_img7=findViewById(R.id.select_img7);
        imageView8=findViewById(R.id.imageView8);
        select_img8=findViewById(R.id.select_img8);
        imageView9=findViewById(R.id.imageView9);
        select_img9=findViewById(R.id.select_img6);
        imageView10=findViewById(R.id.imageView10);
        select_img10=findViewById(R.id.select_img10);
        txt_extrainfo=findViewById(R.id.txt_extrainfo);
        txt_img1=findViewById(R.id.txt_img1);
        txt_img2=findViewById(R.id.txt_img2);
        txt_img3=findViewById(R.id.txt_img3);
        txt_img4=findViewById(R.id.txt_img4);
        txt_img5=findViewById(R.id.txt_img5);
        txt_img6=findViewById(R.id.txt_img6);
        txt_img7=findViewById(R.id.txt_img7);
        txt_img8=findViewById(R.id.txt_img8);
        txt_img9=findViewById(R.id.txt_img9);
        txt_img10=findViewById(R.id.txt_img10);

        tutorial_img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (tutorial_img.getRight() - tutorial_img.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        tutorial_img.setVisibility(View.GONE);
                        return true;
                    }
                }
                return false;
            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                select_image=1;

                select_img1.setVisibility(View.GONE);
                txt_img1.setVisibility(View.GONE);

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                }

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);

            }
        });

/*
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                }

                Intent pickVideoIntent = new Intent(Intent.ACTION_GET_CONTENT);
                pickVideoIntent.setType("video/*");
                startActivityForResult(pickVideoIntent, 1);
            }
        });
*/

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                select_image=2;

                select_img2.setVisibility(View.GONE);
                txt_img2.setVisibility(View.GONE);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        requestPermission();
        }

        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 0);

            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                select_image=3;

                select_img3.setVisibility(View.GONE);
                txt_img3.setVisibility(View.GONE);

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                }

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);

            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                select_image=4;

                select_img4.setVisibility(View.GONE);
                txt_img4.setVisibility(View.GONE);

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                }

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);

            }
        });

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                select_image=5;

                select_img5.setVisibility(View.GONE);
                txt_img5.setVisibility(View.GONE);

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                }

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);

            }
        });

        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                select_image=6;

                select_img6.setVisibility(View.GONE);
                txt_img6.setVisibility(View.GONE);

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                }

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);

            }
        });

        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                select_image=7;

                select_img7.setVisibility(View.GONE);
                txt_img7.setVisibility(View.GONE);

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                }

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);

            }
        });

        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                select_image=8;

                select_img8.setVisibility(View.GONE);
                txt_img8.setVisibility(View.GONE);

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                }

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);

            }
        });

        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                select_image=9;

                select_img9.setVisibility(View.GONE);
                txt_img9.setVisibility(View.GONE);

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                }

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);

            }
        });

        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                select_image=10;

                select_img10.setVisibility(View.GONE);
                txt_img10.setVisibility(View.GONE);

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                }

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);

            }
        });



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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {
                // Get the Image from data
                final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/*; charset=utf-8");
                List<MultipartBody.Part> files = new ArrayList<>();

                Uri selectedImage = data.getData();
                String selectedFilePath = FilePath.getPath(getApplicationContext(), selectedImage);
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getApplicationContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath = cursor.getString(columnIndex);
                if (imagePath != null && !imagePath.isEmpty()) {
                    File panFile = new File(imagePath);
                    files.add(MultipartBody.Part.createFormData("property_image",
                            panFile.getName(),
                            RequestBody.create(panFile, MEDIA_TYPE_PNG)));

                    if (select_image == 1)
                        imageView1.setImageBitmap(BitmapFactory.decodeFile(selectedFilePath));
                    else if (select_image == 2)
                        imageView2.setImageBitmap(BitmapFactory.decodeFile(selectedFilePath));
                    else if (select_image == 3)
                        imageView3.setImageBitmap(BitmapFactory.decodeFile(selectedFilePath));
                    else if (select_image == 4)
                        imageView4.setImageBitmap(BitmapFactory.decodeFile(selectedFilePath));
                    else if (select_image == 5)
                        imageView5.setImageBitmap(BitmapFactory.decodeFile(selectedFilePath));
                    else if (select_image == 6)
                        imageView6.setImageBitmap(BitmapFactory.decodeFile(selectedFilePath));
                    else if (select_image == 7)
                        imageView7.setImageBitmap(BitmapFactory.decodeFile(selectedFilePath));
                    else if (select_image == 8)
                        imageView8.setImageBitmap(BitmapFactory.decodeFile(selectedFilePath));
                    else if (select_image == 9)
                        imageView9.setImageBitmap(BitmapFactory.decodeFile(selectedFilePath));
                    else if (select_image == 10)
                        imageView10.setImageBitmap(BitmapFactory.decodeFile(selectedFilePath));
                    else {
                        Toast.makeText(getApplicationContext(), "No Image found", Toast.LENGTH_LONG).show();
                    }

                    cursor.close();
                    uploadImage(files);
                } else {
                    //RequestBody requestBody = RequestBody.create(MediaType.parse("*/*");
                }
            }

            if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
                final MediaType MEDIA_TYPE_PNG = (MediaType.parse("video/*; charset=utf-8"));
                video = new ArrayList<>();

                Uri selectedImage = data.getData();
                videopath  = FilePath.getPath(getApplicationContext(), selectedImage);

                String[] parts = videopath.split("/");
                String imge_filename = parts[parts.length - 1];
                /*String[] filePathColumn = {MediaStore.Video.Media.DATA};
                Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                videopath = cursor.getString(columnIndex);*/
                if (videopath != null && !videopath.isEmpty()) {
                    File panFile = new File(videopath);
                    video.add(MultipartBody.Part.createFormData("seller_tutorial",
                            panFile.getName(),
                            RequestBody.create(panFile, MEDIA_TYPE_PNG)));

                    tutorial_img.setVisibility(View.VISIBLE);
                    tutorial_img.setText(imge_filename);
                  //  cursor.close();
                }
                else{
                    Toast.makeText(getApplicationContext(),"No video Found",Toast.LENGTH_LONG).show();

            }

            }



        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    private void uploadImage(List<MultipartBody.Part> imagetopath) {

        showprogressbar(true);

        ApiService apiService = ApiService.getInstance();


        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(getApplicationContext());

        HashMap<String, String> params = new HashMap<>();
        params.put("users_id",PreferenceUtils.getStringValue(getApplicationContext(), PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(getApplicationContext(), PreferenceUtils.UserToken));
        params.put("property_id",PreferenceUtils.getStringValue(getApplicationContext(),PreferenceUtils.PropertyID));


        if(imagetopath==null || imagetopath.isEmpty()) {
            params.put("property_image", imagePath);
        }

        Call<SignUpResponse> call = apiService.createFactoryApi().addpropertyimage(headerMap, params,imagetopath);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.e("customerVerification","Response: "+new Gson().toJson(response.body()));
                if(response.body()!=null) {
                    //  hideLoader();
                    SignUpResponse responseClass = response.body();
                    if(responseClass.getSuccess()==1){
                        PropertyData data = responseClass.getResult().getPropertyData();

                        showprogressbar(false);

                        Toast.makeText(getApplicationContext(), responseClass.getMessage(), Toast.LENGTH_SHORT).show();


                    }else{
                        showprogressbar(false);
                        Toast.makeText(getApplicationContext(), responseClass.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }

            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

                Log.e("stepthird"," - > Error    "+t.getMessage());
            }
        });

    }




    private void showprogressbar(Boolean IS_SHOW) {
        if (IS_SHOW) {
            progress = ProgressDialog.show(ThirdStep_Activity.this, "", "");
            progress.setContentView(R.layout.loader);
            progress.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        } else {
            progress.dismiss();
        }
    }

}
