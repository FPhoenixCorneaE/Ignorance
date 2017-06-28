package com.livelearn.ignorance.test.observablescrollview;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.github.ksoichiro.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.observablescrollview.ScrollState;
import com.github.ksoichiro.observablescrollview.Scrollable;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.utils.DisplayUtils;
import com.livelearn.ignorance.widget.TitleBar;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;

import butterknife.BindView;

/**
 * Created on 2017/4/10.
 */

public abstract class TestTitleBarControlBaseActivity<S extends Scrollable> extends BaseActivity implements ObservableScrollViewCallbacks {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    private S mScrollable;

    protected abstract int getLayoutResId();

    protected abstract S createScrollable();

    @Override
    public int getLayoutResource() {
        return getLayoutResId();
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);
        mScrollable = createScrollable();
    }

    @Override
    public void setListeners() {
        mScrollable.addScrollViewCallbacks(this);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        if (scrollState == ScrollState.UP) {
            if (titleBarIsShown()) {
                hideTitleBar();
            }
        } else if (scrollState == ScrollState.DOWN) {
            if (titleBarIsHidden()) {
                showTitleBar();
            }
        }
    }

    private boolean titleBarIsShown() {
        return ViewHelper.getTranslationY(tbTitle) == 0;
    }

    private boolean titleBarIsHidden() {
        return ViewHelper.getTranslationY(tbTitle) == -tbTitle.getHeight();
    }

    private void showTitleBar() {
        moveTitleBar(0);
    }

    private void hideTitleBar() {
        moveTitleBar(-tbTitle.getHeight());
    }

    private void moveTitleBar(float toTranslationY) {
        if (ViewHelper.getTranslationY(tbTitle) == toTranslationY) {
            return;
        }
        ValueAnimator animator = ValueAnimator.ofFloat(ViewHelper.getTranslationY(tbTitle), toTranslationY).setDuration(200);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float translationY = (float) animation.getAnimatedValue();
                ViewHelper.setTranslationY(tbTitle, translationY);
                ViewHelper.setTranslationY((View) mScrollable, translationY);
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) ((View) mScrollable).getLayoutParams();
                lp.height = (int) -translationY + DisplayUtils.getScreenHeight() - DisplayUtils.getStatusBarHeight2(mContext) - lp.topMargin;
                ((View) mScrollable).requestLayout();
            }
        });
        animator.start();
    }
}
