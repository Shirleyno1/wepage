package com.pdmi.app.wepage.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.pdmi.app.wepage.R;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by guoxiuli on 2017/2/22.
 */

public class ArticleActivity extends Activity {
  @InjectView(R.id.web)
  WebView webView;
  private String url;
  private boolean hasLoad;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_article);
    ButterKnife.inject(this);
    ShareSDK.initSDK(this);
    url = getIntent().getStringExtra("url");
    init();
  }

  private void init(){
    //WebView加载web资源
    WebSettings settings = webView.getSettings();
    //启用JS
    settings.setJavaScriptEnabled(true);
    webView.loadUrl(url);
    //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
    webView.setWebChromeClient(new WebChromeClient() {
      @Override
      public void onProgressChanged(final WebView view, int newProgress) {
//        super.onProgressChanged(view, newProgress);
        if (!hasLoad&&newProgress >= 60){
          Log.e("~~~~","newProgress >= 60:"+url);
          view.post(new Runnable() {
          @Override
          public void run() {
            hasLoad = true;
            view.loadUrl("JavaScript:function setTop(){document.querySelector('.hot-news').style.display=\"none\";document.querySelector('.fixed-nav').style.display=\"none\";document.querySelector('.m-t-md').style.display=\"none\";}setTop();");
          }
        });
        }
      }


//      @Override
//      public boolean shouldOverrideUrlLoading(WebView view, String url) {
//        // TODO Auto-generated method
//        //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
//        view.loadUrl(url);
//        return true;
//      }
//
//      @Override
//      public void onPageFinished(final WebView view, String url) {
//        Log.e("~~~~","url:"+url);
//        view.post(new Runnable() {
//          @Override
//          public void run() {
//            view.loadUrl("JavaScript:function setTop(){document.querySelector('.hot-news').style.display=\"none\";document.querySelector('.fixed-nav').style.display=\"none\";}setTop();");
//          }
//        });
//      }
    });
  }
  @OnClick(R.id.back)
  void back(){
    finish();
  }
  @OnClick(R.id.tv_share)
  void share() {
//    ShareSDK.initSDK(this);
    OnekeyShare oks = new OnekeyShare();
    //关闭sso授权
    oks.disableSSOWhenAuthorize();
    // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
    oks.setTitle(getResources().getString(R.string.app_name));
    // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
    // oks.setTitleUrl("http://weixin.pdmi.cn/crt-reporter-data/");
    // text是分享文本，所有平台都需要这个字段
    oks.setText("人民日报中央厨房稿件传播效果评估系统带你追踪稿件");
    //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
    oks.setImageUrl("http://weixin.pdmi.cn/crt-reporter-data/images/LOGO.png");
    // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
    //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
    // url仅在微信（包括好友和朋友圈）中使用
    oks.setUrl(url);
    // comment是我对这条分享的评论，仅在人人网和QQ空间使用
    // oks.setComment("我是测试评论文本");
    // site是分享此内容的网站名称，仅在QQ空间使用
    //oks.setSite("pdmi");
    // siteUrl是分享此内容的网站地址，仅在QQ空间使用
    //oks.setSiteUrl("http://weixin.pdmi.cn/crt-reporter-data/");



    oks.setCallback(new PlatformActionListener() {
      @Override
      public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        runOnUiThread(new Runnable() {
          @Override
          public void run() {
            Toast.makeText(ArticleActivity.this, "onComplete", Toast.LENGTH_LONG).show();
          }
        });
      }

      @Override
      public void onError(Platform platform, int i, final Throwable throwable) {
        runOnUiThread(new Runnable() {
          @Override
          public void run() {
            Toast.makeText(ArticleActivity.this, "onError"+throwable.toString(), Toast.LENGTH_LONG).show();
          }
        });
      }


      @Override
      public void onCancel(Platform platform, int i) {
        runOnUiThread(new Runnable() {
          @Override
          public void run() {
            Toast.makeText(ArticleActivity.this, "onCancel", Toast.LENGTH_LONG).show();
          }
        });
      }
    });

// 启动分享GUI
    oks.show(this);
  }
}
