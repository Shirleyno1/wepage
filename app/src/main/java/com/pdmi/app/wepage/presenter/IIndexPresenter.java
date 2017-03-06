package com.pdmi.app.wepage.presenter;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by guoxiuli on 2017/2/16.
 */

public interface IIndexPresenter extends BasePresenter {
  //获取首页列表
   public void getIndexList();
//   public void subscribe(StudioItem item);
  CompositeSubscription getSubscriptions();

  public void setReadState(String articleId);
}
