package com.livelearn.ignorance.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.presenter.LoginPresenter;
import com.livelearn.ignorance.presenter.WelcomePresenter;
import com.livelearn.ignorance.presenter.contract.WelcomeContract;
import com.livelearn.ignorance.ui.activity.GuideActivity;
import com.livelearn.ignorance.ui.activity.MainActivity;
import com.livelearn.ignorance.ui.activity.WelcomeActivity;
import com.livelearn.ignorance.utils.GlideUtils;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.SharedPreferencesUtils;
import com.livelearn.ignorance.widget.RootView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

public class WelcomeView extends RootView implements WelcomeContract.View {

    @BindView(R.id.iv_welcome_bg)
    ImageView ivWelcomeBg;

    public WelcomeView(Context context) {
        super(context);
    }

    public WelcomeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WelcomeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.view_welcome;
    }

    @Override
    public void init() {
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void setPresenter(@NonNull WelcomeContract.Presenter presenter) {
        presenter.showWelcomeData();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onFailure(String msg) {

    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void onSuccess(List<String> data) {
        if (data != null) {
            int index = getRandomNumber(0, data.size());
            GlideUtils.setupImage(mContext, ivWelcomeBg, data.get(index));
            ivWelcomeBg.animate().scaleX(1.30f).scaleY(1.30f).setDuration(WelcomePresenter.COUNT_DOWN_TIME).setStartDelay(100).start();
        }
    }

    public int getRandomNumber(int min, int max) {
        return new Random().nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 跳转到导航界面或者登录页面或者进入主页面
     */
    public void jumpToNextActivity() {
        boolean guideState = SharedPreferencesUtils.getBoolean(Constant.USER_INFO, Constant.GUIDE_STATE);
        if (guideState) {//已经进入过导航页
            String userAccountList = SharedPreferencesUtils.getString(Constant.USER_INFO, Constant.USER_ACCOUNT);
            if (userAccountList != null && !TextUtils.isEmpty(userAccountList)) {//至少保存了一个账号
                ArrayList<String> accountArray = new ArrayList<>();
                Collections.addAll(accountArray, userAccountList.split(","));
                String userAccount = accountArray.get(0);
                boolean loginState = SharedPreferencesUtils.getBoolean(userAccount, Constant.LOGIN_STATE);
                boolean autoLogin = SharedPreferencesUtils.getBoolean(userAccount, Constant.AUTO_LOGIN);
                if (loginState && autoLogin) {//已登录状态并且自动登录
                    String password = SharedPreferencesUtils.getString(userAccount, Constant.USER_PASSWORD);
                    LoginPresenter.doLogin((BaseActivity) mContext, userAccount, password == null ? "" : password, true, true);
                } else {
                    IntentUtils.startActivity(mContext, MainActivity.class);
                }
            } else {
                IntentUtils.startActivity(mContext, MainActivity.class);
            }
        } else {
            IntentUtils.startActivity(mContext, GuideActivity.class);
        }
        ((WelcomeActivity) mContext).finish();
        ((WelcomeActivity) mContext).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
