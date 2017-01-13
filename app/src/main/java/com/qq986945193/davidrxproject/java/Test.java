package com.qq986945193.davidrxproject.java;/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

/**
 * 进行测试 观察者与观察者
 */
public class Test {

    public static void main(String args[]) {
//        testWatch();
          testObserver();
    }

    /**
     * 测试simpleObserver
     */
    private static void testObserver() {
        SimpleObservable simpleObservable = new SimpleObservable();
        SimpleObserver simpleObserver = new SimpleObserver(simpleObservable);
        simpleObservable.setData(2);
        simpleObservable.setData(1);
        simpleObservable.setData(3);


    }

    /**
     * 测试watch
     */
    private static void testWatch() {
        WatchedImp watchedImp = new WatchedImp();
        WatchImp watchImp = new WatchImp();
        watchedImp.add(watchImp);
        watchedImp.modify("我更新了");
    }
}
