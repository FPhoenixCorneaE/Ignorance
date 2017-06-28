package com.livelearn.ignorance.presenter.contract.book;

import com.livelearn.ignorance.base.BasePresenter;
import com.livelearn.ignorance.base.BaseView;
import com.livelearn.ignorance.model.bean.book.BookListModel;

import java.util.List;

/**
 * Created on 2017/2/28.
 */

public interface SearchBookListContract {

    interface View extends BaseView<Presenter, List<BookListModel>> {

        void showNoData();

        void setListeners();

    }

    interface Presenter extends BasePresenter {

        void getSearchBookListByKeyword(String keyword);

    }
}
