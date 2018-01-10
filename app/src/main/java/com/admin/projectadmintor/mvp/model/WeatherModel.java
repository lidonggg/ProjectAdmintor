package com.admin.projectadmintor.mvp.model;


import com.admin.projectadmintor.mvp.presenter.OnCityCodeListener;
import com.admin.projectadmintor.mvp.presenter.OnWeatherListener;

/**
 * Created by apple on 2018/1/1.
 */

public interface WeatherModel {
    void loadWeather(String citycode, OnWeatherListener listener);
    void loadCitycode(String cityName, OnCityCodeListener listener);
}
