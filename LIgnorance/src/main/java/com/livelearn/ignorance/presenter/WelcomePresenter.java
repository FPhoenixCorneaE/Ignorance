package com.livelearn.ignorance.presenter;

import android.support.annotation.NonNull;

import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.model.bean.image.WelcomeImages;
import com.livelearn.ignorance.presenter.contract.WelcomeContract;
import com.livelearn.ignorance.utils.RxUtils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/**
 * 欢迎
 */
public class WelcomePresenter extends RxPresenter implements WelcomeContract.Presenter {
    private WelcomeContract.View mWelcomeView;
    public static final int COUNT_DOWN_TIME = 4000;

    public WelcomePresenter(@NonNull WelcomeContract.View mWelcomeView) {
        this.mWelcomeView = mWelcomeView;
        mWelcomeView.setPresenter(this);
    }

    @Override
    public void showWelcomeData() {
        mWelcomeView.onSuccess(new WelcomeImages().getImgData());
        startCountDown();
    }

    private void startCountDown() {
        Subscription rxSubscription = Observable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtils.<Long>rxSchedulerHelper())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mWelcomeView.jumpToNextActivity();
                    }
                });
        addSubscribe(rxSubscription);
    }
}
