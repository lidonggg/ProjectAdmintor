package com.admin.projectadmintor.mvp.view.Imp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;


import com.admin.projectadmintor.R;
import com.admin.projectadmintor.homePage.MainActivity;
import com.admin.projectadmintor.mvp.adapter.ScheduleAdapter;
import com.admin.projectadmintor.mvp.bean.Schedule;
import com.admin.projectadmintor.mvp.presenter.Imp.SchedulePresenterImpl;
import com.admin.projectadmintor.mvp.presenter.SchedulePresenter;
import com.admin.projectadmintor.mvp.view.ScheduleView;
import com.admin.projectadmintor.task.tasklist.TaskListActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity implements ScheduleView {
    public ImageButton bt;
    public static Context sContext;
    private RecyclerView mRecyclerView;
    private ScheduleAdapter mScheduleAdapter;
    private SchedulePresenter mSchedulePresenter;
    private ImageButton mImageButton;
    private UIHandler mUIHandler=new UIHandler(this);
    private List<Schedule> mSchedules=new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    String stuid;
    String proid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Intent intent=getIntent();
        stuid=intent.getStringExtra("studentid");
        proid=intent.getStringExtra("projectid");
        sContext=this;
        bt = (ImageButton)findViewById(R.id.back_schedule_btn);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ScheduleActivity.this, TaskListActivity.class);
                intent.putExtra("studentid",stuid);
                startActivity(intent);
            }
        });

        mRecyclerView= (RecyclerView) findViewById(R.id.schedule_recycle_view);
        mSwipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.schedule_refesh_layout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSchedulePresenter=new SchedulePresenterImpl(mUIHandler,this);
        mSchedulePresenter.getSchedules(stuid,proid);
        mScheduleAdapter=new ScheduleAdapter(mSchedules,ScheduleActivity.this);
        mRecyclerView.setAdapter(mScheduleAdapter);

        mImageButton=(ImageButton)findViewById(R.id.schedule_add);
        mImageButton.setOnClickListener(new addScheduleListener());

        mSwipeRefreshLayout.setColorSchemeColors(Color.RED);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSchedules.clear();
                mSchedulePresenter.getSchedules(stuid,proid);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private class addScheduleListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(ScheduleActivity.this,ScheduleEditActivity.class);
            intent.putExtra("studentid",stuid);
            intent.putExtra("projectid",proid);
            startActivity(intent);
        }
    }

    @Override
    public void setSchedules(List<Schedule> schedules) {
        for(Schedule schedule:schedules){
            mSchedules.add(schedule);
        }

        mScheduleAdapter.notifyDataSetChanged();
    }

    public void updateSchedule(String scheduleid){
        mSchedulePresenter.updateSchedule(scheduleid);
    }

    private static class UIHandler extends Handler {
        private WeakReference<ScheduleActivity> reference = null;

        public UIHandler(ScheduleActivity scheduleActivity) {
            reference = new WeakReference<ScheduleActivity>(scheduleActivity);

        }

        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            ScheduleActivity scheduleActivity = reference.get();
            List<Schedule> schedules;
            switch (what) {

                case 1:
                    schedules = (List<Schedule>) msg.obj;
                    scheduleActivity.setSchedules(schedules);
                    break;

                case 0:
                    break;
            }
            super.handleMessage(msg);
        }
    }
}
