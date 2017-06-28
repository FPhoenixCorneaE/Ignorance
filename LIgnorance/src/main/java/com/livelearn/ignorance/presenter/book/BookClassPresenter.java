package com.livelearn.ignorance.presenter.book;

import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.model.bean.book.BookTypeModel;
import com.livelearn.ignorance.presenter.contract.book.BookClassContract;

import java.util.List;

/**
 * Created on 2017/2/14.
 */

public class BookClassPresenter extends RxPresenter implements BookClassContract.Presenter {

    public BookClassPresenter(BookClassContract.View mView, List<BookTypeModel> bookTypeList) {
        mView.setPresenter(this, bookTypeList);
    }
}
