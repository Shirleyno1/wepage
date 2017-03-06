package com.pdmi.app.wepage.presenter;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by guoxiuli on 2017/2/16.
 */

public interface IStudioPresenter extends BasePresenter {
  //获取工作室详情
   public void getStudioDetaiById(String studioId);
   public void subscribe(String studioName);
  public void getState(String studioName);
  public void setReadState(String articleId);

   CompositeSubscription getSubscriptions();
}
