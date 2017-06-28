package com.livelearn.ignorance.ui.fragment.book;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.bean.book.BookTypeModel;
import com.livelearn.ignorance.presenter.book.BookCategoryPresenter;
import com.livelearn.ignorance.ui.view.book.BookCategoryView;

import butterknife.BindView;

/**
 * 书籍类型
 * Created on 2017/2/9.
 */

public class BookCategoryFragment extends BaseFragment {

    @BindView(R.id.bcv_category)
    BookCategoryView bcvCategory;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_book_category;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BookTypeModel bookTypeModel = getArguments().getParcelable(Constant.BOOK_TYPE);
        mPresenter = new BookCategoryPresenter(bcvCategory, bookTypeModel);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyFetchData() {

    }
}
