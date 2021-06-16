package com.livelearn.ignorance.test.alphatabview.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.utils.immersionbar.BarHide;
import com.livelearn.ignorance.utils.immersionbar.ImmersionBar;

import butterknife.BindView;


public class TestAlphaTabThreeFragment extends BaseFragment {

    @BindView(R.id.tb_title)
    Toolbar tbTitle;

    @Override
    public void immersionInit() {
        ImmersionBar.with(getActivity())
                .statusBarDarkFont(false)
                .navigationBarColor(R.color.color_pink)
                .hideBar(BarHide.FLAG_SHOW_BAR)//显示状态栏
                .init();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_test_alpha_tab_three;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tbTitle.setTitle("");
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyFetchData() {

    }
}
