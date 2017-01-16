package com.qq986945193.davidrxproject.android;
/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */


import android.util.Log;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * 登陆的一个工具类
 */
public class LoginUtils {
    /**
     * 登陆 返回Observable<String>类型的
     */
    public static Observable<String> loginMethod(final Map<String, String> params, final String url) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    OkHttpClient okHttpClient = new OkHttpClient();

                    Request request = new Request.Builder().post(getRequestBody(params)).url(url).build();
                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                String json = response.body().string();
                                if (json != null) {
                                    subscriber.onNext(json);
                                    subscriber.onCompleted();
                                }
                            }


                        }
                    });
                }

            }
        });
    }

    public static RequestBody getRequestBody(Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                if (param.getKey() != null && param.getValue() != null) {
                    String key = param.getKey();
                    String value = param.getValue();
                    builder.add(key, value);

                }

            }

        }
        //这里返回了FormBody  因为此类继承抽象类RequestBody
        return builder.build();
    }

}
