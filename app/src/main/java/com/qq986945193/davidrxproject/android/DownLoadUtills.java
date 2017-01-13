package com.qq986945193.davidrxproject.android;/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * 演示下载一张图片的工具类
 */
public class DownLoadUtills {
    /**
     * 声明一个被观察者对象，作为结果返回
     */
    public static Observable<byte[]> downloadImage(final String imgurl) {
        return Observable.create(new Observable.OnSubscribe<byte[]>() {
            @Override
            public void call(final Subscriber<? super byte[]> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    //如果没有绑定 访问网络 这里借助okhttp
                    OkHttpClient client = new OkHttpClient();
                    //创建request
                    Request request = new Request.Builder().url(imgurl).build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                byte[] bytes = response.body().bytes();
                                if (bytes != null) {
                                    subscriber.onNext(bytes);
                                    subscriber.onCompleted();
                                }
                            }
                        }
                    });


                }
            }
        });
    }

}
