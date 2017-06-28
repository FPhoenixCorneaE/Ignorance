package com.livelearn.ignorance.ui.activity.book;

import android.os.Bundle;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.presenter.book.BookDetailsPresenter;
import com.livelearn.ignorance.ui.view.book.BookDetailsView;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.TitleBar;

import butterknife.BindView;

/**
 * 书籍详情
 */
public class LongTimeBookDetailsActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.bdv_book_details)
    BookDetailsView bdvBookDetails;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_long_time_book_details;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(ResourceUtils.getString(R.string.book_details_title));

        String bookUrl = getIntent().getStringExtra(Constant.BOOK_URL);
        String bookName = getIntent().getStringExtra(Constant.BOOK_NAME);
        mPresenter = new BookDetailsPresenter(bdvBookDetails, bookUrl, bookName);
    }

    @Override
    public void setListeners() {

    }
}
