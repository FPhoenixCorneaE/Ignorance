package com.livelearn.ignorance.presenter.book;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.model.bean.book.BookDetailsModel;
import com.livelearn.ignorance.presenter.contract.book.BookDetailsContract;
import com.livelearn.ignorance.retrofitinterface.rxapi.LongTimeBookAPIs;

import rx.Observer;
import rx.Subscription;

/**
 * Created on 2017/2/15.
 */

public class BookDetailsPresenter extends RxPresenter implements BookDetailsContract.Presenter {

    private BookDetailsContract.View mBookDetailsView;
    private String bookUrl;
    private String bookName;

    public BookDetailsPresenter(BookDetailsContract.View mView, String bookUrl, String bookName) {
        this.mBookDetailsView = mView;
        this.bookUrl = bookUrl;
        this.bookName = bookName;
        mBookDetailsView.setPresenter(this);
    }

    @Override
    public void getBookDetails() {
        Subscription subscription = LongTimeBookAPIs.getDefault().getBookDetails(bookUrl, bookName, new Observer<BookDetailsModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                String detailMessage = e.getMessage();
                mBookDetailsView.onFailure(detailMessage);
                LogUtils.e(detailMessage);
            }

            @Override
            public void onNext(BookDetailsModel bookDetailsModel) {
                mBookDetailsView.onSuccess(bookDetailsModel);
            }
        });
        addSubscribe(subscription);
    }
}
