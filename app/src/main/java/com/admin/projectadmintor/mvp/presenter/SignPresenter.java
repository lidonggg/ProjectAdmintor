package com.admin.projectadmintor.mvp.presenter;

/**
 * Created by apple on 2018/1/5.
 */

public interface SignPresenter {
    void sendSignInfo(String signid, String projectid, String studentid, String signdate);
    void getSignInfo(String studentid, String projectid);
}
