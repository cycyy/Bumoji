package com.example.administrator.bumoji.Tools;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/8/11.
 */

public class Httptool {
    private OkHttpClient okHttpClient;
    public void getHttp(String url,Callback callback){
       okHttpClient=new OkHttpClient();
        Request.Builder requestBuilder=new Request.Builder().url(url);
        requestBuilder.method("GET",null);//可以省略，默认是GET请求
        Request request = requestBuilder.build();
        Call call= okHttpClient.newCall(request);
        call.enqueue(callback);

    }
}
