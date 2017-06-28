package com.github.ksoichiro.observablescrollview.test;

import android.app.Activity;
import android.os.Bundle;

import com.github.ksoichiro.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.observablescrollview.ScrollState;
import com.github.ksoichiro.observablescrollview.Scrollable;

public class ScrollViewActivity extends Activity implements ObservableScrollViewCallbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview);
        ((Scrollable) findViewById(R.id.scrollable)).setScrollViewCallbacks(this);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }
}
