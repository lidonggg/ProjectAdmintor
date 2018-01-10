package com.admin.projectadmintor.mvp.presenter;


import com.admin.projectadmintor.mvp.bean.Student;

/**
 * Created by apple on 2018/1/5.
 */

public interface OnGetStudentListener {
    void onGetSuccess(Student student);
    void onGetFailed();
}
