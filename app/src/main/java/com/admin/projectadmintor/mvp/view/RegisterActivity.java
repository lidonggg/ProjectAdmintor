package com.admin.projectadmintor.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.admin.projectadmintor.R;
import com.admin.projectadmintor.mvp.presenter.Imp.StudentPresenterImpl;
import com.admin.projectadmintor.mvp.presenter.StudentPresenter;

import java.lang.ref.WeakReference;

/**
 * Created by apple on 2018/1/5.
 */

public class RegisterActivity extends AppCompatActivity {

    private UIHandler mUIHandler=new UIHandler(this);
    private StudentPresenter mStudentPresenter;
    private Button RegisterBtn;
    private Button clearBtn;
    private EditText studentIdText;
    private EditText usernameText;
    private EditText passwordText;

    private TextView tvLogin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mStudentPresenter=new StudentPresenterImpl(mUIHandler,this);
        RegisterBtn=(Button)findViewById(R.id.btn_register);
        clearBtn=(Button)findViewById(R.id.btn_clear);
        studentIdText=(EditText)findViewById(R.id.student_id);
        usernameText=(EditText)findViewById(R.id.user_name);
        passwordText=(EditText)findViewById(R.id.user_pwd);
        tvLogin=(TextView) findViewById(R.id.mTvLogin);

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String studentId=studentIdText.getText().toString();
                        String username=usernameText.getText().toString();
                        String password=passwordText.getText().toString();
                        mStudentPresenter.sendStudent(studentId,username,password,"student");
                    }
                }).start();

            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentIdText.setText("");
                usernameText.setText("");
                passwordText.setText("");
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);

                startActivity(intent);
            }
        });
    }

    private static class UIHandler extends Handler {
        private WeakReference<RegisterActivity> reference = null;

        public UIHandler(RegisterActivity registerActivity) {
            reference = new WeakReference<RegisterActivity>(registerActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            int what=msg.what;
            RegisterActivity registerActivity=reference.get();
            switch (what){
                case 1:
                    registerActivity.showSendSuccess();
                    break;
                case 0:
                    registerActivity.showSendError();
                    break;
                case 11:

            }
            super.handleMessage(msg);
        }
    }

    public void showSendSuccess(){
        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
    }

    public void showSendError(){
        Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
    }

}
