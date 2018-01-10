package com.admin.projectadmintor.mvp.model;


import com.admin.projectadmintor.mvp.presenter.OnGetSignInfoListener;
import com.admin.projectadmintor.mvp.presenter.OnSendSignInfoListener;

/**
 * Created by apple on 2018/1/5.
 */

public interface SignModel {
    void sendSignInfo(String signid, String projectid, String studentid, String signdate, OnSendSignInfoListener listener);
    void loadSignInfo(String studentid, String projectid, OnGetSignInfoListener listener);
}
