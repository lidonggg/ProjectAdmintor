package com.admin.projectadmintor.mvp.presenter;

/**
 * Created by apple on 2018/1/5.
 */

public interface StudentPresenter {
    void sendStudent(String studentid, String username, String password, String identity);
    void getStudent(String username);
}
