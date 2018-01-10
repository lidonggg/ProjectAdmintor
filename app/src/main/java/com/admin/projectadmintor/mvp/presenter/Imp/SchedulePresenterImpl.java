package com.admin.projectadmintor.mvp.presenter.Imp;

import android.content.Context;
import android.os.Message;


import java.util.List;
import android.os.Handler;

import com.admin.projectadmintor.mvp.bean.Schedule;
import com.admin.projectadmintor.mvp.model.Imp.ScheduleModel;
import com.admin.projectadmintor.mvp.model.Imp.ScheduleModelImpl;
import com.admin.projectadmintor.mvp.presenter.OnGetScheduleListener;
import com.admin.projectadmintor.mvp.presenter.OnSendScheduleListener;
import com.admin.projectadmintor.mvp.presenter.SchedulePresenter;

/**
 * Created by apple on 2018/1/1.
 */

public class SchedulePresenterImpl implements SchedulePresenter,OnGetScheduleListener,OnSendScheduleListener {

    private Handler mHandler;
    private ScheduleModel mScheduleModel;

    public SchedulePresenterImpl(Handler handler, Context context){
        this.mHandler=handler;
        mScheduleModel=new ScheduleModelImpl(context);
    }

    @Override
    public void getSchedules(String studentid, String projectid) {
        mScheduleModel.loadSchedules(studentid,projectid,this);

    }

    @Override
    public void sendSchedule(String scheduleid, String projectid, String memberid, String description, String deadline, String completestate) {
        mScheduleModel.sendSchedule(scheduleid,projectid,memberid,description,deadline,completestate,this);
    }

    @Override
    public void updateSchedule(String scheduleid) {
        mScheduleModel.updateSchedule(scheduleid);
    }

    @Override
    public void onSuccess(List<Schedule> schedules) {
        Message msg=mHandler.obtainMessage();
        msg.obj=schedules;
        msg.what=1;
        mHandler.sendMessage(msg);
    }

    @Override
    public void onFailed() {
        mHandler.sendEmptyMessage(0);
    }

    @Override
    public void sendSuccess() {
        Message msg = mHandler.obtainMessage();
        msg.what = 11;
        mHandler.sendMessage(msg);
    }

    @Override
    public void sendFailed() {
        mHandler.sendEmptyMessage(10);
    }
}
