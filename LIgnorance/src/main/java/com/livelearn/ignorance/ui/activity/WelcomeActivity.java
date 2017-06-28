package com.livelearn.ignorance.ui.activity;

import android.os.Bundle;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.presenter.WelcomePresenter;
import com.livelearn.ignorance.ui.view.WelcomeView;

import butterknife.BindView;

/**
 * 启动页（欢迎界面）
 */
public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.welcome_view)
    WelcomeView welcomeView;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        //不能滑动返回
        setSwipeBackEnable(false);

        mPresenter = new WelcomePresenter(welcomeView);
    }

    @Override
    public boolean hasEnteredTheHomepage() {
        return false;
    }

    @Override
    public void setListeners() {

    }
}
