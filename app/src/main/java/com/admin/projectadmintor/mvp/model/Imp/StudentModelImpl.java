package com.admin.projectadmintor.mvp.model.Imp;


import android.content.Context;

import com.admin.projectadmintor.mvp.bean.Student;
import com.admin.projectadmintor.mvp.model.StudentModel;
import com.admin.projectadmintor.mvp.presenter.OnGetStudentListener;
import com.admin.projectadmintor.mvp.presenter.OnSendStudentListener;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 2018/1/5.
 */

public class StudentModelImpl implements StudentModel {

    private String baseUrl = "http://10.0.2.2:8000/";
    private RequestQueue requestQueue;

    public StudentModelImpl(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }


    @Override
    public void sendStudent(final String studentid, final String username, final String password, final String identity, final OnSendStudentListener listener) {
        String url = baseUrl + "user/insertstudent";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")) {
                    listener.onSendSuccess(response);
                } else {
                    listener.onSendFailed();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("1");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("studentid", studentid);
                map.put("username", username);
                map.put("password", password);
                map.put("identity", identity);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void loadStudent(final String username, final OnGetStudentListener listener) {
        String url = baseUrl + "user/getbyname";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response .equals("")) {

                    JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();

                    Student student = new Gson().fromJson(jsonObject, Student.class);
                    if (student != null) {
                        listener.onGetSuccess(student);
                    } else {
                        listener.onGetFailed();
                    }
                }
                else {
                    listener.onGetFailed();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("username", username);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
