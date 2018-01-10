package com.admin.projectadmintor.mvp.presenter;

/**
 * Created by apple on 2018/1/1.
 */

public interface WeatherPresenter {
    void getWeather(String citycode);
    void getCitycode(String cityname);
}
