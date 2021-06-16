package com.livelearn.ignorance.test.ninegridimagelayout.nicenineimage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hitomi.tilibrary.transfer.Transferee;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.test.ninegridimagelayout.JournalModel;

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
        TestNiceNineImageLayoutListAdapter mAdapter = new TestNiceNineImageLayoutListAdapter(JournalModel.getJournal(0), new Transferee(mContext));
        rvRecycler.setAdapter(mAdapter);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyFetchData() {

    }
}
