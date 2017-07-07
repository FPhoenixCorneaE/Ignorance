package com.livelearn.ignorance.widget.ninegridlayout.niceninelayout;

import android.content.Context;

/**
 * Created by wxy on 2017/6/14.
 */

public class DisplayUtils {
    public static int getDisplayWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;

    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
