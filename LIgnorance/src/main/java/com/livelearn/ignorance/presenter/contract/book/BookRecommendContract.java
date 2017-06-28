package com.livelearn.ignorance.presenter.contract.book;

import com.livelearn.ignorance.base.BasePresenter;
import com.livelearn.ignorance.base.BaseView;
import com.livelearn.ignorance.model.bean.book.BookRecommendModel;

/**
 * Created on 2016/12/26.
 */

public interface BookRecommendContract {

    interface View extends BaseView<Presenter, BookRecommendModel> {

        void setListeners();

    }

    interface Presenter extends BasePresenter {

        void getRecommendData();

    }
}
