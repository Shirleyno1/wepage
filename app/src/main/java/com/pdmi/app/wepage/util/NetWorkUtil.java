package com.pdmi.app.wepage.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by guoxiuli on 2017/2/22.
 */

public class NetWorkUtil {
  public static boolean netConnected(Context context){
    ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo mobNetInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
    NetworkInfo  wifiNetInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
    if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
      return false;
      //改变背景或者 处理网络的全局变量
    }
    return true;
  }
}
