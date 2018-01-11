package com.admin.projectadmintor.homePage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.admin.projectadmintor.R;
import com.admin.projectadmintor.baidu.BaiduRoutePlanActivity;
import com.admin.projectadmintor.mvp.view.Imp.WeatherActivity;
import com.admin.projectadmintor.recorder.RecorderActivity;
import com.admin.projectadmintor.settings.SetActivity;
import com.admin.projectadmintor.task.tasklist.TaskListActivity;


public class MainActivity extends AppCompatActivity {
    String stuid;
    //定义一个gridView的表格布局
    private GridView gridView;

    private PictureAdapter adapter;
    //设置每个item的图片的数组
    private int[] images = new int[]{
            R.mipmap.one,  R.mipmap.weather ,R.mipmap.two
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.home_gv);
        adapter = new PictureAdapter(null, images, this);      //自定义gridview的适配器
        gridView.setAdapter(adapter);       //为gridview设置适配器
        Intent intent=getIntent();
        stuid=intent.getStringExtra("studentid");

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             // 跳转页面
                switch (position) {
                    case 0:
                        Intent intent = new Intent(MainActivity.this, TaskListActivity.class);
                        intent.putExtra("studentid",stuid);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, WeatherActivity.class);
                        intent1.putExtra("studentid",stuid);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, SetActivity.class);
                        intent2.putExtra("studentid",stuid);
                        startActivity(intent2);
                        break;
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //将此activity加到列队里面  以便finish掉
        BaseApplication.addDestroyActiivty(this,"mainActivity");
    }

    /**
     * 该方法是用来此页面跳转到其他的页面的
     * */
    private void startOther(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

//    //点击进入导航页面
    public void navigation(View v) {
        Intent intent = new Intent(MainActivity.this, BaiduRoutePlanActivity.class);
        intent.putExtra("studentid",stuid);
        startActivity(intent);
    }

    //点击进入录音功能界面
    public void record(View v) {
        Intent intent = new Intent(MainActivity.this, RecorderActivity.class);
        intent.putExtra("studentid",stuid);
        startActivity(intent);

    }


}

