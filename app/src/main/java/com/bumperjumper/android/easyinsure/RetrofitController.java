package com.bumperjumper.android.easyinsure;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitController {

    private static final String BASE_URL = "http://192.168.5.234:3000/api/";

    private Retrofit _instance;

    public Retrofit getRetrofitInstance() {

        if(_instance == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();

            _instance= new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return _instance;
    }
}
