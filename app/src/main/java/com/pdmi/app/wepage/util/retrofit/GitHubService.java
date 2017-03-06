package com.pdmi.app.wepage.util.retrofit;

import com.pdmi.app.wepage.util.UrlCst;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by guoxiuli
 */
public class GitHubService {
    private GitHubService() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(UrlCst.BASE_URL)
                .build();

        retrofitApi = retrofit.create(RetrofitApi.class);
    }
    private static GitHubService sInstance = null;

    private RetrofitApi retrofitApi;
    public static GitHubService getInstance() {
        if (sInstance == null) {
            sInstance = new GitHubService();
        }
        return sInstance;
    }
    public RetrofitApi getRetrofitApi() {
        return retrofitApi;
    }
}
