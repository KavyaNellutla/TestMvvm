package com.example.myapplication.Retrofit;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
@GET(APIConstants.API_DATA_URL)
    Call<JsonElement> getDatafromApi(@Query("tags") String tags,@Query("page") int pageNo);
}
