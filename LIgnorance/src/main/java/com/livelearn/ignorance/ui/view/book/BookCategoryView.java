package com.livelearn.ignorance.ui.view.book;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.bean.book.BookListModel;
import com.livelearn.ignorance.model.bean.book.BookTypeModel;
import com.livelearn.ignorance.presenter.contract.book.BookCategoryContract;
import com.livelearn.ignorance.ui.activity.book.LongTimeBookDetailsActivity;
import com.livelearn.ignorance.ui.adapter.book.BookCategoryAdapter;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.RootView;
import com.livelearn.ignorance.widget.viewpagertransformer.OverspreadTransformer;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.container.DefaultHeader;
import com.xiaochao.lcrapiddeveloplibrary.viewtype.ProgressActivity;
import com.xiaochao.lcrapiddeveloplibrary.widget.SpringView;

import java.util.List;

import butterknife.BindView;

/**
 * 书籍种类
 */

public class BookCategoryView extends RootView implements BookCategoryContract.View {

    @BindView(R.id.rv_book_category)
    RecyclerView rvBookCategory;

    @BindView(R.id.sv_book_category)
    SpringView svBookCategory;

    @BindView(R.id.pa_progress)
    ProgressActivity paProgress;

    private BookCategoryAdapter bookCategoryAdapter;
    private BookTypeModel bookTypeModel;
    private int startPage;
    private boolean isLoad;
    private BookCategoryContract.Presenter mPresenter;

    public BookCategoryView(Context context) {
        super(context);
    }

    public BookCategoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BookCategoryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.view_book_category;
    }

    @Override
    public void init() {
        isInFragment = true;

        //遮盖滑动效果需要用到
        rvBookCategory.setTag(OverspreadTransformer.PARALLAX_EFFECT);

        rvBookCategory.setLayoutManager(new LinearLayoutManager(mContext));
        //如果Item高度固定  增加该属性能够提高效率
        rvBookCategory.setHasFixedSize(true);
        //创建适配器
        bookCategoryAdapter = new BookCategoryAdapter(null);
        //设置加载动画
        bookCategoryAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //将适配器添加到RecyclerView
        rvBookCategory.setAdapter(bookCategoryAdapter);

        svBookCategory.setHeader(new DefaultHeader(mContext));
    }

    @Override
    public void setPresenter(@NonNull BookCategoryContract.Presenter presenter, BookTypeModel bookTypeModel) {
        //设置是否自动加载以及加载个数
        bookCategoryAdapter.openLoadMore(bookTypeModel.getPageLength(), true);

        this.mPresenter = presenter;
        this.bookTypeModel = bookTypeModel;
        startPage = bookTypeModel.getStartPage();
        isLoad = false;
        mPresenter.onLoadData(startPage);
    }

    @Override
    public void noData() {
        paProgress.showEmpty(ResourceUtils.getDrawable(R.mipmap.pic_load_no_data), Constant.EMPTY_BOOKS_TITLE, Constant.EMPTY_BOOKS_CONTEXT);
    }

    @Override
    public void loadCompleteAllData() {
        //所有数据加载完成后显示
        try {
            bookCategoryAdapter.notifyDataChangedAfterLoadMore(false);
            View view = ((Activity) mContext).getLayoutInflater().inflate(R.layout.footer_no_more, (ViewGroup) rvBookCategory.getParent(), false);
            bookCategoryAdapter.addFooterView(view);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    public void setPresenter(@NonNull BookCategoryContract.Presenter presenter) {

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
    public void onSuccess(List<BookListModel> data) {
        if (isLoad) {
            if (startPage <= bookTypeModel.getEndPage()) {
                if (data.isEmpty()) {
                    loadCompleteAllData();
                } else {
                    pullOnLoadMore(data);
                }
            } else {
                loadCompleteAllData();
            }
        } else {
            if (data.isEmpty()) {
                noData();
            } else {
                pullRefresh(data);
                hideProgress();
            }
        }
    }

    @Override
    public void onFailure(String msg) {
        loadCompleteAllData();
        paProgress.showError(ResourceUtils.getDrawable(R.mipmap.pic_load_error), Constant.ERROR_TITLE, Constant.ERROR_CONTEXT, Constant.ERROR_BUTTON, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paProgress.showLoading();
                //重试
                startPage = bookTypeModel.getStartPage();
                isLoad = false;
                mPresenter.onLoadData(startPage);
            }
        });
    }

    @Override
    public void pullRefresh(List<BookListModel> newBooks) {
        bookCategoryAdapter.setNewData(newBooks);//新增数据
        bookCategoryAdapter.openLoadMore(bookTypeModel.getPageLength(), true);//设置是否可以下拉加载  以及加载条数
        svBookCategory.onFinishFreshAndLoad();//刷新完成
    }

    @Override
    public void pullOnLoadMore(List<BookListModel> loadMoreBooks) {
        bookCategoryAdapter.notifyDataChangedAfterLoadMore(loadMoreBooks, true);
    }

    @Override
    public void setListeners() {
        svBookCategory.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                startPage = bookTypeModel.getStartPage();
                isLoad = false;
                mPresenter.onLoadData(startPage);
            }

            @Override
            public void onLoadmore() {
            }
        });
        bookCategoryAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (startPage <= bookTypeModel.getEndPage()) {
                    startPage++;
                }
                isLoad = true;
                mPresenter.onLoadData(startPage);
            }
        });
        bookCategoryAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString(Constant.BOOK_URL, bookCategoryAdapter.getItem(position).getCodeId());
                bundle.putString(Constant.BOOK_NAME, bookCategoryAdapter.getItem(position).getBookName());
                IntentUtils.startActivity(mContext, LongTimeBookDetailsActivity.class, bundle);
            }
        });
    }
}
