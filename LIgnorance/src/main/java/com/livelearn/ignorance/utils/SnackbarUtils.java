package com.livelearn.ignorance.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 类描述：Snackbar工具类
 * Created by lizhenya on 16/7/14.
 * 注意：
 * 1. make()方法的第一个参数的view,是不能有一个ScrollView.因为SnackBar的实现逻辑是往这个View去addView.而ScrollView我们知道,是只能有一个Child的.否则会Exception.所以注意。
 * 2. 如果大家想把Toast换成Snackbar，要注意键盘的隐藏，以往的Toast是悬浮在上面的，而Snackbar是直接加入进去的哦
 */
public class SnackbarUtils {

    private volatile static SnackbarUtils mInstance;
    private Snackbar mSnackbar;

    private SnackbarUtils() {
    }

    /**
     * 饿汉式单例模式
     */
    public static SnackbarUtils getInstance() {
        if (mInstance == null) {
            synchronized (SnackbarUtils.class) {
                if (mInstance == null) {
                    mInstance = new SnackbarUtils();
                }
            }
        }
        return mInstance;
    }

    public Snackbar getSnackbar() {
        return mSnackbar;
    }

    public Snackbar.SnackbarLayout getSnackbarLayout() {
        if (null == mSnackbar)
            throw new NullPointerException("You must construct Snackbar object!");
        return (Snackbar.SnackbarLayout) mSnackbar.getView();
    }

    public SnackbarUtils makeShortSnackbar(@NonNull View view, @NonNull CharSequence text) {
        mSnackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
        return this;
    }

    public SnackbarUtils makeShortSnackbar(@NonNull View view, @StringRes int resId) {
        return makeShortSnackbar(view, view.getResources().getText(resId));
    }

    public SnackbarUtils makeLongSnackbar(@NonNull View view, @NonNull CharSequence text) {
        mSnackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG);
        return this;
    }

    public SnackbarUtils makeLongSnackbar(@NonNull View view, @StringRes int resId) {
        return makeLongSnackbar(view, view.getResources().getText(resId));
    }

    public SnackbarUtils makeIndefiniteSnackbar(@NonNull View view, @NonNull CharSequence text) {
        mSnackbar = Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE);
        return this;
    }

    public SnackbarUtils makeIndefiniteSnackbar(@NonNull View view, @StringRes int resId) {
        return makeIndefiniteSnackbar(view, view.getResources().getText(resId));
    }

    /**
     * 设置Snackbar的透明度
     */
    public SnackbarUtils setSnackbarAlpha(float alpha) {
        getSnackbarLayout().setAlpha(alpha);
        return this;
    }

    /**
     * 设置Snackbar的背景颜色
     */
    public SnackbarUtils setSnackbarBackgroundColor(int color) {
        getSnackbarLayout().setBackgroundColor(color);
        return this;
    }

    /**
     * 设置Snackbar的背景图片资源
     */
    public SnackbarUtils setSnackbarBackgroudResource(int resId) {
        getSnackbarLayout().setBackgroundResource(resId);
        return this;
    }

    /**
     * 设置Snackbar中Action的字体颜色
     */
    public SnackbarUtils setActionTextColor(int color) {
        Button btnAction = (Button) getSnackbarLayout().findViewById(android.support.design.R.id.snackbar_action);
        btnAction.setTextColor(color);
        return this;
    }

    /**
     * 设置Snackbar中Action的字体大小
     *
     * @param sizeSp Action的字体大小(单位为sp)
     */
    public SnackbarUtils setActionTextSize(float sizeSp) {
        Button btnAction = (Button) getSnackbarLayout().findViewById(android.support.design.R.id.snackbar_action);
        btnAction.setTextSize(sizeSp);
        return this;
    }

    /**
     * 设置SnackBar左侧TextView控件的文字颜色
     *
     * @param color TextView控件的文字颜色
     */
    public SnackbarUtils setTextColor(int color) {
        TextView tvText = (TextView) getSnackbarLayout().findViewById(android.support.design.R.id.snackbar_text);
        tvText.setTextColor(color);
        return this;
    }

    /**
     * 设置SnackBar左侧TextView控件的文字大小
     *
     * @param sizeSp TextView控件的文字大小
     */
    public SnackbarUtils setTextSize(float sizeSp) {
        TextView tvText = (TextView) getSnackbarLayout().findViewById(android.support.design.R.id.snackbar_text);
        tvText.setTextSize(sizeSp);
        return this;
    }


    /**
     * 在Snackbar左侧添加icon
     *
     * @param drawableResId 添加的icon资源ID
     * @param sizeDp        icon的宽度与高度值
     */
    public SnackbarUtils setIconLeft(int drawableResId, float sizeDp) {
        Snackbar.SnackbarLayout snackbarLayout = getSnackbarLayout();
        //snackbar不同于Toast，snackbar依赖于Activity而存在
        Context mContext = snackbarLayout.getContext();
        if (mContext == null) {
            return this;
        }
        TextView tvText = (TextView) snackbarLayout.findViewById(android.support.design.R.id.snackbar_text);

        Drawable drawable = ContextCompat.getDrawable(mContext, drawableResId);
        if (drawable != null) {
            drawable = fitDrawable(mContext.getResources(), drawable, (int) dpToPx(sizeDp, mContext));
        } else {
            throw new IllegalArgumentException("resource_id is not a valid drawable!");
        }

        Drawable[] compoundDrawables = tvText.getCompoundDrawables();
        tvText.setCompoundDrawables(drawable, compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
        return this;
    }

    public void show() {
        if (null == mSnackbar) return;
        mSnackbar.show();
    }

    /**
     * 将drawable压缩为指定宽高的drawable
     *
     * @param drawable 原始drawable
     * @param sizePx   指定的drawable压缩宽高
     */
    private Drawable fitDrawable(Resources resources, Drawable drawable, int sizePx) {
        if (drawable.getIntrinsicWidth() != sizePx || drawable.getIntrinsicHeight() != sizePx) {
            if (drawable instanceof BitmapDrawable) {
                drawable = new BitmapDrawable(resources, Bitmap.createScaledBitmap(getBitmap(drawable), sizePx, sizePx, true));
            }
        }
        drawable.setBounds(0, 0, sizePx, sizePx);
        return drawable;
    }

    /**
     * 将Drawable转化为Bitmap
     */
    private Bitmap getBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof VectorDrawable) {
            return getBitmap((VectorDrawable) drawable);
        } else {
            throw new IllegalArgumentException("unsupported drawable type");
        }
    }

    /**
     * 将VectorDrawable转化为Bitmap
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Bitmap getBitmap(VectorDrawable vectorDrawable) {
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmap;
    }

    /**
     * dp转px
     */
    private float dpToPx(float dpValue, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     */
    private int spToPx(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    private int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
