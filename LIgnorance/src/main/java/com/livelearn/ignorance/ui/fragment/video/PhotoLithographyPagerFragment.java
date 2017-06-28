package com.livelearn.ignorance.ui.fragment.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.presenter.video.PhotoLithographyPagerPresenter;
import com.livelearn.ignorance.ui.view.video.PhotoLithographyPagerView;

import butterknife.BindView;

/**
 * Created on 2017/6/22.
 */

public class PhotoLithographyPagerFragment extends BaseFragment {

    @BindView(R.id.v_photo_lithography_pager)
    PhotoLithographyPagerView vPhotoLithographyPager;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_photo_lithography_pager;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String title = getArguments().getString(Constant.VIDEO_TITLE);
        String catalogId = getArguments().getString(Constant.VIDEO_CATALOG_ID);
        mPresenter = new PhotoLithographyPagerPresenter(vPhotoLithographyPager, title, catalogId);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyFetchData() {

    }
}
