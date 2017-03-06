package com.pdmi.app.wepage.ui.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.pdmi.app.wepage.R;
import com.pdmi.app.wepage.ui.view.IView;


/**
 * Created by guoxiuli
 */
public abstract class BaseFragment extends Fragment implements IView {

    protected ProgressDialog progressDialog;
    protected Dialog progressBarDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showProgressDialog(int message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this.getActivity());
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
            progressBarDialog = new Dialog(this.getActivity(), R.style.Theme_Teambition_Dialog_Progressbar);
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

    public Toolbar getToolbar() {
        return (Toolbar) this.getActivity().findViewById(R.id.toolbar);
    }

    @Override
    public void showErrorMessage(Throwable throwable) {
        Toast.makeText(getActivity(),throwable.getMessage(),Toast.LENGTH_LONG);
    }

    @Override
    public void showNetWorkErrorMessage() {
        Toast.makeText(getActivity(),"网络错误",Toast.LENGTH_LONG);
    }

//    protected AppComponent getAppComponent() {
//        return ((MainApp) getActivity().getApplication()).getComponent();
//    }
    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void lazyLoad();
    /** Fragment当前状态是否可见 */
    protected boolean isVisible;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }


    /**
     * 不可见
     */
    protected void onInvisible() {


    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
    }
}
