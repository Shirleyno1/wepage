package com.pdmi.app.wepage.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Toast;

import com.pdmi.app.wepage.R;
import com.pdmi.app.wepage.ui.view.IView;

public class BaseActivity extends AppCompatActivity implements IView {
  protected ProgressDialog progressDialog;
  protected Dialog progressBarDialog;
  //记录时间事件

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
  }

  @Override
  public void showProgressDialog(int message) {
    if (progressDialog == null) {
      progressDialog = new ProgressDialog(this);
    }
    progressDialog.setMessage(getString(message));
    progressDialog.show();
  }

  @Override
  public void dismissProgressDialog() {
    if (progressDialog != null && progressDialog.isShowing()) {
      progressDialog.dismiss();
      progressDialog = null;
    }
  }

  @Override
  public void showProgressBar() {
    if (progressBarDialog == null) {
      progressBarDialog = new Dialog(this, R.style.Theme_Teambition_Dialog_Progressbar);
      progressBarDialog.setContentView(R.layout.dialog_loading_indicator);
    }
    if (progressBarDialog != null && !progressBarDialog.isShowing()) {
      progressBarDialog.show();
    }
  }

  @Override
  public void dismissProgressBar() {
    if (progressBarDialog != null && progressBarDialog.isShowing()) {
      progressBarDialog.dismiss();
      progressBarDialog = null;
    }
  }

  @Override
  public void showErrorMessage(Throwable throwable) {

  }

  @Override
  public void showNetWorkErrorMessage() {

  }

  @Override
  public void showToast(String msg) {
    Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
  }

  @Override
  protected void onDestroy() {
    dismissProgressBar();
    dismissProgressDialog();
    super.onDestroy();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_subscribe_add, menu);
    return super.onCreateOptionsMenu(menu);
  }
//  public Toolbar initToolbar() {
//    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//    setSupportActionBar(toolbar);
//    ActionBar actionBar = getSupportActionBar();
//    if (actionBar != null) {
//      actionBar.setDisplayHomeAsUpEnabled(true);
//    }
//    return toolbar;
//  }
  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
//    invalidateOptionsMenu();
  return super.onPrepareOptionsMenu(menu);
}
}