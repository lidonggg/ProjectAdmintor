package com.admin.projectadmintor.mvp.presenter.Imp;

/**
 * Created by apple on 2018/1/1.
 */

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.admin.projectadmintor.mvp.model.Imp.WeatherModelImpl;
import com.admin.projectadmintor.mvp.model.WeatherModel;
import com.admin.projectadmintor.mvp.model.entity.Weather;
import com.admin.projectadmintor.mvp.presenter.OnCityCodeListener;
import com.admin.projectadmintor.mvp.presenter.OnWeatherListener;
import com.admin.projectadmintor.mvp.presenter.WeatherPresenter;

public class WeatherPresenterImpl implements WeatherPresenter,OnWeatherListener,OnCityCodeListener {
    private Handler handler;

    private WeatherModel model;//model的引用


    public WeatherPresenterImpl(Handler handler, Context context) {
        this.handler = handler;
        model = new WeatherModelImpl(context);
    }

    @Override
    public void getWeather(String citycode) {
        model.loadWeather(citycode, this );
    }

    @Override
    public void getCitycode(String cityname) {
        model.loadCitycode(cityname,this);
    }

    @Override
    public void onSuccess(Weather weather) {
        Message msg = handler.obtainMessage();
        msg.obj = weather;
        msg.what = 0x123;
        handler.sendMessage(msg);
    }

    @Override
    public void onFailed() {
        handler.sendEmptyMessage(0x000);
    }

    @Override
    public void onCityCodeSuccess(String citycode) {
        Message msg=handler.obtainMessage();
        msg.obj=citycode;
        msg.what=0x456;
        handler.sendMessage(msg);
    }

    @Override
    public void onCityCodeFailed() {
        handler.sendEmptyMessage(0x000);
    }
}
