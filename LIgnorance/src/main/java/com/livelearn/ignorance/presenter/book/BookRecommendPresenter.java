package com.livelearn.ignorance.presenter.book;


import androidx.annotation.NonNull;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.model.bean.book.BookRecommendModel;
import com.livelearn.ignorance.presenter.contract.book.BookRecommendContract;
import com.livelearn.ignorance.retrofitinterface.rxapi.LongTimeBookAPIs;

import rx.Observer;
import rx.Subscription;

/**
 * Created on 2016/12/26.
 */
public class BookRecommendPresenter extends RxPresenter implements BookRecommendContract.Presenter {


    private BookRecommendContract.View mBookRecommendView;

    public BookRecommendPresenter(@NonNull BookRecommendContract.View mBookRecommendView) {
        this.mBookRecommendView = mBookRecommendView;
        mBookRecommendView.setPresenter(this);
        mBookRecommendView.showProgress();
    }

    @Override
    public void getRecommendData() {
        Subscription subscription = LongTimeBookAPIs.getDefault().getBookRecommend(new Observer<BookRecommendModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                String detailMessage = e.getMessage();
                mBookRecommendView.onFailure(detailMessage);
                LogUtils.e(detailMessage);
            }

            @Override
            public void onNext(BookRecommendModel bookRecommendModel) {
                mBookRecommendView.hideProgress();
                mBookRecommendView.onSuccess(bookRecommendModel);
            }
        });
        addSubscribe(subscription);
    }
}
