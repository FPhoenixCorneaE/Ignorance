package com.livelearn.ignorance.ui.fragment.book.doubanbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.presenter.book.doubanbook.DouBanBookPresenter;
import com.livelearn.ignorance.ui.activity.MainActivity;
import com.livelearn.ignorance.ui.view.book.doubanbook.DouBanBookView;

import butterknife.BindView;

/**
 * Created on 2017/5/8.
 */

public class DouBanBookFragment extends BaseFragment {

    @BindView(R.id.v_dou_ban_book)
    DouBanBookView vDouBanBook;

    private DouBanBookView.OnPanelStateChangedListener mOnPanelStateChangedListener;

    public void setOnPanelStateChangedListener(DouBanBookView.OnPanelStateChangedListener mOnPanelStateChangedListener) {
        this.mOnPanelStateChangedListener = mOnPanelStateChangedListener;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_dou_ban_book;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenter = new DouBanBookPresenter(this, vDouBanBook);
    }

    @Override
    public void setListeners() {
        vDouBanBook.setOnPanelStateChangedListener(mOnPanelStateChangedListener);
        ((MainActivity) mContext).addOnBackPressedListener(new MainActivity.OnBackPressedListener() {
            @Override
            public boolean onPressed() {
                return vDouBanBook.onBackPressed();
            }
        });
    }

    @Override
    public void lazyFetchData() {

    }
}
