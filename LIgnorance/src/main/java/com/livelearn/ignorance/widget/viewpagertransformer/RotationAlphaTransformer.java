package com.livelearn.ignorance.widget.viewpagertransformer;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.nineoldandroids.view.ViewHelper;

public class RotationAlphaTransformer implements ViewPager.PageTransformer {

    private static final float MAX_ROATE = 360f;

    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            ViewHelper.setRotation(view,0);
            ViewHelper.setAlpha(view,1);
        } else if (position <= 1) { // [-1,1]
            float result=position*MAX_ROATE;
            //旋转
            ViewHelper.setPivotX(view,pageWidth*0.5f);
            ViewHelper.setPivotY(view, pageHeight*0.5f);
            ViewHelper.setRotation(view,result);
            //透明度
            ViewHelper.setAlpha(view,1-Math.abs(position));
            //缩放
            ViewHelper.setScaleY(view,1-Math.abs(position));
            ViewHelper.setScaleX(view,1-Math.abs(position));

        } else { // (1,+Infinity]
            ViewHelper.setRotation(view,0);
            ViewHelper.setAlpha(view,1);
        }
    }
}
