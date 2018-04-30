package com.pbms.pbmsandroid.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pbms.pbmsandroid.R;
import com.pbms.pbmsandroid.model.PersonDao;
import com.pbms.pbmsandroid.model.StatusDao;

import java.util.List;

/**
 * Created by User on 1/5/2561.
 */

public class SpPersonAdapter extends BaseAdapter {

    private List<PersonDao> mData;
    private LayoutInflater mInflater;

    public SpPersonAdapter(Context context, List<PersonDao> mData) {
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
        SpPersonAdapter.ViewHolder holder;

        if (convertView == null) {
            holder = new SpPersonAdapter.ViewHolder();
            convertView = mInflater.inflate(R.layout.spinner_list, parent, false);
            holder.name = (TextView) convertView.findViewById(R.id.name);
        } else {
            holder = (SpPersonAdapter.ViewHolder) convertView.getTag();
        }

        holder.name.setText(mData.get(position).getPsName());
        convertView.setTag(holder);

        return convertView;
    }

    public class ViewHolder {
        TextView name;
    }
}
