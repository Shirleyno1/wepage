package com.pdmi.app.wepage.ui.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.pdmi.app.wepage.R;
import com.pdmi.app.wepage.model.IndexItem;
import com.pdmi.app.wepage.ui.widget.NoDoubleClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by guoxiuli on 2016/6/1.
 */
public class IndexMultipleItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private List<IndexItem> mlist1 = new ArrayList<IndexItem>();
    private List mlist2;
    //列表分割线位置
//    private int sepIndex;
    private final static int ITEM_TYPE1 = 1;
    private final static int ITEM_SEPERATR = 2;
    private final static int ITEM_TYPE2 = 3;

    public IndexMultipleItemAdapter(Context mContext, List list1, List list2) {
        this.mContext = mContext;
        this.mlist1 = list1;
//        sepIndex = mlist1.size();
        this.mlist2 = list2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = null;
        switch (viewType){
            case ITEM_TYPE1:
                return new ViewHolder1(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_index_part11_item, parent, false));
            case ITEM_TYPE2:
               return new ViewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_index_part2_item, parent, false));
            case ITEM_SEPERATR:
               return new ViewHolder3(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_index_seperator_item, parent, false));
        }
        return new ViewHolder3(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_index_seperator_item, parent, false));
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder1) {
            final View view = ((ViewHolder1)holder).mView;
            if(mlist1!=null&&mlist1.size()>0&&!TextUtils.isEmpty(mlist1.get(position).getLogoFile())){
                Picasso
                    .with(mContext)
                    .load(mlist1.get(position).getLogoFile())
                    .tag("Profile ListView") //参数为 Object
//                    .error(R.drawable.u6)
                    .into(((ViewHolder1)holder).iv_subscrib_logo);
            }
//            else
//                ((ViewHolder1)holder).iv_subscrib_logo.setBackgroundResource(R.drawable.u6);
            ((ViewHolder1)holder).tv_subscribe_name.setText(mlist1.get(position).getSubName());
            if(null!=mlist1.get(position).getIndexItemList()&&mlist1.get(position).getIndexItemList().size()>0) {
                if(!TextUtils.isEmpty(mlist1.get(position).getIndexItemList().get(0).getLogoFile())) {
                    Picasso
                        .with(mContext)
                        .load(mlist1.get(position).getIndexItemList().get(0).getLogoFile())
                        .tag("Profile ListView") //参数为 Object
//                        .error(R.drawable.u183)
                        .into(((ViewHolder1) holder).iv_item1);
                }
                ((ViewHolder1) holder).tv_item1.setText(mlist1.get(position).getIndexItemList().get(0).getTitle());
//                ((ViewHolder1) holder).tv_author1.setText(mlist1.get(position).getIndexItemList().get(0).getAuthor());
//                ((ViewHolder1) holder).tv_date1.setText(mlist1.get(position).getIndexItemList().get(0).getPublishDate());
                ((ViewHolder1) holder).ll_item1.setVisibility(View.VISIBLE);
                ((ViewHolder1) holder).line2.setVisibility(View.VISIBLE);

                switch (mlist1.get(position).getIndexItemList().size()){
                    case 1:
                        ((ViewHolder1) holder).ll_item2.setVisibility(View.GONE);
                        ((ViewHolder1) holder).ll_item3.setVisibility(View.GONE);
                        ((ViewHolder1) holder).line1.setVisibility(View.GONE);
                        break;
                    case 2:
                        ((ViewHolder1) holder).ll_item2.setVisibility(View.VISIBLE);
                        ((ViewHolder1) holder).ll_item3.setVisibility(View.GONE);
                        if(!TextUtils.isEmpty(mlist1.get(position).getIndexItemList().get(1).getLogoFile())) {
                            Picasso
                                .with(mContext)
                                .load(mlist1.get(position).getIndexItemList().get(1).getLogoFile())
                                .tag("Profile ListView") //参数为 Object
//                                .error(R.drawable.u183)
                                .into(((ViewHolder1) holder).iv_item2);
                        }
                        if(mlist1.get(position).getIndexItemList().get(1).isHasRead()){
                            ((ViewHolder1) holder).tv_item2.setTextColor(mContext.getResources().getColor(R.color.text_read_color));
                        }else{
                            ((ViewHolder1) holder).tv_item2.setTextColor(mContext.getResources().getColor(R.color.text_color));
                        }
                        ((ViewHolder1) holder).tv_item2.setText(mlist1.get(position).getIndexItemList().get(1).getTitle());
//                        ((ViewHolder1) holder).tv_author2.setText(mlist1.get(position).getIndexItemList().get(1).getAuthor());
//                        ((ViewHolder1) holder).tv_date2.setText(mlist1.get(position).getIndexItemList().get(1).getPublishDate());
                        ((ViewHolder1) holder).line1.setVisibility(View.GONE);
                        break;
                    default:
                        ((ViewHolder1) holder).ll_item2.setVisibility(View.VISIBLE);
                        ((ViewHolder1) holder).ll_item3.setVisibility(View.VISIBLE);
                        if(!TextUtils.isEmpty(mlist1.get(position).getIndexItemList().get(1).getLogoFile())) {
                                Picasso
                                    .with(mContext)
                                    .load(mlist1.get(position).getIndexItemList().get(1).getLogoFile())
                                    .tag("Profile ListView") //参数为 Object
//                                    .error(R.drawable.u183)
                                    .into(((ViewHolder1) holder).iv_item2);
                            }
                            ((ViewHolder1) holder).tv_item2.setText(mlist1.get(position).getIndexItemList().get(1).getTitle());
//                        ((ViewHolder1) holder).tv_author2.setText(mlist1.get(position).getIndexItemList().get(1).getAuthor());
//                        ((ViewHolder1) holder).tv_date2.setText(mlist1.get(position).getIndexItemList().get(1).getPublishDate());

                            if(!TextUtils.isEmpty(mlist1.get(position).getIndexItemList().get(2).getLogoFile())) {
                                Picasso
                                    .with(mContext)
                                    .load(mlist1.get(position).getIndexItemList().get(2).getLogoFile())
                                    .tag("Profile ListView") //参数为 Object
//                                    .error(R.drawable.u183)
                                    .into(((ViewHolder1) holder).iv_item3);
                            }
                            ((ViewHolder1) holder).tv_item3.setText(mlist1.get(position).getIndexItemList().get(2).getTitle());
//                        ((ViewHolder1) holder).tv_author3.setText(mlist1.get(position).getIndexItemList().get(2).getAuthor());
//                        ((ViewHolder1) holder).tv_date3.setText(mlist1.get(position).getIndexItemList().get(2).getPublishDate());
                        ((ViewHolder1) holder).line1.setVisibility(View.VISIBLE);
                        if(mlist1.get(position).getIndexItemList().get(1).isHasRead()){
                            ((ViewHolder1) holder).tv_item2.setTextColor(mContext.getResources().getColor(R.color.text_read_color));
                        }else{
                            ((ViewHolder1) holder).tv_item2.setTextColor(mContext.getResources().getColor(R.color.text_color));
                        }
                        if(mlist1.get(position).getIndexItemList().get(2).isHasRead()){
                            ((ViewHolder1) holder).tv_item3.setTextColor(mContext.getResources().getColor(R.color.text_read_color));
                        }else{
                            ((ViewHolder1) holder).tv_item3.setTextColor(mContext.getResources().getColor(R.color.text_color));
                        }
                        break;
                }
            }else{
                ((ViewHolder1) holder).ll_item1.setVisibility(View.GONE);
                ((ViewHolder1) holder).ll_item2.setVisibility(View.GONE);
                ((ViewHolder1) holder).ll_item3.setVisibility(View.GONE);
                ((ViewHolder1) holder).line1.setVisibility(View.GONE);
                ((ViewHolder1) holder).line2.setVisibility(View.GONE);
            }
        }

