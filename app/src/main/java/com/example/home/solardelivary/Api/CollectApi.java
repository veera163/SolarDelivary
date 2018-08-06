package com.example.home.solardelivary.Api;





import com.example.home.solardelivary.Model.UploadImageResult;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface CollectApi {
    @Multipart
    @POST("upload/deliveryStatus/plantId/{plantId}/material/{material}/device/{deviceId}/trip/{tripId}")
    Call<UploadImageResult> uploadImage(@Part MultipartBody.Part[] file, @Path("plantId") String plantId, @Path("material") String material, @Path("deviceId") String deviceId, @Path("tripId") String tripId);
   /* @GET("location")
    Call<Result> test();*/
}
