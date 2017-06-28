package com.livelearn.ignorance.test.pulltorefreshwithloadmore.entity;

import android.view.View;

import com.livelearn.ignorance.utils.ResourceUtils;

/**
 * Created on 2017/3/13.
 */

public class MenuItemInfo {

    private int mColor;
    private String mTitle;
    private View.OnClickListener mOnClickListener;

    public MenuItemInfo(int title, int color) {
        this(ResourceUtils.getString(title), ResourceUtils.getColor(color), null);
    }

    public MenuItemInfo(int title, int color, View.OnClickListener onClickListener) {
        this(ResourceUtils.getString(title), ResourceUtils.getColor(color), onClickListener);
    }

    public MenuItemInfo(String title, int color, View.OnClickListener onClickListener) {
        mTitle = title;
        mColor = color;
        mOnClickListener = onClickListener;
    }

    public void onClick(View v) {
        if (mOnClickListener != null) {
            mOnClickListener.onClick(v);
        }
    }

    public int getColor() {
        return mColor;
    }

    public String getTitle() {
        return mTitle;
    }
}
