package com.livelearn.ignorance.presenter.contract.book;


import androidx.annotation.NonNull;

import com.livelearn.ignorance.base.BasePresenter;
import com.livelearn.ignorance.base.BaseView;
import com.livelearn.ignorance.model.bean.book.BookListModel;
import com.livelearn.ignorance.model.bean.book.BookTypeModel;

import java.util.List;

/**
 * Created on 2017/2/10.
 */

public interface BookCategoryContract {

    interface View extends BaseView<Presenter, List<BookListModel>> {

        //下拉刷新
        void pullRefresh(List<BookListModel> newBooks);

        //上拉加载更多
        void pullOnLoadMore(List<BookListModel> loadMoreBooks);

        void setListeners();

        void setPresenter(@NonNull Presenter presenter, BookTypeModel bookTypeModel);

        void noData();

        void loadCompleteAllData();

    }

    interface Presenter extends BasePresenter {

        void onLoadData(int startPage);

    }
}
