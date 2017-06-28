package com.livelearn.ignorance.presenter.contract.book;

import com.livelearn.ignorance.base.BasePresenter;
import com.livelearn.ignorance.base.BaseView;
import com.livelearn.ignorance.model.bean.book.BookDetailsModel;

/**
 * Created on 2017/2/15.
 */

public interface BookDetailsContract {

    interface View extends BaseView<Presenter, BookDetailsModel> {

    }

    interface Presenter extends BasePresenter {

        void getBookDetails();
    }
}
