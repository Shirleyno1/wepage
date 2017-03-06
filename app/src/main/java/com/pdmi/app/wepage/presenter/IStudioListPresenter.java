package com.pdmi.app.wepage.presenter;

import com.pdmi.app.wepage.model.StudioItem;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by guoxiuli on 2017/2/16.
 */

public interface IStudioListPresenter extends BasePresenter {
  //获取工作室列表
   public void getStudioList();
   public void subscribe(StudioItem item);
  CompositeSubscription getSubscriptions();
}
