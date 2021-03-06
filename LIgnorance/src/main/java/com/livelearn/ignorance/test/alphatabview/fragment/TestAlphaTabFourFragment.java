package com.livelearn.ignorance.test.alphatabview.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.utils.immersionbar.BarHide;
import com.livelearn.ignorance.utils.immersionbar.ImmersionBar;
import com.livelearn.ignorance.widget.TitleBar;

import butterknife.BindView;


public class TestAlphaTabFourFragment extends BaseFragment {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @Override
    public void immersionInit() {
        tbTitle.setTitleBarBackgroundColor(ResourceUtils.getColor(R.color.color_light_blue));
        ImmersionBar.with(getActivity())
                .statusBarDarkFont(false)
                .hideBar(BarHide.FLAG_SHOW_BAR)//显示状态栏
                .init();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_test_alpha_tab_four;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tbTitle.hideLeftIcon()
                .setTitleText("我的")
                .setTitleBarBackgroundColor(ResourceUtils.getColor(R.color.color_light_blue));
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyFetchData() {

    }
}
