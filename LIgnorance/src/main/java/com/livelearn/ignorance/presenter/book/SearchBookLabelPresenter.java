package com.livelearn.ignorance.presenter.book;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.presenter.contract.book.SearchBookLabelContract;
import com.livelearn.ignorance.retrofitinterface.rxapi.LongTimeBookAPIs;

import java.util.List;

import rx.Observer;
import rx.Subscription;

/**
 * Created on 2017/2/28.
 */

public class SearchBookLabelPresenter extends RxPresenter implements SearchBookLabelContract.Presenter {

    private SearchBookLabelContract.View mSearchBookLabelView;

    public SearchBookLabelPresenter(SearchBookLabelContract.View mSearchBookLabelView) {
        this.mSearchBookLabelView = mSearchBookLabelView;
        mSearchBookLabelView.setPresenter(this);
        mSearchBookLabelView.showProgress();
    }

    @Override
    public void getSearchBookLable() {
        Subscription subscription = LongTimeBookAPIs.getDefault().getSearchBookLabel(new Observer<List<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                String detailMessage = e.getMessage();
                mSearchBookLabelView.onFailure(detailMessage);
                LogUtils.e(detailMessage);
            }

            @Override
            public void onNext(List<String> searchLabels) {
                mSearchBookLabelView.onSuccess(searchLabels);
            }
        });
        addSubscribe(subscription);
    }
}
