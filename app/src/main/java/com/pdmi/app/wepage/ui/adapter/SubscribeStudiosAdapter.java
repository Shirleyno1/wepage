package com.pdmi.app.wepage.ui.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.pdmi.app.wepage.R;
import com.pdmi.app.wepage.model.StudioItem;
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
public class SubscribeStudiosAdapter extends RecyclerView.Adapter<SubscribeStudiosAdapter.ViewHolder> {
    private static Context mContext;
    private static List<StudioItem> datas = new ArrayList<StudioItem>();
    public SubscribeStudiosAdapter(Context mContext, List<StudioItem> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }
    private static OnSubscribeListener clickListener;
    public void setSubscribeListener(OnSubscribeListener clickListener) {
        this.clickListener = clickListener;
    }

    public static interface OnSubscribeListener {
        void onClick(int position,boolean checked);
    }

    private static OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public static interface OnItemClickListener {
        void onClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.view_subscribe_list_item, parent, false);
        return new ViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final SubscribeStudiosAdapter.ViewHolder holder, final int position) {
        if(!TextUtils.isEmpty(datas.get(position).getLogoFile())) {
            Picasso
                .with(mContext)
                .load(datas.get(position).getLogoFile())
                .tag("Profile ListView") //参数为 Object
//                .error(R.drawable.u46)
                .into(holder.iv_logo);
        }else{
//            holder.iv_logo.setBackgroundResource(R.drawable.u46);
        }
        holder.bt_subscribe.setChecked(datas.get(position).isHasSubscribed());
        if(holder.bt_subscribe.isChecked()){
            holder.bt_subscribe.setText(mContext.getResources().getString(R.string.subscribed));
        }else{
            holder.bt_subscribe.setText(mContext.getResources().getString(R.string.subscribe));
        }
        holder.tv_name.setText(datas.get(position).getSubName());
        holder.tv_content.setText(datas.get(position).getDescription());
        holder.itemView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(holder.itemView, "translationZ", 20, 0);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
//                        mContext.startActivity(new Intent(mContext, DetailActivity.class));
                        onItemClickListener.onClick(position);
                    }
                });
                animator.start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.iv_item_logo2)
        public ImageView iv_logo;
        @InjectView(R.id.bt_subscribe)
        public CheckBox bt_subscribe;
        @InjectView(R.id.tv_title)
        public TextView tv_name;
        @InjectView(R.id.tv_content)
        public TextView tv_content;
        public final View mView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
            mView = itemView;
        }
        @OnClick(R.id.bt_subscribe)
        void subscribe(){
            bt_subscribe.setChecked(!datas.get(getAdapterPosition()).isHasSubscribed());
            if(bt_subscribe.isChecked()){
                bt_subscribe.setText(mContext.getResources().getString(R.string.subscribed));
            }else{
                bt_subscribe.setText(mContext.getResources().getString(R.string.subscribe));
            }
            datas.get(getAdapterPosition()).setHasSubscribed(!datas.get(getAdapterPosition()).isHasSubscribed());
            clickListener.onClick(getAdapterPosition(),!datas.get(getAdapterPosition()).isHasSubscribed());
        }
    }

    public static List<StudioItem> getDatas() {
        return datas;
    }

    public void setDatas(List<StudioItem> datas) {
        SubscribeStudiosAdapter.datas = datas;
        notifyDataSetChanged();
    }

}
