package com.admin.projectadmintor.mvp.view.Imp;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.admin.projectadmintor.R;
import com.admin.projectadmintor.homePage.MainActivity;
import com.admin.projectadmintor.mvp.adapter.ListItemAdapter;
import com.admin.projectadmintor.mvp.model.entity.Weather;
import com.admin.projectadmintor.mvp.model.entity.WeatherForecast;
import com.admin.projectadmintor.mvp.presenter.Imp.WeatherPresenterImpl;
import com.admin.projectadmintor.mvp.presenter.WeatherPresenter;
import com.admin.projectadmintor.mvp.view.WeatherView;

import java.lang.ref.WeakReference;


public class WeatherActivity extends AppCompatActivity implements WeatherView, View.OnClickListener{

    private WeatherPresenter weatherPresenter;
    //声明UI组件
    private AutoCompleteTextView atv_city_name;
    private Button btn_submit;
    private ProgressBar progressBar;
    private ListView listView;

    private UIHandler handler = new UIHandler(this);  //在Presenter更新UI的handler
    private ListItemAdapter listAdapter;//ListView的适配器，用于填充天气信息
    private String[] values = new String[9];//临时存储listView中要设置的天气信息
    private ImageButton back;
    private String cityCode;
    String stuid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        //绑定View
        bindView();
        //初始化WeatherPresenter控制器
        weatherPresenter = new WeatherPresenterImpl(handler, this);

    }

    private void bindView() {
        Intent intent=getIntent();
        stuid=intent.getStringExtra("studentid");
        back = (ImageButton)findViewById(R.id.goback) ;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WeatherActivity.this, MainActivity.class);
                intent.putExtra("studentid",stuid);
                startActivity(intent);
            }
        });
        atv_city_name = (AutoCompleteTextView) findViewById(R.id.atv_city_name);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        listView = (ListView) findViewById(R.id.listView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //初始化为不可见
        progressBar.setVisibility(View.INVISIBLE);
        //设置按钮监听
        btn_submit.setOnClickListener(this);
        //设置自动填充文本框内容
        String[] citys = getResources().getStringArray(R.array.city_names);
        ArrayAdapter cityNamesAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, citys);
        atv_city_name.setAdapter(cityNamesAdapter);
        //初始化listView
        listAdapter = new ListItemAdapter(values, this);
        listView.setAdapter(listAdapter);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        btn_submit.setEnabled(false);
    }

    @Override
    public void dismissLoading() {
        progressBar.setVisibility(View.INVISIBLE);
        btn_submit.setEnabled(true);
    }

    @Override
    public void showError() {
        Toast.makeText(this, "天气查询失败", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setWeatherInfo(Weather weather) {

        WeatherForecast forecasts = weather.getForecast()[0];

        values[0] = weather.getCityname();
        values[1] = weather.getCitycode();
        values[2] = forecasts.getDate();
        values[3] = forecasts.getTmp().getMax();
        values[4] = forecasts.getTmp().getMin();
        values[5] = forecasts.getWind().getSc();
        values[6] = forecasts.getWind().getDir();
        values[7] = forecasts.getCond().getCond_d();
        values[8] = forecasts.getCond().getCond_n();

        //通知数据更新
        listAdapter.notifyDataSetChanged();
    }

    public void setCitycode(String citycode){
        this.cityCode=citycode;
    }

    public String getCitycode(){
        return this.cityCode;
    }

    @Override
    public void onClick(View v) {
        if (!TextUtils.isEmpty(atv_city_name.getText())) {
            String cityName = atv_city_name.getText().toString();
            switch (cityName) {
                case "shanghai":
                    weatherPresenter.getCitycode("上海");
                    break;
                case "nanjing":
                    weatherPresenter.getCitycode("南京");
                    weatherPresenter.getWeather(cityCode);
                    break;
                default:
                    Toast.makeText(this,"请输入shanghai或者nanjing",Toast.LENGTH_LONG).show();
                    break;
            }
        } else {
            Toast.makeText(this, "城市名称不能为空", Toast.LENGTH_SHORT).show();
        }

    }

    private static class UIHandler extends Handler {
        private WeakReference<WeatherActivity> reference = null;

        public UIHandler(WeatherActivity weatherActivity) {
            reference = new WeakReference<WeatherActivity>(weatherActivity);

        }

        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            WeatherActivity weatherActivity = reference.get();
            Weather weather;
            switch (what) {
                //0x123:代表数据更新成功，进行刷新数据操作
                case 0x123:
                    weather = (Weather) msg.obj;
                    weatherActivity.setWeatherInfo(weather);
                    break;

                case 0x456:
                    weatherActivity.setCitycode((String)msg.obj);
                    weatherActivity.weatherPresenter.getWeather(weatherActivity.cityCode);
                    break;
            }
            super.handleMessage(msg);
        }
    }
}
