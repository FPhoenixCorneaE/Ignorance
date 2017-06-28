package com.livelearn.ignorance.presenter.contract.book.doubanbook;

import android.content.Context;

import com.livelearn.ignorance.base.BasePresenter;
import com.livelearn.ignorance.base.BaseView;
import com.livelearn.ignorance.model.bean.book.douban.DouBanBookDetailsBean;

/**
 * Created on 2017/5/10.
 */

public interface DouBanBookDetailsContract {

    interface View extends BaseView<Presenter, DouBanBookDetailsBean> {

        Context getContext();

        void showBookReviewDetails(String bookReviewTitle, String bookReviewFullContent);
    }

    interface Presenter extends BasePresenter {

        void getDouBanBookDetailsByBookId(String bookId, String bookName);

        void getBookDetailsFromInternet(String bookId, String bookName);

        void getBookReviewDetails(String url, String bookNameReviewTitle);
    }
}
