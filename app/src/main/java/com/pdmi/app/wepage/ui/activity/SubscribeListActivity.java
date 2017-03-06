package com.pdmi.app.wepage.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pdmi.app.wepage.R;
import com.pdmi.app.wepage.model.StudioList;
import com.pdmi.app.wepage.presenter.impl.StudioList2Presenter;
import com.pdmi.app.wepage.ui.view.IStudioList2View;
import com.pdmi.app.wepage.ui.widget.OnItemClick;
import com.pdmi.app.wepage.ui.widget.SearchTipsGroupView;
import com.pdmi.app.wepage.util.NetWorkUtil;
import com.pdmi.app.wepage.util.retrofit.GitHubService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 订阅界面
 */
public class SubscribeListActivity extends BaseActivity implements OnItemClick,IStudioList2View,SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = "SubscribeListFragment";
  @InjectView(R.id.sg_studios)
  SearchTipsGroupView sg_studios;
  @InjectView(R.id.save)
  TextView tv_save;
  @InjectView(R.id.swipe_container)
  SwipeRefreshLayout refreshLayout;
  private StudioList2Presenter presenter;
//  private String items[] = {"半亩方塘", "一秒世界", "侠客岛","半亩方塘", "一秒世界", "侠客岛","半亩方塘", "一秒世界", "侠客岛", "融媒体新闻工作室后勤部", "侠客岛"};

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_subscribelist);
    ButterKnife.inject(this);
    initViews();
    requestData();
  }

  private void requestData() {
    presenter = new StudioList2Presenter(this, GitHubService.getInstance().getRetrofitApi());
    if(NetWorkUtil.netConnected(this)){
      presenter.getStudioList();
    }else{
      if(null!=refreshLayout){
        refreshLayout.setRefreshing(false);
      }
      showToast(getResources().getString(R.string.msg_network_error));
    }
  }

  private void initViews() {
    refreshLayout.setOnRefreshListener(this);
    refreshLayout.setColorSchemeResources(R.color.tabTitleSelect);
  }

  @OnClick(R.id.save)
  void save(){
    presenter.subscribe(mStudios.getList());
  }
 @OnClick(R.id.back)
 void back(){
   finish();
//   getFragmentManager().popBackStack();
 }
  @Override
  public void onClick(int position,boolean isChecked) {
    mStudios.getList().get(position).setHasSubscribed(isChecked);
  }
  StudioList mStudios;
  @Override
  public void setStudioList(StudioList studios) {
    refreshLayout.setRefreshing(false);
    mStudios = studios;
    sg_studios.clear();
    sg_studios.initViews(studios.getList(), this);
  }

  @Override
  public void subscribe() {
    SubscribeListActivity.this.finish();
    Toast.makeText(this, "订阅成功", Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onRefresh() {
      requestData();
  }
}
