package com.cashmyproperty.app.View.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiService {

    private static String BASE_URL = "https://apkconnectlab.com/cmpdtest/api/";
    private static ApiService instance;

    private ApiService(){

    }
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    OkHttpClient okHttpClient = UnsafeHttpClient.getUnsafeOkHttpClient();

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(80, TimeUnit.SECONDS)
            .readTimeout(80, TimeUnit.SECONDS)
            .writeTimeout(80, TimeUnit.SECONDS)
            .build();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    public static ApiService getInstance(){
        if(instance == null){
            instance = new ApiService();
        }
        return instance;
    }

    public FactoryApi createFactoryApi(){

        return retrofit.create(FactoryApi.class);

    }



}
