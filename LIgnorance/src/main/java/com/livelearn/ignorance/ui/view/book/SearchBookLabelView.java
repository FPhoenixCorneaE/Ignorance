package com.livelearn.ignorance.ui.view.book;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.presenter.contract.book.SearchBookLabelContract;
import com.livelearn.ignorance.utils.NetworkUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.RootView;
import com.livelearn.ignorance.widget.flowlayout.TagCloudView;
import com.xiaochao.lcrapiddeveloplibrary.viewtype.ProgressActivity;

import java.util.List;

import butterknife.BindView;

/**
 * Created on 2017/3/1.
 */

public class SearchBookLabelView extends RootView implements SearchBookLabelContract.View {


    @BindView(R.id.tcv_search_label)
    TagCloudView tcvSearchLabel;

    @BindView(R.id.pa_progress)
    ProgressActivity paProgress;

    private SearchBookLabelContract.Presenter mPresenter;
    private OnTagCloudChildClickListener onTagCloudChildClickListener;

    public SearchBookLabelView(Context context) {
        super(context);
    }

    public SearchBookLabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchBookLabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.view_search_book_label;
    }

    @Override
    public void init() {
        isInFragment = true;
    }

    @Override
    public void setPresenter(@NonNull SearchBookLabelContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
        mPresenter.getSearchBookLable();
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
    public void onSuccess(List<String> mData) {
        if (mData.isEmpty()) {
            showNoData();
        } else {
            hideProgress();
            tcvSearchLabel.setTags(mData);
        }

    }

    @Override
    public void onFailure(String msg) {
        if (!NetworkUtils.isNetworkAvailable()) {
            showNoData();
        } else {
            paProgress.showError(ResourceUtils.getDrawable(R.mipmap.pic_load_error), Constant.ERROR_TITLE, Constant.ERROR_CONTEXT, Constant.ERROR_BUTTON, new OnClickListener() {
                @Override
                public void onClick(View view) {
                    showProgress();
                    //重试
                    mPresenter.getSearchBookLable();
                }
            });
        }
    }

    @Override
    public void showNoData() {
        paProgress.showEmpty(ResourceUtils.getDrawable(R.mipmap.pic_load_no_data), Constant.EMPTY_SEARCH_BOOK_TITLE, Constant.EMPTY_SEARCH_BOOK_CONTEXT);
    }

    @Override
    public void setListeners() {
        tcvSearchLabel.setOnTagClickListener(new TagCloudView.OnTagClickListener() {
            @Override
            public void onTagClick(int position) {
                if (onTagCloudChildClickListener != null) {
                    String label = tcvSearchLabel.getTags().get(position);
                    onTagCloudChildClickListener.onClick(label);
                }
            }
        });
    }

    public interface OnTagCloudChildClickListener {
        void onClick(String keyword);
    }

    public void setOnTagCloudChildClickListener(OnTagCloudChildClickListener onTagCloudChildClickListener) {
        this.onTagCloudChildClickListener = onTagCloudChildClickListener;
    }
}
