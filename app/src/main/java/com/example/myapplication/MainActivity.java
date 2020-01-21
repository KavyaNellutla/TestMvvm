package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.myapplication.Model.DataModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;
    private PostsAdapter postsAdapter;
    private List<DataModel> dataModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviews();
        dataModelList = new ArrayList<>();
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        initAdapter();
    }

    private void initAdapter() {

    }

    private void initviews() {
        recyclerView = findViewById(R.id.rv_posts);
        swipeRefreshLayout= findViewById(R.id.swipe);
        progressBar = findViewById(R.id.pb_progress);
    }
}
