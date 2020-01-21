package com.example.myapplication.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;

    public  static  Retrofit getRetrofitClient(){
        if(retrofit==null){
            retrofit= new Retrofit.Builder()
                    .baseUrl(APIConstants.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
