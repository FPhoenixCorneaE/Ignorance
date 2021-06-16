package com.livelearn.ignorance.ui.view.book.doubanbook;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.base.BaseRecyclerAdapter;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.bean.book.douban.DouBanBookBean;
import com.livelearn.ignorance.presenter.contract.book.doubanbook.DouBanBookPagerContract;
import com.livelearn.ignorance.ui.activity.book.doubanbook.DouBanBookDetailsActivity;
import com.livelearn.ignorance.ui.adapter.book.doubanbook.DouBanBookPagerAdapter;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.NetworkUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.utils.ToastUtils;
import com.livelearn.ignorance.widget.RootView;
import com.livelearn.ignorance.widget.viewpagertransformer.OverspreadTransformer;
import com.xiaochao.lcrapiddeveloplibrary.viewtype.ProgressActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created on 2017/1/20.
 */

public class DouBanBookPagerView extends RootView implements DouBanBookPagerContract.View {

    public static final int RECORD_COUNT = 18;

    @BindView(R.id.rv_book_pager)
    RecyclerView rvBookPager;

    @BindView(R.id.srl_book_pager)
    SwipeRefreshLayout srlBookPager;

    @BindView(R.id.fab_return_top)
    FloatingActionButton fabReturnTop;

    @BindView(R.id.pa_progress)
    ProgressActivity paProgress;

    private DouBanBookPagerContract.Presenter mPresenter;
    private DouBanBookPagerAdapter douBanBookPagerAdapter;
    private View footerView;
    private int mStart = 0;
    private PULLMode mPullMode;

    public DouBanBookPagerView(Context context) {
        super(context);
    }

    public DouBanBookPagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DouBanBookPagerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.view_book_pager;
    }

    @Override
    public void init() {
        isInFragment = true;

        //下拉刷新
        srlBookPager.setColorSchemeResources(R.color.color_light_blue, R.color.purple, R.color.color_light_red);
        srlBookPager.setProgressViewOffset(false, 0, 48);

        //遮盖滑动效果需要用到
        rvBookPager.setTag(OverspreadTransformer.PARALLAX_EFFECT);

        //RecyclerView
        List<DouBanBookBean.BooksBean> mBookList = new ArrayList<>();
        GridLayoutManager mLayoutManager = new GridLayoutManager(mContext, 3);
        rvBookPager.setLayoutManager(mLayoutManager);
        douBanBookPagerAdapter = new DouBanBookPagerAdapter(mContext, mBookList);
        footerView = LayoutInflater.from(mContext).inflate(R.layout.custom_view_footer_loadmore, rvBookPager, false);
        douBanBookPagerAdapter.setFooterView(footerView);
        rvBookPager.setAdapter(douBanBookPagerAdapter);
    }

    @Override
    public void setPresenter(@NonNull DouBanBookPagerContract.Presenter presenter) {
        this.mPresenter = presenter;
        showProgress();
        pullRefresh();
    }

    @Override
    public void showProgress() {
        paProgress.showLoading();
    }

    @Override
    public void hideProgress() {
        paProgress.showContent();
    }

    @Override
    public void onSuccess(DouBanBookBean booksBeen) {
        hideProgress();
        onCompleted();
        if (booksBeen != null && booksBeen.getBooks() != null) {
            if (PULLMode.PULL_TO_REFRESH == mPullMode) {
                douBanBookPagerAdapter.updateDatas(booksBeen.getBooks());
            } else {
                douBanBookPagerAdapter.addDatas(booksBeen.getBooks());
            }
        }
    }

    @Override
    public void onFailure(String msg) {
        if (PULLMode.PULL_TO_REFRESH == mPullMode) {
            paProgress.showError(ResourceUtils.getDrawable(R.mipmap.pic_load_error), Constant.ERROR_TITLE, Constant.ERROR_CONTEXT, Constant.ERROR_BUTTON, new OnClickListener() {
                @Override
                public void onClick(View v) {
                    showProgress();
                    //重试
                    pullRefresh();
                }
            });
        } else {
            ToastUtils.showToast("电波无法到达哟");
            footerView.setVisibility(GONE);
        }
    }

    @Override
    public void onCompleted() {
        if (srlBookPager.isRefreshing()) {
            srlBookPager.setRefreshing(false);
        }
        footerView.setVisibility(GONE);
    }

    @Override
    public void pullRefresh() {
        mPullMode = PULLMode.PULL_TO_REFRESH;
        srlBookPager.setRefreshing(true);

        mPresenter.refreshData(0, RECORD_COUNT);
    }

    @Override
    public void pullOnLoading() {
        mPullMode = PULLMode.PULL_ON_LOADING;

        if (douBanBookPagerAdapter.getStart() == mStart) {
            return;
        }

        mStart = douBanBookPagerAdapter.getStart();
        mPresenter.loadingData(mStart, RECORD_COUNT);
    }

    @Override
    public void setListeners() {
        douBanBookPagerAdapter.setOnClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, String bookId, String bookName) {
                Bundle bundle = new Bundle();
                bundle.putString(Constant.BOOK_ID, bookId);
                bundle.putString(Constant.BOOK_NAME, bookName);
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                    Intent intent = new Intent(mContext, DouBanBookDetailsActivity.class);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent,
                            ActivityOptions.makeSceneTransitionAnimation((BaseActivity) mContext).toBundle());
                } else {
                    IntentUtils.startActivity(mContext, DouBanBookDetailsActivity.class, bundle);
                }
            }
        });

        //顶部下拉松开时会调用这个方法
        srlBookPager.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullRefresh();
            }
        });

        //悬浮按钮，点击返回顶部
        fabReturnTop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rvBookPager.getAdapter() != null) {
                    rvBookPager.scrollToPosition(0);
                }
            }
        });

        //RecyclerView滑动监听
        rvBookPager.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //如果 dx>0 则表示右滑 ， dx<0 表示左滑;dy<0 表示上滑， dy>0 表示下滑;通过这几个参数就可以监听滑动方向的状态。
                if (dy > 0) {
                    fabReturnTop.hide();
                } else if (dy < 0) {
                    fabReturnTop.show();
                }
                if (!recyclerView.canScrollVertically(1) && NetworkUtils.isNetworkAvailable()) {
                    pullOnLoading();
                    footerView.setVisibility(View.VISIBLE);
                    rvBookPager.scrollToPosition(douBanBookPagerAdapter.getItemCount() - 1);
                }
            }
        });
    }

    private enum PULLMode {
        PULL_TO_REFRESH,
        PULL_ON_LOADING
    }
}
