package com.livelearn.ignorance.ui.view.book;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.bean.book.BookTypeModel;
import com.livelearn.ignorance.presenter.contract.book.LongTimeBookContract;
import com.livelearn.ignorance.ui.activity.book.LongTimeBookClassActivity;
import com.livelearn.ignorance.ui.fragment.book.BookCategoryFragment;
import com.livelearn.ignorance.ui.fragment.book.BookRecommendFragment;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.RootView;
import com.livelearn.ignorance.widget.smarttablayout.FragmentPagerItem;
import com.livelearn.ignorance.widget.smarttablayout.FragmentPagerItemAdapter;
import com.livelearn.ignorance.widget.smarttablayout.FragmentPagerItems;
import com.livelearn.ignorance.widget.smarttablayout.SmartTabLayout;
import com.livelearn.ignorance.widget.viewpager.jazzyviewpager.JazzyViewPager;
import com.livelearn.ignorance.widget.viewpagertransformer.OverspreadTransformer;
import com.xiaochao.lcrapiddeveloplibrary.viewtype.ProgressActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 久久书库
 * Created on 2017/2/10.
 */

public class LongTimeBookView extends RootView implements LongTimeBookContract.View {

    @BindView(R.id.stl_book)
    SmartTabLayout stlBook;

    @BindView(R.id.ll_book_category)
    LinearLayout llBookCategory;

    @BindView(R.id.jvp_book)
    JazzyViewPager jvpBook;

    @BindView(R.id.pa_progress)
    ProgressActivity paProgress;

    private FragmentPagerItems fragmentPagerItems;
    private List<BookTypeModel> bookTypeModelList;
    private LongTimeBookContract.Presenter presenter;
    private Fragment fragment;

    public LongTimeBookView(Context context) {
        super(context);
    }

    public LongTimeBookView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LongTimeBookView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.view_long_time_book;
    }

    @Override
    public void init() {
        isInFragment = true;
    }

    @Override
    public void setPresenter(Fragment fragment, LongTimeBookContract.Presenter mPresenter) {
        this.fragment = fragment;
        this.presenter = mPresenter;
        presenter.getBookTypeList();
    }

    @Override
    public void setPresenter(@NonNull LongTimeBookContract.Presenter presenter) {

    }

    @Override
    public void showProgress() {
        paProgress.showLoading();
    }

    @Override
    public void hideProgress() {
        paProgress.showContent();

        //Fragment嵌套Fragment,用Fragment的getChildFragmentManager()代替FragmentActivity的getSupportFragmentManager()
        FragmentPagerItemAdapter pagerItemAdapter = new FragmentPagerItemAdapter(fragment.getChildFragmentManager(), fragmentPagerItems);

        //遮盖转换效果
        jvpBook.setPageTransformer(true, new OverspreadTransformer());
        jvpBook.setAdapter(pagerItemAdapter);
        jvpBook.setOffscreenPageLimit(3);
        stlBook.setViewPager(jvpBook);
    }

    @Override
    public void onSuccess(List<BookTypeModel> data) {
        if (data == null || data.isEmpty()) {
            noData();
        } else {
            hasData(data);
        }
    }

    @Override
    public void onFailure(String msg) {
        paProgress.showError(ResourceUtils.getDrawable(R.mipmap.pic_load_error), Constant.ERROR_TITLE, Constant.ERROR_CONTEXT, Constant.ERROR_BUTTON, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paProgress.showLoading();
                //重试
                presenter.getBookTypeList();
            }
        });
    }

    @OnClick(R.id.ll_book_category)
    public void onClick() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constant.BOOK_TYPE, (ArrayList<? extends Parcelable>) bookTypeModelList);
        IntentUtils.startActivity(mContext, LongTimeBookClassActivity.class, bundle);
    }

    @Override
    public void noData() {
        paProgress.showEmpty(ResourceUtils.getDrawable(R.mipmap.pic_load_no_data), "没有获取到书本分类数据", Constant.EMPTY_SEARCH_BOOK_CONTEXT);
    }

    @Override
    public void hasData(List<BookTypeModel> bookTypeModelList) {
        this.bookTypeModelList = bookTypeModelList;

        fragmentPagerItems = new FragmentPagerItems(mContext);
        fragmentPagerItems.add(FragmentPagerItem.of("推荐", BookRecommendFragment.class));

        for (BookTypeModel bookTypeModel : bookTypeModelList) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constant.BOOK_TYPE, bookTypeModel);
            fragmentPagerItems.add(FragmentPagerItem.of(bookTypeModel.getBookTypeName(), BookCategoryFragment.class, bundle));
        }
    }

    @Override
    public void setListeners() {

    }

    public void setCurrentItem(int position) {
        jvpBook.setCurrentItem(position + 1, true);
    }
}
