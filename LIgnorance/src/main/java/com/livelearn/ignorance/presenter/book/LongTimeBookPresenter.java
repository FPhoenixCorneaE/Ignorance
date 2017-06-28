package com.livelearn.ignorance.presenter.book;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.model.bean.book.BookTypeModel;
import com.livelearn.ignorance.presenter.contract.book.LongTimeBookContract;
import com.livelearn.ignorance.retrofitinterface.rxapi.LongTimeBookAPIs;

import java.util.List;

import rx.Observer;
import rx.Subscription;

/**
 * Created on 2017/2/10.
 */

public class LongTimeBookPresenter extends RxPresenter implements LongTimeBookContract.Presenter {

    private LongTimeBookContract.View mBookView;

    public LongTimeBookPresenter(Fragment fragment, @NonNull LongTimeBookContract.View mBookView) {
        this.mBookView = mBookView;
        mBookView.showProgress();
        mBookView.setPresenter(fragment, this);
    }

    @Override
    public void getBookTypeList() {
        Subscription subscription = LongTimeBookAPIs.getDefault().getBookTypes(new Observer<List<BookTypeModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                String detailMessage = e.getMessage();
                mBookView.onFailure(detailMessage);
                LogUtils.e(detailMessage);
            }

            @Override
            public void onNext(List<BookTypeModel> bookTypeModels) {
                mBookView.onSuccess(bookTypeModels);
                mBookView.hideProgress();
            }
        });
        addSubscribe(subscription);
    }
}
