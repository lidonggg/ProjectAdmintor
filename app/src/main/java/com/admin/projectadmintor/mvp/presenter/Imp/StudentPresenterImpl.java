package com.admin.projectadmintor.mvp.presenter.Imp;

import android.content.Context;
import android.os.Handler;
import android.os.Message;


import com.admin.projectadmintor.mvp.bean.Student;
import com.admin.projectadmintor.mvp.model.Imp.StudentModelImpl;
import com.admin.projectadmintor.mvp.model.StudentModel;
import com.admin.projectadmintor.mvp.presenter.OnGetStudentListener;
import com.admin.projectadmintor.mvp.presenter.OnSendStudentListener;
import com.admin.projectadmintor.mvp.presenter.StudentPresenter;

/**
 * Created by apple on 2018/1/5.
 */

public class StudentPresenterImpl implements StudentPresenter,OnSendStudentListener,OnGetStudentListener {
    private Handler mHandler;
    private StudentModel mStudentModel;

    public StudentPresenterImpl(Handler handler, Context context){
        this.mHandler=handler;
        mStudentModel=new StudentModelImpl(context);
    }

    @Override
    public void sendStudent(String studentid, String username, String password, String identity) {
        mStudentModel.sendStudent(studentid,username,password,identity,this);
    }

    @Override
    public void getStudent(String username) {
        mStudentModel.loadStudent(username,this);
    }

    @Override
    public void onSendSuccess(String result) {
        Message msg=mHandler.obtainMessage();
        msg.obj=result;
        msg.what=1;
        mHandler.sendMessage(msg);
    }

    @Override
    public void onSendFailed() {
        mHandler.sendEmptyMessage(0);
    }

    @Override
    public void onGetSuccess(Student student) {
        Message msg=mHandler.obtainMessage();
        msg.obj=student;
        msg.what=11;
        mHandler.sendMessage(msg);
    }

    @Override
    public void onGetFailed() {
        mHandler.sendEmptyMessage(10);
    }
}
