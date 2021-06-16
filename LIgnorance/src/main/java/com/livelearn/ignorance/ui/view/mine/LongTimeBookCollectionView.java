package com.livelearn.ignorance.ui.view.mine;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.db.dbentity.LongTimeBookCollection;
import com.livelearn.ignorance.presenter.contract.mine.LongTimeBookCollectionContract;
import com.livelearn.ignorance.ui.activity.book.LongTimeBookDetailsActivity;
import com.livelearn.ignorance.ui.adapter.mine.LongTimeBookCollectionGridAdapter;
import com.livelearn.ignorance.ui.adapter.mine.LongTimeBookCollectionListAdapter;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.SharedPreferencesUtils;
import com.livelearn.ignorance.widget.RootView;

import java.util.List;

import butterknife.BindView;

/**
 * Created on 2017/7/19.
 */

public class LongTimeBookCollectionView extends RootView implements LongTimeBookCollectionContract.View {

    @BindView(R.id.rv_book_collection)
    EasyRecyclerView rvBookCollection;

    private LongTimeBookCollectionContract.Presenter mPresenter;
    private RecyclerArrayAdapter mAdapter;
    private int pageNum;
    private final int PAGE_SIZE = 10;
    private String arrangementMode;

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
            mAdapter = new LongTimeBookCollectionListAdapter(mContext);
        } else {
            mLayoutManager = new GridLayoutManager(mContext, 3);
            mAdapter = new LongTimeBookCollectionGridAdapter(mContext);
            ((GridLayoutManager) mLayoutManager).setSpanSizeLookup(mAdapter.obtainGridSpanSizeLookUp(3));
        }

        rvBookCollection.setLayoutManager(mLayoutManager);
        rvBookCollection.setAdapter(mAdapter);
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

    @SuppressWarnings("unchecked")
    @Override
    public void onSuccess(List<LongTimeBookCollection> mData) {
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
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString(Constant.BOOK_URL, ((LongTimeBookCollection) mAdapter.getItem(position)).getBook_read_url());
                bundle.putString(Constant.BOOK_NAME, ((LongTimeBookCollection) mAdapter.getItem(position)).getBook_name());
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
