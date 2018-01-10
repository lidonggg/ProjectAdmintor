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
import com.admin.projectadmintor.homePage.MainActivity;
import com.admin.projectadmintor.mvp.bean.Student;
import com.admin.projectadmintor.mvp.presenter.Imp.StudentPresenterImpl;
import com.admin.projectadmintor.mvp.presenter.StudentPresenter;

import java.lang.ref.WeakReference;

/**
 * Created by apple on 2018/1/5.
 */

public class LoginActivity extends AppCompatActivity {

    private UIHandler mUIHandler = new UIHandler(this);
    private StudentPresenter mStudentPresenter;
    private Button loginBtn;
    private Button clearBtn;
    private EditText usernameText;
    private EditText passwordText;
    private Student selectedStudent = new Student();
    private String loginId;

    private TextView tvRegister;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = (Button) findViewById(R.id.btn_login);
        clearBtn = (Button) findViewById(R.id.btn_clear);
        usernameText = (EditText) findViewById(R.id.user_name);
        passwordText = (EditText) findViewById(R.id.user_pwd);
        tvRegister = (TextView) findViewById(R.id.mRegister);

        mStudentPresenter = new StudentPresenterImpl(mUIHandler, this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = usernameText.getText().toString();
                mStudentPresenter.getStudent(username);

            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameText.setText("");
                passwordText.setText("");
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

                startActivity(intent);
            }
        });
    }

    private static class UIHandler extends Handler {
        private WeakReference<LoginActivity> reference = null;

        public UIHandler(LoginActivity loginActivity) {
            reference = new WeakReference<LoginActivity>(loginActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            LoginActivity loginActivity = reference.get();
            switch (what) {
                case 11:
                    Student student = (Student) msg.obj;
                    if (student == null) loginActivity.EmptyUserError();
                    else {
                        loginActivity.setStudent(student);
                        loginActivity.showGetSuccess();
                        String password=loginActivity.passwordText.getText().toString();
                        if (password.equals(loginActivity.selectedStudent.getPassword())) {
                            loginActivity.turnToNextAct();
                        } else {
                            loginActivity.showPwdError();
                        }
                    }
                    break;
                case 10:
                    loginActivity.EmptyUserError();
                    break;

            }
            super.handleMessage(msg);
        }
    }

    public void setStudent(Student student) {
        this.selectedStudent = student;
    }

    public void showGetSuccess() {

    }

    public void EmptyUserError() {
        Toast.makeText(LoginActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
    }

    public void showPwdError() {
        //Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
    }

    public void turnToNextAct(){
        loginId = selectedStudent.getStudentid();
        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("studentid", loginId);
        startActivity(intent);
    }
}
