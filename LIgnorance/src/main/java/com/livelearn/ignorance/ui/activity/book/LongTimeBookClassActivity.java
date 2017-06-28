package com.livelearn.ignorance.ui.activity.book;

import android.os.Bundle;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.bean.book.BookTypeModel;
import com.livelearn.ignorance.presenter.book.BookClassPresenter;
import com.livelearn.ignorance.ui.view.book.BookClassView;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 全部分类
 */
public class LongTimeBookClassActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.bcv_book_class)
    BookClassView bcvBookClass;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_long_time_book_class;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(ResourceUtils.getString(R.string.book_class_title));

        ArrayList<BookTypeModel> bookTypeList = getIntent().getParcelableArrayListExtra(Constant.BOOK_TYPE);
        mPresenter = new BookClassPresenter(bcvBookClass, bookTypeList);
    }

    @Override
    public void setListeners() {

    }
}
