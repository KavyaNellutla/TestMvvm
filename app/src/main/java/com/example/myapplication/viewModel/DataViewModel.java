package com.example.myapplication.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication.Model.DataModel;
import com.example.myapplication.Repoistory.DataRepoistory;

import java.util.List;

public class DataViewModel extends AndroidViewModel {
    private DataRepoistory dataRepoistory;
    public DataViewModel(@NonNull Application application) {
        super(application);
        dataRepoistory = new DataRepoistory();
    }

    public LiveData<List<DataModel>>getDataLiveData(){
        return dataRepoistory.getDataLiveData();
    }

    public  void  getDatawithPageNo(int PageNo){
        dataRepoistory.getDataFromApi(PageNo);
    }
}
