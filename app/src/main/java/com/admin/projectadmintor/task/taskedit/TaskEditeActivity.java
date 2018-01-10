package com.admin.projectadmintor.task.taskedit;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.admin.projectadmintor.R;
import com.admin.projectadmintor.bean.StuInTask;
import com.admin.projectadmintor.task.tasklist.TaskListActivity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.Calendar;

public class TaskEditeActivity extends Activity implements View.OnClickListener{
    private ImageButton back;
    private Button send_task;
    private TextView mid;
    private EditText startDate,endDate;
    private EditText name;
    private EditText theme;
    private EditText content;
    private EditText budget;
    private ImageButton addMembers;
    private LinearLayout memberList;
    private HttpUtils httpUitil;
    private RequestParams params;
    private int numid=0,numname=1;
    StuInTask stuInTask;
    String stuid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_send_layout);
        init();
    }

    private void init(){
        Intent intent=getIntent();
        stuid=intent.getStringExtra("studentid");
        back = (ImageButton)findViewById(R.id.back_im_btn);
        mid = (TextView)findViewById(R.id.send_task_id);
        mid.setText(System.currentTimeMillis()+"");
        startDate = (EditText)findViewById(R.id.send_task_startdate);
        startDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    showDatePigkDlg();
                    return true;
                }
                return false;
            }
        });

        endDate = (EditText)findViewById(R.id.send_task_enddate);
        endDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    showDatePigkDlg1();
                    return true;
                }
                return false;
            }
        });
        send_task = (Button)findViewById(R.id.send_task);
        name = (EditText) findViewById(R.id.send_task_name);
        theme = (EditText)findViewById(R.id.send_task_theme);
        content = (EditText)findViewById(R.id.send_task_content);
        budget = (EditText)findViewById(R.id.send_task_budget);
        addMembers = (ImageButton)findViewById(R.id.send_task_members);
        memberList = (LinearLayout)findViewById(R.id.member_list);
        back.setOnClickListener(this);
        send_task.setOnClickListener(this);
        addMembers.setOnClickListener(this);
        httpUitil = new HttpUtils();
        params = new RequestParams();
    }

    @Override
    protected void onStart(){
        super.onStart();
        //num=0;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_im_btn:
                Intent intent=new Intent(TaskEditeActivity.this, TaskListActivity.class);
                intent.putExtra("studentid",stuid);
                startActivity(intent);
                finish();
                break;
            case R.id.send_task:
                sendData();
                TextView member;
                for(int i=0;i<numid/2;i++){
                    member=(TextView)findViewById(2*i);
                    String msg=member.getText().toString().trim();
                    sendDateWithStudents(msg);
                }

                break;
            case R.id.send_task_members:
                LayoutInflater factory = LayoutInflater.from(TaskEditeActivity.this);//提示框
                final View view = factory.inflate(R.layout.add_members, null);//这里必须是final的
                final EditText studentID=(EditText)view.findViewById(R.id.student_ID);//获得输入框对象
                final EditText studentName=(EditText)view.findViewById(R.id.student_Name);
                AlertDialog dialog=new AlertDialog.Builder(TaskEditeActivity.this)
                        .setTitle("请输入学号姓名")//提示框标题
                        .setView(view)
                        .setPositiveButton("确定",//提示框的两个按钮
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                            int which) {
                                        String myname=studentName.getText().toString();
                                        String myID=studentID.getText().toString();
                                        try{
                                            stuInTask=new StuInTask(myID,myname);
                                            final int inid=numid;
                                            final int inname=numname;
                                            //Toast.makeText(TaskEditeActivity.this,stuInTask.getStudentID(),Toast.LENGTH_SHORT).show();
                                            LinearLayout myInfo=new LinearLayout(TaskEditeActivity.this);
                                            LinearLayout.LayoutParams linearParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                            memberList.addView(myInfo,linearParams);
                                            TextView mid = new TextView(TaskEditeActivity.this);
                                            mid.setText(myID);
                                            mid.setId(inid);
                                            mid.setPadding(20,5,0,5);
                                            myInfo.addView(mid);
                                            TextView name = new TextView(TaskEditeActivity.this);
                                            name.setText(myname);
                                            name.setId(inname);
                                            name.setPadding(100,5,20,5);
                                            myInfo.addView(name);
                                            //Toast.makeText(TaskEditeActivity.this,""+mid.getId()+name.getId(),Toast.LENGTH_LONG).show();
                                            numid+=2;
                                            numname+=2;
                                            //System.out.println(myidid);
                                        }catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }).setNegativeButton("取消", null).create();
                WindowManager m = getWindowManager();
                Display d = m.getDefaultDisplay();
                dialog.show();
                WindowManager.LayoutParams p = dialog.getWindow().getAttributes();
                p.height = (int) (d.getHeight() * 0.4);
                p.width = (int) (d.getWidth() * 0.7);
                dialog.getWindow().setAttributes(p);
                break;
        }
    }

    private void sendData(){
        String url="http://10.0.2.2:8000/project/insertproject";
        String pid=mid.getText().toString().trim();
        String pname=name.getText().toString().trim();
        String pstartdate=startDate.getText().toString().trim();
        String penddate=endDate.getText().toString().trim();
        String pcontent=content.getText().toString().trim();
        String pbudget=budget.getText().toString().trim();

        params.addBodyParameter("projectid",pid);
        params.addBodyParameter("projectname",pname);
        params.addBodyParameter("description",pcontent);
        params.addBodyParameter("funds",pbudget);
        params.addBodyParameter("startdate",pstartdate);
        params.addBodyParameter("enddate",penddate);
        uploadMethod(params,url);
    }

    public void uploadMethod(final RequestParams params, final String uploadHost) {
        httpUitil.send(HttpRequest.HttpMethod.POST, uploadHost, params, new RequestCallBack<String>() {
            private boolean progressShow = true;
            final ProgressDialog pd = new ProgressDialog(TaskEditeActivity.this);

            @Override
            public void onStart() {
                //上传开始
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                //上传中

                pd.setCanceledOnTouchOutside(false);
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        progressShow = false;
                    }
                });
                pd.setMessage("正在提交....");
                pd.show();
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //上传成功，这里面的返回值，就是服务器返回的数据
                String result = responseInfo.result.toString();
                if (result.equals("success")) {
                    pd.dismiss();
                    Toast.makeText(TaskEditeActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    pd.dismiss();
                    Toast.makeText(TaskEditeActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                //上传失败
                pd.dismiss();
                Toast.makeText(TaskEditeActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void sendDateWithStudents(String msg){
        String url="http://10.0.2.2:8000/project/insertrelation";
        String pid=mid.getText().toString().trim();

        params.addBodyParameter("projectid",pid);
        params.addBodyParameter("memberid",msg);
        uploadMethod(params,url);
    }
    public void showDatePigkDlg(){
        Calendar calendar=Calendar.getInstance();
        DatePickerDialog datePickerDialog=new DatePickerDialog(TaskEditeActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                TaskEditeActivity.this.startDate.setText(year+"-"+(month+1)+"-"+day);
            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    public void showDatePigkDlg1(){
        Calendar calendar=Calendar.getInstance();
        DatePickerDialog datePickerDialog=new DatePickerDialog(TaskEditeActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                TaskEditeActivity.this.endDate.setText(year+"-"+(month+1)+"-"+day);
            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

}
