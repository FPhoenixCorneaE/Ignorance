package com.livelearn.ignorance.presenter.book;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.model.bean.book.BookListModel;
import com.livelearn.ignorance.presenter.contract.book.SearchBookListContract;
import com.livelearn.ignorance.retrofitinterface.rxapi.LongTimeBookAPIs;

import java.util.List;

import rx.Observer;
import rx.Subscription;

/**
 * Created on 2017/2/28.
 */

public class SearchBookListPresenter extends RxPresenter implements SearchBookListContract.Presenter {

    private SearchBookListContract.View mSearchBookListView;

    public SearchBookListPresenter(SearchBookListContract.View mSearchBookListView) {
        this.mSearchBookListView = mSearchBookListView;
        mSearchBookListView.setPresenter(this);
    }

    @Override
    public void getSearchBookListByKeyword(String keyword) {
        Subscription subscription = LongTimeBookAPIs.getDefault().getSearchBookListByKeyword(keyword, new Observer<List<BookListModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                String detailMessage = e.getMessage();
                mSearchBookListView.onFailure(detailMessage);
                LogUtils.e(detailMessage);
            }

            @Override
            public void onNext(List<BookListModel> bookListModels) {
                mSearchBookListView.onSuccess(bookListModels);
            }
        });
        addSubscribe(subscription);
    }
}
