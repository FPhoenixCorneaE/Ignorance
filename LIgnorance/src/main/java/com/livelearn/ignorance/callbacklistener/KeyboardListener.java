package com.livelearn.ignorance.callbacklistener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * simple and powerful Keyboard show/hidden listener,view {@android.R.id.content} and {@ViewTreeObserver.OnGlobalLayoutListener}
 * <p>
 * make activity: android:windowSoftInputMode="adjustResize"
 * onDestroy() call destroy().
 * <p>
 * Created by yes.cpu@gmail.com 2016/7/13.
 */
public class KeyboardListener implements ViewTreeObserver.OnGlobalLayoutListener {

    private static final String TAG = KeyboardListener.class.getSimpleName();
    private View mContentView;
    private int mOriginHeight;
    private int mPreHeight;
    private OnKeyboardChangeListener mOnKeyboardChangeListener;

    public interface OnKeyboardChangeListener {
        /**
         * call back
         *
         * @param isShow         true is show else hidden
         * @param keyboardHeight keyboard height
         */
        void onKeyboardChange(boolean isShow, int keyboardHeight);
    }

    public void setOnKeyboardChangeListener(OnKeyboardChangeListener keyBoardListener) {
        this.mOnKeyboardChangeListener = keyBoardListener;
    }

    public KeyboardListener(Activity contextObj) {
        if (contextObj == null) {
            Log.i(TAG, "contextObj is null");
            return;
        }
        mContentView = findContentView(contextObj);
        if (mContentView != null) {
            addContentTreeObserver();
        }
    }

    private View findContentView(Activity contextObj) {
        return contextObj.findViewById(android.R.id.content);
    }

    private void addContentTreeObserver() {
        mContentView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        int currHeight = mContentView.getHeight();
        if (currHeight == 0) {
            Log.i(TAG, "currHeight is 0");
            return;
        }
        boolean hasChange = false;
        if (mPreHeight == 0) {
            mPreHeight = currHeight;
            mOriginHeight = currHeight;
        } else {
            if (mPreHeight != currHeight) {
                hasChange = true;
                mPreHeight = currHeight;
            } else {
                hasChange = false;
            }
        }
        if (hasChange) {
            boolean isShow;
            int keyboardHeight = 0;
            if (mOriginHeight == currHeight) {
                //hidden
                isShow = false;
            } else {
                //show
                keyboardHeight = mOriginHeight - currHeight;
                isShow = true;
            }

            if (mOnKeyboardChangeListener != null) {
                mOnKeyboardChangeListener.onKeyboardChange(isShow, keyboardHeight);
            }
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    public void destroy() {
        if (mContentView != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mContentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        }
    }
}
