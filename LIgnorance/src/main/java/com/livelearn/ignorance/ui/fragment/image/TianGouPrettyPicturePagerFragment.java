package com.livelearn.ignorance.ui.fragment.image;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.presenter.image.TianGouPrettyPicturePagerPresenter;
import com.livelearn.ignorance.ui.view.image.TianGouPrettyPicturePagerView;

import butterknife.BindView;

/**
 * Created on 2017/5/31.
 */

public class TianGouPrettyPicturePagerFragment extends BaseFragment {

    @BindView(R.id.v_pretty_picture)
    TianGouPrettyPicturePagerView vPrettyPicture;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_tian_gou_pretty_picture_pager;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int type = getArguments().getInt(Constant.IMAGE_TYPE);
        mPresenter = new TianGouPrettyPicturePagerPresenter(vPrettyPicture, type);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyFetchData() {

    }
}
