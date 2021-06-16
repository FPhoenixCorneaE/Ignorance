package com.livelearn.ignorance.widget.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * 控制ViewPager是否可以左右滑动
 */
public class UnScrollViewPager extends ViewPager {

    private boolean isScrollable = false;

    public UnScrollViewPager(Context context) {
        super(context);
    }

    public UnScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollable(boolean scrollable) {
        isScrollable = scrollable;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return isScrollable && super.onTouchEvent(motionEvent);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return isScrollable && super.onInterceptTouchEvent(motionEvent);
    }
}