package com.admin.projectadmintor.mvp.presenter;


import com.admin.projectadmintor.mvp.bean.Schedule;

import java.util.List;

/**
 * Created by apple on 2018/1/1.
 */

public interface OnGetScheduleListener {
    void onSuccess(List<Schedule> schedules);
    void onFailed();
}
