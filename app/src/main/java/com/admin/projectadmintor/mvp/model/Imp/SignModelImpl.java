package com.admin.projectadmintor.mvp.model.Imp;

import android.content.Context;

import com.admin.projectadmintor.mvp.bean.SignInfo;
import com.admin.projectadmintor.mvp.model.SignModel;
import com.admin.projectadmintor.mvp.presenter.OnGetSignInfoListener;
import com.admin.projectadmintor.mvp.presenter.OnSendSignInfoListener;
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

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 2018/1/5.
 */

public class SignModelImpl implements SignModel {
    private String baseUrl="http://10.0.2.2:8000/";
    private RequestQueue requestQueue;

    public SignModelImpl(Context context){
        requestQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void sendSignInfo(final String signid, final String projectid, final String studentid, final String signdate, final OnSendSignInfoListener listener) {
        String url=baseUrl+"sign/insertsigninfo";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                if (response.equals("success")) {
                    listener.onSendSuccess(response);
                } else {
                    listener.onSendFailed();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("signid",signid);
                map.put("projectid",projectid);
                map.put("studentid",studentid);
                map.put("signdate",signdate);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void loadSignInfo(final String studentid, final String projectid, final OnGetSignInfoListener listener) {
        String url=baseUrl+"sign/getsigninfo";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JsonArray jsonArray = new JsonParser().parse(response).getAsJsonArray();
                List<SignInfo> signdates = new ArrayList<SignInfo>();
                for (JsonElement jsonElement : jsonArray) {
                    SignInfo signInfo = new Gson().fromJson(jsonElement, SignInfo.class);
                    signdates.add(signInfo);
                }

                listener.onGetSuccess(signdates);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("studentid",studentid);
                map.put("projectid",projectid);
                return map;
            }

        };
        requestQueue.add(stringRequest);
    }
}
