package com.livelearn.ignorance.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class RootView extends FrameLayout {

    /**
     * 是否被销毁
     */
    protected boolean mActive;
    protected Context mContext;
    protected Unbinder unbinder;
    /**
     * 如果是在Fragment中，ButterKnife不可解绑，否则view为null
     */
    protected boolean isInFragment;


    public RootView(Context context) {
        this(context, null);
    }

    public RootView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        onCreateView();
    }

    private void onCreateView() {
        mContext = getContext();
        inflate(mContext, getLayoutResource(), this);
        unbinder = ButterKnife.bind(this);

        init();

        setListeners();
    }

    public abstract int getLayoutResource();

    public abstract void init();

    public abstract void setListeners();

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mActive = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!isInFragment) {
            mActive = false;
            unbinder.unbind();
            mContext = null;
        }
    }
}
