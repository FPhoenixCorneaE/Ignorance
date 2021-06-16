package com.livelearn.ignorance.widget.viewpagertransformer;

import androidx.viewpager.widget.ViewPager;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

public class RotationTransformer implements ViewPager.PageTransformer {

    private static final float MAX_ROATE = 20f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            ViewHelper.setRotation(view,0);
        } else if (position <= 1) { // [-1,1]
            float result=position*MAX_ROATE;
            ViewHelper.setPivotX(view,pageWidth*0.5f);
            ViewHelper.setPivotY(view,pageHeight);
            ViewHelper.setRotation(view,result);
        } else { // (1,+Infinity]
            ViewHelper.setRotation(view,0);
        }
    }
}
