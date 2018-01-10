package com.admin.projectadmintor.mvp.model;


import com.admin.projectadmintor.mvp.presenter.OnGetStudentListener;
import com.admin.projectadmintor.mvp.presenter.OnSendStudentListener;

/**
 * Created by apple on 2018/1/5.
 */

public interface StudentModel {
    void sendStudent(String studentid, String username, String password, String identity, OnSendStudentListener listener);
    void loadStudent(String username, OnGetStudentListener listener);
}
