package com.livelearn.ignorance.ui.view.book.doubanbook;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.presenter.contract.book.doubanbook.DouBanBookContract;
import com.livelearn.ignorance.ui.adapter.book.doubanbook.BookLabelAdapter;
import com.livelearn.ignorance.ui.adapter.book.doubanbook.helper.ItemDragHelperCallback;
import com.livelearn.ignorance.ui.fragment.book.doubanbook.DouBanBookPagerFragment;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.utils.SharedPreferencesUtils;
import com.livelearn.ignorance.widget.RootView;
import com.livelearn.ignorance.widget.smarttablayout.FragmentPagerItem;
import com.livelearn.ignorance.widget.smarttablayout.FragmentPagerItems;
import com.livelearn.ignorance.widget.smarttablayout.FragmentStatePagerItemAdapter;
import com.livelearn.ignorance.widget.smarttablayout.SmartTabLayout;
import com.livelearn.ignorance.widget.viewpager.jazzyviewpager.JazzyViewPager;
import com.livelearn.ignorance.widget.viewpagertransformer.OverspreadTransformer;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 豆瓣图书
 * Created on 2017/5/8.
 */

public class DouBanBookView extends RootView implements DouBanBookContract.View {

    @BindView(R.id.stl_book)
    SmartTabLayout stlBook;

    @BindView(R.id.jvp_book)
    JazzyViewPager jvpBook;

    @BindView(R.id.ll_add_label)
    LinearLayout llAddLabel;

    @BindView(R.id.rv_book_label)
    RecyclerView rvBookLabel;

    @BindView(R.id.fl_sliding_drag)
    FrameLayout flSlidingDrag;

    @BindView(R.id.supl_panel)
    SlidingUpPanelLayout suplPanel;

    @BindView(R.id.iv_close)
    ImageView ivClose;

    private FragmentPagerItems fragmentPagerItems;
    private List<String> addedBookLabelList;
    private List<String> otherBookLabelList;
    private BookLabelAdapter bookLabelAdapter;
    private FragmentStatePagerItemAdapter pagerItemAdapter;
    private OnPanelStateChangedListener mOnPanelStateChangedListener;

    public DouBanBookView(Context context) {
        super(context);
    }

