package com.pdmi.app.wepage.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pdmi.app.wepage.R;
import com.pdmi.app.wepage.model.IndexItem;
import com.pdmi.app.wepage.presenter.impl.IndexListPresenter;
import com.pdmi.app.wepage.ui.activity.ArticleActivity;
import com.pdmi.app.wepage.ui.activity.StudioActivity;
import com.pdmi.app.wepage.ui.adapter.IndexMultipleItemAdapter;
import com.pdmi.app.wepage.ui.view.IIndexView;
import com.pdmi.app.wepage.ui.widget.XCRecyclerView;
import com.pdmi.app.wepage.util.Constant;
import com.pdmi.app.wepage.util.NetWorkUtil;
import com.pdmi.app.wepage.util.retrofit.GitHubService;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 首页
 */
public class IndexFragment extends BaseFragment implements IIndexView,SwipeRefreshLayout.OnRefreshListener{
  @InjectView(R.id.rv_list)
  XCRecyclerView rv_list;
  @InjectView(R.id.swipe_container)
  SwipeRefreshLayout refreshLayout;
  IndexListPresenter presenter;
  private IndexMultipleItemAdapter adapter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_index, container, false);//关联布局文件
    ButterKnife.inject(this,rootView);
    initViews();
    requestData();
    return rootView;
  }

  private void initViews() {
    refreshLayout.setOnRefreshListener(this);
    refreshLayout.setColorSchemeResources(R.color.tabTitleSelect);
    rv_list.setNestedScrollingEnabled(false);
    rv_list.setLayoutManager(new LinearLayoutManager(rv_list.getContext()));
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.reset(this);
  }

  @Override
  public void setIndexList(final List<IndexItem> items) {
    refreshLayout.setRefreshing(false);
    if(adapter == null){
      adapter = new IndexMultipleItemAdapter(getActivity(),items,null);
    } if(rv_list.getAdapter() == null){
//      Log.e("~~~~~~~~~","getAdapter is null:");
      rv_list.setAdapter(adapter);
    } else if(adapter != null){
        adapter.setMlist1(items);
//        Log.e("~~~~~~~~~","items:"+items.size());
        rv_list.setHasFixedSize(true);//最重要的这句
//      adapter.notifyItemInserted(adapter.getItemCount());
    }
    adapter.setStudioClickListener(new IndexMultipleItemAdapter.OnStudioClickListener() {
      @Override
      public void onClick(int position) {
//        StudioFragment studioFragment = null;
//        if (studioFragment == null) {
//          studioFragment = StudioFragment.newInstance("haha");
//        }
//        if (!studioFragment.isAdded()) {
//          FragmentManager fm = getChildFragmentManager();
//          fm.beginTransaction().replace(R.id.container, studioFragment, null).commit();
//        }
        Intent intent = new Intent(getActivity(),StudioActivity.class);
        //https://original.hubpd.com/json/gzs/ybzj/
        intent.putExtra("link", items.get(position).getLink());
        startActivity(intent);
      }
    });
    adapter.setNewsClickListener(new IndexMultipleItemAdapter.OnNewsClickListener() {
      @Override
      public void onClick(int position, int index) {
        //设置已读样式
        if(!items.get(position).getIndexItemList().get(index).isHasRead()){
          presenter.setReadState(items.get(position).getIndexItemList().get(index).getArticleId());
        }
        //跳转到新闻详情页面
        Intent intent = new Intent(getActivity(),ArticleActivity.class);
        //https://original.hubpd.com/json/gzs/ybzj/
        intent.putExtra("url", items.get(position).getIndexItemList().get(index).getUrl());
        startActivity(intent);
      }
    });
  }

  @Override
  protected void lazyLoad() {
    requestData();
  }

  @Override
  public void onResume() {
    super.onResume();
    requestData();
  }

  @Override
  public void onRefresh() {
    requestData();
  }

  public void requestData() {
    presenter = new IndexListPresenter(this, GitHubService.getInstance().getRetrofitApi());
    if(NetWorkUtil.netConnected(getContext())){
      presenter.getIndexList();
    }else{
      if(null!=refreshLayout){
        refreshLayout.setRefreshing(false);
      }
      showToast(getResources().getString(R.string.msg_network_error));
    }
  }
}
