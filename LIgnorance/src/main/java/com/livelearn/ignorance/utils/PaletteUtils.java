package com.livelearn.ignorance.utils;

import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;

import com.livelearn.ignorance.R;

/**
 * Created by ChinaLHR on 2016/12/19.
 * Email:13435500980@163.com
 */

public class PaletteUtils {

    /**
     * 根据bitmap提取颜色
     */
    public static int getColor(Bitmap bitmap) {
        if (bitmap != null) {
            Palette p = Palette.from(bitmap).generate();
            Palette.Swatch s_dm = p.getDarkMutedSwatch();
            Palette.Swatch s_dv = p.getDarkVibrantSwatch();
            if (s_dm != null) {
                return s_dm.getRgb();
            } else {
                if (s_dv != null) {
                    return s_dv.getRgb();
                } else {
                    return ResourceUtils.getColor(R.color.title_bar);
                }
            }
        } else {
            return ResourceUtils.getColor(R.color.title_bar);
        }
    }
}
