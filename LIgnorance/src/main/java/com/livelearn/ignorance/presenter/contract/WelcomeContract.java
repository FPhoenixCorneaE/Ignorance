package com.livelearn.ignorance.presenter.contract;


import com.livelearn.ignorance.base.BasePresenter;
import com.livelearn.ignorance.base.BaseView;

import java.util.List;

public interface WelcomeContract {

    interface View extends BaseView<Presenter, List<String>> {

        boolean isActive();

        void jumpToNextActivity();

    }

    interface Presenter extends BasePresenter {

        void showWelcomeData();

    }
}
