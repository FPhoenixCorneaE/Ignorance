package com.livelearn.ignorance.ui.view.mine;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.db.dbentity.LongTimeBookCollection;
import com.livelearn.ignorance.presenter.contract.mine.LongTimeBookCollectionContract;
import com.livelearn.ignorance.ui.activity.book.LongTimeBookDetailsActivity;
import com.livelearn.ignorance.ui.adapter.book.LongTimeBookCollectionListAdapter;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.widget.RootView;

import java.util.List;

import butterknife.BindView;

/**
 * Created on 2017/7/19.
 */

public class LongTimeBookCollectionView extends RootView implements LongTimeBookCollectionContract.View {

    @BindView(R.id.rv_book_collection)
    EasyRecyclerView rvBookCollection;

    private RecyclerView.LayoutManager mLayoutManager;
    private LongTimeBookCollectionContract.Presenter mPresenter;
    private LongTimeBookCollectionListAdapter mListAdapter;
    private int pageNum;
    private final int PAGE_SIZE = 10;

    public LongTimeBookCollectionView(Context context) {
        super(context);
    }

    public LongTimeBookCollectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LongTimeBookCollectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.view_long_time_book_collection;
    }

    @Override
    public void init() {
        mLayoutManager = new LinearLayoutManager(mContext);
        rvBookCollection.setLayoutManager(mLayoutManager);

        mListAdapter = new LongTimeBookCollectionListAdapter(mContext);
        rvBookCollection.setAdapter(mListAdapter);
    }

    @Override
    public void setPresenter(@NonNull LongTimeBookCollectionContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;

        showProgress();
        this.mPresenter.queryBookCollectionByPage(pageNum, PAGE_SIZE);
    }

    @Override
    public void showProgress() {
        rvBookCollection.showProgress();
    }

    @Override
    public void hideProgress() {
        rvBookCollection.showRecycler();
    }

    @Override
    public void onSuccess(List<LongTimeBookCollection> mData) {
        hideProgress();
        if (pageNum == 0) {
            mListAdapter.clear();
        }
        mListAdapter.addAll(mData);
    }

    @Override
    public void onFailure(String message) {
        if (pageNum == 0) {
            rvBookCollection.showError();
        } else {
            mListAdapter.pauseMore();
        }
    }

    @Override
    public void setListeners() {
        rvBookCollection.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.queryBookCollectionByPage(pageNum = 0, PAGE_SIZE);
            }
        });
        mListAdapter.setMore(R.layout.custom_view_footer_loadmore_easy_recycler_view, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                mPresenter.queryBookCollectionByPage(++pageNum, PAGE_SIZE);
            }

            @Override
            public void onMoreClick() {
            }
        });
        mListAdapter.setNoMore(R.layout.custom_view_footer_nomore_easy_recycler_view, new RecyclerArrayAdapter.OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {
            }

            @Override
            public void onNoMoreClick() {
                mListAdapter.resumeMore();
            }
        });
        mListAdapter.setError(R.layout.custom_view_footer_error_easy_recycler_view, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
            }

            @Override
            public void onErrorClick() {
                mListAdapter.resumeMore();
            }
        });
        mListAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString(Constant.BOOK_URL, mListAdapter.getItem(position).getBook_read_url());
                bundle.putString(Constant.BOOK_NAME, mListAdapter.getItem(position).getBook_name());
                IntentUtils.startActivity(mContext, LongTimeBookDetailsActivity.class, bundle);
            }
        });
    }

    /**
     * 更新图书收藏
     */
    public void updateBookCollection() {
        mPresenter.queryBookCollectionByPage(pageNum = 0, PAGE_SIZE);
    }
}
