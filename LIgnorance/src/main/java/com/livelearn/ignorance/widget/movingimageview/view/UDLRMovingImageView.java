package com.livelearn.ignorance.widget.movingimageview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.livelearn.ignorance.R;

/**
 * Created by hzhuqi on 2016/11/23.
 */

public class UDLRMovingImageView extends ImageView {


    private Drawable mDrawable;
    private int mLeft = 0;
    private int mTop = 0;
    private int mSpeed = 2;
    private boolean isSetVerticalMove;
    private boolean isMoveLeft;
    private boolean isMoveUp;
    private Handler mHandler;
    private int mCanvasBgSize;

    public UDLRMovingImageView(Context context) {
        super(context);
    }

    public UDLRMovingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUp(context, attrs);
    }

    public UDLRMovingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUp(context, attrs);
    }

    private void setUp(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.UDLRMovingImageView);
        int orientation = a.getInt(R.styleable.UDLRMovingImageView_orientation, -1);
        a.recycle();
        if (orientation == -1) {
            throw new RuntimeException("You don't set direction properties,If you don't want to do that." +
                    "You can use ordinary ImageView instead");
        } else if (orientation == 1) {
            isSetVerticalMove = true;
        } else if (orientation == 0) {
            isSetVerticalMove = false;
        } else {
            throw new RuntimeException("Direction attribute set is not valid,It is only allowed to set to vertical or horizontal");
        }
        mDrawable = getDrawable();
        mHandler = new MoveHandler();
        mHandler.sendEmptyMessageDelayed(1, 220L);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isSetVerticalMove) {
            canvas.translate(0.0F, mTop);
        } else {
            canvas.translate(mLeft, 0.0F);
        }
        mDrawable.draw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (isSetVerticalMove) {
            mCanvasBgSize = getMeasuredHeight() * 3 / 2;
            mDrawable.setBounds(0, 0, getMeasuredWidth(), mCanvasBgSize);
        } else {
            mCanvasBgSize = getMeasuredWidth() * 3 / 2;
            mDrawable.setBounds(0, 0, mCanvasBgSize, getMeasuredHeight());
        }
    }

    private class MoveHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (isSetVerticalMove) {
                if (isMoveUp) {
                    if (mTop <= getMeasuredHeight() - mCanvasBgSize)//此时表示移到了最up的位置
                    {
                        mTop += mSpeed;
                        isMoveUp = false;
                    } else//继续下移
                    {
                        mTop -= mSpeed;
                    }
                } else {
                    if (mTop == 0)//此时表示移动到了最down，此时图片的up侧应该与屏幕up侧对齐，即坐标值为0
                    {
                        mTop -= mSpeed;
                        isMoveUp = true;//图片已经移动到了最down侧，需要修改其移动方向为up
                    } else {

                        mTop += mSpeed;//继续下移
                    }
                }
            } else {
                if (isMoveLeft)//向左移动
                {
                    if (mLeft <= getMeasuredWidth() - mCanvasBgSize)//此时表示移到了最左侧的位置
                    {
                        mLeft += mSpeed;
                        isMoveLeft = false;
                    } else//继续左移
                    {
                        mLeft -= mSpeed;
                    }

                } else {
                    if (mLeft == 0)//此时表示移动到了最右侧，此时图片的左侧应该与屏幕左侧对齐，即坐标值为0
                    {
                        mLeft -= mSpeed;
                        isMoveLeft = true;//图片已经移动到了最右侧，需要修改其移动方向为向左
                    } else {
                        mLeft += mSpeed;//继续右移
                    }
                }
            }
            invalidate();
            mHandler.sendEmptyMessageDelayed(1, 22);
        }
    }
}
