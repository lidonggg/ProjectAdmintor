package com.admin.projectadmintor.mvp.model.Imp;

import android.content.Context;

import com.admin.projectadmintor.mvp.model.WeatherModel;
import com.admin.projectadmintor.mvp.model.entity.Weather;
import com.admin.projectadmintor.mvp.presenter.OnCityCodeListener;
import com.admin.projectadmintor.mvp.presenter.OnWeatherListener;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URLEncoder;

/**
 * Created by apple on 2018/1/1.
 */

public class WeatherModelImpl implements WeatherModel {
    //根据城市名称查询的API地址
    private static final String URL = "http://service.envicloud.cn:8082";
    //环境云分配的请求ID
    private static final String ACCESSID = "Y3JHENLWAHIXNTE0NZM1ODM2NZG2";

    private RequestQueue requestQueue;
    private Gson gson = new Gson();


    public WeatherModelImpl(Context context) {

        requestQueue = Volley.newRequestQueue(context);
    }



    @Override
    public void loadWeather(String cityCode, OnWeatherListener litener) {
        doGet(cityCode, litener);
    }

    @Override
    public void loadCitycode(String cityName, final OnCityCodeListener listener) {
        String url = URL + "/v2/citycode/" +ACCESSID + "/" + URLEncoder.encode(cityName);

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
                if(jsonObject.get("rcode").getAsInt()==200) {
                    String cityCode = jsonObject.get("citycode").getAsString();

                    listener.onCityCodeSuccess(cityCode);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void doGet(String cityCode, final OnWeatherListener litener) {

        String url = URL + "/v2/weatherforecast/" +ACCESSID + "/" +cityCode;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                System.out.println(s);
                //将json字符串转换为weather对象
                Weather weather = gson.fromJson(s, Weather.class);
                //如果weather的resultCode==0证明查询数据成功
                if (weather.getRcode()==200) {
                    //调用listener的onSuccess（）方法，通知数据查询成功，更新数据。
                    litener.onSuccess(weather);
                } else {
                    //数据查询失败
                    litener.onFailed();
                }


            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //数据查询失败
                System.out.println("ERROR" + volleyError.getMessage());
                litener.onFailed();
            }
        });

        //执行post请求
        requestQueue.add(stringRequest);
    }
}
