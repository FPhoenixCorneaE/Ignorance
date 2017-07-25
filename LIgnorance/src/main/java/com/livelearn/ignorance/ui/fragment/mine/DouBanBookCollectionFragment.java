package com.livelearn.ignorance.ui.fragment.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.presenter.mine.DouBanBookCollectionPresenter;
import com.livelearn.ignorance.ui.view.mine.DouBanBookCollectionView;

import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * Created on 2017/7/18.
 */

public class DouBanBookCollectionFragment extends BaseFragment {

    @BindView(R.id.v_dou_ban_book_collection)
    DouBanBookCollectionView vDouBanBookCollection;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_dou_ban_book_collection;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenter = new DouBanBookCollectionPresenter(vDouBanBookCollection);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyFetchData() {

    }

    /**
     * 图书收藏
     *
     * @param isCollectedSuccess 收藏成功与否
     */
    @Subscriber(tag = Constant.BOOK_COLLECTION_DOU_BAN, mode = ThreadMode.MAIN)
    public void onCollection(boolean isCollectedSuccess) {
        if (isCollectedSuccess) {
            vDouBanBookCollection.updateBookCollection();
        }
    }

    /**
     * 取消图书收藏
     *
     * @param isCancelCollectedSuccess 取消收藏成功与否
     */
    @Subscriber(tag = Constant.BOOK_COLLECTION_CANCEL_DOU_BAN, mode = ThreadMode.MAIN)
    public void onCancelCollection(boolean isCancelCollectedSuccess) {
        if (isCancelCollectedSuccess) {
            vDouBanBookCollection.updateBookCollection();
        }
    }

    /**
     * 列表或者九宫格显示
     *
     * @param arrangementMode 排列方式
     */
    @Subscriber(tag = Constant.BOOK_COLLECTION_ARRANGEMENT_MODE, mode = ThreadMode.MAIN)
    public void onToggleArrangementMode(String arrangementMode) {
        vDouBanBookCollection.toggleArrangementMode(arrangementMode);
    }
}
