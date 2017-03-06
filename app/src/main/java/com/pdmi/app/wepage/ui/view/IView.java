package com.pdmi.app.wepage.ui.view;

/**
 * Created by guoxiuli on 2017/2/16.
 */

  public interface IView {
    /**
     * 显示ProgressDialog
     *
     * @param message 对话框消息
     */
    void showProgressDialog(int message);

    /**
     * 取消显示ProgressDialog
     */
    void dismissProgressDialog();

    /**
     * 显示ProgressBar
     */
    void showProgressBar();

    /**
     * 取消显示ProgressBar
     */
    void dismissProgressBar();

    /**
     * show specified error message
     *
     * @param throwable error
     */
    void showErrorMessage(Throwable throwable);

    void showNetWorkErrorMessage();
    void showToast(String msg);
  }