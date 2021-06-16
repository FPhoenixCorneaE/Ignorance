package com.livelearn.ignorance.widget.viewpagertransformer;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.nineoldandroids.view.ViewHelper;

/**
 * 遮盖滑动效果
 * Created by zuoliangzhu on 16/9/26.
 */

public class OverspreadTransformer implements ViewPager.PageTransformer {

    public static final String PARALLAX_EFFECT = "PARALLAX_EFFECT";

    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        View wallpaper = view.findViewWithTag(PARALLAX_EFFECT);
        if (wallpaper == null) {
            return;
        }
        if (position < -1) {// [-Infinity,-1)
            ViewHelper.setTranslationX(wallpaper, 0);
            ViewHelper.setTranslationX(view, 0);
        } else if (position <= 1) { // [-1,1]
            ViewHelper.setTranslationX(wallpaper, pageWidth * getFactor(position));
            ViewHelper.setTranslationX(view, 8 * position);
        } else { // (1,+Infinity]
            ViewHelper.setTranslationX(wallpaper, 0);
            ViewHelper.setTranslationX(view, 0);
        }
    }

    private float getFactor(float position) {
        return -position / 2;
    }
}
