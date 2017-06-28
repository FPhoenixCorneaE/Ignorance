package com.livelearn.ignorance.widget.indicatorbox;

import android.graphics.Canvas;

/**
 * Created by wusp on 16/4/22.
 */
public interface DynamicPatternDrawer {
    void onDrawPattern(Canvas canvas, int width, int height, float progressFraction);
}
