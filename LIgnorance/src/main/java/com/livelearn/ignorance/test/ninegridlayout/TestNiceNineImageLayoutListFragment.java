package com.livelearn.ignorance.test.ninegridlayout;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;

import butterknife.BindView;

/**
 * Created on 2017/7/6.
 */

public class TestNiceNineImageLayoutListFragment extends BaseFragment {

    @BindView(R.id.rv_recycler)
    RecyclerView rvRecycler;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_test_nice_nine_image_layout_list;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rvRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        rvRecycler.setHasFixedSize(true);
        rvRecycler.setNestedScrollingEnabled(false);
        TestNiceNineImageLayoutListAdapter mAdapter = new TestNiceNineImageLayoutListAdapter(JournalModel.getJournal(0));
        rvRecycler.setAdapter(mAdapter);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyFetchData() {

    }
}