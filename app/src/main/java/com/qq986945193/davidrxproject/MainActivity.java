package com.qq986945193.davidrxproject;
/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qq986945193.davidrxproject.android.RXUtils;

/**
 * RX响应式编程
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 创建create 方法演示
     */
    public void createMethod(View view){
        RXUtils.createObservable();
    }


    /**
     * 创建create第二种方法演示
     */
    public void createMethod2(View view){
        RXUtils.createMethod2();
    }


}
