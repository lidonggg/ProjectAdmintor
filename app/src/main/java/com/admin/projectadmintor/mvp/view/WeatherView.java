package com.admin.projectadmintor.mvp.view;


import com.admin.projectadmintor.mvp.model.entity.Weather;

/**
 * Created by apple on 2018/1/1.
 */

public interface WeatherView {
    void showLoading();
    void dismissLoading();
    void showError();
    void setWeatherInfo(Weather weather);
}
