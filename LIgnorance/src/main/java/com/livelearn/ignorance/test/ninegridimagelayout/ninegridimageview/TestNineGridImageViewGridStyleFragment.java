package com.livelearn.ignorance.test.ninegridimagelayout.ninegridimageview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.test.ninegridimagelayout.OnePiece;
import com.livelearn.ignorance.widget.ninegridimagelayout.ninegridimage.NineGridImageView;

import butterknife.BindView;

/**
 * Created on 2017/7/11.
 */

public class TestNineGridImageViewGridStyleFragment extends BaseFragment {

    @BindView(R.id.rv_nine_grid)
    RecyclerView rvNineGrid;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_test_nine_grid_image_view_grid_style;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rvNineGrid.setLayoutManager(new LinearLayoutManager(mContext));
        rvNineGrid.setHasFixedSize(true);
        rvNineGrid.setNestedScrollingEnabled(false);
        rvNineGrid.setAdapter(new TestNineGridImageViewAdapter(OnePiece.mData.subList(0, 9), NineGridImageView.STYLE_GRID));
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyFetchData() {

    }
}
