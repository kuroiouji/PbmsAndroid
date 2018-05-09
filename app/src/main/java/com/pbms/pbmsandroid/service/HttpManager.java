package com.pbms.pbmsandroid.service;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    private static HttpManager instance;

    public static HttpManager getInstance() {
        if (instance == null)
            instance = new HttpManager();
        return instance;
    }

    private Context mContext;
    private ApiService service;

    private HttpManager() {
        OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.80.39.17/TSP58/nursing/index.php/pbms/project/android/ServiceApi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//                .client(okHttpClient)
        service = retrofit.create(ApiService.class);
    }

    public ApiService getService() {
        return service;
    }
}
