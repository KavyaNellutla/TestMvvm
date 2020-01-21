package com.example.myapplication.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.myapplication.Repoistory.DataRepoistory;

public class DataViewModel extends AndroidViewModel {
    private DataRepoistory dataRepoistory;
    public DataViewModel(@NonNull Application application) {
        super(application);
        dataRepoistory = new DataRepoistory();
    }
}
