package com.livelearn.ignorance.widget.imageloadingprogress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import com.livelearn.ignorance.widget.imageloadingprogress.base.BaseBuilder;
import com.livelearn.ignorance.widget.imageloadingprogress.base.BaseProgress;


/**
 * Created by peng on 16-10-23.
 */
public class LevelProgress extends BaseProgress {

    private Drawable mImage;

    private boolean mEnableAlpha;


    @Override
    public void DrawOther(Canvas canvas) {
        Rect bounds = getBounds();
        int startX = bounds.width() / 2 - mImage.getMinimumWidth() / 2;
        int startY = bounds.height() / 2 - mImage.getMinimumHeight() / 2;
        int width = mImage.getMinimumWidth();
        int height = mImage.getMinimumHeight();

        if (mEnableAlpha) {
            mImage.setAlpha((int) (((int) (((double) ((double) mProgress / (double) mMaxValue)) * (255 * 0.6))) + (255 * 0.4)));
        }

        mImage.setLevel((int) (((double) ((double) mProgress / (double) mMaxValue)) * 10000));

        mImage.setBounds(startX, startY, startX + width, startY + height);

        mImage.draw(canvas);
    }


    public static class Builder extends BaseBuilder<LevelProgress, Builder> {

        public Builder(@DrawableRes int resid, @NonNull Context context) {
            mProgress = new LevelProgress();
            mProgress.mImage = context.getResources().getDrawable(resid);
        }

        /*
        * Allow the alpha will change by the progress changed
         */
        public Builder EnableAlphafilter() {
            mProgress.mEnableAlpha = true;
            return this;
        }

    }
}
