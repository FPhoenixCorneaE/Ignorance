package com.livelearn.ignorance.widget.ninegridlayout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by linlongxin on 2015/12/31.
 */
public abstract class MultiViewAdapter<T> {

    private Context mContext;

    private MultiView mMultiView;

    private List<T> mDatas = new ArrayList<>();

    public MultiViewAdapter(Context context) {
        mContext = context;
    }

    public void attachView(MultiView view) {
        mMultiView = view;
    }

    public abstract View getView(ViewGroup parent, int position);

    public abstract void setData(T data);

    public Context getContext() {
        return mContext;
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public int getCount() {
        return mDatas.size();
    }

    public T getItem(int position) {
        return mDatas.get(position);
    }

    public void add(T data) {
        mDatas.add(data);
        notifyItemInsert(mDatas.indexOf(data));
    }

    public void addAll(List<T> mDatas) {
        this.mDatas.addAll(mDatas);
        notifyDataChanged();
    }

    public void addAll(T[] mDatas) {
        this.mDatas.addAll(Arrays.asList(mDatas));
        notifyDataChanged();
    }

    public void clear() {
        this.mDatas.clear();
        notifyDataChanged();
    }

    public void notifyDataChanged() {
        mMultiView.addViews();
    }

    public void notifyItemInsert(int position) {
        mMultiView.addView(position);
    }

    public void setOnItemClick(List<T> mDatas, int position) {

    }
}
