package com.livelearn.ignorance.ui.view.book;

import android.content.Context;
import androidx.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.presenter.contract.book.SearchBookContract;
import com.livelearn.ignorance.utils.ToastUtils;
import com.livelearn.ignorance.widget.RootView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 搜索书籍
 * Created on 2017/2/23.
 */

public class SearchBookView extends RootView implements SearchBookContract.View, SearchBookLabelView.OnTagCloudChildClickListener {

    @BindView(R.id.et_search_keyword)
    EditText etSearchKeyword;

    @BindView(R.id.iv_search_keyword_delete)
    ImageView ivSearchKeywordDelete;

    @BindView(R.id.ll_search_keyword)
    LinearLayout llSearchKeyword;

    @BindView(R.id.btn_search)
    Button btnSearch;

    @BindView(R.id.fl_container)
    FrameLayout flContainer;

    private SearchBookContract.Presenter mPresenter;

    public SearchBookView(Context context) {
        super(context);
    }

    public SearchBookView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchBookView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.view_search_book;
    }

    @Override
    public void init() {

    }

    @Override
    public void setPresenter(@NonNull SearchBookContract.Presenter presenter) {
        this.mPresenter = presenter;
        presenter.addSearchBookLabelFragment((BaseActivity) mContext, R.id.fl_container);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onSuccess(Void mData) {

    }

    @Override
    public void onFailure(String msg) {

    }

    @OnClick({R.id.et_search_keyword, R.id.iv_search_keyword_delete, R.id.ll_search_keyword, R.id.btn_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_search_keyword:
                etSearchKeyword.setFocusable(true);
                etSearchKeyword.setFocusableInTouchMode(true);
                etSearchKeyword.requestFocus();
                break;
            case R.id.iv_search_keyword_delete:
                etSearchKeyword.setText("");
                mPresenter.showSearchBookLabel((BaseActivity) mContext, R.id.fl_container);
                break;
            case R.id.ll_search_keyword:
                etSearchKeyword.setFocusable(true);
                etSearchKeyword.setFocusableInTouchMode(true);
                etSearchKeyword.requestFocus();
                break;
            case R.id.btn_search:
                String searchKeyword = etSearchKeyword.getText().toString();
                if (searchKeyword.isEmpty()) {
                    ToastUtils.showToast("请输入书名或作者");
                    return;
                }
                //失去焦点
                etSearchKeyword.setFocusable(false);
                etSearchKeyword.clearFocus();

                mPresenter.showSearchBookList((BaseActivity) mContext, R.id.fl_container, searchKeyword);
                break;
        }
    }

    @Override
    public void setListeners() {
        //输入框焦点监听
        etSearchKeyword.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    llSearchKeyword.setBackgroundResource(R.drawable.shape_background_layout_search_keyword_focuse);
                } else {
                    llSearchKeyword.setBackgroundResource(R.drawable.shape_background_layout_search_keyword_normal);
                    //关闭软键盘
                    InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etSearchKeyword.getWindowToken(), 0);
                }
            }
        });
        //输入框文字监听
        etSearchKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    ivSearchKeywordDelete.setVisibility(GONE);
                } else {
                    ivSearchKeywordDelete.setVisibility(VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public SearchBookLabelView.OnTagCloudChildClickListener getOnTagCloudChildClickListener() {
        return this;
    }

    /**
     * 标签点击
     *
     * @param keyword 关键字
     */
    @Override
    public void onClick(String keyword) {
        etSearchKeyword.setFocusable(false);
        etSearchKeyword.clearFocus();
        etSearchKeyword.setText(keyword);

        mPresenter.showSearchBookList((BaseActivity) mContext, R.id.fl_container, keyword);
    }
}
