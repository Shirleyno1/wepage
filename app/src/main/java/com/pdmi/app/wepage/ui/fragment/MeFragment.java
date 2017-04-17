package com.pdmi.app.wepage.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pdmi.app.wepage.R;

/**
 * 订阅界面
 */
public class MeFragment extends BaseFragment{
  private static final String TAG = "MeFragment";
  public static MeFragment newInstance() {
    MeFragment newFragment = new MeFragment();
//    newFragment.setArguments(bundle);
    return newFragment;
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_me, container, false);
//    initToolbar();
    return rootView;
  }

  @Override
  protected void lazyLoad() {

  }
}
