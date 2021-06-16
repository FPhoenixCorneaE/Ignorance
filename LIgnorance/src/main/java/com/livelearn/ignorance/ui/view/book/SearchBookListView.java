package com.livelearn.ignorance.ui.view.book;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.bean.book.BookListModel;
import com.livelearn.ignorance.presenter.contract.book.SearchBookListContract;
import com.livelearn.ignorance.ui.activity.book.LongTimeBookDetailsActivity;
import com.livelearn.ignorance.ui.adapter.book.BookCategoryAdapter;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.RootView;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.viewtype.ProgressActivity;

import java.util.List;

import butterknife.BindView;

/**
 * Created on 2017/3/1.
 */

public class SearchBookListView extends RootView implements SearchBookListContract.View {

    @BindView(R.id.rv_search_book_list)
    RecyclerView rvSearchBookList;

    @BindView(R.id.pa_progress)
    ProgressActivity paProgress;

    private BookCategoryAdapter bookListAdapter;
    private String keyword;
    private SearchBookListContract.Presenter mPresenter;

    public SearchBookListView(Context context) {
        super(context);
    }

    public SearchBookListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchBookListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.view_search_book_list;
    }

    @Override
    public void init() {
        isInFragment = true;

        rvSearchBookList.setLayoutManager(new LinearLayoutManager(mContext));
        rvSearchBookList.setHasFixedSize(true);
        bookListAdapter = new BookCategoryAdapter(null);
        bookListAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        rvSearchBookList.setAdapter(bookListAdapter);
    }

    @Override
    public void setPresenter(@NonNull SearchBookListContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
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
    public void onSuccess(List<BookListModel> mData) {
        if (mData.isEmpty()) {
            showNoData();
        } else {
            hideProgress();
            bookListAdapter.setNewData(mData);
        }
    }

    @Override
    public void onFailure(String message) {
        paProgress.showError(ResourceUtils.getDrawable(R.mipmap.pic_load_error), Constant.ERROR_TITLE, Constant.ERROR_CONTEXT, Constant.ERROR_BUTTON, new OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress();
                //重试
                mPresenter.getSearchBookListByKeyword(keyword);
            }
        });
    }

    @Override
    public void showNoData() {
        paProgress.showEmpty(ResourceUtils.getDrawable(R.mipmap.pic_load_no_data), Constant.EMPTY_SEARCH_BOOK_TITLE, Constant.EMPTY_SEARCH_BOOK_CONTEXT);
    }

    @Override
    public void setListeners() {
        bookListAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString(Constant.BOOK_URL, bookListAdapter.getItem(position).getCodeId());
                bundle.putString(Constant.BOOK_NAME, bookListAdapter.getItem(position).getBookName());
                IntentUtils.startActivity(mContext, LongTimeBookDetailsActivity.class, bundle);
            }
        });
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
        showProgress();
        mPresenter.getSearchBookListByKeyword(keyword);
    }

    public void clearData() {
        this.keyword = "";
        bookListAdapter.getData().clear();
    }
}
