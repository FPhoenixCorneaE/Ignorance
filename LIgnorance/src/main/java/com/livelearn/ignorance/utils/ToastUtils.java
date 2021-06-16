package com.livelearn.ignorance.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.core.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Toast操作
 */
public class ToastUtils {

    private static final int DEFAULT_TIME = Toast.LENGTH_SHORT;
    private static final int DEFAULT_POSITION = Gravity.TOP;
    private static final int DEFAULT_XOFFSET = 0;
    private static final int DEFAULT_YOFFSET = Integer.MAX_VALUE;

    @SuppressLint("StaticFieldLeak")
    private static Context mContext = null;
    private static Toast mToast = null;
    private static Object mTN = null;
    private static Handler mHandler = new Handler(Looper.getMainLooper());

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
     * Get toast
     */
    private static Toast getToast() {
        if (null == mContext) {
            throw new IllegalStateException("Please invoke init method first.");
        }
        if (null != mToast) {
            return mToast;
        }
        return Toast.makeText(mContext, "", DEFAULT_TIME);
    }

    /**
     * Show toast
     *
     * @param msgId 字符串资源id
     */
    public static void showToast(int msgId) {
        showToast(ResourceUtils.getString(msgId), DEFAULT_TIME);
    }

    /**
     * Show toast
     *
     * @param msg 字符串
     */
    public static void showToast(String msg) {
        showToast(msg, DEFAULT_TIME);
    }

    /**
     * Show toast with duration
     */
    public static void showToast(String msg, int duration) {
        showToast(msg, duration, DEFAULT_POSITION, DEFAULT_XOFFSET, DEFAULT_YOFFSET);
    }

    /**
     * show CenterToast 中心吐司
     */
    public static void showCenterToast(String msg) {
        showToast(msg, DEFAULT_TIME, Gravity.CENTER, 0, 0);
    }

    /**
     * show CenterToast 中心吐司
     */
    public static void showCenterToast(String msg, int duration) {
        showToast(msg, duration, Gravity.CENTER, 0, 0);
    }

    /**
     * Show toast with duration gratity xOffset and yOffset
     */
    public static void showToast(final String msg, final int duration, final int gravity, final int xOffset, final int yOffset) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            makeToast(msg, duration, gravity, xOffset, yOffset);
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    makeToast(msg, duration, gravity, xOffset, yOffset);
                }
            });
        }
    }

    private static void makeToast(String msg, int duration, int gravity, int xOffset, int yOffset) {
        mToast = getToast();
        mToast.setGravity(gravity, xOffset, (yOffset == Integer.MAX_VALUE ? DisplayUtils.getScreenHeight() * 4 / 5 : yOffset));

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.shape_background_toast_02));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView tvToast = new TextView(mContext);
        tvToast.setLayoutParams(layoutParams);
        tvToast.setText(msg);
        tvToast.setTextColor(ContextCompat.getColor(mContext, R.color.color_pale));
        tvToast.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        tvToast.getPaint().setFakeBoldText(false);
        tvToast.setGravity(Gravity.CENTER);
        tvToast.setPadding(8, 2, 8, 2);

        linearLayout.addView(tvToast);

        mToast.setView(linearLayout);
        mToast.setDuration(duration);
        mToast.show();
    }

    /**
     * show center toast with image and text 有图片和文字的中心吐司
     */
    public static void showImageCenterToast(final int resId, final String text, final boolean isLong) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            makeImageCenterToast(resId, text, isLong);
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    makeImageCenterToast(resId, text, isLong);
                }
            });
        }
    }

    private static void makeImageCenterToast(int resId, String text, boolean isLong) {
        Toast centerToast = getToast();
        centerToast.setGravity(Gravity.CENTER, 0, 0);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout shadowLayout = new LinearLayout(mContext);
        shadowLayout.setLayoutParams(layoutParams);
        shadowLayout.setBackground(ContextCompat.getDrawable(mContext, R.drawable.layerlist_shadow));

        ViewGroup.LayoutParams containerLayoutParams = new ViewGroup.LayoutParams((int) (DisplayUtils.getScreenWidth() * 0.6f), ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout container = new LinearLayout(mContext);
        container.setLayoutParams(containerLayoutParams);
        container.setBackground(ContextCompat.getDrawable(mContext, R.drawable.shape_background_toast_01));
        container.setOrientation(LinearLayout.VERTICAL);
        container.setGravity(Gravity.CENTER_HORIZONTAL);

        ViewGroup.LayoutParams imageLayoutParams = new ViewGroup.LayoutParams(DisplayUtils.dp2px(60), DisplayUtils.dp2px(60));
        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(imageLayoutParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(resId);
        imageView.setPadding(8, 2, 8, 2);

        TextView tvToast = new TextView(mContext);
        tvToast.setLayoutParams(layoutParams);
        tvToast.setText(text);
        tvToast.setTextColor(ContextCompat.getColor(mContext, R.color.color_pale));
        tvToast.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        tvToast.setGravity(Gravity.CENTER);
        tvToast.setPadding(8, 2, 8, 2);

        container.addView(imageView);
        container.addView(tvToast);
        shadowLayout.addView(container);
        centerToast.setView(shadowLayout);
        centerToast.setDuration((isLong) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        centerToast.show();
    }

    public static void showSuccessToast(String text) {
        showImageCenterToast(R.mipmap.ic_ok, text, false);
    }

    public static void showErrorToast(String text) {
        showImageCenterToast(R.mipmap.ic_error, text, false);
    }

    /**
     * Show toast, the toast will be showing always if you are not calling hideToast().
     */
    public static void showToastAlways(Context context, String msg) {
        if (mTN != null) { // toast is showing
            return;
        }
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        try {
            Field field = toast.getClass().getDeclaredField("mTN");
            field.setAccessible(true);
            mTN = field.get(toast);
            // start, only used by 4.x
            Field mNextViewField = mTN.getClass().getDeclaredField("mNextView");
            mNextViewField.setAccessible(true);
            mNextViewField.set(mTN, toast.getView());
            // end
            Method method = mTN.getClass().getDeclaredMethod("show", new Class<?>[0]);
            method.invoke(mTN, new Object[]{});
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    /**
     * Hide toast, only used after showToast(context, msg), hide the toast of having shown.
     */
    public static void hideToast() {
        if (mTN == null) { // the method of showToast is not called
            return;
        }
        try {
            Method method = mTN.getClass().getDeclaredMethod("hide", new Class<?>[0]);
            method.invoke(mTN, new Object[]{});
            mTN = null;
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }
}
