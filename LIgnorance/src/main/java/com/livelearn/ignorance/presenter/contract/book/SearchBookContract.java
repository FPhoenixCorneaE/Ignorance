package com.livelearn.ignorance.presenter.contract.book;

import android.support.v4.app.FragmentActivity;

import com.livelearn.ignorance.base.BasePresenter;
import com.livelearn.ignorance.base.BaseView;
import com.livelearn.ignorance.ui.view.book.SearchBookLabelView;

public interface SearchBookContract {

    interface View extends BaseView<Presenter, Void> {

        SearchBookLabelView.OnTagCloudChildClickListener getOnTagCloudChildClickListener();

        void setListeners();

    }

    interface Presenter extends BasePresenter {

        void addSearchBookLabelFragment(FragmentActivity activity, int containerViewId);

        void showSearchBookLabel(FragmentActivity activity, int containerViewId);

        void showSearchBookList(FragmentActivity activity, int containerViewId, String keyword);

    }
}
