package com.pbms.pbmsandroid.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.pbms.pbmsandroid.model.ProjectDao;

import java.util.List;

/**
 * Created by computer on 23/4/2561.
 */

public class RvStatusAdapter extends RecyclerView.Adapter<RvStatusAdapter.ProjectViewHolder> {
    private List<ProjectDao> projectDaos;
    @NonNull
    @Override
    public RvStatusAdapter.ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RvStatusAdapter.ProjectViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ProjectViewHolder extends RecyclerView.ViewHolder {
        public ProjectViewHolder(View itemView) {
            super(itemView);
        }
    }
}
