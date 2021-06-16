package com.livelearn.ignorance.presenter.contract.book;


import androidx.fragment.app.Fragment;

import com.livelearn.ignorance.base.BasePresenter;
import com.livelearn.ignorance.base.BaseView;
import com.livelearn.ignorance.model.bean.book.BookTypeModel;

import java.util.List;

/**
 * Created on 2017/2/10.
 */

public interface LongTimeBookContract {

    interface View extends BaseView<Presenter, List<BookTypeModel>> {

        void setPresenter(Fragment fragment, Presenter mPresenter);

        void noData();

        void hasData(List<BookTypeModel> bookTypeModelList);

        void setListeners();

    }

    interface Presenter extends BasePresenter {

        void getBookTypeList();

    }
}
