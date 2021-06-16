package com.livelearn.ignorance.ui.view.book;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.eventbusevent.BookClassEvent;
import com.livelearn.ignorance.model.bean.book.BookTypeModel;
import com.livelearn.ignorance.presenter.contract.book.BookClassContract;
import com.livelearn.ignorance.ui.adapter.book.BookClassGridAdapter;
import com.livelearn.ignorance.widget.RootView;
import com.livelearn.ignorance.widget.slidinglayout.SlidingLayout;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;

import org.simple.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;

/**
 * Created on 2017/2/14.
 */

public class BookClassView extends RootView implements BookClassContract.View {

    @BindView(R.id.rv_book_class)
    RecyclerView rvBookClass;

    @BindView(R.id.sl_sliding_layout)
    SlidingLayout slSlidingLayout;

    private BookClassGridAdapter bookClassGridAdapter;

    public BookClassView(Context context) {
        super(context);
    }

    public BookClassView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BookClassView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.view_book_class;
    }

    @Override
    public void init() {

        rvBookClass.setLayoutManager(new GridLayoutManager(mContext, 3));
        //如果Item高度固定  增加该属性能够提高效率
        rvBookClass.setHasFixedSize(true);
        //设置适配器
        bookClassGridAdapter = new BookClassGridAdapter(null);
        //设置加载动画
        bookClassGridAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //将适配器添加到RecyclerView
        rvBookClass.setAdapter(bookClassGridAdapter);
    }

    @Override
    public void setPresenter(@NonNull BookClassContract.Presenter presenter, List<BookTypeModel> bookTypeList) {
        bookClassGridAdapter.setNewData(bookTypeList);
    }

    @Override
    public void setListeners() {
        bookClassGridAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                EventBus.getDefault().post(new BookClassEvent(position), Constant.BOOK_CLASS);
                ((Activity) mContext).onBackPressed();
            }
        });
        slSlidingLayout.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        LogUtils.i("up");
                        return false;
                    default:
                        return false;
                }
            }
        });
        slSlidingLayout.setSlidingListener(new SlidingLayout.SlidingListener() {
            @Override
            public void onSlidingOffset(View view, float delta) {
                LogUtils.i("delta:" + delta);
            }

            @Override
            public void onSlidingStateChange(View view, int state) {
                LogUtils.i("state:" + state);
            }

            @Override
            public void onSlidingChangePointer(View view, int pointerId) {
                LogUtils.i("pointerId:" + pointerId);
            }
        });
    }
}
