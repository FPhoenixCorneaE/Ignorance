package com.livelearn.ignorance.ui.fragment.book;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.presenter.book.SearchBookListPresenter;
import com.livelearn.ignorance.ui.view.book.SearchBookListView;

import butterknife.BindView;

/**
 * Created on 2017/2/28.
 */

public class SearchBookListFragment extends BaseFragment {

    @BindView(R.id.sblv_search_book_list)
    SearchBookListView sblvSearchBookList;

    private String keyword;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_search_book_list;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenter = new SearchBookListPresenter(sblvSearchBookList);
        sblvSearchBookList.setKeyword(keyword);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyFetchData() {

    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
        if (sblvSearchBookList != null) {
            sblvSearchBookList.setKeyword(keyword);
        }
    }

    public void clearData() {
        this.keyword = "";
        if (sblvSearchBookList != null) {
            sblvSearchBookList.clearData();
        }
    }
}
