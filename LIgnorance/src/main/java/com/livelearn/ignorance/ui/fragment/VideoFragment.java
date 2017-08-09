package com.livelearn.ignorance.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.ui.fragment.video.PhotoLithographyFragment;
import com.livelearn.ignorance.utils.FragmentUtils;

/**
 * 视频
 */
public class VideoFragment extends BaseFragment {

    //微影
    private PhotoLithographyFragment photoLithographyFragment;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_video;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        photoLithographyFragment = new PhotoLithographyFragment();
        FragmentUtils.addChildFragment(this, R.id.fl_container, photoLithographyFragment, null, false);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyFetchData() {

    }
}
