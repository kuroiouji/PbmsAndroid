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
import android.widget.Toast;

import com.pbms.pbmsandroid.MainActivity;
import com.pbms.pbmsandroid.R;
import com.pbms.pbmsandroid.model.ProjectDao;
import com.pbms.pbmsandroid.model.StatusDao;
import com.pbms.pbmsandroid.model.TransDao;
import com.pbms.pbmsandroid.service.HttpManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by computer on 23/4/2561.
 */

public class RvStatusAdapter extends RecyclerView.Adapter<RvStatusAdapter.ProjectDaoViewHolder> {
    private List<ProjectDao> projectDaos;
    private List<StatusDao> statusDaos;
    private Context mContext;
    private String bgyId;
//    String pjId,type,stId;

    public RvStatusAdapter(List<ProjectDao> projectDaos, List<StatusDao> statusDaos, Context mContext, String bgyId) {
        this.projectDaos = projectDaos;
        this.statusDaos = statusDaos;
        this.mContext = mContext;
        this.bgyId = bgyId;
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
    public void onBindViewHolder(@NonNull final RvStatusAdapter.ProjectDaoViewHolder holder, int position) {
        holder.pj_name.setText(projectDaos.get(position).getPjName());
        holder.pj_code.setText(projectDaos.get(position).getPjCode());
        holder.pj_budget.setText("ใช้ไป: " + projectDaos.get(position).getPjSpend() + " บาท");

        if (projectDaos.get(position).getType().equals("project")) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#ffffff"));
        } else if (projectDaos.get(position).getType().equals("activity")) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#d9d9d9"));
        }

        final int i = position;
        String pjId = projectDaos.get(position).getPjId();
        String type = projectDaos.get(position).getType();
//        final String stId = projectDaos.get(i).getPjStId();

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
                    String st = statusDaos.get(position).getStId();
                    String stPj = projectDaos.get(i).getPjStId();
                    String pjId = projectDaos.get(i).getPjId();
                    String type = projectDaos.get(i).getType();
                    if (!st.equals(stPj)) {
                        holder.gradientDrawable.setColor(Color.parseColor(statusDaos.get(position).getStColor()));
                        projectDaos.get(i).setPjStId(statusDaos.get(position).getStId());
                        Log.d("tg", "onItemSelected: " + stPj + "--" + st + " " + pjId + " " + i);
                        Log.d("tg", "onItemSelected: " + projectDaos.get(i).getPjStId());
                        Call<TransDao> call = HttpManager.getInstance().getService().updateStatus(pjId, st, bgyId, type);
                        call.enqueue(new Callback<TransDao>() {
                            @Override
                            public void onResponse(Call<TransDao> call, Response<TransDao> response) {
                                if (response.isSuccessful()) {
                                    TransDao res = response.body();
                                    Log.d("insert", "onResponse: " + res.getStatus());
                                    Toast.makeText(mContext,res.getStr(),Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.d("insert", "onResponse: " + response.message());
                                }
                            }

                            @Override
                            public void onFailure(Call<TransDao> call, Throwable t) {
                                Log.d("insert", "onFailure: " + t);
                            }
                        });
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
            gradientDrawable = (GradientDrawable) pj_code.getBackground().mutate();
        }
    }
}
