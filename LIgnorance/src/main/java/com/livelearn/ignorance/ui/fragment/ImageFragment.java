package com.livelearn.ignorance.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.ui.fragment.image.TianGouPrettyPictureFragment;
import com.livelearn.ignorance.utils.FragmentUtils;

/**
 * 图片
 */
public class ImageFragment extends BaseFragment {

    //天狗美图
    private TianGouPrettyPictureFragment tianGouPrettyPictureFragment;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_image;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tianGouPrettyPictureFragment = new TianGouPrettyPictureFragment();
        FragmentUtils.addChildFragment(this, R.id.fl_container, tianGouPrettyPictureFragment, null, false);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyFetchData() {

    }
}
