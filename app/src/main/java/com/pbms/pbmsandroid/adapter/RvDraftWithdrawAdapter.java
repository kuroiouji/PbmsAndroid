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
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pbms.pbmsandroid.R;
import com.pbms.pbmsandroid.model.DraftWithdrawDao;
import com.pbms.pbmsandroid.model.ProjectDao;
import com.pbms.pbmsandroid.model.StatusDao;
import com.pbms.pbmsandroid.model.TransDao;
import com.pbms.pbmsandroid.service.HttpManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RvDraftWithdrawAdapter extends RecyclerView.Adapter<RvDraftWithdrawAdapter.DraftWithdrawViewHolder> {
    private List<DraftWithdrawDao> draftWithdrawDaos;
    private Context mContext;
    private String bgyId;

    public RvDraftWithdrawAdapter(List<DraftWithdrawDao> draftWithdrawDaos, Context mContext, String bgyId) {
        this.draftWithdrawDaos = draftWithdrawDaos;
        this.mContext = mContext;
        this.bgyId = bgyId;
    }

    @NonNull
    @Override
    public RvDraftWithdrawAdapter.DraftWithdrawViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.row_draft, parent, false);
        DraftWithdrawViewHolder draftWithdrawViewHolder = new DraftWithdrawViewHolder(view);
        return draftWithdrawViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull DraftWithdrawViewHolder holder, int position) {
        holder.wd_code.setText(draftWithdrawDaos.get(position).getWdCode());
        holder.wd_topic.setText(draftWithdrawDaos.get(position).getWdTopic());
        holder.wd_person.setText(draftWithdrawDaos.get(position).getWdPsName());
        holder.wd_cutbal.setText(draftWithdrawDaos.get(position).getWdCutbalDate());

        if (draftWithdrawDaos.get(position).getWdActive().equals("Y")) {
            holder.gradientDrawable.setColor(Color.parseColor("#7eb73d"));
            holder.wd_active.setText("เปิดใช้งาน");
        } else if (draftWithdrawDaos.get(position).getWdActive().equals("N")) {
            holder.gradientDrawable.setColor(Color.parseColor("#686868"));
            holder.wd_active.setText("ปิดใช้งาน");
        } else if (draftWithdrawDaos.get(position).getWdActive().equals("D")) {
            holder.gradientDrawable.setColor(Color.parseColor("#03a9f4"));
            holder.wd_active.setText("แบบร่าง");
        }

    }

    @Override
    public int getItemCount() {
        return draftWithdrawDaos.size();
    }

    public class DraftWithdrawViewHolder extends RecyclerView.ViewHolder {
        TextView wd_code;
        TextView wd_topic;
        TextView wd_person;
        TextView wd_cutbal;
        TextView wd_active;
        CardView cardView;
        GradientDrawable gradientDrawable;

        public DraftWithdrawViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.draftcard);
            wd_code = (TextView) itemView.findViewById(R.id.wd_code);
            wd_topic = (TextView) itemView.findViewById(R.id.wd_topic);
            wd_person = (TextView) itemView.findViewById(R.id.wd_person);
            wd_cutbal = (TextView) itemView.findViewById(R.id.wd_cutbal);
            wd_active = (TextView) itemView.findViewById(R.id.wd_active);
            gradientDrawable = (GradientDrawable) wd_code.getBackground().mutate();
        }
    }
}
