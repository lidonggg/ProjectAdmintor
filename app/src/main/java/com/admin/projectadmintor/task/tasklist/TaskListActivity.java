package com.admin.projectadmintor.task.tasklist;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.admin.projectadmintor.R;

import com.admin.projectadmintor.homePage.MainActivity;
import com.admin.projectadmintor.routeActivity;
import com.admin.projectadmintor.task.taskedit.TaskEditeActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskListActivity extends AppCompatActivity implements View.OnClickListener{
    public static Context context;
    private RecyclerView recyclerView;
    private List<Project> mlist = new ArrayList<>();
    private ImageButton addProject;
    ProjectAdapter projectAdapter;
    private ImageButton back;
    String stuid;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //getDate("1");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        init();
        projectAdapter = new ProjectAdapter(mlist,stuid);
        recyclerView.setAdapter(projectAdapter);
        getDate(stuid);
    }
    private void init(){
        context=this;
        Intent intent=getIntent();
        stuid=intent.getStringExtra("studentid");
        back = (ImageButton)findViewById(R.id.myback);
        addProject = (ImageButton)findViewById(R.id.add_project);
        addProject.setOnClickListener(this);
        back.setOnClickListener(this);
        mSwipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.list_refesh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mlist.clear();
                getDate(stuid);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getDate(final String msg) {
        String url = "http://10.0.2.2:8000/project/selectproject";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JsonArray jsonArray = new JsonParser().parse(response).getAsJsonArray();
                List<StudentProject> studentProjects = new ArrayList<StudentProject>();
                for (JsonElement jsonElement : jsonArray) {
                    StudentProject studentProject = new Gson().fromJson(jsonElement, StudentProject.class);
                    studentProjects.add(studentProject);
                }
                showDate(studentProjects);
                Toast.makeText(TaskListActivity.this,"success",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TaskListActivity.this,"网络错误",Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("studentid", msg);

                return map;
            }
        };
        queue.add(stringRequest);
    }
    private void showDate(List<StudentProject> studentProjects){
        Project project;
        StudentProject studentProject;
        for(int i=0;i<studentProjects.size();i++){
            studentProject=studentProjects.get(i);
            project=new Project(studentProject.getProjectid(),studentProject.getProjectname(),studentProject.getDescription());
            mlist.add(project);
        }
        projectAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_project:
                Intent intent=new Intent(TaskListActivity.this, TaskEditeActivity.class);
                intent.putExtra("studentid",stuid);
                startActivity(intent);
                break;
            case R.id.myback:
                Intent intent1=new Intent(TaskListActivity.this, MainActivity.class);
                intent1.putExtra("studentid",stuid);
                startActivity(intent1);
                break;
        }
    }
    public String getStuid(){
        return stuid;
    }
}


