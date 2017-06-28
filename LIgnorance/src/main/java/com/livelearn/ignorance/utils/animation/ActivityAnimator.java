package com.livelearn.ignorance.utils.animation;


import android.app.Activity;

import com.livelearn.ignorance.R;

public class ActivityAnimator {

    public void appearTopLeft(Activity a) {
        a.overridePendingTransition(R.anim.appear_top_left_in, R.anim.appear_top_left_out);
    }

    public void appearBottomRight(Activity a) {
        a.overridePendingTransition(R.anim.appear_bottom_right_in, R.anim.appear_bottom_right_out);
    }

    public void disappearBottomRight(Activity a) {
        a.overridePendingTransition(R.anim.disappear_bottom_right_in, R.anim.disappear_bottom_right_out);
    }

    public void disappearTopLeft(Activity a) {
        a.overridePendingTransition(R.anim.disappear_top_left_in, R.anim.disappear_top_left_out);
    }

    public void fade(Activity a) {
        a.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void flipHorizontal(Activity a) {
        a.overridePendingTransition(R.anim.flip_horizontal_in, R.anim.flip_horizontal_out);
    }

    public void flipVertical(Activity a) {
        a.overridePendingTransition(R.anim.flip_vertical_in, R.anim.flip_vertical_out);
    }

    public void jump(Activity a) {
        a.overridePendingTransition(R.anim.jump_from_down, R.anim.jump_to_down);
    }

    public void pullDownPushTop(Activity a) {
        a.overridePendingTransition(R.anim.pull_in_down, R.anim.push_out_top);
    }

    public void pullLeftPushRight(Activity a) {
        a.overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }

    public void pullRightPushLeft(Activity a) {
        a.overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    public void pullTopPushDown(Activity a) {
        a.overridePendingTransition(R.anim.pull_in_top, R.anim.push_out_down);
    }

    public void rotateDown(Activity a) {
        a.overridePendingTransition(R.anim.rotate_down_in, R.anim.rotate_down_out);
    }

    public void rotateUp(Activity a) {
        a.overridePendingTransition(R.anim.rotate_up_in, R.anim.rotate_up_out);
    }

    public void scale(Activity a) {
        a.overridePendingTransition(R.anim.scale_up, R.anim.scale_down);
    }

    public void unzoom(Activity a) {
        a.overridePendingTransition(R.anim.unzoom_in, R.anim.unzoom_out);
    }

    public void zoom(Activity a) {
        a.overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
    }
}
