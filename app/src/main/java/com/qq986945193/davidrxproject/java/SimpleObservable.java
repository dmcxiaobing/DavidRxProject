package com.qq986945193.davidrxproject.java;/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import java.util.Observable;

/**
 * 这是一个被观察者
 */
public class SimpleObservable extends Observable {

    private int data = 0;
    public int getData(){
        return data;
    }

    public void setData(int i){
        if (this.data!=i){
            this.data = i;
            //发生改变
            setChanged();
            //通知观察者,表示状态发生改变
            notifyObservers();

        }
    }
}
