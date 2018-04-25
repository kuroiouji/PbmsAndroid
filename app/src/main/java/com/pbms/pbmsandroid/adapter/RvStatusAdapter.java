package com.pbms.pbmsandroid.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.pbms.pbmsandroid.R;
import com.pbms.pbmsandroid.model.ProjectDao;
import com.pbms.pbmsandroid.model.StatusDao;

import java.util.List;

/**
 * Created by computer on 23/4/2561.
 */

public class RvStatusAdapter extends RecyclerView.Adapter<RvStatusAdapter.ProjectDaoViewHolder> {
    private List<ProjectDao> projectDaos;
    private List<StatusDao> statusDaos;
    private Context mContext;
    String pjId;
    String type;
    String stId;
    int pjPosition;

    public RvStatusAdapter(List<ProjectDao> projectDaos, List<StatusDao> statusDaos, Context mContext) {
        this.projectDaos = projectDaos;
        this.statusDaos = statusDaos;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RvStatusAdapter.ProjectDaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false);
        ProjectDaoViewHolder projectDaoViewHolder = new ProjectDaoViewHolder(view);
        return projectDaoViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RvStatusAdapter.ProjectDaoViewHolder holder, int position) {
        pjPosition = position;
        holder.pj_name.setText(projectDaos.get(position).getPjName());
        holder.pj_code.setText(projectDaos.get(position).getPjCode());
        holder.pj_budget.setText(projectDaos.get(position).getPjSpend() + " บาท");
        if (projectDaos.get(position).getType().equals("project")) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#ffffff"));
        } else if (projectDaos.get(position).getType().equals("activity")) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#d9d9d9"));
        }
        pjId = projectDaos.get(position).getPjId();
        type = projectDaos.get(position).getType();
        stId = projectDaos.get(position).getPjStId();
        if (statusDaos.size() > 0) {
            SpStatusAdapter adapter = new SpStatusAdapter(mContext, statusDaos, pjId, type);
            holder.spinner.setAdapter(adapter);
            int ps = 0;
            for (StatusDao st : statusDaos) {
                if (st.getStId().equals(projectDaos.get(position).getPjStId())) {
                    holder.spinner.setSelection(ps);
                    holder.gradientDrawable.setColor(Color.parseColor(st.getStColor()));
                    break;
                }
                ps++;
            }
            holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(!statusDaos.get(position).getStId().equals(stId)) {
                        Log.d("tg", "onItemSelected: " + position + " " + pjId + " " + type);
                        projectDaos.get(pjPosition).setPjStId(statusDaos.get(position).getStId());
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return projectDaos.size();
    }

    public class ProjectDaoViewHolder extends RecyclerView.ViewHolder {
        TextView pj_name;
        TextView pj_code;
        TextView pj_budget;
        Spinner spinner;
        CardView cardView;
        GradientDrawable gradientDrawable;

        public ProjectDaoViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card);
            pj_name = (TextView) itemView.findViewById(R.id.pj_name);
            pj_code = (TextView) itemView.findViewById(R.id.pj_code);
            pj_budget = (TextView) itemView.findViewById(R.id.pj_budget);
            spinner = (Spinner) itemView.findViewById(R.id.pj_status);
            gradientDrawable = (GradientDrawable)pj_code.getBackground().mutate();
        }
    }
}
