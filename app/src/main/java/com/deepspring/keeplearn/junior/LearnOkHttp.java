package com.deepspring.keeplearn.junior;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LearnOkHttp {

    private static final String URL = "https://wanandroid.com/wxarticle/chapters/json";

    private static void getNetWork() throws IOException {

        OkHttpClient okHttpClient1 = new OkHttpClient();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        Request request = new Request.Builder()
                .url(URL)
                .build();

        Call call = okHttpClient.newCall(request);

        //同步
        Response response = call.execute();
        System.out.println(response.body().string());

        //异步
        okHttpClient1.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });
    }

    public static void main(String[] args) throws IOException {
        getNetWork();
    }
}