//        if(position < getItemCount()){
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ObjectAnimator animator = ObjectAnimator.ofFloat(holder.itemView, "translationZ", 20, 0);
//                animator.addListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
////                        mContext.startActivity(new Intent(mContext, DetailActivity.class));
//
//                    }
//                });
//                animator.start();
//            }
//        });
//        }else if(position > getItemCount()){
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ObjectAnimator animator = ObjectAnimator.ofFloat(holder.itemView, "translationZ", 20, 0);
//                animator.addListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
////                        mContext.startActivity(new Intent(mContext, DetailActivity.class));
//                  studioClickListener.onClick(position);
//                    }
//                });
//                animator.start();
//            }
//        });
//        }

    }
    private static OnStudioClickListener studioClickListener;
    private static OnNewsClickListener newsClickListener;

    public void setStudioClickListener(OnStudioClickListener studioClickListener) {
        this.studioClickListener = studioClickListener;
    }

    public void setNewsClickListener(OnNewsClickListener clickListener) {
        this.newsClickListener = clickListener;
    }

    public static interface OnNewsClickListener {
        void onClick(int position,int index);
    }
    public static interface OnStudioClickListener{
        void onClick(int position);
    }


    @Override
    public int getItemCount() {
        if(mlist2!=null&&mlist2.size()>0){
            return mlist1.size()+mlist2.size()+1;
        }else return mlist1.size();
    }

    public void AddFooterItem(List<IndexItem> items){
        mlist2.addAll(items);
        notifyDataSetChanged();
    }


    public static class ViewHolder1 extends RecyclerView.ViewHolder{
        @InjectView(R.id.iv_subscrib_logo)
        ImageView iv_subscrib_logo;
//        @InjectView(R.id.tv_num)
//        TextView tv_num;
        @InjectView(R.id.iv_item1)
        ImageView iv_item1;
        @InjectView(R.id.tv_item1)
        TextView tv_item1;
        @InjectView(R.id.ll_item2)
        RelativeLayout ll_item2;
        @InjectView(R.id.tv_item2)
        TextView tv_item2;
        @InjectView(R.id.iv_item2)
        ImageView iv_item2;
        @InjectView(R.id.tv_item3)
        TextView tv_item3;
        @InjectView(R.id.iv_item3)
        ImageView iv_item3;
        @InjectView(R.id.ll_item3)
        RelativeLayout ll_item3;
        @InjectView(R.id.ll_item1)
        RelativeLayout ll_item1;
        @InjectView(R.id.tv_subscribe_name)
        TextView tv_subscribe_name;
        @InjectView(R.id.line1)
        View line1;
        @InjectView(R.id.line2)
        View line2;
        @InjectView(R.id.rv_studio)
        RelativeLayout rv_studio;


//        @InjectView(R.id.tv_author1)
//        TextView tv_author1;
//        @InjectView(R.id.tv_author2)
//        TextView tv_author2;
//        @InjectView(R.id.tv_author3)
//        TextView tv_author3;
//        @InjectView(R.id.tv_date1)
//        TextView tv_date1;
//        @InjectView(R.id.tv_date2)
//        TextView tv_date2;
//        @InjectView(R.id.tv_date3)
//        TextView tv_date3;
        public final View mView;
        public ViewHolder1(View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.inject(this,mView);
        }

        @OnClick(R.id.rv_studio)
        void getStudioDetail(){
            //跳转到新闻详情
//            MaterialRippleLayout.on(rv_studio)
//                .rippleOverlay(true)
//                .rippleAlpha(0.2f)
//                .rippleColor(0xFF585858)
//                .rippleHover(true)
//                .create();
            studioClickListener.onClick(getAdapterPosition());
        }
        @OnClick(R.id.ll_item1)
        void getFirstNews(){
            //跳转到新闻详情
            MaterialRippleLayout.on(ll_item1)
                .rippleOverlay(true)
                .rippleAlpha(0.2f)
                .rippleColor(0xFF585858)
                .rippleHover(true)
                .create();
            ll_item1.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    newsClickListener.onClick(getAdapterPosition(),0);
                }
            });
        }
        @OnClick(R.id.ll_item2)
        void getSecondNews(){
            //跳转到新闻详情
            MaterialRippleLayout.on(ll_item2)
                .rippleOverlay(true)
                .rippleAlpha(0.2f)
                .rippleColor(0xFF585858)
                .rippleHover(true)
                .create();
            ll_item2.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
               newsClickListener.onClick(getAdapterPosition(),1);

                }
            });
        }
        @OnClick(R.id.ll_item3)
        void getThirdNews(){
            //跳转到新闻详情
            MaterialRippleLayout.on(ll_item3)
                .rippleOverlay(true)
                .rippleAlpha(0.2f)
                .rippleColor(0xFF585858)
                .rippleHover(true)
                .create();
            ll_item3.setOnClickListener(new NoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                newsClickListener.onClick(getAdapterPosition(),2);
                }
            });

        }
    }
    public static class ViewHolder2 extends RecyclerView.ViewHolder{
        @InjectView(R.id.iv_item_logo2)
        ImageView iv_item_logo2;
        @InjectView(R.id.tv_title)
        TextView tv_title;
        @InjectView(R.id.tv_content)
        TextView tv_content;
        @InjectView(R.id.tv_time)
        TextView tv_time;

        public final View mView;
        public ViewHolder2(View itemView) {
            super(itemView);
            mView = itemView;
        }
    }
    public static class ViewHolder3 extends RecyclerView.ViewHolder{

        public final View mView;
        public ViewHolder3(View itemView) {
            super(itemView);
            mView = itemView;
        }
    }

    public List<IndexItem> getMlist1() {
        return mlist1;
    }

    public void setMlist1(List<IndexItem> mlist) {
        this.mlist1 = mlist;
        notifyDataSetChanged();
        Log.e("IndexMultipleAdapter","setMlist1 :"+ getItemCount());
    }

    @Override
    public int getItemViewType(int position) {
        int sepIndex = getItemCount();
         if(position < sepIndex){
            return ITEM_TYPE1;
        }else if(mlist2!=null&&position == sepIndex&&position != 0&&mlist2.size()>0){
            return ITEM_SEPERATR;
        }else if(position > sepIndex){
            return ITEM_TYPE2;
        } return ITEM_TYPE1;
    }
}
