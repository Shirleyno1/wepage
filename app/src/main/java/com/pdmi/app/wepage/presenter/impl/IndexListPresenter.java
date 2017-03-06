package com.pdmi.app.wepage.presenter.impl;

import com.pdmi.app.wepage.model.ArticleItem;
import com.pdmi.app.wepage.model.Index;
import com.pdmi.app.wepage.model.IndexItem;
import com.pdmi.app.wepage.model.StudioItem;
import com.pdmi.app.wepage.presenter.IIndexPresenter;
import com.pdmi.app.wepage.ui.view.IIndexView;
import com.pdmi.app.wepage.util.retrofit.RetrofitApi;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by guoxiuli on 2017/2/16.
 */
public class IndexListPresenter implements IIndexPresenter {
  private IIndexView mView;
  private CompositeSubscription mSubScriptions;
  private RetrofitApi retrofitApi;

  public IndexListPresenter(IIndexView callback, RetrofitApi retrofitApi) {
    mSubScriptions = new CompositeSubscription();
    this.mView = callback;
    this.retrofitApi = retrofitApi;
//    mView.dismissProgressBar();
  }

  @Override
  public void getIndexList() {
//    mView.showProgressBar();
    Subscription subscription = retrofitApi.getIndexList()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Index>() {
          @Override
          public void onCompleted() {

          }

          @Override
          public void onError(Throwable e) {
          }

          @Override
          public void onNext(Index index) {
//            mView.dismissProgressBar();
            checkLocalDatas(index.getList());
          }
        });
    mSubScriptions.add(subscription);
  }

  @Override
  public CompositeSubscription getSubscriptions() {
    return mSubScriptions;
  }

  @Override
  public void setReadState(String articleId) {
    try {
      Realm mRealm = Realm.getDefaultInstance();
      if(!mRealm.isInTransaction()){
        mRealm.beginTransaction();
      }
      boolean isNew = false;
      ArticleItem article = mRealm.where(ArticleItem.class).equalTo("articleId", articleId).findFirst();
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
  public void startPresenter() {

  }

  @Override
  public void stopPresenter() {
    mSubScriptions.clear();
  }
  private void checkLocalDatas(List<IndexItem> items) {
    List<IndexItem> mItems = new ArrayList<IndexItem>();
    //如果没有任何订阅，默认学习大国
    IndexItem xxdg = null;
    try {
      Realm mRealm = Realm.getDefaultInstance();
      if(!mRealm.isInTransaction()){
        mRealm.beginTransaction();
      }
      int count = items.size();
      for (int i = 0; i < count; i++) {
        StudioItem item = mRealm.where(StudioItem.class).equalTo("id", items.get(i).getId()).findFirst();
        if(item.getSubName().equals("学习大国"))
          xxdg = items.get(i);
        if (null != item && item.isHasSubscribed()) {
          mItems.add(items.get(i));
        }
        int count2 = items.get(i).getIndexItemList().size();
        for(int j = 0; j < count2; j++){
          //文章是否已读检查数据库
          ArticleItem article = mRealm.where(ArticleItem.class).equalTo("articleId", items.get(i).getIndexItemList().get(j).getArticleId()).findFirst();
          if(null!=article){
            items.get(i).getIndexItemList().get(j).setHasRead(true);
          }
        }
      }
      mRealm.commitTransaction();
    }catch (Exception e){
      e.printStackTrace();
    }
    //如果已订阅，显示订阅列表
    if(mItems.size() == 0){
      mItems.add(xxdg);
    }
    mView.setIndexList(mItems);
  }
}
