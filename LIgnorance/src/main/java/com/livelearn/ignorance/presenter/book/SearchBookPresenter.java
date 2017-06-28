package com.livelearn.ignorance.presenter.book;

import android.support.v4.app.FragmentActivity;

import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.presenter.contract.book.SearchBookContract;
import com.livelearn.ignorance.ui.fragment.book.SearchBookLabelFragment;
import com.livelearn.ignorance.ui.fragment.book.SearchBookListFragment;
import com.livelearn.ignorance.utils.FragmentUtils;

public class SearchBookPresenter extends RxPresenter implements SearchBookContract.Presenter {

    private SearchBookContract.View mView;

    private SearchBookLabelFragment mBookLabelFragment;
    private SearchBookListFragment mBookListFragment;

    public SearchBookPresenter(SearchBookContract.View mSearchBookView) {
        this.mView = mSearchBookView;

        mBookLabelFragment = new SearchBookLabelFragment();
        mBookListFragment = new SearchBookListFragment();

        mView.setPresenter(this);
    }

    @Override
    public void addSearchBookLabelFragment(FragmentActivity activity, int containerViewId) {
        mBookLabelFragment.setOnTagCloudChildClickListener(mView.getOnTagCloudChildClickListener());
        FragmentUtils.addFragment(activity, containerViewId, mBookLabelFragment, null, false);
    }

    @Override
    public void showSearchBookLabel(FragmentActivity activity, int containerViewId) {
        mBookListFragment.clearData();
        FragmentUtils.hideAndShowFragment(activity, containerViewId, mBookListFragment, mBookLabelFragment, null, false);
    }

    @Override
    public void showSearchBookList(FragmentActivity activity, int containerViewId, String keyword) {
        mBookListFragment.setKeyword(keyword);
        FragmentUtils.hideAndShowFragment(activity, containerViewId, mBookLabelFragment, mBookListFragment, null, false);
    }
}
