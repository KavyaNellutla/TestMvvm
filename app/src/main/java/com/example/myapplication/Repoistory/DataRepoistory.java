package com.example.myapplication.Repoistory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.Model.DataModel;
import com.example.myapplication.Retrofit.APIInterface;
import com.example.myapplication.Retrofit.RetrofitClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepoistory {
 private APIInterface apiInterface;
 private MutableLiveData<List<DataModel>> mutableLiveData;
 private  int LastPageIndex=0;

 public DataRepoistory(){
    apiInterface= RetrofitClient.getRetrofitClient().create(APIInterface.class);
    }

 public LiveData<List<DataModel>>getDataLiveData(){
 if (mutableLiveData==null){
   mutableLiveData = new MutableLiveData<>();
}
 return mutableLiveData;
 }
 public void getDataFromApi(int pageno){
     if(pageno<= LastPageIndex){
         apiInterface.getDatafromApi("story",pageno).enqueue(new Callback<JsonElement>() {
             @Override
             public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                 if(response.isSuccessful()){
                     List<DataModel> dataModels = new ArrayList<>();
                     if (response.body() != null) {
                         LastPageIndex = response.body().getAsJsonObject().get("nbPages").getAsInt();
                         JsonArray jsonArray = response.body().getAsJsonObject().get("hits").getAsJsonArray();
                         for (int i = 0;i<jsonArray.size();i++){
                             String Data_title =""; String Created_date = "";
                             if(!jsonArray.get(i).getAsJsonObject().get("title").isJsonNull()){
                                 Data_title = jsonArray.get(i).getAsJsonObject().get("title").getAsString();
                             }else{
                                 Data_title = null;
                             }
                             if(!jsonArray.get(i).getAsJsonObject().get("created_at").isJsonNull()){
                                 Created_date = jsonArray.get(i).getAsJsonObject().get("created_at").getAsString();
                             }else{
                                 Created_date = null;
                             }
                             final DataModel dataModel = new DataModel();
                             dataModel.setTitle(Data_title);
                             dataModel.setCreated_at(Created_date);
                             dataModel.setIschecked(false);
                             dataModels.add(dataModel);
                         }
                         mutableLiveData.postValue(dataModels);
                     }

                 }
             }

             @Override
             public void onFailure(Call<JsonElement> call, Throwable t) {

             }
         });
     }
 }
}

