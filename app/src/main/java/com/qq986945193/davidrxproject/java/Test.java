package com.qq986945193.davidrxproject.java;/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

public class Test {

    public static void main(String args[]){
        WatchedImp watchedImp = new WatchedImp();
        WatchImp watchImp = new WatchImp();
        watchedImp.add(watchImp);
        watchedImp.modify("我更新了");
    }
}
