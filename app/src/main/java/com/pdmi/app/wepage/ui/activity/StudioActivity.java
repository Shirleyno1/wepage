package com.pdmi.app.wepage.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.pdmi.app.wepage.R;
import com.pdmi.app.wepage.model.Studio;
import com.pdmi.app.wepage.presenter.impl.StudioPresenter;
import com.pdmi.app.wepage.ui.adapter.StudioNewsAdapter;
import com.pdmi.app.wepage.ui.view.IStudioView;
import com.pdmi.app.wepage.ui.widget.MyHorizontalDividerItemDecoration;
import com.pdmi.app.wepage.ui.widget.MyLinearLayoutManager;
import com.pdmi.app.wepage.util.NetWorkUtil;
import com.pdmi.app.wepage.util.retrofit.GitHubService;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 工作室详情界面
 */
public class StudioActivity extends BaseActivity implements IStudioView,SwipeRefreshLayout.OnRefreshListener{
  private static final String TAG = "TestFragment";
  StudioPresenter presenter;
  @InjectView(R.id.rv_studio_newslist)
  RecyclerView rv_studio_newslist;
  @InjectView(R.id.collapsing_toolbar)
  CollapsingToolbarLayout collapsingToolbar;
  @InjectView(R.id.iv_studio_logo)
  ImageView iv_studio_logo;
  @InjectView(R.id.tv_studio_name)
  TextView tv_studio_name;
  @InjectView(R.id.toolbar_studio)
  Toolbar toolbar_studio;
  @InjectView(R.id.tv_description)
  TextView tv_description;
  @InjectView(R.id.swipe_container)
  SwipeRefreshLayout refreshLayout;
  @InjectView(R.id.appbar)
  AppBarLayout appBarLayout;
  @InjectView(R.id.bt_subscribe)
  CheckBox bt_subscribe;
  private StudioNewsAdapter adapter;
  @Override
  public void  onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_studio);
    ButterKnife.inject(this);
    initViews();
    initToolBar();
    requestData();
  }

  private void initToolBar() {
    collapsingToolbar.setCollapsedTitleGravity(Gravity.CENTER);
    collapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT);//设置还没收缩时状态下字体颜色
    collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜
  }

  private void initViews() {
//    refreshLayout.setProgressViewOffset(true, -20, 100);
    appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
      @Override
      public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset >= 0) {
          refreshLayout.setEnabled(true);
        } else {
          refreshLayout.setEnabled(false);
        }
      }
    });
    refreshLayout.setOnRefreshListener(this);
    refreshLayout.setColorSchemeResources(R.color.tabTitleSelect);
    //    rv_studio_newslist.setLayoutManager(new LinearLayoutManager(rv_studio_newslist.getContext()));
    rv_studio_newslist.setLayoutManager(new MyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    rv_studio_newslist.setNestedScrollingEnabled(false);
    //这句就是添加我们自定义的分隔线
    rv_studio_newslist.addItemDecoration(new MyHorizontalDividerItemDecoration.Builder(this)
        .colorResId(R.color.line_color)
        .sizeResId(R.dimen.divider)
        .marginResId(R.dimen.margin14, R.dimen.margin14)
        .build());
  }

  private void requestData() {
    presenter = new StudioPresenter(this, GitHubService.getInstance().getRetrofitApi());
    String link = getIntent().getStringExtra("link");
    if(NetWorkUtil.netConnected(this)){
      presenter.getStudioDetaiById(link.substring(1,link.length()));
    }else{
      if(null!=refreshLayout){
        refreshLayout.setRefreshing(false);
      }
      showToast(getResources().getString(R.string.msg_network_error));
    }
  }
  String studioName;
  @Override
  public void setStudioData(final Studio studio) {
    studioName = studio.getName();
    presenter.getState(studioName);
    refreshLayout.setRefreshing(false);
    if(!TextUtils.isEmpty(studio.getLogoFile())) {
      Picasso
          .with(this)
          .load(studio.getLogoFile())
//          .tag("Profile ListView") //参数为 Object
//          .error(R.drawable.u46)
          .into(iv_studio_logo);
    }else{
//      iv_studio_logo.setBackgroundResource(R.drawable.u46);
    }
    tv_studio_name.setText(studio.getName());
    tv_description.setText(studio.getDescription());
    collapsingToolbar.setTitle(studio.getName());

//    setSupportActionBar(toolbar_studio);
//    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//    toolbar_studio.setNavigationOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        onBackPressed();
//      }
//    });
//    getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
    if(adapter == null){
      adapter = new StudioNewsAdapter(this,studio.getList());
      rv_studio_newslist.setAdapter(adapter);
      adapter.setOnItemClickListener(new StudioNewsAdapter.OnItemClickListener() {
        @Override
        public void onClick(int position) {
          if(!studio.getList().get(position).isHasRead()){
            presenter.setReadState(studio.getList().get(position).getArticleId());
          }
          Intent intent = new Intent(StudioActivity.this,ArticleActivity.class);
          intent.putExtra("url", studio.getList().get(position).getUrl());
          startActivity(intent);
        }
      });
    }else{
      adapter.setMlist(studio.getList());
      adapter.notifyDataSetChanged();
    }

  }
  @OnClick(R.id.bt_subscribe)
  void subscribe(){
    presenter.subscribe(studioName);
  }
  @Override
  public void subscribe(boolean isHasSubscribed) {
    bt_subscribe.setChecked(isHasSubscribed);
    bt_subscribe.setText(isHasSubscribed?getResources().getString(R.string.subscribed):getResources().getString(R.string.subscribe));
  }

  @OnClick(R.id.back)
  void back(){
    onBackPressed();
  }
  @Override
  public void setState(boolean isSubscribed) {
    bt_subscribe.setChecked(isSubscribed);
    bt_subscribe.setText(isSubscribed?getResources().getString(R.string.subscribed):getResources().getString(R.string.subscribe));
  }

  @Override
  protected void onResume() {
    super.onResume();
    requestData();
  }

  @Override
  public void onRefresh() {
    requestData();
  }
}
