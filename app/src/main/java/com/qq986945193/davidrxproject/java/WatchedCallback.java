package com.qq986945193.davidrxproject.java;/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

/**
 * 定义一个被观察者的接口
 */
public interface WatchedCallback {

    public void add(WatchImp watchImp);

    public void del(WatchImp watchImp);

    public void modify(String string);

}
