package com.livelearn.ignorance.ui.activity.mine;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.bean.UserInfo;
import com.livelearn.ignorance.presenter.mine.ChangeBackgroundPresenter;
import com.livelearn.ignorance.ui.view.mine.IChangeBackgroundView;
import com.livelearn.ignorance.widget.TitleBar;

import org.simple.eventbus.EventBus;

import butterknife.BindView;

/**
 * 更换背景
 * Created on 2017/8/8.
 */

public class ChangeBackgroundActivity extends BaseActivity implements IChangeBackgroundView {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.rv_background)
    RecyclerView rvBackground;

    private ChangeBackgroundPresenter mChangeBackgroundPresenter;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_change_background;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setLeftIconTintColor(R.color.color_pale)
                .setLeftText("返回")
                .setLeftTextColorRes(R.color.color_pale)
                .setTitleText("更换背景")
                .setTitleTextColorRes(R.color.color_pale)
                .setRightText("确定")
                .setRightTextColorRes(R.color.color_pale);

        mChangeBackgroundPresenter = new ChangeBackgroundPresenter(this);

        mChangeBackgroundPresenter.setAdapter();
    }

    @Override
    public void setListeners() {
        mChangeBackgroundPresenter.setOnItemClickListener();
        tbTitle.setOnClickRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        EventBus.getDefault().post(mChangeBackgroundPresenter.getBackgroundUrlInUse(), Constant.BACKGROUND_URL_IN_USE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mChangeBackgroundPresenter = null;
    }

    @Override
    public BaseActivity getmContext() {
        return mContext;
    }

    @Override
    public UserInfo getmUserInfo() {
        return Constant.getUserInfo();
    }

    @Override
    public RecyclerView getRecyclerView() {
        return rvBackground;
    }
}
