package com.admin.projectadmintor.mvp.presenter;

/**
 * Created by apple on 2018/1/1.
 */

public interface SchedulePresenter {
    void getSchedules(String studentid, String projectid);
    void sendSchedule(String scheduleid, String projectid, String memberid, String description, String deadline, String completestate);
    void updateSchedule(String scheduleid);
}
