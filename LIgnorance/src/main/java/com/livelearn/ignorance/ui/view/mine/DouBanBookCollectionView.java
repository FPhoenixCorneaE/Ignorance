package com.livelearn.ignorance.ui.view.mine;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.db.dbentity.DouBanBookCollection;
import com.livelearn.ignorance.presenter.contract.mine.DouBanBookCollectionContract;
import com.livelearn.ignorance.ui.activity.book.doubanbook.DouBanBookDetailsActivity;
import com.livelearn.ignorance.ui.adapter.mine.DouBanBookCollectionGridAdapter;
import com.livelearn.ignorance.ui.adapter.mine.DouBanBookCollectionListAdapter;
import com.livelearn.ignorance.ui.adapter.viewholder.mine.DouBanBookCollectionGridViewHolder;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.SharedPreferencesUtils;
import com.livelearn.ignorance.widget.RootView;

import java.util.List;

import butterknife.BindView;

/**
 * Created on 2017/7/24.
 */

public class DouBanBookCollectionView extends RootView implements DouBanBookCollectionContract.View {

    @BindView(R.id.rv_book_collection)
    EasyRecyclerView rvBookCollection;

    private DouBanBookCollectionContract.Presenter mPresenter;
    private RecyclerArrayAdapter mAdapter;
    private int pageNum;
    private final int PAGE_SIZE = 10;
    private String arrangementMode;

    public DouBanBookCollectionView(Context context) {
        super(context);
    }

    public DouBanBookCollectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DouBanBookCollectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.view_book_collection;
    }

    @Override
    public void init() {
        arrangementMode = SharedPreferencesUtils.getString(Constant.USER_INFO, Constant.BOOK_COLLECTION_ARRANGEMENT_MODE);
        initialLayoutManager();
    }

    private void initialLayoutManager() {
        RecyclerView.LayoutManager mLayoutManager;
        if (Constant.BOOK_COLLECTION_ARRANGEMENT_MODE_LIST.equals(arrangementMode)) {
            mLayoutManager = new LinearLayoutManager(mContext);
            mAdapter = new DouBanBookCollectionListAdapter(mContext);
        } else {
            mLayoutManager = new GridLayoutManager(mContext, 3);
            mAdapter = new DouBanBookCollectionGridAdapter(mContext);
            ((GridLayoutManager) mLayoutManager).setSpanSizeLookup(mAdapter.obtainGridSpanSizeLookUp(3));
        }

        rvBookCollection.setLayoutManager(mLayoutManager);
        rvBookCollection.setAdapter(mAdapter);
    }

    @Override
    public void setPresenter(@NonNull DouBanBookCollectionContract.Presenter mPresenter) {
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

    @SuppressWarnings("unchecked")
    @Override
    public void onSuccess(List<DouBanBookCollection> mData) {
        hideProgress();
        if (pageNum == 0) {
            mAdapter.clear();
        }
        mAdapter.addAll(mData);
    }

    @Override
    public void onFailure(String message) {
        if (pageNum == 0) {
            rvBookCollection.showError();
        } else {
            mAdapter.pauseMore();
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
        mAdapter.setMore(R.layout.custom_view_footer_loadmore_easy_recycler_view, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                mPresenter.queryBookCollectionByPage(++pageNum, PAGE_SIZE);
            }

            @Override
            public void onMoreClick() {
            }
        });
        mAdapter.setNoMore(R.layout.custom_view_footer_nomore_easy_recycler_view, new RecyclerArrayAdapter.OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {
            }

            @Override
            public void onNoMoreClick() {
                mAdapter.resumeMore();
            }
        });
        mAdapter.setError(R.layout.custom_view_footer_error_easy_recycler_view, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
            }

            @Override
            public void onErrorClick() {
                mAdapter.resumeMore();
            }
        });
        if (mAdapter instanceof DouBanBookCollectionListAdapter) {
            mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    startDouBanBookDetailsActivity(((DouBanBookCollectionListAdapter) mAdapter).getItem(position).getBook_id(), ((DouBanBookCollectionListAdapter) mAdapter).getItem(position).getBook_name());
                }
            });
        } else {
            ((DouBanBookCollectionGridAdapter) mAdapter).setmOnItemClickListener(new DouBanBookCollectionGridViewHolder.OnItemClickListener() {
                @Override
                public void onClick(int position, String bookId, String bookName) {
                    startDouBanBookDetailsActivity(bookId, bookName);
                }
            });
        }
    }

    /**
     * 启动豆瓣图书详情
     *
     * @param bookId   图书id
     * @param bookName 图书名称
     */
    private void startDouBanBookDetailsActivity(String bookId, String bookName) {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.BOOK_ID, bookId);
        bundle.putString(Constant.BOOK_NAME, bookName);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(mContext, DouBanBookDetailsActivity.class);
            intent.putExtras(bundle);
            mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((BaseActivity) mContext).toBundle());
        } else {
            IntentUtils.startActivity(mContext, DouBanBookDetailsActivity.class, bundle);
        }
    }

    /**
     * 更新图书收藏
     */
    public void updateBookCollection() {
        mPresenter.queryBookCollectionByPage(pageNum = 0, PAGE_SIZE);
    }

    /**
     * 切换排列方式
     */
    public void toggleArrangementMode(String arrangementMode) {
        this.arrangementMode = arrangementMode;
        initialLayoutManager();
        setListeners();
        mPresenter.queryBookCollectionByPage(pageNum = 0, PAGE_SIZE);
    }
}
