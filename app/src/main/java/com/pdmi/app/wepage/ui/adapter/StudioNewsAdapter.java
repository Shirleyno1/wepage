package com.pdmi.app.wepage.ui.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.pdmi.app.wepage.R;
import com.pdmi.app.wepage.model.ArticleItem;
import com.pdmi.app.wepage.ui.widget.NoDoubleClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by guoxiuli on 2016/6/1.
 */
public class StudioNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ArticleItem> mlist = new ArrayList<ArticleItem>();
    //数据
    private final static int ITEM_TYPE1 = 1;
    //分割title
    private final static int ITEM_TYPE2 = 2;
    public static final int TYPE_FOOTER = 3;  //说明是带有Footer的
    private View mFooterView;
    private static final int newCount = 3;
    public View getFooterView() {
        return mFooterView;
    }
    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyDataSetChanged();
//        notifyItemInserted(getItemCount()-1);
    }

    public StudioNewsAdapter(Context mContext, List<ArticleItem> list) {
        this.mContext = mContext;
        this.mlist = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE1) {
//            return new ViewHolder1(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_studio_part2_item, parent, false));
            return new ViewHolder1(
                MaterialRippleLayout.on((LayoutInflater.from(parent.getContext()).inflate(R.layout.view_studio_part2_item, parent, false)))
                .rippleOverlay(true)
                .rippleAlpha(0.2f)
                .rippleColor(0xFF585858)
                .rippleHover(true)
                .create()
            );
        } else if (viewType == ITEM_TYPE2){
            return new ViewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_seperator, parent, false));
        }if(mFooterView != null && viewType == TYPE_FOOTER){
            return new ViewHolder3(mFooterView);
        }
        return new ViewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_studio_part2_item, parent, false));
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ViewHolder1) {
            int mPosition = position -1;
            if(position > newCount){
                mPosition = position-2;
            }
            ((ViewHolder1) holder).tv_title.setText(mlist.get(mPosition).getTitle());
            if(mlist.get(mPosition).isHasRead()){
                ((ViewHolder1) holder).tv_title.setTextColor(mContext.getResources().getColor(R.color.text_read_color));
            }else{
                ((ViewHolder1) holder).tv_title.setTextColor(mContext.getResources().getColor(R.color.text_color));
            }
            if(!TextUtils.isEmpty(mlist.get(mPosition).getLogoFile())) {
                Picasso
                    .with(mContext)
                    .load(mlist.get(mPosition).getLogoFile())
                    .tag("Profile ListView") //参数为 Object
//                    .error(R.drawable.u183)
                    .into(((ViewHolder1) holder).iv_logo);
            }
//            else((ViewHolder1)holder).iv_logo.setBackgroundResource(R.drawable.u6);
        }else{
            if (position == 0){
                ((ViewHolder2)holder).tv_seperator.setText("最新");
            }else{
                ((ViewHolder2)holder).tv_seperator.setText("更多");
            }
        }
        holder.itemView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(holder.itemView, "translationZ", 20, 0);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
//                        Toast.makeText(mContext,"mPosition:"+(position > newCount?position-2:position-1),Toast.LENGTH_LONG).show();
                        clickListener.onClick(position > newCount?position-2:position-1);
                    }
                });
                animator.start();
            }
        });
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if(mlist.size() == 0){
            count = 0;
        } else if(mlist.size()>newCount){
            count = mlist.size()+2;
        } if(mlist.size() != 0 && mFooterView != null){
            count = count +1;
        }
        return count;
    }
    private static OnItemClickListener clickListener;
    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public static interface OnItemClickListener {
        void onClick(int position);
    }

    public static class ViewHolder1 extends RecyclerView.ViewHolder{
        @InjectView(R.id.iv_item_logo2)
        ImageView iv_logo;
        @InjectView(R.id.tv_article_title)
        TextView tv_title;
        public final View mView;
        public ViewHolder1(View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.inject(this,mView);
        }
    }
    public static class ViewHolder2 extends RecyclerView.ViewHolder{
        @InjectView(R.id.tv_seperator)
        TextView tv_seperator;
        public final View mView;
        public ViewHolder2(View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.inject(this,mView);
        }
    }
    public static class ViewHolder3 extends RecyclerView.ViewHolder{
        public final View mView;
        public ViewHolder3(View itemView) {
            super(itemView);
            mView = itemView;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position!=0 && mFooterView != null && position == getItemCount()-1){
            //最后一个,应该加载Footer
            return TYPE_FOOTER;
        } else if(position == newCount+1 ||position == 0){
            return ITEM_TYPE2;
        }else
            return ITEM_TYPE1;
    }

    public List<ArticleItem> getMlist() {
        return mlist;
    }

    public void setMlist(List<ArticleItem> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
    }
}
