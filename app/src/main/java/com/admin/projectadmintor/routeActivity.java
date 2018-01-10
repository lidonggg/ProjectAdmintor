package com.admin.projectadmintor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.admin.projectadmintor.homePage.MainActivity;
import com.admin.projectadmintor.mvp.view.Imp.ScheduleEditActivity;
import com.admin.projectadmintor.mvp.view.Imp.WeatherActivity;
import com.admin.projectadmintor.mvp.view.LoginActivity;
import com.admin.projectadmintor.mvp.view.RegisterActivity;
import com.admin.projectadmintor.recorder.RecorderActivity;


public class routeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_route);
    }
    public void send(View view){
        Intent intent=new Intent(routeActivity.this,LoginActivity.class);

        startActivity(intent);
    }
}
