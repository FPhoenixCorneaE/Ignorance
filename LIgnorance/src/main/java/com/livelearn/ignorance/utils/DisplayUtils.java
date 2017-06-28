/*
 * Copyright (C) 2016 venshine.cn@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.livelearn.ignorance.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.view.Window;

import com.apkfuns.logutils.LogUtils;

/**
 * 屏幕显示相关信息
 *
 * @author venshine
 */
public class DisplayUtils {

    private static Context mContext;

    /**
     * Init method, always by invoked in Application
     */
    public static void init(Context context) {
        if (null == context) {
            throw new IllegalArgumentException("context cannot be null.");
        }
        mContext = context.getApplicationContext();
    }

    /**
     * 是否横屏
     */
    public static boolean isLandscape() {
        return mContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * 是否竖屏
     */
    public static boolean isPortrait() {
        return mContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    /**
     * Get screen width, in pixels
     */
    public static int getScreenWidth() {
        return mContext.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * Get screen height, in pixels
     */
    public static int getScreenHeight() {
        return mContext.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * Get screen density, the logical density of the display
     */
    public static float getScreenDensity(Context context) {
        return mContext.getResources().getDisplayMetrics().density;
    }

    /**
     * Get screen density dpi, the screen density expressed as dots-per-inch
     */
    public static int getScreenDensityDPI(Context context) {
        return mContext.getResources().getDisplayMetrics().densityDpi;
    }

    /**
     * Get titlebar height, this method cannot be used in onCreate(),onStart(),onResume(), unless it is called in the
     * post(Runnable).
     */
    public static int getTitleBarHeight(Activity activity) {
        int statusBarHeight = getStatusBarHeight(activity);
        int contentViewTop = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int titleBarHeight = contentViewTop - statusBarHeight;
        return titleBarHeight < 0 ? 0 : titleBarHeight;
    }

    /**
     * Get statusbar height, this method cannot be used in onCreate(),onStart(),onResume(), unless it is called in the
     * post(Runnable).
     */
    public static int getStatusBarHeight(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    /**
     * Get statusbar height
     */
    public static int getStatusBarHeight2(Activity activity) {
        int statusBarHeight = getStatusBarHeight(activity);
        if (0 == statusBarHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int id = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusBarHeight = activity.getResources().getDimensionPixelSize(id);
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        return statusBarHeight;
    }

    /**
     * Convert dp to px by the density of phone
     */
    public static int dp2px(float dp) {
        return (int) (dpToPx(dp) + 0.5f);
    }

    /**
     * Convert dp to px
     */
    public static float dpToPx(float dp) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return dp * scale;
    }

    /**
     * Convert px to dp by the density of phone
     */
    public static int px2dp(float px) {
        return (int) (pxToDp(px) + 0.5f);
    }

    /**
     * Convert px to dp
     */
    public static float pxToDp(float px) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return px / scale;
    }

    /**
     * Convert px to sp
     */
    public static int px2sp(float px) {
        return (int) (pxToSp(px) + 0.5f);
    }

    /**
     * Convert px to sp
     */
    public static float pxToSp(float px) {
        float fontScale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return px / fontScale;
    }

    /**
     * Convert sp to px
     */
    public static int sp2px(float sp) {
        return (int) (spToPx(sp) + 0.5f);
    }

    /**
     * Convert sp to px
     */
    public static float spToPx(float sp) {
        float fontScale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return sp * fontScale;
    }
}
