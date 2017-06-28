package com.livelearn.ignorance.ui.view.book;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.bean.book.BookDetailsModel;
import com.livelearn.ignorance.presenter.contract.book.BookDetailsContract;
import com.livelearn.ignorance.utils.GlideUtils;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.RootView;
import com.ms.expandable.ExpandableTextView;
import com.xiaochao.lcrapiddeveloplibrary.viewtype.ProgressActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 书籍详情
 * Created on 2017/2/13.
 */

public class BookDetailsView extends RootView implements BookDetailsContract.View {

    @BindView(R.id.iv_book_url)
    ImageView ivBookUrl;

    @BindView(R.id.tv_book_name)
    TextView tvBookName;

    @BindView(R.id.tv_book_author)
    TextView tvBookAuthor;

    @BindView(R.id.tv_book_type)
    TextView tvBookType;

    @BindView(R.id.tv_book_length)
    TextView tvBookLength;

    @BindView(R.id.tv_book_progress)
    TextView tvBookProgress;

    @BindView(R.id.tv_book_update_time)
    TextView tvBookUpdateTime;

    @BindView(R.id.ll_online_reading)
    LinearLayout llOnlineReading;

    @BindView(R.id.ll_file_download)
    LinearLayout llFileDownload;

    @BindView(R.id.tv_book_introduction)
    ExpandableTextView tvBookIntroduction;

    @BindView(R.id.pa_progress)
    ProgressActivity paProgress;

    private BookDetailsContract.Presenter mPresenter;
    private BookDetailsModel bookDetailsModel;

    public BookDetailsView(Context context) {
        super(context);
    }

    public BookDetailsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BookDetailsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.view_book_details;
    }

    @Override
    public void init() {

    }

    @Override
    public void setListeners() {
        tvBookIntroduction.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {

            }
        });
    }

    @Override
    public void setPresenter(@NonNull BookDetailsContract.Presenter presenter) {
        this.mPresenter = presenter;
        mPresenter.getBookDetails();
        showProgress();
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
    public void onSuccess(BookDetailsModel data) {
        if (data == null || "".equals(data.getBookName())) {
            showError();
        } else {
            hideProgress();

            bookDetailsModel = data;

            GlideUtils.setupImage(mContext, ivBookUrl, data.getBookImageUrl(), R.mipmap.ic_nocover);
            tvBookName.setText(data.getBookName());
            tvBookAuthor.setText(data.getBookAuthor());
            tvBookType.setText(data.getBookType());
            tvBookLength.setText(data.getBookLength());
            tvBookProgress.setText(data.getBookProgress());
            tvBookUpdateTime.setText(data.getBookUpdateTime());
            tvBookIntroduction.setText(Html.fromHtml(data.getBookIntroduction()));
        }
    }

    @Override
    public void onFailure(String msg) {
        showError();
    }

    private void showError() {
        paProgress.showError(ResourceUtils.getDrawable(R.mipmap.pic_load_error), Constant.ERROR_TITLE, Constant.ERROR_CONTEXT, Constant.ERROR_BUTTON, new OnClickListener() {
            @Override
            public void onClick(View view) {
                paProgress.showLoading();
                //重试
                mPresenter.getBookDetails();
            }
        });
    }

    @OnClick({R.id.ll_online_reading, R.id.ll_file_download})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_online_reading:
                IntentUtils.openUrl(mContext, bookDetailsModel.getBookReadUrl());
                break;
            case R.id.ll_file_download:

                break;
        }
    }
}
