package com.pdmi.app.wepage.util.retrofit;

import com.pdmi.app.wepage.model.Index;
import com.pdmi.app.wepage.model.Studio;
import com.pdmi.app.wepage.model.StudioList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by guoxiuli on 2017/2/16.
 */
public interface RetrofitApi {
    //获取工作室详情
    @GET("{link}")
    Observable<Studio> getStudioById(@Path("link") String link);

    //获取工作室列表
    @GET("json/gzsapp/")
    Observable<StudioList> getStudioList();
    //获取首页列表
    @GET("json/")
    Observable<Index> getIndexList();

}
