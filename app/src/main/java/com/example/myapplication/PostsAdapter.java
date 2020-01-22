package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.DataModel;
import com.example.myapplication.Utils.AppUtils;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private List<DataModel> dataModelList;
    private DataItemClickListener dataItemClickListener;
    private int selecteddatacount =0;
    public PostsAdapter(List<DataModel> dataModelList, DataItemClickListener dataItemClickListener) {
        this.dataModelList=dataModelList;
        this.dataItemClickListener = dataItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.post_title.setText(dataModelList.get(position).getTitle());
        holder.created_at.setText(AppUtils.getFormattedDate(dataModelList.get(position).getCreated_at()));
        holder.aSwitch.setChecked(dataModelList.get(position).isIschecked());
        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    selecteddatacount++;
                    dataModelList.get(position).setIschecked(true);
                }else{
                    selecteddatacount--;
                    dataModelList.get(position).setIschecked(false);
                }
                dataItemClickListener.onItemClick(position,selecteddatacount);

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.aSwitch.performClick();
            }
        });

    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView post_title;
        private TextView created_at;
        private Switch aSwitch;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            post_title = itemView.findViewById(R.id.post_title);
            created_at=itemView.findViewById(R.id.created_at);
            aSwitch=itemView.findViewById(R.id.switch_selected);
        }
    }
}
