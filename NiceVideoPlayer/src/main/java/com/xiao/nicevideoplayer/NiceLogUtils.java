package com.xiao.nicevideoplayer;

import android.util.Log;

/**
 * Created by XiaoJianjun on 2017/5/4.
 * log工具.
 */
class NiceLogUtils {

    private static final String TAG = "NiceVideoPlayer";

    static void d(String message) {
        Log.d(TAG, message);
    }

    static void e(String message, Throwable throwable) {
        Log.e(TAG, message, throwable);
    }
}
