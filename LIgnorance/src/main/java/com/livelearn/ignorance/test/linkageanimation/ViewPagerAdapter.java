package com.livelearn.ignorance.test.linkageanimation;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.collection.SparseArrayCompat;
import androidx.viewpager.widget.PagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by wkz on 2017/09/11.
 */

public class ViewPagerAdapter extends PagerAdapter {
    // 界面列表
    private final List<Integer> views;
    private final SparseArrayCompat<WeakReference<View>> holder;
    private final LayoutInflater inflater;

    ViewPagerAdapter(Context context, @NonNull List<Integer> mViews) {
        this.views = mViews;
        this.holder = new SparseArrayCompat<>(mViews.size());
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * 获得当前界面数
     */
    @Override
    public int getCount() {
        return views.size();
    }

    /**
     * 初始化position位置的界面
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(views.get(position), container, false);
        container.addView(view);
        holder.put(position, new WeakReference<>(view));
        return view;
    }

    /**
     * 判断是否由对象生成界面
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    /**
     * 销毁position位置的界面
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        holder.remove(position);
        container.removeView((View) object);
    }

    public View getPage(int position) {
        final WeakReference<View> weakRefItem = holder.get(position);
        return weakRefItem != null ? weakRefItem.get() : null;
    }
}
