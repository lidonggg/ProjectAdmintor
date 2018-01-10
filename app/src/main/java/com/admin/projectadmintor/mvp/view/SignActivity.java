package com.admin.projectadmintor.mvp.view;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.admin.projectadmintor.R;
import com.admin.projectadmintor.mvp.bean.SignInfo;
import com.admin.projectadmintor.mvp.presenter.Imp.SignPresenterImpl;
import com.admin.projectadmintor.mvp.presenter.SignPresenter;
import com.admin.projectadmintor.mvp.view.Imp.ScheduleActivity;
import com.admin.projectadmintor.sign.CalanderAdapter;
import com.admin.projectadmintor.sign.SpecialCalander;
import com.admin.projectadmintor.task.tasklist.TaskListActivity;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SignActivity extends AppCompatActivity implements GridView.OnItemClickListener{
    private ImageButton back;
    private GridView registration_calander_gv;
    private TextView today;
    private CalanderAdapter mCalanderAdapter;
    private SimpleDateFormat mSimpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    static boolean[] signState=new boolean[42];
    int mYear=0;
    int mMonth=0;
    int mDay=0;

    private UIHandler mUIHandler=new UIHandler(this);
    private SignPresenter mSignPresenter;

    private SensorManager mSensorManager;
    private Vibrator mVibrator;
    private static final int SENSOR_SHAKE=10;
    String stuid;
    String proid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        Intent intent=getIntent();
        stuid=intent.getStringExtra("studentid");
        proid=intent.getStringExtra("projectid");
        registration_calander_gv=(GridView)findViewById(R.id.registration_calendar_gv);
        today=(TextView)findViewById(R.id.today);

        Calendar calander= Calendar.getInstance();
        mYear=calander.get(Calendar.YEAR);
        mMonth=calander.get(Calendar.MONTH);
        mDay=calander.get(Calendar.DAY_OF_MONTH);

        SpecialCalander specialCalander=new SpecialCalander();
        boolean isLeapYear = specialCalander.isLeapYear(mYear);
        int mDays=specialCalander.getDaysOfMonth(isLeapYear,mMonth+1);
        int week=specialCalander.getWeekdayOfMonth(mYear,mMonth);

        for(int index=0;index<42;index++){
            signState[index]=false;
        }

        mCalanderAdapter=new CalanderAdapter(this,mDays,week,mDay,signState);

        mSignPresenter=new SignPresenterImpl(mUIHandler,this);
        mSignPresenter.getSignInfo(stuid,proid);

        registration_calander_gv.setAdapter(mCalanderAdapter);
        registration_calander_gv.setOnItemClickListener(this);
        today.setText(mYear+"年"+(mMonth+1)+"月"+mDay+"日");

        mSensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        mVibrator=(Vibrator)getSystemService(VIBRATOR_SERVICE);
        back=(ImageButton)findViewById(R.id.comeback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignActivity.this, TaskListActivity.class);
                intent.putExtra("studentid",stuid);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mSensorManager!=null){
            mSensorManager.registerListener(sensorEventListener,mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mSensorManager!=null){
            mSensorManager.unregisterListener(sensorEventListener);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String today=mYear+"-"+(mMonth+1)+"-"+l;
        if(l!=0){
            if(l==mDay){
                TextView today_tv=(TextView)view.findViewById(R.id.day);
                today_tv.setBackgroundResource(R.mipmap.ok);
                today_tv.setTextColor(Color.BLACK);
                today_tv.setText(l+"");
                view.setBackgroundColor(Color.parseColor("#ffffff"));

                Date date;
                try{
                    date=mSimpleDateFormat.parse(today);
                }catch (ParseException e){

                }
                mSignPresenter.sendSignInfo(System.currentTimeMillis()+"",proid,stuid,today);
                Toast.makeText(view.getContext(),"签到成功",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(view.getContext(),"您选择的日期:"+today,Toast.LENGTH_SHORT).show();
            }
        }
    }

    private SensorEventListener sensorEventListener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] values=sensorEvent.values;
            float x=values[0];
            float y=values[1];
            float z=values[2];
            int medumValue=39;
            if (Math.abs(x) > medumValue || Math.abs(y) > medumValue || Math.abs(z) > medumValue){
                mVibrator.vibrate(200);
                Message msg=new Message();
                msg.what=SENSOR_SHAKE;
                mUIHandler.sendMessage(msg);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    private static class UIHandler extends Handler {
        private WeakReference<SignActivity> reference = null;

        public UIHandler(SignActivity signActivity) {
            reference = new WeakReference<SignActivity>(signActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            int what=msg.what;
            SignActivity signActivity=reference.get();
            switch (what){
                case 1:
                    //signActivity.showSendSuccess();
                    break;
                case 0:
                    signActivity.showError();
                    break;
                case 11:
                    List<SignInfo> signInfos=(List<SignInfo>) msg.obj;
                    List<String> signdates=new ArrayList<>();
                    for(SignInfo signInfo:signInfos){
                        String signdate=signInfo.getSigndate();
                        signdates.add(signdate);
                    }
                    signActivity.setSignInfo(signdates);
                    break;
                case SENSOR_SHAKE:
                    String today=signActivity.mYear+"-"+(signActivity.mMonth+1)+"-"+signActivity.mDay;
                    signActivity.mSignPresenter.sendSignInfo(System.currentTimeMillis()+"",signActivity.proid,signActivity.stuid,today);
                    signActivity.showSendSuccess();
                    break;
            }
            super.handleMessage(msg);
        }
    }

    public void showSendSuccess(){
        Toast.makeText(SignActivity.this,"签到成功",Toast.LENGTH_SHORT).show();
    }

    public void showError(){

    }

    public void setSignInfo(List<String> signdates){
        for(String signdate:signdates){
            String day=signdate.substring(signdate.lastIndexOf("-")+1,signdate.length());
            for(int k=0;k<42;k++){
                int[] d=mCalanderAdapter.getDayNumber();
                if(Integer.valueOf(day).intValue()==mCalanderAdapter.getDayNumber()[k]){
                    signState[k]=true;
                }
            }
        }
        mCalanderAdapter.notifyDataSetChanged();
    }

}
