package com.cashmyproperty.app.View.Steps;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Activities.Navigation_Seller;
import com.cashmyproperty.app.View.Adapter.PropertyAdapter;
import com.cashmyproperty.app.View.Helper.FilePath;
import com.cashmyproperty.app.View.Response.AreaMeasurement;
import com.cashmyproperty.app.View.Response.LocationDatum;
import com.cashmyproperty.app.View.Response.PropertyData;
import com.cashmyproperty.app.View.Response.PropertyHolder;
import com.cashmyproperty.app.View.Response.SignUpResponse;
import com.cashmyproperty.app.View.Utility.PreferenceUtils;
import com.cashmyproperty.app.View.ViewModel.DataViewModel;
import com.cashmyproperty.app.View.network.ApiService;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
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

public class Step_Second extends AppCompatActivity implements PropertyAdapter.OnclickListener {

    Button btn_next;
    RecyclerView rec_holder;
    TextView edit_deed, edit_eid, edit_tradelicense, eid, poa, txt_residental, txt_commercial,bedrooms;
    ImageView plus_button_deed, eid_plus_button, license_plus_button, eid_button, poa_button;
    EditText txt_areadetails, address;
    PropertyAdapter propertyAdapter;
    String location_place = "", bed_no = "", location_id = "", areadetails = "",
            area_type = "",prop_holderid="", prop_holder = "", buffer = "", address_holder = "";
    Button btn_location;
    private static final int REQUEST_LOCATION = 1;
    private static final int PERMISSION_READ_STATE = 1;
    SignUpResponse responseClass;
    LocationManager locationManager;
    List<LocationDatum> data;
    List<AreaMeasurement> areaMeasurements;
    List<MultipartBody.Part> tit_deed,eid_passport,tradelicence,eid_company,pow_att;
    DataViewModel dataViewModel;
    List<PropertyHolder> propertyholder;
    ArrayList<String> locationData, property_holder, sqmt_list;
    String latitude, longitude;
    TextView titledeedimg,eid_passimg,tra_lic_img,eid_com_img,poa_img;
    AutoCompleteTextView spi_sqmt;
    String title_deed = "", eid_or_passport = "", trade_license = "", eid_img = "", power_of_attorney = "", imagePath = "";
    AutoCompleteTextView spi_location_place, spi_bedrooms;
    ImageView back;
    private ProgressDialog progress;
    String cat_name="";
    TextInputLayout txt_bedrooms;
    private LocationRequest locationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_stepsecond);

        cat_name=getIntent().getStringExtra("cat_name");
        initviews();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);




        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);

        getstep_second();
        get_bedno();


        btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ThirdStep_Fragment thirdStep_fragment = new ThirdStep_Fragment();
                fragmentTransaction.replace(R.id.nav_host_fragment, thirdStep_fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
                sub_stepsecond();


            }
        });

        plus_button_deed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                    return;
                }

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);

            }
        });

        eid_plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                    return;
                }

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 1);
            }
        });

        license_plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                    return;
                }

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 2);
            }
        });

        eid_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                    return;
                }

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 3);
            }
        });

        poa_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                    return;
                }

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 4);
            }
        });


    }


    private void sub_stepsecond() {

        showprogressbar(true);

        areadetails = txt_areadetails.getText().toString();
         String addres_s = address.getText().toString();
        address_holder = addres_s.substring(0, 1).toUpperCase() + addres_s.substring(1).toLowerCase();

        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(getApplicationContext());

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(getApplicationContext(), PreferenceUtils.customer_id));
        params.put("users_token", PreferenceUtils.getStringValue(getApplicationContext(), PreferenceUtils.UserToken));
        params.put("property_id", PreferenceUtils.getStringValue(getApplicationContext(), PreferenceUtils.PropertyID));
        params.put("location", location_id);
        params.put("no_of_bed", bed_no);
        params.put("total_area", areadetails);
        params.put("area_type", area_type);
        params.put("property_holder", prop_holderid);
        params.put("current_location", buffer);
        params.put("address", address_holder);




        Call<SignUpResponse> call = apiService.createFactoryApi().addsecond_step(headerMap, params,tit_deed,eid_passport,tradelicence,eid_company,pow_att);
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

                        PreferenceUtils.setStringValue(getApplicationContext(), PreferenceUtils.PropertyID, String.valueOf(data.getPropertyId()));


                        Intent intent=new Intent(getApplicationContext(),ThirdStep_Activity.class);
                        startActivity(intent);

                    } else
                        showprogressbar(false);
                        Toast.makeText(getApplicationContext() ,responseClass.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                // hideLoader();
                // showSnackbar();
                showprogressbar(false);
                Log.e("sub_stepone", " - > Error    " + t.getMessage());
            }
        });

    }

    private void get_bedno() {
        int i;
        ArrayList<String> bedno = new ArrayList<>();

        for (i = 1; i <= 20; i++) {
            bedno.add(String.valueOf(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_list_item, bedno);
        spi_bedrooms.setAdapter(adapter);
        spi_bedrooms.setText(adapter.getItem(0), false);

        bed_no = bedno.get(0);
        spi_bedrooms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                bed_no = bedno.get(position);
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


                final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/*; charset=utf-8");
                tit_deed = new ArrayList<>();

                Uri selectedImage = data.getData();
                String selectedFilePath = FilePath.getPath(getApplicationContext(), selectedImage);
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getApplicationContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                title_deed= cursor.getString(columnIndex);
                String[] parts = title_deed.split("/");
                String imge_filename = parts[parts.length - 1];
                if(title_deed!=null && !title_deed.isEmpty()) {

                    File titledeed = new File(title_deed);
                    tit_deed.add(MultipartBody.Part.createFormData("title_deed",
                            titledeed.getName(),
                            RequestBody.create(MEDIA_TYPE_PNG, titledeed)));

                    titledeedimg.setVisibility(View.VISIBLE);
                    titledeedimg.setText(imge_filename);

                    cursor.close();

                }


            }

            if (requestCode == 1 && resultCode == RESULT_OK && null != data) {

                final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/*; charset=utf-8");
                eid_passport = new ArrayList<>();

                Uri selectedImage = data.getData();
                String selectedFilePath = FilePath.getPath(getApplicationContext(), selectedImage);
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getApplicationContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                eid_or_passport = cursor.getString(columnIndex);
                String[] parts = eid_or_passport.split("/");
                String imge_filename = parts[parts.length - 1];
                if (eid_or_passport != null && !eid_or_passport.isEmpty()) {

                    File eid_pss = new File(eid_or_passport);
                    eid_passport.add(MultipartBody.Part.createFormData("eid_or_passport",
                            eid_pss.getName(),
                            RequestBody.create(MEDIA_TYPE_PNG, eid_pss)));

                    eid_passimg.setVisibility(View.VISIBLE);
                    eid_passimg.setText(imge_filename);
                    cursor.close();

                }
            }

            if (requestCode == 2 && resultCode == RESULT_OK && null != data) {

                final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/*; charset=utf-8");
                tradelicence = new ArrayList<>();

                Uri selectedImage = data.getData();
                String selectedFilePath = FilePath.getPath(getApplicationContext(), selectedImage);
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getApplicationContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                trade_license = cursor.getString(columnIndex);
                String[] parts = trade_license.split("/");
                String imge_filename= parts[parts.length - 1];
                if (trade_license != null && !trade_license.isEmpty()) {

                    File tradelicense = new File(trade_license);
                    tradelicence.add(MultipartBody.Part.createFormData("trade_license",
                            tradelicense.getName(),
                            RequestBody.create(MEDIA_TYPE_PNG, tradelicense)));

                    tra_lic_img.setVisibility(View.VISIBLE);
                    tra_lic_img.setText(imge_filename);
                    cursor.close();


                }
            }

            if (requestCode == 3 && resultCode == RESULT_OK && null != data) {

                final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/*; charset=utf-8");
                eid_company = new ArrayList<>();

                Uri selectedImage = data.getData();
                String selectedFilePath = FilePath.getPath(getApplicationContext(), selectedImage);
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getApplicationContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                eid_img  = cursor.getString(columnIndex);
                String[] parts = eid_img.split("/");
                String imge_filename = parts[parts.length - 1];

                if (eid_img != null && !eid_img.isEmpty()) {

                    File eid = new File(eid_img);
                    eid_company.add(MultipartBody.Part.createFormData("eid",
                            eid.getName(),
                            RequestBody.create(MEDIA_TYPE_PNG, eid)));

                    eid_com_img.setVisibility(View.VISIBLE);
                    eid_com_img.setText(imge_filename);

                    cursor.close();
                }
            }

            if (requestCode == 4 && resultCode == RESULT_OK && null != data) {

                final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/*; charset=utf-8");
                pow_att = new ArrayList<>();

                Uri selectedImage = data.getData();
                String selectedFilePath = FilePath.getPath(getApplicationContext(), selectedImage);
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getApplicationContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                power_of_attorney = cursor.getString(columnIndex);
                String[] parts = power_of_attorney.split("/");
                String imge_filename = parts[parts.length - 1];

                if (power_of_attorney != null && !power_of_attorney.isEmpty()) {

                    File poa = new File(power_of_attorney);
                    pow_att.add(MultipartBody.Part.createFormData("power_of_attorney",
                            poa.getName(),
                            RequestBody.create(MEDIA_TYPE_PNG, poa)));

                    poa_img.setVisibility(View.VISIBLE);
                    poa_img.setText(imge_filename);
                    cursor.close();
                }

            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }



    private void getstep_second() {

        showprogressbar(true);
        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(getApplicationContext());

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(getApplicationContext(), PreferenceUtils.customer_id));
        params.put("users_token", PreferenceUtils.getStringValue(getApplicationContext(), PreferenceUtils.UserToken));


        Call<SignUpResponse> call = apiService.createFactoryApi().steptwo(headerMap, params);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.e("customerSignUpForm", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    responseClass = response.body();
                    if (responseClass.getSuccess() == 1) {

                        data = responseClass.getResult().getLocationData();
                        propertyholder = responseClass.getResult().getPropertyHolder();
                        areaMeasurements=responseClass.getResult().getAreaMeasurement();

                        locationData = new ArrayList<>();
                        sqmt_list = new ArrayList<>();

                        for (LocationDatum datum : data) {
                            locationData.add(datum.getLocationName());
                        }

                        for(AreaMeasurement areaMeasurement:areaMeasurements){
                            sqmt_list.add(areaMeasurement.getMeasurment());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_list_item, sqmt_list);
                        spi_sqmt.setAdapter(adapter);
                        spi_sqmt.setText(adapter.getItem(0), false);

                         area_type= String.valueOf(responseClass.getResult().getAreaMeasurement().get(0).getMeasurment());
                        spi_location_place.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                               @Override
                              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                              Object item = parent.getItemAtPosition(position);
                               area_type = String.valueOf(responseClass.getResult().getAreaMeasurement().get(position).getMeasurment());

                                 }
                                });

                        get_location();


                        showprogressbar(false);
                        propertyAdapter = new PropertyAdapter(getApplicationContext(), propertyholder, Step_Second.this);
                        rec_holder.setAdapter(propertyAdapter);


                    } else {
                        showprogressbar(false);
                        Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();
                    }


                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

            }
        });


    }



    private void get_location() {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_list_item, locationData);
        spi_location_place.setAdapter(adapter);
        spi_location_place.setText(adapter.getItem(0), false);

        location_id = String.valueOf(data.get(0).getLocationId());
        spi_location_place.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                location_id = String.valueOf(data.get(position).getLocationId());
            }
        });

    }

    private void initviews() {
        edit_deed = findViewById(R.id.edit_deed);
        edit_eid = findViewById(R.id.edit_eid);
        bedrooms=findViewById(R.id.bedrooms);
        edit_tradelicense = findViewById(R.id.edit_tradelicense);
        eid = findViewById(R.id.eid);
        txt_bedrooms=findViewById(R.id.txt_bedrooms);
        poa = findViewById(R.id.poa);
        plus_button_deed = findViewById(R.id.plus_button_deed);
        eid_plus_button = findViewById(R.id.eid_plus_button);
        license_plus_button = findViewById(R.id.license_plus_button);
        eid_button = findViewById(R.id.eid_button);
        poa_button = findViewById(R.id.poa_button);
        address = findViewById(R.id.address);
        back = findViewById(R.id.back);
        txt_areadetails = findViewById(R.id.txt_areadetails);
        spi_location_place = findViewById(R.id.spi_location_place);
        spi_bedrooms = findViewById(R.id.spi_bedrooms);
        btn_location = findViewById(R.id.btn_location);
        spi_sqmt=findViewById(R.id.sqmt);
        rec_holder = findViewById(R.id.rec_holder);
        rec_holder.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        titledeedimg=findViewById(R.id.titledeedimg);
        eid_passimg=findViewById(R.id.eid_passimg);
        tra_lic_img=findViewById(R.id.tra_lic_img);
        eid_com_img=findViewById(R.id.eid_com_img);
        poa_img=findViewById(R.id.poa_img);

        if(cat_name.equalsIgnoreCase("Commercial")){
            spi_bedrooms.setVisibility(View.GONE);
            bedrooms.setVisibility(View.GONE);
            txt_bedrooms.setVisibility(View.GONE);
        }
        else{
            spi_bedrooms.setVisibility(View.VISIBLE);
            bedrooms.setVisibility(View.VISIBLE);
            txt_bedrooms.setVisibility(View.VISIBLE);
        }

        titledeedimg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (titledeedimg.getRight() - titledeedimg.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        titledeedimg.setVisibility(View.GONE);
                        return true;
                    }
                }
                return false;
            }
        });


        eid_passimg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (eid_passimg.getRight() - eid_passimg.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        eid_passimg.setVisibility(View.GONE);
                        return true;
                    }
                }
                return false;
            }
        });

        tra_lic_img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (tra_lic_img.getRight() - tra_lic_img.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        tra_lic_img.setVisibility(View.GONE);
                        return true;
                    }
                }
                return false;
            }
        });

        eid_com_img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (eid_com_img.getRight() - eid_com_img.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        eid_com_img.setVisibility(View.GONE);
                        return true;
                    }
                }
                return false;
            }
        });

        poa_img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (poa_img.getRight() - poa_img.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        poa_img.setVisibility(View.GONE);
                        return true;
                    }
                }
                return false;
            }
        });

        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isGPSEnabled()) {

                    getCurrentLocation();

                }else {

                    turnOnGPS();
                }

               /* getLocation();

               ActivityCompat.requestPermissions( getActivity(),
                        new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

                        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                            OnGPS();
                        } else {
                            getLocation();
                        }*/
            }
        });



       /* txt_areadetails.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (txt_areadetails.getRight() - txt_areadetails.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        Toast.makeText(getActivity(),"",Toast.LENGTH_LONG).show();
                              get_Sqmt();
                        return true;
                    }
                }
                return false;
            }
        });*/
    }

    private void turnOnGPS() {

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(getApplicationContext(), "GPS is already tured on", Toast.LENGTH_SHORT).show();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(Step_Second.this, 2);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //Device does not have location
                            break;
                    }
                }
            }
        });

    }

    private void getCurrentLocation() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                if (isGPSEnabled()) {

                    LocationServices.getFusedLocationProviderClient(getApplicationContext())
                            .requestLocationUpdates(locationRequest, new LocationCallback() {
                                @Override
                                public void onLocationResult(@NonNull LocationResult locationResult) {
                                    super.onLocationResult(locationResult);

                                    LocationServices.getFusedLocationProviderClient(getApplicationContext())
                                            .removeLocationUpdates(this);

                                    if (locationResult != null && locationResult.getLocations().size() >0){

                                        int index = locationResult.getLocations().size() - 1;
                                        double latitude = locationResult.getLocations().get(index).getLatitude();
                                        double longitude = locationResult.getLocations().get(index).getLongitude();

                                        buffer=new StringBuilder().append(latitude).append(longitude).toString();
                                        Toast.makeText(getApplicationContext(),"Location get successfully",Toast.LENGTH_LONG).show();

                                    }
                                }
                            }, Looper.getMainLooper());

                } else {
                    turnOnGPS();
                }

            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }

    }


    private boolean isGPSEnabled() {

        LocationManager locationManager = null;
        boolean isEnabled = false;

        if (locationManager == null) {
            locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;

    }


    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            buffer=new StringBuilder().append(0).append(0).toString();
            Toast.makeText(getApplicationContext(),"Location get successfully",Toast.LENGTH_LONG).show();
            /*Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
                buffer=new StringBuilder().append(latitude).append(longitude).toString();
                Toast.makeText(getActivity(),"Location get successfully",Toast.LENGTH_LONG).show();

            } else {
                buffer=new StringBuilder().append(0).append(0).toString();
               // Toast.makeText(getActivity(), "Unable to find location.", Toast.LENGTH_SHORT).show();
            }*/
        }
    }





    @Override
    public void onClick(String string,String name) {
        prop_holderid=string;
        prop_holder=name;

        if(prop_holder.equalsIgnoreCase("Individual")){
            edit_deed.setVisibility(View.VISIBLE);
            plus_button_deed.setVisibility(View.VISIBLE);
            edit_eid.setVisibility(View.VISIBLE);
            eid_plus_button.setVisibility(View.VISIBLE);
            edit_tradelicense.setVisibility(View.GONE);
            eid.setVisibility(View.GONE);
            poa.setVisibility(View.GONE);
            license_plus_button.setVisibility(View.GONE);
            eid_button.setVisibility(View.GONE);
            poa_button.setVisibility(View.GONE);
            tra_lic_img.setVisibility(View.GONE);
            eid_com_img.setVisibility(View.GONE);
            poa_img.setVisibility(View.GONE);
        }
        else{
            edit_deed.setVisibility(View.GONE);
            plus_button_deed.setVisibility(View.GONE);
            edit_eid.setVisibility(View.GONE);
            eid_plus_button.setVisibility(View.GONE);
            edit_tradelicense.setVisibility(View.VISIBLE);
            eid.setVisibility(View.VISIBLE);
            poa.setVisibility(View.VISIBLE);
            license_plus_button.setVisibility(View.VISIBLE);
            eid_button.setVisibility(View.VISIBLE);
            poa_button.setVisibility(View.VISIBLE);
            titledeedimg.setVisibility(View.GONE);
            eid_passimg.setVisibility(View.GONE);
        }

    }

