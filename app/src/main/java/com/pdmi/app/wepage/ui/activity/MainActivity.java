package com.pdmi.app.wepage.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pdmi.app.wepage.R;
import com.pdmi.app.wepage.ui.adapter.MyFragmentPagerAdapter;
import com.pdmi.app.wepage.ui.fragment.IndexFragment;
import com.pdmi.app.wepage.ui.fragment.MeFragment;
import com.pdmi.app.wepage.ui.fragment.SubscribeFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends FragmentActivity {

  private ArrayList<Fragment> fragmentList;

  private int currIndex;//当前页卡编号
  @InjectView(R.id.viewpager)
  ViewPager mPager;
  @InjectView(R.id.tab1)
  LinearLayout view1;
  @InjectView(R.id.tab2)
  LinearLayout view2;
  @InjectView(R.id.tab3)
  LinearLayout view3;
  @InjectView(R.id.iv_first)
  ImageView iv_first;
  @InjectView(R.id.iv_subcr)
  ImageView iv_subcr;
  @InjectView(R.id.iv_me)
  ImageView iv_me;
  @InjectView(R.id.tv_guid1)
  TextView tv_first;
  @InjectView(R.id.tv_guid2)
  TextView tv_subscribe;
  @InjectView(R.id.tv_guid3)
  TextView tv_me;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
//    initToolbar();
    ButterKnife.inject(this);

//    RecyclerListView rv;
    InitTabView();
//    InitImage();
    InitViewPager();
  }


  /*
   * 初始化标签名
   */
  public void InitTabView(){
    view1.setOnClickListener(new txListener(0));
    view2.setOnClickListener(new txListener(1));
    view3.setOnClickListener(new txListener(2));
  }

  public class txListener implements View.OnClickListener{
    private int index=0;

    public txListener(int i) {
      index =i;
    }
    @Override
    public void onClick(View v) {
      // TODO Auto-generated method stub
      mPager.setCurrentItem(index);
    }
  }
private void setState(int index){
  switch (index){
    case 0:
      tv_first.setTextColor(getResources().getColor(R.color.tabTitleSelect));
      tv_subscribe.setTextColor(getResources().getColor(R.color.tabTitle));
      tv_me.setTextColor(getResources().getColor(R.color.tabTitle));
      iv_first.setBackgroundResource(R.drawable.tab1_selected);
      iv_subcr.setBackgroundResource(R.drawable.tab2);
      iv_me.setBackgroundResource(R.drawable.tab3);
      break;
    case 1:
      tv_first.setTextColor(getResources().getColor(R.color.tabTitle));
      tv_subscribe.setTextColor(getResources().getColor(R.color.tabTitleSelect));
      tv_me.setTextColor(getResources().getColor(R.color.tabTitle));
      iv_first.setBackgroundResource(R.drawable.tab1);
      iv_subcr.setBackgroundResource(R.drawable.tab2_selected);
      iv_me.setBackgroundResource(R.drawable.tab3);
      break;
    case 2:
      tv_first.setTextColor(getResources().getColor(R.color.tabTitle));
      tv_subscribe.setTextColor(getResources().getColor(R.color.tabTitle));
      tv_me.setTextColor(getResources().getColor(R.color.tabTitleSelect));
      iv_first.setBackgroundResource(R.drawable.tab1);
      iv_subcr.setBackgroundResource(R.drawable.tab2);
      iv_me.setBackgroundResource(R.drawable.tab3_selected);
      break;
  }
}

//  /*
//   * 初始化图片的位移像素
//   */
//  public void InitImage(){
//    image = (ImageView)findViewById(R.id.cursor);
//    bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.cursor).getWidth();
//    DisplayMetrics dm = new DisplayMetrics();
//    getWindowManager().getDefaultDisplay().getMetrics(dm);
//    int screenW = dm.widthPixels;
//    offset = (screenW/4 - bmpW)/2;
//
//    //imgageview设置平移，使下划线平移到初始位置（平移一个offset）
//    Matrix matrix = new Matrix();
//    matrix.postTranslate(offset, 0);
//    image.setImageMatrix(matrix);
//  }

  /*
   * 初始化ViewPager
   */
  public void InitViewPager(){
//    mPager = (ViewPager)findViewById(R.id.viewpager);
    fragmentList = new ArrayList<Fragment>();
    Fragment btFragment= new IndexFragment();
    Fragment secondFragment = SubscribeFragment.newInstance("this is second fragment");
    Fragment thirdFragment = MeFragment.newInstance("this is third fragment");
    fragmentList.add(btFragment);
    fragmentList.add(secondFragment);
    fragmentList.add(thirdFragment);

    //给ViewPager设置适配器
    mPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
    mPager.setCurrentItem(0);//设置当前显示标签页为第一页
    setState(0);
    mPager.addOnPageChangeListener(new MyOnPageChangeListener());//页面变化时的监听器
  }


  public class MyOnPageChangeListener implements OnPageChangeListener{
//    private int one = offset *2 +bmpW;//两个相邻页面的偏移量

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
      // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
      // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(int arg0) {
      setState(arg0);
      // TODO Auto-generated method stub
//      Animation animation = new TranslateAnimation(currIndex*one,arg0*one,0,0);//平移动画
      currIndex = arg0;
//      animation.setFillAfter(true);//动画终止时停留在最后一帧，不然会回到没有执行前的状态
//      animation.setDuration(200);//动画持续时间0.2秒
//      image.startAnimation(animation);//是用ImageView来显示动画的
//      int i = currIndex + 1;
//      Toast.makeText(MainActivity.this, "您选择了第"+i+"个页卡", Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_subscribe_add, menu);
    return super.onCreateOptionsMenu(menu);
  }
  @Override
  protected void onDestroy() {
    super.onDestroy();
    ButterKnife.reset(this);//解除绑定，官方文档只对fragment做了解绑
  }
//  public Toolbar initToolbar() {
//    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//    setSupportActionBar(toolbar);
//    ActionBar actionBar = getSupportActionBar();
//    if (actionBar != null) {
//      actionBar.setDisplayHomeAsUpEnabled(true);
//    }
//    return toolbar;
//  }
  @Override
  public void onBackPressed() {
    if(getSupportFragmentManager().getBackStackEntryCount()>0){
      getSupportFragmentManager().popBackStackImmediate();
    }else {
        super.onBackPressed();
    }
  }
}