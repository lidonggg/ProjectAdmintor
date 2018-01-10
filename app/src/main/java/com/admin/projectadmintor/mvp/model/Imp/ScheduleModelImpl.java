package com.admin.projectadmintor.mvp.model.Imp;

import android.content.Context;

import com.admin.projectadmintor.mvp.bean.Schedule;
import com.admin.projectadmintor.mvp.presenter.OnGetScheduleListener;
import com.admin.projectadmintor.mvp.presenter.OnSendScheduleListener;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 2018/1/1.
 */

public class ScheduleModelImpl implements ScheduleModel {
    private String baseUrl="http://10.0.2.2:8000/";
    private RequestQueue requestQueue;

    public ScheduleModelImpl(Context context){
        requestQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void loadSchedules(final String studentid, final String projectid, final OnGetScheduleListener listener) {
        String url=baseUrl+"schedule/selectschedules";

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JsonArray jsonArray= new JsonParser().parse(response).getAsJsonArray();
                List<Schedule> schedules=new ArrayList<Schedule>();
                for(JsonElement jsonElement:jsonArray){
                    Schedule schedule=new Gson().fromJson(jsonElement,Schedule.class);
                    schedules.add(schedule);
                }

                listener.onSuccess(schedules);

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String > map=new HashMap<>();
                map.put("studentid",studentid);
                map.put("projectid",projectid);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void sendSchedule(final String scheduleid, final String projectid, final String memberid, final String description
            , final String deadline, final String completestate, final OnSendScheduleListener listener) {
        String url=baseUrl+"schedule/insertschedule";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("success")){
                    listener.sendSuccess();
                }
                else
                    listener.sendFailed();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String > map=new HashMap<>();
                map.put("scheduleid",scheduleid);
                map.put("projectid",projectid);
                map.put("memberid",memberid);
                map.put("description",description);
                map.put("deadline",deadline);
                map.put("completestate",completestate);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void updateSchedule(final String scheduleid) {
        String url=baseUrl+"schedule/updateschedule";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String > map=new HashMap<>();
                map.put("scheduleid",scheduleid);
                map.put("completestate","completed");
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
