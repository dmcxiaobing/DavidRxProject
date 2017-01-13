package com.qq986945193.davidrxproject.android;/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */


import android.util.Log;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
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

    /**
     * from方法演示  使用在被观察者，返回的对象有时候都是数值类型
     */
    public static void from() {
        Integer[] items = {1,2,3,4,5,6,7,8,9};
        Observable observable = Observable.from(items);
        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.i(TAG, "call: "+o.toString());
            }
        });
    }

    /**
     * 处理数组集合
     */
    public static void just() {
        Integer[] items1 = {1,3,5,7,9};
        Integer[] items2 = {2,4,6,8};
        Observable observable = Observable.just(items1,items2);
        observable.subscribe(new Subscriber<Integer[]>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer[] integers) {
                for (int i = 0;i<integers.length;i++){
                    Log.i(TAG, "onNext: "+integers[i]);
                }
            }
        });
    }

    /**
     * 使用过滤功能
     */
    public static void filter() {
        Observable observable = Observable.just(1,2,3,4,5,6,7);
        observable.filter(new Func1<Integer,Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                //这样只保留了小于5的数字
                return integer<5;
            }
        }).observeOn(Schedulers.io()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "onNext: "+integer.toString());
            }
        });

    }

    /**
     * 使用范围数据，指定输出数据的范围
     */
    public static void range(){
        //保留1-20内的数字，包括1和20
        Observable observable = Observable.range(1,20);
        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "onNext: "+integer);
            }
        });
    }
}
