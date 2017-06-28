package com.livelearn.ignorance.presenter.contract.book;

import com.livelearn.ignorance.base.BasePresenter;
import com.livelearn.ignorance.base.BaseView;

import java.util.List;

/**
 * Created on 2017/2/28.
 */

public interface SearchBookLabelContract {

    interface View extends BaseView<Presenter, List<String>> {

        void showNoData();

        void setListeners();

    }

    interface Presenter extends BasePresenter {

        void getSearchBookLable();

    }
}
