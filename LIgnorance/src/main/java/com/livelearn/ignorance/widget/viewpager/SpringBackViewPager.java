package com.livelearn.ignorance.widget.viewpager;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.TranslateAnimation;

import androidx.viewpager.widget.ViewPager;

/**
 * 回弹ViewPager
 */
public class SpringBackViewPager extends ViewPager {

    private int currentPosition = 0;
    private Rect mRect = new Rect();//用来记录初始位置
    private boolean handleDefault = true;
    private float preX = 0f;
    private static final float RATIO = 0.5f;//摩擦系数
    private static final float SCROLL_WIDTH = 10f;

    private int mTouchSlop;

    public SpringBackViewPager(Context context) {
        this(context, null);
    }

    public SpringBackViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        Context context = getContext();
        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledPagingTouchSlop();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            preX = ev.getX();//记录起点
            currentPosition = getCurrentItem();
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                onTouchActionUp();
                break;
            case MotionEvent.ACTION_MOVE:
                if (getAdapter().getCount() == 1) {
                    float nowX = ev.getX();
                    float offset = nowX - preX;
                    preX = nowX;

                    if (offset > SCROLL_WIDTH) {//手指滑动的距离大于设定值
                        whetherConditionIsRight(offset);
                    } else if (offset < -SCROLL_WIDTH) {
                        whetherConditionIsRight(offset);
                    } else if (!handleDefault) {//这种情况是已经出现缓冲区域了，手指慢慢恢复的情况
                        if (getLeft() + (int) (offset * RATIO) != mRect.left) {
                            layout(getLeft() + (int) (offset * RATIO), getTop(), getRight() + (int) (offset * RATIO), getBottom());
                        }
                    }
                } else if ((currentPosition == 0 || currentPosition == getAdapter().getCount() - 1)) {
                    float nowX = ev.getX();
                    float offset = nowX - preX;
                    preX = nowX;

                    if (currentPosition == 0) {
                        if (offset > SCROLL_WIDTH) {//手指滑动的距离大于设定值
                            whetherConditionIsRight(offset);
                        } else if (!handleDefault) {//这种情况是已经出现缓冲区域了，手指慢慢恢复的情况
                            if (getLeft() + (int) (offset * RATIO) >= mRect.left) {
                                layout(getLeft() + (int) (offset * RATIO), getTop(), getRight() + (int) (offset * RATIO), getBottom());
                            }
                        }
                    } else {
                        if (offset < -SCROLL_WIDTH) {
                            whetherConditionIsRight(offset);
                        } else if (!handleDefault) {
                            if (getRight() + (int) (offset * RATIO) <= mRect.right) {
                                layout(getLeft() + (int) (offset * RATIO), getTop(), getRight() + (int) (offset * RATIO), getBottom());
                            }
                        }
                    }
                } else {
                    handleDefault = true;
                }

                if (!handleDefault) {
                    return true;
                }
                break;

            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void whetherConditionIsRight(float offset) {
        if (mRect.isEmpty()) {
            mRect.set(getLeft(), getTop(), getRight(), getBottom());
        }
        handleDefault = false;
        layout(getLeft() + (int) (offset * RATIO), getTop(), getRight() + (int) (offset * RATIO), getBottom());
    }

    private void onTouchActionUp() {
        if (!mRect.isEmpty()) {
            recoveryPosition();
        }
    }

    private void recoveryPosition() {
        TranslateAnimation ta = new TranslateAnimation(getLeft(), mRect.left, 0, 0);
        ta.setDuration(300);
        startAnimation(ta);
        layout(mRect.left, mRect.top, mRect.right, mRect.bottom);
        mRect.setEmpty();
        handleDefault = true;
    }

    float mLastX;
    float mLastY;

    /**
     * 解决SwipeBackLayout滑动返回与ViewPager水平滑动冲突
     */
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
