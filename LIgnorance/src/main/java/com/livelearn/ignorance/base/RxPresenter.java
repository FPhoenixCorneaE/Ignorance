package com.livelearn.ignorance.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 基于Rx的Presenter封装,控制订阅的生命周期在Activity、Fragment的生命周期之内
 */
public class RxPresenter<V extends VacantView> implements BasePresenter<V> {

    protected V mView;
    protected CompositeSubscription mCompositeSubscription;

    /**
     * 取消订阅
     */
    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    /**
     * 添加订阅
     *
     * @param subscription 订阅
     */
    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