/*    private List<MultipartBody.Part> imageFilesSend() {

        final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/*; charset=utf-8");

        List<MultipartBody.Part> files = new ArrayList<>();

        File titledeed = new File(title_deed);
        files.add(MultipartBody.Part.createFormData("title_deed",
                titledeed.getName(),
                RequestBody.create(MEDIA_TYPE_PNG, titledeed)));


        File eid_pss = new File(eid_or_passport);
        files.add(MultipartBody.Part.createFormData("eid_or_passport",
                eid_pss.getName(),
                RequestBody.create(MEDIA_TYPE_PNG, eid_pss)));


        File tradelicense = new File(trade_license);
        files.add(MultipartBody.Part.createFormData("trade_license",
                tradelicense.getName(),
                RequestBody.create(MEDIA_TYPE_PNG, tradelicense)));


        File eid = new File(eid_img);
        files.add(MultipartBody.Part.createFormData("eid",
                eid.getName(),
                RequestBody.create(MEDIA_TYPE_PNG, eid)));

        File poa = new File(power_of_attorney);
        files.add(MultipartBody.Part.createFormData("power_of_attorney",
                poa.getName(),
                RequestBody.create(MEDIA_TYPE_PNG, poa)));


        return files;
    }*/

    private void showprogressbar(Boolean IS_SHOW) {
        if (IS_SHOW) {
            progress = ProgressDialog.show(Step_Second.this, "", "");
            progress.setContentView(R.layout.loader);
            progress.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        } else {
            progress.dismiss();
        }
    }
}
