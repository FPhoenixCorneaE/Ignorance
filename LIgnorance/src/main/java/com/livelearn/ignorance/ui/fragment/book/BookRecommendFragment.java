package com.livelearn.ignorance.ui.fragment.book;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.presenter.book.BookRecommendPresenter;
import com.livelearn.ignorance.ui.view.book.BookRecommendView;

import butterknife.BindView;

/**
 * 书籍推荐
 * Created on 2016/12/26.
 */

public class BookRecommendFragment extends BaseFragment {

    @BindView(R.id.brv_book_recommend)
    BookRecommendView brvBookRecommend;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_book_recommend;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenter = new BookRecommendPresenter(brvBookRecommend);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyFetchData() {

    }
}
