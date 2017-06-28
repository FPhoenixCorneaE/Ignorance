package com.livelearn.ignorance.test.movingimageview;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.TitleBar;
import com.livelearn.ignorance.widget.movingimageview.view.MovingImageView;

import butterknife.BindView;
import butterknife.OnClick;

public class TestMovingImageViewActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.miv_moving)
    MovingImageView mivMoving;

    @BindView(R.id.btn_next)
    Button btnNext;

    @BindView(R.id.btn_up_down)
    Button btnUpDown;

    @BindView(R.id.btn_left_right)
    Button btnLeftRight;

    @BindView(R.id.btn_lefttop_rightbottom)
    Button btnLefttopRightbottom;

    @BindView(R.id.btn_leftbottom_righttop)
    Button btnLeftbottomRighttop;

    @BindView(R.id.btn_loiter_around)
    Button btnLoiterAround;

    @BindView(R.id.btn_stop_moving)
    Button btnStopMoving;

    @BindView(R.id.btn_start_moving)
    Button btnStartMoving;

    @BindView(R.id.btn_default_moving)
    Button btnDefaultMoving;

    int[] imageList = new int[]{R.mipmap.pic_another_world, R.mipmap.pic_future_city, R.mipmap.pic_space_cargo};
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_moving_image_view;
    }

    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);
    }

    public void setListeners() {
        mivMoving.getMovingAnimator().addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                LogUtils.i("Start");
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                LogUtils.i("End");
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                LogUtils.i("Cancel");
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                LogUtils.i("Repeat");
            }
        });
    }

    @OnClick({R.id.btn_next, R.id.btn_up_down, R.id.btn_left_right, R.id.btn_lefttop_rightbottom, R.id.btn_leftbottom_righttop, R.id.btn_loiter_around, R.id.btn_stop_moving, R.id.btn_start_moving, R.id.btn_default_moving})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next://下一张
                index = (index + 1) >= imageList.length ? 0 : index + 1;
                mivMoving.setImageResource(imageList[index]);
                break;
            case R.id.btn_up_down://上下移动
                mivMoving.getMovingAnimator().addCustomMovement()
                        .addVerticalMoveToDown()
                        .addVerticalMoveToUp()
                        .start();
                break;
            case R.id.btn_left_right://左右移动
                mivMoving.getMovingAnimator().addCustomMovement()
                        .addHorizontalMoveToRight()
                        .addHorizontalMoveToLeft()
                        .start();
                break;
            case R.id.btn_lefttop_rightbottom://左上右下移动
                mivMoving.getMovingAnimator().addCustomMovement()
                        .addDiagonalMoveToDownRight()
                        .addDiagonalMoveToUpLeft()
                        .start();
                break;
            case R.id.btn_leftbottom_righttop://左下右上移动
                mivMoving.getMovingAnimator().addCustomMovement()
                        .addDiagonalMoveToUpRight()
                        .addDiagonalMoveToDownLeft()
                        .start();
                break;
            case R.id.btn_loiter_around://四处逛荡
                mivMoving.getMovingAnimator().addCustomMovement()
                        .addDiagonalMoveToDownRight()
                        .addHorizontalMoveToLeft()
                        .addDiagonalMoveToUpRight()
                        .addVerticalMoveToDown()
                        .addHorizontalMoveToLeft()
                        .addVerticalMoveToUp()
                        .start();
                break;
            case R.id.btn_stop_moving://停止移动
                mivMoving.getMovingAnimator().pause();
                break;
            case R.id.btn_start_moving://开始移动
                mivMoving.getMovingAnimator().resume();
                break;
            case R.id.btn_default_moving://默认移动
                mivMoving.getMovingAnimator().clearCustomMovement();
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mivMoving != null)
            mivMoving.getMovingAnimator().cancel();
    }
}
