package com.admin.projectadmintor.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.admin.projectadmintor.R;


/**
 * Created by apple on 2018/1/1.
 */

public class ListItemAdapter extends BaseAdapter{
    private Context context;
    private String[] values;
    private String[] titles;

    public ListItemAdapter(String[] values, Context context) {
        this.context = context;
        this.values = values;
        titles = new String[]{"地点", "城市码", "预测日期", "最高温度", "最低温度", "风力", "风向", "白天天气", "夜晚天气"};
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_layout, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.value = (TextView) convertView.findViewById(R.id.value);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(titles[position]);
        holder.value.setText(values[position]);
        return convertView;
    }

    static class ViewHolder {
        TextView title;
        TextView value;
    }
}
