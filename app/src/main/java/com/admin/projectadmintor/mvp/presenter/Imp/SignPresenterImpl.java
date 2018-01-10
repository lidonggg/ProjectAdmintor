package com.admin.projectadmintor.mvp.presenter.Imp;

import android.content.Context;
import android.os.Handler;
import android.os.Message;


import com.admin.projectadmintor.mvp.bean.SignInfo;
import com.admin.projectadmintor.mvp.model.Imp.SignModelImpl;
import com.admin.projectadmintor.mvp.model.SignModel;
import com.admin.projectadmintor.mvp.presenter.OnGetSignInfoListener;
import com.admin.projectadmintor.mvp.presenter.OnSendSignInfoListener;
import com.admin.projectadmintor.mvp.presenter.SignPresenter;

import java.util.List;

/**
 * Created by apple on 2018/1/5.
 */

public class SignPresenterImpl implements SignPresenter,OnSendSignInfoListener,OnGetSignInfoListener {
    private Handler mHandler;
    private SignModel mSignModel;

    public SignPresenterImpl(Handler handler, Context context){
        this.mHandler=handler;
        mSignModel=new SignModelImpl(context);
    }
    @Override
    public void sendSignInfo(String signid, String projectid, String studentid, String signdate) {
        mSignModel.sendSignInfo(signid,projectid,studentid,signdate,this);
    }

    @Override
    public void getSignInfo(String studentid, String projectid) {
        mSignModel.loadSignInfo(studentid,projectid,this);
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
    public void onGetSuccess(List<SignInfo> signdates) {
        Message msg=mHandler.obtainMessage();
        msg.obj=signdates;
        msg.what=11;
        mHandler.sendMessage(msg);
    }

    @Override
    public void onGetFailed() {
        mHandler.sendEmptyMessage(0);
    }
}
