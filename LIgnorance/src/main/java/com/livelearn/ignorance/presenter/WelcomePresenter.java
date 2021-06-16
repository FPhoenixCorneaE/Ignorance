package com.livelearn.ignorance.presenter;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.bean.image.WelcomeImages;
import com.livelearn.ignorance.presenter.contract.WelcomeContract;
import com.livelearn.ignorance.utils.RxUtils;
import com.livelearn.ignorance.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 欢迎
 */
public class WelcomePresenter extends RxPresenter implements WelcomeContract.Presenter {

    public static final int COUNT_DOWN_TIME = 4000;
    private static final String GOTO_HOME = "goto_home";
    private static final String GOTO_GUIDE = "goto_guide";
    private static final String DO_LOGIN = "do_login";

    private WelcomeContract.View mWelcomeView;

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
        addSubscribe(Observable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtils.<Long>rxSchedulerHelper())
                .flatMap(new Func1<Long, Observable<String>>() {
                    @Override
                    public Observable<String> call(Long aLong) {
                        boolean hasEnteredNavigationPages = SharedPreferencesUtils.getBoolean(Constant.USER_INFO, Constant.GUIDE_STATE);
                        if (hasEnteredNavigationPages) {//已经进入过导航页
                            String userAccountList = SharedPreferencesUtils.getString(Constant.USER_INFO, Constant.USER_ACCOUNT);
                            if (userAccountList != null && !TextUtils.isEmpty(userAccountList)) {//至少保存了一个账号
                                ArrayList<String> accountArray = new ArrayList<>();
                                Collections.addAll(accountArray, userAccountList.split(","));
                                String userAccount = accountArray.get(0);
                                boolean loginState = SharedPreferencesUtils.getBoolean(userAccount, Constant.LOGIN_STATE);
                                boolean autoLogin = SharedPreferencesUtils.getBoolean(userAccount, Constant.AUTO_LOGIN);
                                if (loginState && autoLogin) {//已登录状态并且自动登录
                                    return Observable.just(DO_LOGIN);
                                }
                            }
                            return Observable.just(GOTO_HOME);
                        } else {
                            return Observable.just(GOTO_GUIDE);
                        }
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String state) {
                        switch (state) {
                            case GOTO_HOME:
                                mWelcomeView.startMainActivity();
                                break;
                            case GOTO_GUIDE:
                                mWelcomeView.startGuideActivity();
                                break;
                            case DO_LOGIN:
                                mWelcomeView.doLogin();
                                break;
                        }
                    }
                }));
    }
}
