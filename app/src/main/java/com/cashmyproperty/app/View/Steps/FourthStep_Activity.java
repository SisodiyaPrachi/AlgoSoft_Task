package com.cashmyproperty.app.View.Steps;

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
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.CheckBox;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Activities.Navigation_Activity;
import com.cashmyproperty.app.View.Activities.Navigation_Seller;
import com.cashmyproperty.app.View.Adapter.KindPropertyAdapter;
import com.cashmyproperty.app.View.Helper.FilePath;
import com.cashmyproperty.app.View.Response.KindOfProperty;
import com.cashmyproperty.app.View.Response.PropertyData;
import com.cashmyproperty.app.View.Response.SignUpResponse;
import com.cashmyproperty.app.View.Response.SignUpResult;
import com.cashmyproperty.app.View.Response.TermsConditionData;
import com.cashmyproperty.app.View.Utility.PreferenceUtils;
import com.cashmyproperty.app.View.ViewModel.DataViewModel;
import com.cashmyproperty.app.View.network.ApiService;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
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

public class FourthStep_Activity extends AppCompatActivity {

    TextView txt_termsconditions,noc;
    private static final int PERMISSION_READ_STATE = 1;
    String termsconditions="N";
    String imagePath="";
    Button sub_fourstep;
    Dialog dialog;
    TextView noc_img;
    DataViewModel dataViewModel;
    List<MultipartBody.Part> files;
    ImageView back;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_stepfourth);


        initviews();

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        txt_termsconditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_termscond();
            }
        });


    }

    private void open_termscond() {
       dialog.setContentView(R.layout.termsandcondition);
       dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
       TextView title=dialog.findViewById(R.id.title);
       TextView view=dialog.findViewById(R.id.view);
        CheckBox chk_policy=dialog.findViewById(R.id.chk_policy);
        TextView txt_policy=dialog.findViewById(R.id.txt_policy);
        ImageView cancel=dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dataViewModel.getTermsandCondition(getApplicationContext()).observe(this, new Observer<SignUpResult>() {
            @Override
            public void onChanged(SignUpResult result) {
                //hideLoader();
                if (result != null) {
                    {

                        TermsConditionData termsConditionData=result.getTermsConditionData();
                       view.setText(termsConditionData.getDescription());
                       title.setText(termsConditionData.getHeading());

                    }
                }
            }
        });

        chk_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!chk_policy.isChecked()){
                    termsconditions="N";
                }
                else{
                    termsconditions="Y";

                }
            }
        });



        dialog.show();
    }

    private void sub_fourthstep(List<MultipartBody.Part> imagetopath) {

        showprogressbar(true);

        ApiService apiService = ApiService.getInstance();

        HashMap<String, String> headerMap = PreferenceUtils.getHeaderMap(getApplicationContext());

        HashMap<String, String> params = new HashMap<>();

        params.put("users_id", PreferenceUtils.getStringValue(getApplicationContext(),PreferenceUtils.customer_id));
        params.put("users_token",PreferenceUtils.getStringValue(getApplicationContext(),PreferenceUtils.UserToken));
        params.put("property_id",PreferenceUtils.getStringValue(getApplicationContext(),PreferenceUtils.PropertyID));
        params.put("term_n_condition",termsconditions);

        if(imagetopath==null || imagetopath.isEmpty()) {
            params.put("property_noc", imagePath);
        }



        Call<SignUpResponse> call = apiService.createFactoryApi().fourstepsubmit(headerMap, params,imagetopath);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.e("stepfour", "Response: " + new Gson().toJson(response.body()));
                if (response.body() != null) {

                    SignUpResponse responseClass = response.body();
                    if (responseClass.getSuccess() == 1) {
                        PropertyData data = responseClass.getResult().getPropertyData();
                        showprogressbar(false);
                       approval_alert();
                    }  else
                        showprogressbar(false);
                        Toast.makeText(getApplicationContext(), responseClass.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                // hideLoader();
                // showSnackbar();
                Log.e("sub_stepfour", " - > Error    " + t.getMessage());
            }
        });

    }

    private void approval_alert() {

        dialog.setContentView(R.layout.app_alert);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView title=dialog.findViewById(R.id.title);
        title.setText("Approval Awaited");
        TextView view=dialog.findViewById(R.id.view);
        view.setText("Property listed successfully.It will be live after inspector and admin approval.Thanks for your patience.");
        Button btn_ok=dialog.findViewById(R.id.btn_ok);
        btn_ok.setText("OK");
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Navigation_Seller.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        ImageView cancel=dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void initviews() {
        txt_termsconditions=findViewById(R.id.txt_termsconditions);
        noc=findViewById(R.id.noc);
        back=findViewById(R.id.back);
        sub_fourstep=findViewById(R.id.sub_fourstep);
        dialog=new Dialog(FourthStep_Activity.this);
        noc_img=findViewById(R.id.noc_img);

        noc_img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(event.getRawX() >= (noc_img.getRight() - noc_img.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        noc_img.setVisibility(View.GONE);
                        return true;
                    }
                }
                return false;
            }
        });

        noc.setOnClickListener(new View.OnClickListener() {
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

        sub_fourstep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(termsconditions.equals("N")){
                    Toast.makeText(getApplicationContext(),"Please Select Terms & Condition",Toast.LENGTH_LONG).show();
                }
                else
                sub_fourthstep(files);
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
                 files = new ArrayList<>();

                Uri selectedImage = data.getData();
                String selectedFilePath = FilePath.getPath(getApplicationContext(), selectedImage);
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getApplicationContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath= cursor.getString(columnIndex);
                String[] parts = imagePath.split("/");
                String imge_filename  = parts[parts.length - 1];
                if(imagePath!=null && !imagePath.isEmpty()) {
                    File panFile = new File(imagePath);
                    files.add(MultipartBody.Part.createFormData("property_noc",
                            panFile.getName(),
                            RequestBody.create(panFile, MEDIA_TYPE_PNG)));

                    noc_img.setVisibility(View.VISIBLE);
                    noc_img.setText(imge_filename);


                    cursor.close();

                }

                else{

                }
            } // When an Video is picked
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }




    private void showprogressbar(Boolean IS_SHOW) {
        if (IS_SHOW) {
            progress = ProgressDialog.show(FourthStep_Activity.this, "", "");
            progress.setContentView(R.layout.loader);
            progress.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        } else {
            progress.dismiss();
        }
    }
}
