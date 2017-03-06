package com.pdmi.app.wepage.ui;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by guoxiuli on 2017/2/21.
 */

public class MyApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    CrashReport.initCrashReport(getApplicationContext(), "b9e904b014", false);
    Realm.init(this);
    RealmConfiguration config = new  RealmConfiguration.Builder()
        .name("wePage.realm")
        .deleteRealmIfMigrationNeeded()
        .build();
    Realm.setDefaultConfiguration(config);

  }
}