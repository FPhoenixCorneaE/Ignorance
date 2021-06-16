package com.livelearn.ignorance.presenter.contract.book.doubanbook;


import androidx.fragment.app.Fragment;

import com.livelearn.ignorance.base.BasePresenter;
import com.livelearn.ignorance.base.BaseView;

/**
 * Created on 2017/5/8.
 */

public interface DouBanBookContract {

    interface View extends BaseView<Presenter, Void> {

        void setPresenter(Fragment fragment, Presenter mPresenter);
    }

    interface Presenter extends BasePresenter {

    }
}
