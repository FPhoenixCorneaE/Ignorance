package com.livelearn.ignorance.utils;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.widget.TextView;

import com.livelearn.ignorance.R;

import cn.iwgang.simplifyspan.SimplifySpanBuild;
import cn.iwgang.simplifyspan.unit.SpecialTextUnit;

/**
 * Created on 2017/4/25.
 */

public class SpanUtils {

    /**
     * 左右两边不同颜色大小文字
     *
     * @param context        上下文
     * @param targetView     目标文本框
     * @param leftText       左边文字
     * @param leftTextColor  左边文字颜色
     * @param leftSize       左边字号
     * @param rightText      右边文字
     * @param rightTextColor 右边文字颜色
     * @param rightSize      右边字号
     */
    public static void setLeftRightDifferentColorSizeText(Context context, TextView targetView, String leftText, @ColorRes int leftTextColor, float leftSize, String rightText, @ColorRes int rightTextColor, float rightSize) {
        SimplifySpanBuild walletSpanBuild = new SimplifySpanBuild(context, targetView);
        walletSpanBuild
                .appendSpecialUnit(new SpecialTextUnit(leftText, ResourceUtils.getColor(leftTextColor), leftSize))
                .appendSpecialUnit(new SpecialTextUnit(rightText, ResourceUtils.getColor(rightTextColor), rightSize));
        targetView.setText(walletSpanBuild.build());
    }

    /**
     * 左边浅灰色15号右边灰色12号文字
     *
     * @param context    上下文
     * @param targetView 目标文本框
     * @param leftText   左边文字
     * @param rightText  右边文字
     */
    public static void setLeftLightPurple15thRightGray12thText(Context context, TextView targetView, String leftText, String rightText) {
        setLeftRightDifferentColorSizeText(context, targetView, leftText, R.color.color_light_purple, 15, rightText, R.color.color_gray, 12);
    }
}
