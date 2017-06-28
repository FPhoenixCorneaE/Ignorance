package com.livelearn.ignorance.ui.fragment.guide;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.presenter.GuidePresenter;
import com.livelearn.ignorance.ui.view.GuideView;

import butterknife.BindView;

public class GuideFragment extends BaseFragment {

    @BindView(R.id.guide_view)
    GuideView guideView;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_guide;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenter = new GuidePresenter(guideView, this);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyFetchData() {

    }
}
