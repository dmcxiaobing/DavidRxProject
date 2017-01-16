package com.qq986945193.davidrxproject;
/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.qq986945193.davidrxproject.android.DownLoadUtills;
import com.qq986945193.davidrxproject.android.LoginUtils;
import com.qq986945193.davidrxproject.android.RXUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setTitle("请稍等。。");

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

    public static final String LOGIN_URL = "http://192.168.0.111:8080/JavaWebBase/LoginActionServlet";
    ProgressDialog mProgressDialog;

    /**
     * 这里测试登陆的一个功能。账号和密码我直接填上去了。
     */
    public void login(View view) {
        Map<String, String> params = new HashMap<>();
        params.put("username", "admin");
        params.put("password", "123456");

        LoginUtils.loginMethod(params, LOGIN_URL).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                hideDialog();

            }

            @Override
            public void onError(Throwable e) {
                hideDialog();
                Toast.makeText(MainActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNext(String s) {
                if (mProgressDialog != null) {
                    mProgressDialog.show();
                }
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(s);
                    String jsonValue = jsonObject.getJSONObject("result").getString("resultMsg");
                    if (jsonValue.equals("success")) {
                        Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                } finally {
                    hideDialog();
                }

            }

        });

    }

    private void hideDialog() {
        if (mProgressDialog != null && !mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        } else {
            mProgressDialog.dismiss();
        }
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
