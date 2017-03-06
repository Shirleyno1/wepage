package com.pdmi.app.wepage.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.pdmi.app.wepage.R;
import com.pdmi.app.wepage.model.StudioItem;

import java.util.List;

/**
 * 整齐摆放动态item
* Created by guoxiuli on 2017/3/3.
 */

public class SearchTipsGroupView extends LinearLayout {

    private Context context;
    private static int pageMarginTop;
    private static int pageMarginLeft;
    private static int itemHeight;
    private static int screenWidth;
    //小控件最多显示字数
    private static final int MAX_COUNT = 6;

    public SearchTipsGroupView(Context context) {
        super(context);
        this.context = context;
        setOrientation(VERTICAL);//设置方向
        //设置margintop

    }

    public SearchTipsGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setOrientation(VERTICAL);//设置方向
    }

    public SearchTipsGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setOrientation(VERTICAL);//设置方向
    }
    int itemWidth;
    /**
     * 外部接口调用
     *
     * @param items
     * @param onItemClick
     */
    public void initViews(List<StudioItem> items, final OnItemClick onItemClick) {
        screenWidth = getScreenWidth();//屏幕的宽度
        pageMarginTop = ((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()));
        pageMarginLeft = ((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()));
        itemWidth = getItemWidth();
        int itemLong = 0;
        int length = 0;//一行加载item 的宽度

        LinearLayout layout = null;

        LayoutParams layoutLp = null;

        boolean isNewLine = true;//是否换行
        int size = items.size();
        for (int i = 0; i < size; i++) {//便利items
            if (isNewLine) {//是否开启新的一行
                layout = new LinearLayout(context);
                layoutLp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutLp.topMargin = pageMarginTop;
            }

            View view = LayoutInflater.from(context).inflate(R.layout.item_textview, null);
            final CheckBox itemView = (CheckBox) view.findViewById(R.id.text);
            itemView.setText(items.get(i).getSubName());
            itemView.setChecked(items.get(i).isHasSubscribed());

            final int j = i;
            itemView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {//给每个item设置点击事件
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (null != onItemClick) {
                        onItemClick.onClick(j,isChecked);
                    }
                }
            });
            //设置item的参数
            LayoutParams itemLp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            itemLp.leftMargin = pageMarginLeft;
            if(itemHeight == 0){
                itemHeight = ((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36, getResources().getDisplayMetrics()));
            }
            itemLp.height =itemHeight;
            if(itemView.getText().length() <= MAX_COUNT){
                itemLp.width = itemWidth;
            }else{
                if(itemLong == 0){
                    itemLong = getItemLong();
                }
                itemLp.width = itemLong;
            }

            //得到当前行的长度
//            length += pageMarginLeft + getViewWidth(itemView);
            length += pageMarginLeft + itemLp.width;
            if (length + pageMarginLeft > screenWidth) {//当前行的长度大于屏幕宽度则换行
                length = 0;
                addView(layout, layoutLp);
                isNewLine = true;
                i--;
            } else {//否则添加到当前行
                isNewLine = false;
                layout.addView(view, itemLp);
            }
        }
        addView(layout, layoutLp);
    }

    private int getItemLong() {
        return screenWidth-(pageMarginLeft*3+itemWidth);
    }

    private int getItemWidth() {
        return (screenWidth-pageMarginLeft*4)/3;
    }

//    /**
//     * @param items
//     * @param onItemClick
//     */
//    public void initViews(List<String> items, OnItemClick onItemClick) {
//        initViews((String[]) items.toArray(), onItemClick);
//    }

    /**
     * 得到手机屏幕的宽度
     *
     * @return
     */
    private int getScreenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 得到view控件的宽度
     *
     * @param view
     * @return
     */
    private int getViewWidth(View view) {
        int w = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        int h = MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return view.getMeasuredWidth();
    }
    public void clear(){
        removeAllViews();
    }
}
