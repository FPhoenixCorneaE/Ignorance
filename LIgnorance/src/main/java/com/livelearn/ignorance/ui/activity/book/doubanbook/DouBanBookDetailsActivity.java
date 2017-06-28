package com.livelearn.ignorance.ui.activity.book.doubanbook;

import android.os.Bundle;
import android.view.KeyEvent;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.presenter.book.doubanbook.DouBanBookDetailsPresenter;
import com.livelearn.ignorance.ui.view.book.doubanbook.DouBanBookDetailsView;

import butterknife.BindView;

public class DouBanBookDetailsActivity extends BaseActivity {

    @BindView(R.id.v_dou_ban_book_details)
    DouBanBookDetailsView vDouBanBookDetails;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_dou_ban_book_details;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        mPresenter = new DouBanBookDetailsPresenter(vDouBanBookDetails);
    }

    @Override
    public boolean hasHorizontalScrollChildView() {
        return true;
    }

    @Override
    public void setListeners() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return vDouBanBookDetails.onKeyDown(keyCode, event, super.onKeyDown(keyCode, event));
    }
}