    public DouBanBookView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DouBanBookView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.view_dou_ban_book;
    }

    @Override
    public void init() {
        isInFragment = true;

        fragmentPagerItems = new FragmentPagerItems(mContext);
        addedBookLabelList = new ArrayList<>();
        otherBookLabelList = new ArrayList<>();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);
        rvBookLabel.setLayoutManager(gridLayoutManager);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemDragHelperCallback());
        itemTouchHelper.attachToRecyclerView(rvBookLabel);

        bookLabelAdapter = new BookLabelAdapter(mContext, itemTouchHelper, addedBookLabelList, otherBookLabelList, Constant.BOOK_LABEL_ADDED);

        //设置头布局占四个span，其他占一个
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = bookLabelAdapter.getItemViewType(position);
                return viewType == BookLabelAdapter.TYPE_MY_LABEL || viewType == BookLabelAdapter.TYPE_OTHER_LABEL ? 1 : 4;
            }
        });

        rvBookLabel.setAdapter(bookLabelAdapter);

        initData();
    }

    private void initData() {

        //已添加豆瓣图书标签
        String addedBookLabel = SharedPreferencesUtils.getString(Constant.USER_INFO, Constant.BOOK_LABEL_ADDED);
        String[] douBanBookCategory;
        if (addedBookLabel == null) {
            douBanBookCategory = ResourceUtils.getStringArray(R.array.BookFragment_DouBanBookDefaultCategory);
        } else {
            douBanBookCategory = addedBookLabel.split(",");
        }

        addedBookLabelList.clear();
        fragmentPagerItems.clear();

        for (String bookCategory : douBanBookCategory) {
            bookCategory = bookCategory.trim();
            addedBookLabelList.add(bookCategory);
            Bundle bundle = new Bundle();
            bundle.putString(Constant.BOOK_TYPE, bookCategory);
            fragmentPagerItems.add(FragmentPagerItem.of(bookCategory, DouBanBookPagerFragment.class, bundle));
        }

        //所有标签
        String[] douBanBookAllCategory = ResourceUtils.getStringArray(R.array.BookFragment_DouBanBookAllCategory);

        otherBookLabelList.clear();

        for (String label : douBanBookAllCategory) {
            if (!addedBookLabelList.contains(label) && !otherBookLabelList.contains(label)) {
                otherBookLabelList.add(label);
            }
        }
    }

    @Override
    public void setPresenter(Fragment fragment, DouBanBookContract.Presenter mPresenter) {

        //Fragment嵌套Fragment,用Fragment的getChildFragmentManager()代替FragmentActivity的getSupportFragmentManager()
        //这里不用FragmentPagerItemAdapter，因为用FragmentPagerItemAdapter，如果标签个数是减少的话，Fragment个数并没有减少，并且新Fragment数据会沿用旧Fragment数据，
        //用FragmentStatePagerItemAdapter就不会了
        pagerItemAdapter = new FragmentStatePagerItemAdapter(fragment.getChildFragmentManager(), fragmentPagerItems);

        //遮盖滑动效果
        jvpBook.setPageTransformer(true, new OverspreadTransformer());
        jvpBook.setAdapter(pagerItemAdapter);
        jvpBook.setOffscreenPageLimit(1);
        jvpBook.setPageMargin(30);
        stlBook.setViewPager(jvpBook);
    }

    @Override
    public void setPresenter(@NonNull DouBanBookContract.Presenter mPresenter) {
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
    public void onFailure(String message) {

    }

    @Override
    public void setListeners() {
        suplPanel.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                LogUtils.i("onPanelSlide, offset " + slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                LogUtils.i("onPanelStateChanged from: " + previousState + "to: " + newState);

                if (mOnPanelStateChangedListener != null) {
                    mOnPanelStateChangedListener.onPanelStateChanged(panel, previousState, newState);
                }

                //面板收起
                if (SlidingUpPanelLayout.PanelState.COLLAPSED == newState
                        || SlidingUpPanelLayout.PanelState.HIDDEN == newState) {
                    initData();
                    pagerItemAdapter.notifyDataSetChanged();
                    stlBook.setViewPager(jvpBook);
                }
            }
        });

        //面板透明背景点击监听
        suplPanel.setFadeOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //隐藏面板
                suplPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });

        //我的图书标签item点击监听
        bookLabelAdapter.setOnMyLabelItemClickListener(new BookLabelAdapter.OnMyLabelItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                suplPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                jvpBook.setCurrentItem(position, true);
            }
        });
    }

    @OnClick({R.id.ll_add_label, R.id.iv_close, R.id.fl_sliding_drag})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_add_label:
                //可滑动拖拽面板高度占父容器百分比为0.9
                suplPanel.setAnchorPoint(0.9f);
                suplPanel.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);

                initData();
                bookLabelAdapter.notifyDataSetChanged();
                break;
            case R.id.iv_close:
                //隐藏面板
                suplPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                break;
            case R.id.fl_sliding_drag:
                //可滑动拖拽面板高度占满父容器
                suplPanel.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                break;
        }
    }

    /**
     * 返回键监听
     */
    public boolean onBackPressed() {
        if (suplPanel != null && (suplPanel.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED
                || suplPanel.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            suplPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            return true;
        }
        return false;
    }

    public void setOnPanelStateChangedListener(OnPanelStateChangedListener mOnPanelStateChangedListener) {
        this.mOnPanelStateChangedListener = mOnPanelStateChangedListener;
    }

    public interface OnPanelStateChangedListener {
        void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState);
    }
}
