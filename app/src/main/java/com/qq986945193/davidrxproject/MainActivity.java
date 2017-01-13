package com.qq986945193.davidrxproject;
/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.qq986945193.davidrxproject.android.DownLoadUtills;
import com.qq986945193.davidrxproject.android.RXUtils;
import com.squareup.picasso.Picasso;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.MainThreadSubscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * RX响应式编程
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        RXUtils.range();
        iv = (ImageView) findViewById(R.id.iv);

    }


    /**
     * 下载一张图片
     */
    public static final String IMG_URL = "http://git.oschina.net/uploads/70/577070_MCXIAOBING.png?1468392539";
    public void downloadImage(View view) {
        //这里返回的是observable 所以直接绑定即可
        //让Observable运行在新线程中
        //让subscriber运行在主线程中
        DownLoadUtills.downloadImage(IMG_URL).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<byte[]>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: 对话框消失");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, e.getMessage());

            }

            @Override
            public void onNext(byte[] bytes) {
//                这里加载 得到了字节流，利用bitmap进行转换显示
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                iv.setImageBitmap(bitmap);
            }
        });
    }

    /**
     * 创建create 方法演示
     */
    public void createMethod(View view) {
        RXUtils.createObservable();
    }


    /**
     * 创建create第二种方法演示
     */
    public void createMethod2(View view) {
        RXUtils.createMethod2();
    }

    /**
     * from 方法演示
     */
    public void from(View view) {
        RXUtils.from();
    }

    /**
     * just方法
     */
    public void just(View view) {
        RXUtils.just();
    }

    /**
     * filter 方法
     */
    public void filter(View view) {
        RXUtils.filter();
    }
}
