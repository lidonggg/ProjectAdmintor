package com.admin.projectadmintor.mvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;


import com.admin.projectadmintor.R;
import com.admin.projectadmintor.mvp.bean.Schedule;
import com.admin.projectadmintor.mvp.view.Imp.ScheduleActivity;

import java.util.List;

/**
 * Created by apple on 2018/1/1.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder>{

    public List<Schedule> mSchedules=null;

    private ScheduleActivity context;

    public ScheduleAdapter(List<Schedule> schedules,ScheduleActivity context){
        this.mSchedules=schedules;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.schedule_item,viewGroup,false);
        ViewHolder vh=new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        viewHolder.scheduleId.setText("id:"+mSchedules.get(position).getScheduleid());
        viewHolder.scheduleDetailView.setText("detail:"+mSchedules.get(position).getDescription());
        viewHolder.scheduleDeadlineView.setText("deadline:"+mSchedules.get(position).getDeadline());
        viewHolder.scheduleStateBtn.setChecked(mSchedules.get(position).getCompletestate().equals("completed"));
        viewHolder.scheduleStateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String scheduleid=viewHolder.scheduleId.getText().toString();
                context.updateSchedule(scheduleid.substring(scheduleid.indexOf(":")+1,scheduleid.length()));
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mSchedules==null)return 0;
        else
            return mSchedules.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView scheduleId;
        public TextView scheduleDetailView;
        public TextView scheduleDeadlineView;
        public RadioButton scheduleStateBtn;

        public ViewHolder(View view){
            super(view);
            scheduleId=(TextView)view.findViewById(R.id.schedule_id) ;
            scheduleDetailView=(TextView) view.findViewById(R.id.schedule_detail);
            scheduleDeadlineView=(TextView) view.findViewById(R.id.schedule_deadline);
            scheduleStateBtn=(RadioButton) view.findViewById(R.id.schedule_state);
        }
    }
}
