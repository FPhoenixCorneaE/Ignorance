package com.livelearn.ignorance.callbacklistener;

import android.view.View;

import com.livelearn.ignorance.utils.ToastUtils;

/**
 * 禁止重复点击
 * Created on 2017/6/16.
 */
public abstract class OnForbidDuplicateClickListener implements View.OnClickListener {

    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;

    @Override
    public void onClick(View v) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNoDoubleClick(v);
        } else {
            ToastUtils.showToast("你点击得太快了,先休息一下吧");
        }
    }

    public abstract void onNoDoubleClick(View v);
}
