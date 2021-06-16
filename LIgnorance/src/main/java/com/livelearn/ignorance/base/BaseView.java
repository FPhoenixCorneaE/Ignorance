package com.livelearn.ignorance.base;


import androidx.annotation.NonNull;

public interface BaseView<P, M> {

    //初始化
    void init();

    //设置主导器
    void setPresenter(@NonNull P mPresenter);

    //显示加载页
    void showProgress();

    //关闭加载页
    void hideProgress();

    //加载新数据
    void onSuccess(M mData);

    //显示加载失败
    void onFailure(String message);

}
