package com.livelearn.ignorance.ui.view;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
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
    public void onSuccess(List<String> data) {
        if (data != null) {
            int index = getRandomNumber(0, data.size());
            GlideUtils.setupImage(mContext, ivWelcomeBg, data.get(index));

            //启动动画
            PropertyValuesHolder alphaAnim = PropertyValuesHolder.ofFloat("alpha", 0.0F, 1.0F);
            PropertyValuesHolder scaleXAnim = PropertyValuesHolder.ofFloat("scaleX", 1.0F, 1.3F);
            PropertyValuesHolder scaleYAnim = PropertyValuesHolder.ofFloat("scaleY", 1.0F, 1.3F);

            ObjectAnimator.ofPropertyValuesHolder(ivWelcomeBg, alphaAnim, scaleXAnim, scaleYAnim)
                    .setDuration(WelcomePresenter.COUNT_DOWN_TIME)
                    .start();
        }
    }

    /**
     * 获取随机数字
     *
     * @param min 最小
     * @param max 最大
     */
    public int getRandomNumber(int min, int max) {
        return new Random().nextInt(max) % (max - min + 1) + min;
    }

    @Override
    public void startMainActivity() {
        IntentUtils.startActivity(mContext, MainActivity.class);
        ((Activity) mContext).finish();
        ((Activity) mContext).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void startGuideActivity() {
        IntentUtils.startActivity(mContext, GuideActivity.class);
        ((Activity) mContext).finish();
        ((Activity) mContext).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void doLogin() {
        String userAccountList = SharedPreferencesUtils.getString(Constant.USER_INFO, Constant.USER_ACCOUNT);
        if (userAccountList != null && !TextUtils.isEmpty(userAccountList)) {//至少保存了一个账号
            ArrayList<String> accountArray = new ArrayList<>();
            Collections.addAll(accountArray, userAccountList.split(","));
            String userAccount = accountArray.get(0);
            String password = SharedPreferencesUtils.getString(userAccount, Constant.USER_PASSWORD);
            LoginPresenter.doLogin((BaseActivity) mContext, userAccount, password == null ? "" : password, true, true);
        }
    }
}
