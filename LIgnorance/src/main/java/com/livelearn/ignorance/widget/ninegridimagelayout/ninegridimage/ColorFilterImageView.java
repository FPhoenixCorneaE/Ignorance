package com.livelearn.ignorance.widget.ninegridimagelayout.ninegridimage;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 点击时显示明暗变化(滤镜效果)的ImageView
 */
public class ColorFilterImageView extends AppCompatImageView {

    public ColorFilterImageView(Context context) {
        this(context, null);
    }

    public ColorFilterImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                //在按下事件中设置滤镜
                setFilter();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                //在CANCEL和UP事件中清除滤镜
                removeFilter();
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 在它从屏幕中消失时回调，去掉drawable引用，能加快内存的回收。
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setImageDrawable(null);
    }

    /**
     * 设置滤镜
     */
    private void setFilter() {
        //先获取设置的src图片
        Drawable drawable = getDrawable();
        //当src图片为Null，获取背景图片
        if (drawable == null) {
            drawable = getBackground();
        }
        if (drawable != null) {
            //设置滤镜
            drawable.mutate().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        }
    }

    /**
     * 清除滤镜
     */
    private void removeFilter() {
        //先获取设置的src图片
        Drawable drawable = getDrawable();
        //当src图片为Null，获取背景图片
        if (drawable == null) {
            drawable = getBackground();
        }
        if (drawable != null) {
            //清除滤镜
            drawable.mutate().clearColorFilter();
        }
    }
}