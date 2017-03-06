package com.pdmi.app.wepage.presenter.impl;

import com.pdmi.app.wepage.model.ArticleItem;
import com.pdmi.app.wepage.model.Studio;
import com.pdmi.app.wepage.model.StudioItem;
import com.pdmi.app.wepage.presenter.IStudioPresenter;
import com.pdmi.app.wepage.ui.view.IStudioView;
import com.pdmi.app.wepage.util.retrofit.RetrofitApi;

import io.realm.Realm;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by guoxiuli on 2017/2/16.
 */
public class StudioPresenter implements IStudioPresenter {
  private IStudioView callback;
  private RetrofitApi retrofitApi;
  private CompositeSubscription mSubScriptions;

  public StudioPresenter(IStudioView callback, RetrofitApi retrofitApi) {
    mSubScriptions = new CompositeSubscription();
    this.callback = callback;
    this.retrofitApi = retrofitApi;
  }

  @Override
  public void getStudioDetaiById(String studioId) {
    callback.showProgressBar();
    Subscription subscription =  retrofitApi.getStudioById(studioId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Studio>() {
          @Override
          public void onCompleted() {

          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onNext(Studio studio) {
            callback.dismissProgressBar();
            checkHasRead(studio);

          }
        });
    mSubScriptions.add(subscription);
}

  //检查文章是否已读并标记
  private void checkHasRead(Studio studio) {
    int count = studio.getList().size();
    Realm mRealm = Realm.getDefaultInstance();
    if(!mRealm.isInTransaction()){
      mRealm.beginTransaction();
    }
    for(int i = 0; i < count; i++){
        //文章是否已读检查数据库
        ArticleItem article = mRealm.where(ArticleItem.class).equalTo("articleId", studio.getList().get(i).getArticleId()).findFirst();
        if(null!=article){
          studio.getList().get(i).setHasRead(true);
      }
    }
    callback.setStudioData(studio);
  }

  @Override
  public void subscribe(String studioName) {
    callback.showProgressBar();
    try {
      Realm mRealm = Realm.getDefaultInstance();
      if(!mRealm.isInTransaction()){
        mRealm.beginTransaction();
      }
      StudioItem item1 = mRealm.where(StudioItem.class).equalTo("subName", studioName).findFirst();
      item1.setHasSubscribed(!item1.isHasSubscribed());
      mRealm.commitTransaction();
      callback.subscribe(item1.isHasSubscribed());
      callback.dismissProgressBar();
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override
  public void getState(String studioName) {
    callback.showProgressBar();
    try {
      Realm mRealm = Realm.getDefaultInstance();
      StudioItem item1 = mRealm.where(StudioItem.class).equalTo("subName", studioName).findFirst();
      callback.setState(item1.isHasSubscribed());
      callback.dismissProgressBar();
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override
  public CompositeSubscription getSubscriptions() {
    return mSubScriptions;
  }

  @Override
  public void startPresenter() {

  }
  @Override
  public void setReadState(String articleId) {
    try {
      Realm mRealm = Realm.getDefaultInstance();
      if(!mRealm.isInTransaction()){
        mRealm.beginTransaction();
      }
      ArticleItem article = mRealm.where(ArticleItem.class).equalTo("articleId", articleId).findFirst();
      boolean isNew = false;
      if (null == article) {
        isNew = true;
        article = new ArticleItem();
      } else {
        isNew = false;
      }
      article.setArticleId(articleId);
      article.setHasRead(true);
      if (isNew) {
        mRealm.copyToRealm(article);
      }
      mRealm.commitTransaction();
    }catch (Exception e){
      e.printStackTrace();
    }
  }
  @Override
  public void stopPresenter() {
    mSubScriptions.clear();
  }
}
