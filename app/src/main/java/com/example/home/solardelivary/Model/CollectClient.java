package com.example.home.solardelivary.Model;



import com.example.home.solardelivary.Api.CollectApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @author Pratik Butani
 */
public class CollectClient {

    /**
     * Upload URL of your folder with php file name...
     * You will find this file in php_upload folder in this project
     * You can copy that folder and paste in your htdocs folder...
     */
    private static final String ROOT_URL = "http://34.214.70.124/";


    public CollectClient() {

    }
    private static Retrofit getRetroClient() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static CollectApi getApiService() {

        return getRetroClient().create(CollectApi.class);
    }
}
