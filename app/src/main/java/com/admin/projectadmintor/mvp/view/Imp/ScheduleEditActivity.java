package com.admin.projectadmintor.mvp.view.Imp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.admin.projectadmintor.R;
import com.admin.projectadmintor.mvp.presenter.Imp.SchedulePresenterImpl;
import com.admin.projectadmintor.mvp.presenter.SchedulePresenter;

import java.lang.ref.WeakReference;
import java.util.Calendar;

/**
 * Created by apple on 2018/1/2.
 */

public class ScheduleEditActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView idText;
    private EditText detailText;
    private EditText deadlineText;
    private Button sendButton;
    private UIHandler mUIHandler=new UIHandler(this);
    private SchedulePresenter mSchedulePresenter;
    private ImageButton backButton;
    String stuid,proid;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_edit);

        mSchedulePresenter=new SchedulePresenterImpl(mUIHandler,this);
        Intent intent=getIntent();
        stuid=intent.getStringExtra("studentid");
        proid=intent.getStringExtra("projectid");
        idText=(TextView) findViewById(R.id.send_schedule_id);
        detailText=(EditText) findViewById(R.id.send_schedule_content);
        deadlineText=(EditText)findViewById(R.id.send_schedule_deadline);
        sendButton=(Button)findViewById(R.id.send_schedule) ;
        backButton=(ImageButton)findViewById(R.id.back_schedule_btn);

        idText.setText(System.currentTimeMillis()+"");
        sendButton.setOnClickListener(this);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ScheduleEditActivity.this,ScheduleActivity.class);
                intent.putExtra("studentid",stuid);
                intent.putExtra("projectid",proid);
                startActivity(intent);
            }
        });

        deadlineText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    showDatePigkDlg();
                    return true;
                }
                return false;
            }
        });

        deadlineText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    showDatePigkDlg();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        String scheduleDetail=detailText.getText().toString();
        String deadline=deadlineText.getText().toString();
        String scheduleId=idText.getText().toString();

        mSchedulePresenter.sendSchedule(scheduleId,proid,stuid,scheduleDetail,deadline,"ncompleted");

        idText.setText(System.currentTimeMillis()+"");
        detailText.setText("");
        deadlineText.setText("");
    }

    private static class UIHandler extends Handler {
        private WeakReference<ScheduleEditActivity> reference = null;

        public UIHandler(ScheduleEditActivity scheduleEditActivity) {
            reference = new WeakReference<ScheduleEditActivity>(scheduleEditActivity);

        }

        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            ScheduleEditActivity scheduleEditActivity = reference.get();
            switch (what) {

                case 11:
                    scheduleEditActivity.showSuccess();
                    break;

                case 10:
                    scheduleEditActivity.showFailed();
                    break;
            }
            super.handleMessage(msg);
        }
    }

    public void showSuccess(){
        Toast.makeText(ScheduleEditActivity.this,"success",Toast.LENGTH_SHORT).show();
    }

    public void showFailed(){
        Toast.makeText(ScheduleEditActivity.this,"failed",Toast.LENGTH_SHORT).show();
    }

    public void showDatePigkDlg(){
        Calendar calendar=Calendar.getInstance();
        DatePickerDialog datePickerDialog=new DatePickerDialog(ScheduleEditActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                ScheduleEditActivity.this.deadlineText.setText(year+"-"+(month+1)+"-"+day);
            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
