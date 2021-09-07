package com.cashmyproperty.app.View.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cashmyproperty.app.R;
import com.cashmyproperty.app.View.Utility.PreferenceUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if(PreferenceUtils.isNetworkAvailable(getApplicationContext())) {
                    if (PreferenceUtils.getBoolValue(getApplicationContext(), PreferenceUtils.Login)) {
                        if (PreferenceUtils.getBoolValue(getApplicationContext(), PreferenceUtils.LoginTypeSeller)) {
                            startActivity(new Intent(getApplicationContext(), Navigation_Seller.class));
                        } else {
                            startActivity(new Intent(getApplicationContext(), Navigation_Activity.class));
                        }
                    } else {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                    finish();
                }else{

                }
               /* Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();*/
            }
        }, 3000);

        setFCMToken();

    }

    private void setFCMToken(){

        try {

            FirebaseMessaging.getInstance().subscribeToTopic("weather")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (!task.isSuccessful()) {
                                Log.w("Firebase", "Fetching FCM registration token failed", task.getException());
                              //  return;
                            }

                           /* String token = task.getResult();
                            if (token != null && !token.isEmpty()) {
                                PreferenceUtils.setStringValue(getApplicationContext(), PreferenceUtils.FCMTOKEN, token);
                            }
                            Log.e("Firebase-Token", token);*/
                        }


                    });

           /* FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull Task<String> task) {
                            if (!task.isSuccessful()) {
                                Log.w("Firebase", "Fetching FCM registration token failed", task.getException());
                                return;
                            }

                            String token = task.getResult();
                            if (token != null && !token.isEmpty()) {
                                PreferenceUtils.setStringValue(getApplicationContext(), PreferenceUtils.FCMTOKEN, token);
                            }
                            Log.e("Firebase-Token", token);
                        }


                    });*/

        }catch (Exception e){
            e.printStackTrace();
        }

    }

        }


