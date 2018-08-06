package com.example.home.solardelivary.Api;



import com.example.home.solardelivary.Model.LocationRes;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by home on 12/15/2017.
 */

public interface LocationApi {
    @GET
    Call<List<LocationRes>> getData(@Url String url);
}
