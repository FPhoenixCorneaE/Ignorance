package com.livelearn.ignorance.presenter.contract.book;

import android.support.annotation.NonNull;

import com.livelearn.ignorance.base.BasePresenter;
import com.livelearn.ignorance.model.bean.book.BookTypeModel;

import java.util.List;

/**
 * Created on 2017/2/14.
 */

public interface BookClassContract {

    interface View {
        //设置主导器
        void setPresenter(@NonNull Presenter presenter, List<BookTypeModel> bookTypeList);
    }

    interface Presenter extends BasePresenter {

    }
}
