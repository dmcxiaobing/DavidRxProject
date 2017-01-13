package com.qq986945193.davidrxproject.java;/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import java.util.Observable;
import java.util.Observer;

/**
 * 创建一个观察者
 */
public class SimpleObserver implements Observer {
    /**
     * 当更新的话会调用此方法
     * @param observable
     * @param data
     */
    @Override
    public void update(Observable observable, Object data) {
        System.out.println("data is changed"+((SimpleObservable)observable).getData());
    }

    public SimpleObserver(SimpleObservable simpleObservable){
        simpleObservable.addObserver(this);
    }


}
