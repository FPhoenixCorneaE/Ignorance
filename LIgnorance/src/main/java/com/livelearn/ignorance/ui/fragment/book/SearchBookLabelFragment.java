package com.livelearn.ignorance.ui.fragment.book;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.presenter.book.SearchBookLabelPresenter;
import com.livelearn.ignorance.ui.view.book.SearchBookLabelView;

import butterknife.BindView;

/**
 * Created on 2017/2/28.
 */

public class SearchBookLabelFragment extends BaseFragment {

    @BindView(R.id.sblv_search_book_label)
    SearchBookLabelView sblvSearchBookLabel;

    private SearchBookLabelView.OnTagCloudChildClickListener onTagCloudChildClickListener;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_search_book_label;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sblvSearchBookLabel.setOnTagCloudChildClickListener(onTagCloudChildClickListener);
        mPresenter = new SearchBookLabelPresenter(sblvSearchBookLabel);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyFetchData() {

    }

    public void setOnTagCloudChildClickListener(SearchBookLabelView.OnTagCloudChildClickListener onTagCloudChildClickListener) {
        this.onTagCloudChildClickListener = onTagCloudChildClickListener;
    }
}
