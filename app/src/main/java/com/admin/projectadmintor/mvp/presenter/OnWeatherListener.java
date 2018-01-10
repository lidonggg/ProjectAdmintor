package com.admin.projectadmintor.mvp.presenter;


import com.admin.projectadmintor.mvp.model.entity.Weather;

/**
 * Created by apple on 2018/1/1.
 */

public interface OnWeatherListener {
    void onSuccess(Weather weather);
    void onFailed();
}
