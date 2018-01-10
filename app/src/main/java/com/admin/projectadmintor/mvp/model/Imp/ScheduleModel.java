package com.admin.projectadmintor.mvp.model.Imp;


import com.admin.projectadmintor.mvp.presenter.OnGetScheduleListener;
import com.admin.projectadmintor.mvp.presenter.OnSendScheduleListener;

/**
 * Created by apple on 2018/1/1.
 */

public interface ScheduleModel {
    void loadSchedules(String studentid, String projectid, OnGetScheduleListener listener);
    void sendSchedule(String scheduleid, String projectid, String memberid, String description, String deadline, String completestate, OnSendScheduleListener listener);
    void updateSchedule(String scheduleid);
}
