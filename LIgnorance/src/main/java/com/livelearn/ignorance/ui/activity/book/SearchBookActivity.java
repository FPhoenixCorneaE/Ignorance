package com.livelearn.ignorance.ui.activity.book;

import android.os.Bundle;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.presenter.book.SearchBookPresenter;
import com.livelearn.ignorance.ui.view.book.SearchBookView;
import com.livelearn.ignorance.widget.TitleBar;

import butterknife.BindView;

public class SearchBookActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.sbv_search_book)
    SearchBookView sbvSearchBook;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_search_book;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText("搜索");

        mPresenter = new SearchBookPresenter(sbvSearchBook);
    }

    @Override
    public void setListeners() {

    }
}
