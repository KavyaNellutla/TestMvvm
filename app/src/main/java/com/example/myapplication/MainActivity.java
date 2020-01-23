package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.Model.DataModel;
import com.example.myapplication.viewModel.DataViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;
    private PostsAdapter postsAdapter;
    private List<DataModel> dataModelList;
    private int lastPageIterated = 0;
    private Toolbar toolbar;
    private TextView tv_count;
    private DataViewModel dataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviews();
        dataModelList = new ArrayList<>();
        dataViewModel= ViewModelProviders.of(this).get(DataViewModel.class);
        dataViewModel.getDatawithPageNo(lastPageIterated);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        initAdapter();
        initRecyclerview();
        addRecyclerOnScroll();
        observeDataListLiveData();
        setSupportActionBar(toolbar);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                dataModelList.clear();
                dataViewModel.getDatawithPageNo(lastPageIterated);
                tv_count.setText("0");

            }
        });
    }

    private void addRecyclerOnScroll() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!recyclerView.canScrollVertically(1)){
                    progressBar.setVisibility(View.VISIBLE);
                    dataViewModel.getDatawithPageNo(lastPageIterated++);
                }

            }
        });
    }

    private void initRecyclerview() {
        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(postsAdapter);
    }

    private void observeDataListLiveData() {
        dataViewModel.getDataLiveData().observe(this,new Observer<List<DataModel>>(){
            @Override
            public void onChanged(List<DataModel> dataModels) {
                swipeRefreshLayout.setRefreshing(false);
                if(dataModels.get(0).isLastPage()){
                    progressBar.setVisibility(View.GONE);
                }else {
                    progressBar.setVisibility(View.GONE);
                    dataModelList.addAll(dataModels);
                    postsAdapter.notifyDataSetChanged();
                }

            }
        });
    }

    private void initAdapter() {
        postsAdapter = new PostsAdapter(dataModelList, new DataItemClickListener() {
            @Override
            public void onItemClick(int position, int switchCheckedCount) {
                tv_count.setText(String.valueOf(switchCheckedCount));

            }
        });
        postsAdapter.setHasStableIds(true);

    }

    private void initviews() {
        recyclerView = findViewById(R.id.rv_dataitems);
        swipeRefreshLayout= findViewById(R.id.swipe_layout);
        progressBar = findViewById(R.id.progress_data);
        toolbar= findViewById(R.id.toolbar);
        tv_count=findViewById(R.id.tv_count);
    }
}
