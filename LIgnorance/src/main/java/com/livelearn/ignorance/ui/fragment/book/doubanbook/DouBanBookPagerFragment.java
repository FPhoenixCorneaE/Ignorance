package com.livelearn.ignorance.ui.fragment.book.doubanbook;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.presenter.book.doubanbook.DouBanBookPagerPresenter;
import com.livelearn.ignorance.ui.view.book.doubanbook.DouBanBookPagerView;

import butterknife.BindView;

public class DouBanBookPagerFragment extends BaseFragment {

    @BindView(R.id.bpv_book_pager)
    DouBanBookPagerView bpvBookPager;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_dou_ban_book_pager;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String bookCategory = bundle.getString(Constant.BOOK_TYPE);
        mPresenter = new DouBanBookPagerPresenter(bpvBookPager, bookCategory);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyFetchData() {

    }
}
