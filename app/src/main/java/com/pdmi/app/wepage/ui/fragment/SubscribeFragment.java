package com.pdmi.app.wepage.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pdmi.app.wepage.R;
import com.pdmi.app.wepage.model.StudioItem;
import com.pdmi.app.wepage.model.StudioList;
import com.pdmi.app.wepage.presenter.impl.StudioListPresenter;
import com.pdmi.app.wepage.ui.activity.StudioActivity;
import com.pdmi.app.wepage.ui.activity.SubscribeListActivity;
import com.pdmi.app.wepage.ui.adapter.SubscribeStudiosAdapter;
import com.pdmi.app.wepage.ui.view.IStudioListView;
import com.pdmi.app.wepage.util.NetWorkUtil;
import com.pdmi.app.wepage.util.retrofit.GitHubService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 订阅界面
 */
public class SubscribeFragment extends BaseFragment implements IStudioListView,SwipeRefreshLayout.OnRefreshListener{
  private static final String TAG = "TestFragment";
  private StudioListPresenter presenter;
  @InjectView(R.id.rv_studio)
  RecyclerView rv_studios;
  @InjectView(R.id.toolbar_subscribe)
  Toolbar toolbar;
//  @InjectView(R.id.add_subcribe_list)
//  TextView add_subcribe_list;
  @InjectView(R.id.swipe_container)
  SwipeRefreshLayout refreshLayout;
  private SubscribeStudiosAdapter adapter;
  public static SubscribeFragment newInstance(String s) {
    SubscribeFragment newFragment = new SubscribeFragment();
    Bundle bundle = new Bundle();
    bundle.putString("hello", s);
    newFragment.setArguments(bundle);
    return newFragment;
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_subscribe, container, false);
//    initToolbar();
    ButterKnife.inject(this,rootView);
    initViews();
    requestData();
    return rootView;
  }

  private void initViews() {
    refreshLayout.setOnRefreshListener(this);
    refreshLayout.setColorSchemeResources(R.color.tabTitleSelect);
    rv_studios.setLayoutManager(new LinearLayoutManager(getContext()));
  }

  private void requestData() {
    presenter = new StudioListPresenter(this, GitHubService.getInstance().getRetrofitApi());
    if(NetWorkUtil.netConnected(getContext())){
      presenter.getStudioList();
    }else{
      if(null!=refreshLayout){
        refreshLayout.setRefreshing(false);
      }
      showToast(getResources().getString(R.string.msg_network_error));
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    requestData();
  }

  @OnClick(R.id.add_subcribe_list)
  void addSubcribes(){
    Intent intent = new Intent(getActivity(),SubscribeListActivity.class);
    //https://original.hubpd.com/json/gzs/ybzj/
//    intent.putExtra("link", studios.getList().get(position).getLink());
    startActivity(intent);
//    Toast.makeText(getActivity(), "OnClick addSubcribes", Toast.LENGTH_SHORT).show();
//    FragmentManager fm = getChildFragmentManager();
//    FragmentTransaction ft = fm.beginTransaction();
//    SubscribeListFragment subscribeListFragment = SubscribeListFragment.newInstance("haha");
//    if (subscribeListFragment == null) {
//      subscribeListFragment = SubscribeListFragment.newInstance("haha");
//      ft.add(R.id.container, subscribeListFragment, null);
//    } else {
//      ft.show(subscribeListFragment);
//    }
//    ft.commit();
  }
  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setHasOptionsMenu(true);
  }
//  @Override
//  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//    super.onCreateOptionsMenu(menu, inflater);
//    menu.clear();
//    inflater.inflate(R.menu.menu_subscribe_add, menu);
//    MenuItem addItem = menu.findItem(R.id.menu_add);
//    addItem.setIcon(R.drawable.add);
//  }
//
//  @Override
//  public boolean onOptionsItemSelected(MenuItem item) {
//    switch (item.getItemId()) {
//      case R.id.menu_add:
//        //完成创建新事件 事件
//        SubscribeListFragment subscribeListFragment = SubscribeListFragment.newInstance("haha");
//        FragmentManager fm = getFragmentManager();
//        fm.beginTransaction().add(R.id.container, subscribeListFragment, null).commit();
//
//// note getSupportFragmentManager() instead getFragmentManager()
//        break;
//    }
//    return super.onOptionsItemSelected(item);
//  }

  @Override
  public void setStudioList(final StudioList studios) {
    refreshLayout.setRefreshing(false);
    if(adapter == null){
      adapter = new SubscribeStudiosAdapter(getActivity(),studios.getList());
      rv_studios.setAdapter(adapter);
    } else {
      adapter.setDatas(studios.getList());
      adapter.notifyDataSetChanged();
    }

    adapter.setSubscribeListener(new SubscribeStudiosAdapter.OnSubscribeListener(){
      @Override
      public void onClick(int position, boolean checked) {
         presenter.subscribe(studios.getList().get(position));
      }
    });
    adapter.setOnItemClickListener(new SubscribeStudiosAdapter.OnItemClickListener() {
      @Override
      public void onClick(int position) {
        Intent intent = new Intent(getActivity(),StudioActivity.class);
        //https://original.hubpd.com/json/gzs/ybzj/
        intent.putExtra("link", studios.getList().get(position).getLink());
        startActivity(intent);
      }
    });
  }

  @Override
  public void subscribe(StudioItem item) {
  }

  @Override
  protected void lazyLoad() {
    requestData();
  }

  @Override
  public void onRefresh() {
    requestData();
  }
//  public Toolbar initToolbar() {
//    AppCompatActivity mAppCompatActivity = (AppCompatActivity) getActivity();
//
//    mAppCompatActivity.setSupportActionBar(toolbar);
//    ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
//    if (actionBar != null) {
//      actionBar.setDisplayHomeAsUpEnabled(false);
//      actionBar.setDisplayShowTitleEnabled(false);
//    }
//    return toolbar;
//  }
}
