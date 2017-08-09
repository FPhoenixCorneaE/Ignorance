package com.livelearn.ignorance.presenter.contract;


import com.livelearn.ignorance.base.BasePresenter;
import com.livelearn.ignorance.base.BaseView;

import java.util.List;

public interface WelcomeContract {

    interface View extends BaseView<Presenter, List<String>> {

        void startMainActivity();

        void startGuideActivity();

        void doLogin();

    }

    interface Presenter extends BasePresenter {

        void showWelcomeData();

    }
}
