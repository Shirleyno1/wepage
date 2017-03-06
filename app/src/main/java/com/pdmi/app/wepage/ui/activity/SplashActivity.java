package com.pdmi.app.wepage.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.pdmi.app.wepage.R;

public class SplashActivity extends Activity {
  private static final int SHOW_TIME_MIN = 2000;// 最小显示时间
  private long mStartTime;// 开始时间
//  public static String netType;
  private Handler mHandler = new Handler() {
    public void handleMessage(android.os.Message msg) {

          long loadingTime = System.currentTimeMillis() - mStartTime;// 计算一下总共花费的时间
          if (loadingTime < SHOW_TIME_MIN) {// 如果比最小显示时间还短，就延时进入MainActivity，否则直接进入
            mHandler.postDelayed(goToMainActivity, SHOW_TIME_MIN
                - loadingTime);
          } else {
            mHandler.post(goToMainActivity);
          }

    }
  };
  //进入下一个Activity
  Runnable goToMainActivity = new Runnable() {

    @Override
    public void run() {
      SplashActivity.this.startActivity(new Intent(SplashActivity.this,
          MainActivity.class));
      finish();
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    mStartTime = System.currentTimeMillis();//记录开始时间，
//    netType = NetWorkTypeUtils.getNetType1(this);
    mHandler.sendEmptyMessage(0);
  }
}


