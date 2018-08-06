package com.example.home.solardelivary.Model;



import com.example.home.solardelivary.Api.DigitalApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by home on 12/15/2017.
 */

public class DigitalClient {
    private static final String ROOT_URL = "http://34.214.70.124/";


    public DigitalClient() {

    }
    private static Retrofit getRetroClient() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static DigitalApi getApiService() {

        return getRetroClient().create(DigitalApi.class);
    }
}
