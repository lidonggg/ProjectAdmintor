package com.admin.projectadmintor.sign;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.admin.projectadmintor.R;

/**
 * Created by apple on 2018/1/4.
 */

public class CalanderAdapter extends BaseAdapter {
    private Context mContext;
    private final int days;
    private final int week;
    private int[] dayNumber;
    private boolean[] signState;
    private final int day;
    private ViewHolder mViewHolder;


    public CalanderAdapter(Context context,int days,int week,int day,boolean[] signState){
        mContext=context;
        this.days=days;
        this.week=week;
        this.day=day;
        this.signState=signState;
        getEveryday();
    }

    @Override
    public int getCount() {
        return 42;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return dayNumber[i];
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_adapter,null);
            mViewHolder=new ViewHolder(view);
            view.setTag(mViewHolder);
        }else {
            mViewHolder=(ViewHolder)view.getTag();
        }
        mViewHolder.day.setText(dayNumber[i]==0?"":dayNumber[i]+"");

        if(signState[i]){
            mViewHolder.day.setBackgroundResource(R.mipmap.ok);
        }

        if(dayNumber[i]==day){
            view.setBackgroundResource(R.color.colorPrimary);
            mViewHolder.day.setTextColor(Color.parseColor("#ffffff"));
        }

        return view;
    }

    private void getEveryday(){
        dayNumber=new int[42];

        for(int i=0;i<42;i++){
            if(i<days+week&&i>=week){
                dayNumber[i]=i-week+1;
            }else {
                dayNumber[i]=0;
            }
        }
    }

    public int[] getDayNumber(){
        return dayNumber;
    }

    private class ViewHolder{
        private TextView day;
        public ViewHolder(View view){
            this.day=(TextView)view.findViewById(R.id.day);
        }
    }
}
