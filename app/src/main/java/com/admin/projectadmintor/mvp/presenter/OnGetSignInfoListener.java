package com.admin.projectadmintor.mvp.presenter;


import com.admin.projectadmintor.mvp.bean.SignInfo;

import java.util.List;

/**
 * Created by apple on 2018/1/5.
 */

public interface OnGetSignInfoListener {
    void onGetSuccess(List<SignInfo> signdates);
    void onGetFailed();
}
