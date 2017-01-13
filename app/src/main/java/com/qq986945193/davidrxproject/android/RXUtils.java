package com.qq986945193.davidrxproject.android;/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */


import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RXUtils {
    //获取类名
    public static String TAG = RXUtils.class.getSimpleName();

    /**
     * 使用create放法
     */
    public static void createObservable() {
        //定义被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    //添加数据
                    subscriber.onNext("hello");
                    subscriber.onNext("hi");
                    subscriber.onNext(dowLoadJson());
                    subscriber.onNext("world");
                    subscriber.onCompleted();
                }
            }
        });
        /**
         * 这里根据被观察者进行关联 并观察subscriber所执行的方法
         */
        Subscriber<String> subscriber = new Subscriber<String>() {
            //成功时调用
            @Override
            public void onCompleted() {

                Log.i(TAG, "onCompleted");

            }

            //失败时调用
            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: " + e.getMessage());
            }

            /**继续添加
             */
            @Override
            public void onNext(String s) {
                Log.i(TAG, "ddd: " + s);
            }
        };

        observable.subscribe(subscriber);

    }

    /**
     * 调用网络接口获取数据  这里模拟演示直接返回json
     */
    private static String dowLoadJson() {
        return "my name is jsondata";
    }

    /**
     * create的第二种方法
     */
    public static void createMethod2() {
        //新建一个被观察者并进行绑定
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    for (int i = 0; i < 10; i++) {
                        subscriber.onNext(i + "");
                    }
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.newThread())    //让Observable运行在新线程中
                .observeOn(AndroidSchedulers.mainThread())   //让subscriber运行在主线程中
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {

                    }
                });
    }

}
