package com.livelearn.ignorance.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.livelearn.ignorance.model.bean.UserInfo;
import com.livelearn.ignorance.ui.adapter.viewholder.AddressBookViewHolder;

/**
 * 通讯录适配器
 * Created on 2017/4/29.
 */

public class AddressBookAdapter extends RecyclerArrayAdapter<UserInfo> {

    private String type;
    private boolean swipeEnable;
    private AddressBookViewHolder.OnDeleteListener mOnDeleteListener;

    public AddressBookAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddressBookViewHolder(parent)
                .setType(type)
                .setSwipeEnable(swipeEnable)
                .setOnDeleteListener(mOnDeleteListener);
    }

    public AddressBookAdapter setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * 设置侧滑功能开关
     */
    public AddressBookAdapter setSwipeEnable(boolean swipeEnable) {
        this.swipeEnable = swipeEnable;
        return this;
    }

    public void setOnDeleteListener(AddressBookViewHolder.OnDeleteListener mOnDeleteListener) {
        this.mOnDeleteListener = mOnDeleteListener;
    }
}
