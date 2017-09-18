package com.livelearn.ignorance.widget.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * 解决SwipeBackLayout滑动返回与ViewPager水平滑动冲突
 * Description:
 * Creator: yxc
 * date: $date $time
 */
public class SwipeBackViewPager extends ViewPager {

    private int mTouchSlop;

    public SwipeBackViewPager(Context context) {
        this(context, null);
    }

    public SwipeBackViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        Context context = getContext();
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledPagingTouchSlop();
    }

    private int preX;

    /**
     * 滑动时拦截事件传递使ViewPager滑动更流畅
     *
     * @return 返回true, 表示拦截事件，返回false，表示不拦截事件
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent even) {

        if (even.getAction() == MotionEvent.ACTION_DOWN) {
            preX = (int) even.getX();
        } else {
            if (Math.abs((int) even.getX() - preX) > 10) {
                return true;
            } else {
                preX = (int) even.getX();
            }
        }
        return super.onInterceptTouchEvent(even);
    }

    private float mLastX, mLastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentItem() == 0) {
            float x = ev.getX();
            float y = ev.getY();
            switch (ev.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    float xDiff = Math.abs(x - mLastY);
                    float yDiff = Math.abs(y - mLastY);
                    //在第一页，判断到是向左边滑动，即想滑动第二页
                    if (xDiff > 0 && x - mLastX < 0 && xDiff * 0.5f > yDiff) {
                        //告诉父容器不要拦截事件
                        getParent().requestDisallowInterceptTouchEvent(true);
                    } else if (yDiff > mTouchSlop && xDiff < mTouchSlop) {
                        //竖直滑动时，告诉父容器拦截事件，用于在ScrollView中可以竖直滑动
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    getParent().requestDisallowInterceptTouchEvent(false);
                    break;
            }
            mLastX = x;
            mLastY = y;
        } else {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return super.dispatchTouchEvent(ev);
    }
}