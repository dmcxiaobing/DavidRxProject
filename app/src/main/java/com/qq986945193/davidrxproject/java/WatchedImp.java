package com.qq986945193.davidrxproject.java;/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.content.SyncStatusObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者的实现类
 */
public class WatchedImp implements WatchedCallback{
    private List<WatchImp> watchImps = new ArrayList<>();
    @Override
    public void add(WatchImp watchImp) {
        System.out.println("我添加了");
        watchImps.add(watchImp);
    }

    @Override
    public void del(WatchImp watchImp) {
        System.out.println("我删除了");
        watchImps.remove(watchImp);
    }

    @Override
    public void modify(String string) {
        for (WatchImp watchImp:watchImps){
            watchImp.notifyFunction(string);
        }
    }


}
