package com.pbms.pbmsandroid.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pbms.pbmsandroid.R;
import com.pbms.pbmsandroid.model.BudgetYear;
import com.pbms.pbmsandroid.model.StatusDao;

import java.util.List;

public class SpBgyAdapter extends BaseAdapter {
    private List<BudgetYear> mData;
    private LayoutInflater mInflater;

    public SpBgyAdapter(Context context, List<BudgetYear> mData) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;

    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.spinner_list, parent, false);
            holder.name = (TextView) convertView.findViewById(R.id.name);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(mData.get(position).getBgyTitle());
        convertView.setTag(holder);

        return convertView;
    }

    public class ViewHolder {
        TextView name;
    }
}
