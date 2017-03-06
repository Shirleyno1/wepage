package com.pdmi.app.wepage.presenter.impl;

import com.pdmi.app.wepage.model.StudioItem;
import com.pdmi.app.wepage.model.StudioList;
import com.pdmi.app.wepage.presenter.IStudioList2Presenter;
import com.pdmi.app.wepage.ui.view.IStudioList2View;
import com.pdmi.app.wepage.util.retrofit.RetrofitApi;

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
public class StudioList2Presenter implements IStudioList2Presenter {
  private IStudioList2View mView;
  private CompositeSubscription mSubScriptions;
  private RetrofitApi retrofitApi;

  public StudioList2Presenter(IStudioList2View callback, RetrofitApi retrofitApi) {
    mSubScriptions = new CompositeSubscription();
    this.mView = callback;
    this.retrofitApi = retrofitApi;
  }

  @Override
  public void getStudioList() {
    mView.showProgressBar();
    Subscription subscription = retrofitApi.getStudioList()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<StudioList>() {
          @Override
          public void onCompleted() {

          }

          @Override
          public void onError(Throwable e) {
            mView.dismissProgressBar();

          }

          @Override
          public void onNext(StudioList studio) {
            mView.dismissProgressBar();
            mView.setStudioList(checkLocalDatas(studio));
          }
        });
    mSubScriptions.add(subscription);
  }

  @Override
  public void subscribe(List<StudioItem> list) {
    mView.showProgressBar();
    try {
      Realm mRealm = Realm.getDefaultInstance();
      if(!mRealm.isInTransaction()){
        mRealm.beginTransaction();
      }
      int count = list.size();
      for(int i = 0; i<count; i++){
        StudioItem item1 = mRealm.where(StudioItem.class).equalTo("id", list.get(i).getId()).findFirst();
        item1.setHasSubscribed(list.get(i).isHasSubscribed());
      }
      mRealm.commitTransaction();
      mView.dismissProgressBar();
      mView.subscribe();
    }catch (Exception e){
      mView.dismissProgressBar();
      e.printStackTrace();
    }
  }

  private StudioList checkLocalDatas(StudioList studios) {
    try {
      Realm mRealm = Realm.getDefaultInstance();
      if(!mRealm.isInTransaction()){
        mRealm.beginTransaction();
      }
      int count = studios.getList().size();
      boolean isNew;
      for (int i = 0; i < count; i++) {
        StudioItem item = mRealm.where(StudioItem.class).equalTo("id", studios.getList().get(i).getId()).findFirst();
        if (null == item) {
          isNew = true;
          item = new StudioItem();
        } else {
          isNew = false;
        }
        item.setAlias(studios.getList().get(i).getAlias());
        item.setChildCount(studios.getList().get(i).getChildCount());
        item.setLink(studios.getList().get(i).getLink());
        item.setSubName(studios.getList().get(i).getSubName());
        item.setId(studios.getList().get(i).getId());
        if (isNew) {
          mRealm.copyToRealm(item);
        }

        studios.getList().get(i).setHasSubscribed(item.isHasSubscribed());
      }
      mRealm.commitTransaction();
      mView.dismissProgressBar();
    }catch (Exception e){
      mView.dismissProgressBar();
      e.printStackTrace();
    }
    return studios;
  }

  @Override
  public CompositeSubscription getSubscriptions() {
    return mSubScriptions;
  }

  @Override
  public void startPresenter() {

  }

  @Override
  public void stopPresenter() {
    mSubScriptions.clear();
  }
}
