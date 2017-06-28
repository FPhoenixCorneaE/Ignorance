package com.livelearn.ignorance.ui.activity.mine;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.model.bean.UserInfo;
import com.livelearn.ignorance.presenter.mine.SettingsPresenter;
import com.livelearn.ignorance.ui.view.mine.ISettingsView;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.utils.SpanUtils;
import com.livelearn.ignorance.utils.ToastUtils;
import com.livelearn.ignorance.widget.TitleBar;
import com.maiml.library.BaseItemLayout;
import com.maiml.library.config.ConfigAttrs;
import com.maiml.library.config.Mode;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置
 */
public class SettingsActivity extends BaseActivity implements ISettingsView {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.bil_base_item_layout)
    BaseItemLayout bilBaseItemLayout;

    @BindView(R.id.btn_logout)
    Button btnLogout;

    private TextView tvCache;
    private SettingsPresenter mSettingsPresenter;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_settings;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText("设置");

        List<String> valueList = Arrays.asList(ResourceUtils.getStringArray(R.array.Settings_Item));

        ConfigAttrs configAttrs = new ConfigAttrs();
        configAttrs.setValueList(valueList)//文字List
                .setIconWidth(24)//设置icon大小
                .setIconHeight(24)
                .setItemHeight(46)//设置高度
                .setItemBackgroundResId(R.drawable.selector_solid_normalwhite_pressedlightgray)//设置item背景
                .setItemMarginTop(10)//设置item间距
                .setItemMarginRight(8)
                .setItemMode(Mode.ARROW)//设置item模式
                .setItemMode(0, Mode.TEXT)
                .setIconMarginLeft(16)
                .setIconTextMargin(10)
                .setTextColor(ResourceUtils.getColor(R.color.color_light_purple))//设置文字颜色
                .setTextSize(16)//设置文字大小
                .setArrowResId(R.mipmap.ic_next)
                .setShowStartLine(false)
                .setShowEndLine(false)
        ;
        bilBaseItemLayout.setConfigAttrs(configAttrs).create();

        tvCache = (TextView) bilBaseItemLayout.getViewList().get(0).findViewById(R.id.right_text_id);

        if (getmUserInfo() != null) {
            btnLogout.setVisibility(View.VISIBLE);
        }

        mSettingsPresenter = new SettingsPresenter(this);
        //获取缓存
        mSettingsPresenter.getCache();
    }

    @Override
    public void setListeners() {
        bilBaseItemLayout.setOnBaseItemClick(new int[]{0, 1}, new BaseItemLayout.OnBaseItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0://清除缓存
                        mSettingsPresenter.showClearCacheDialog();
                        break;
                    case 1://清除Glide缓存
                        mSettingsPresenter.clearGlideCache();
                        break;
                }
            }
        });
    }

    @OnClick(R.id.btn_logout)
    public void onViewClicked() {
        mSettingsPresenter.logout();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSettingsPresenter = null;
    }

    @Override
    public void setCache(String size, String unit) {
        SpanUtils.setLeftLightPurple15thRightGray12thText(mContext, tvCache, size, unit);
    }

    @Override
    public void onClearCacheSuccess(Dialog clearingDialog) {
        if (clearingDialog != null)
            clearingDialog.dismiss();
        ToastUtils.showToast("清除缓存成功");
        setCache("0.00", "B");
    }

    @Override
    public Context getmContext() {
        return mContext;
    }

    @Override
    public Activity getmActivity() {
        return mContext;
    }

    @Override
    public UserInfo getmUserInfo() {
        return mUserInfo;
    }
}
