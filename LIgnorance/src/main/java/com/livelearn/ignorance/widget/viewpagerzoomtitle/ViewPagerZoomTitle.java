package com.livelearn.ignorance.widget.viewpagerzoomtitle;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.livelearn.ignorance.R;

public class ViewPagerZoomTitle extends LinearLayout {

    private ViewPager viewPager;
    private TextAttr textAttr;


    public ViewPagerZoomTitle(Context context) {
        super(context);
    }

    public ViewPagerZoomTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.textAttr = getTextAttr(attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initView();
    }

    private TextAttr getTextAttr(AttributeSet attrs) {
        int defaultTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics());
        TextAttr textAttr = new TextAttr();
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ViewPagerZoomTitle);
        textAttr.setHvTextSize(typedArray.getDimension(R.styleable.ViewPagerZoomTitle_hvTextSize, defaultTextSize));
        textAttr.setHvPadding(typedArray.getDimension(R.styleable.ViewPagerZoomTitle_hvPadding, 0));
        textAttr.setHvTextColor(typedArray.getInt(R.styleable.ViewPagerZoomTitle_hvTextColor, 0));
        textAttr.setHvTextColorActiveTab(typedArray.getInt(R.styleable.ViewPagerZoomTitle_hvTextColorActiveTab, 0));
        textAttr.setHvTextAlpha(typedArray.getFloat(R.styleable.ViewPagerZoomTitle_hvTextAlpha, 1.0f));
        textAttr.setHvTextAlphaActiveTab(typedArray.getFloat(R.styleable.ViewPagerZoomTitle_hvTextAlphaActiveTab, 1.0f));
        textAttr.setHvMinScale(typedArray.getFloat(R.styleable.ViewPagerZoomTitle_hvTextScale, 1.0f));
        textAttr.setHvMaxScale(typedArray.getFloat(R.styleable.ViewPagerZoomTitle_hvTextScaleActiveTab, 1.0f));
        typedArray.recycle();
        return textAttr;
    }

    private void initView() {
        this.setOrientation(LinearLayout.VERTICAL);
        getViewPager();
        initHeader();
    }

    private void getViewPager() {
        if (getChildCount() > 1 || (getChildCount() == 0 && !(getChildAt(0) instanceof ViewPager))) {
            throw new IllegalStateException("ViewPagerZoomTitle can host only ViewPager child");
        }
        viewPager = (ViewPager) getChildAt(0);
    }

    private void initHeader() {
        if (viewPager != null && viewPager.getAdapter() != null) {
            ViewPagerHeader header = new ViewPagerHeader(getContext(), textAttr);
            header.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
            header.setViewPager(viewPager);
            this.addView(header, 0);
        }
    }

}