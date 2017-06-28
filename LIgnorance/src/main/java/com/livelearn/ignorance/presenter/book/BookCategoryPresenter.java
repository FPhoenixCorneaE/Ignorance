package com.livelearn.ignorance.presenter.book;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.model.bean.book.BookListModel;
import com.livelearn.ignorance.model.bean.book.BookTypeModel;
import com.livelearn.ignorance.presenter.contract.book.BookCategoryContract;
import com.livelearn.ignorance.retrofitinterface.rxapi.LongTimeBookAPIs;

import java.util.List;

import rx.Observer;
import rx.Subscription;

/**
 * Created on 2017/2/13.
 */

public class BookCategoryPresenter extends RxPresenter implements BookCategoryContract.Presenter {

    private BookCategoryContract.View mBookCategoryView;
    private BookTypeModel bookTypeModel;

    public BookCategoryPresenter(BookCategoryContract.View mBookCategoryView, BookTypeModel bookTypeModel) {
        this.mBookCategoryView = mBookCategoryView;
        this.bookTypeModel = bookTypeModel;
        mBookCategoryView.setPresenter(this, bookTypeModel);
        mBookCategoryView.showProgress();
    }

    @Override
    public void onLoadData(int startPage) {
        Subscription subscription = LongTimeBookAPIs.getDefault().getBookTypeList(bookTypeModel, startPage, new Observer<List<BookListModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                String detailMessage = e.getMessage();
                mBookCategoryView.onFailure(detailMessage);
                LogUtils.e(detailMessage);
            }

            @Override
            public void onNext(List<BookListModel> bookListModels) {
                mBookCategoryView.onSuccess(bookListModels);
            }
        });
        addSubscribe(subscription);
    }
}
