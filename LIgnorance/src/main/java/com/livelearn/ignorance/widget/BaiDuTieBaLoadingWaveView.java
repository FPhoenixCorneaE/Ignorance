package com.livelearn.ignorance.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.livelearn.ignorance.R;

/**
 * author：Jics
 * 2017/7/27 10:45
 * 用Xfermode方式代替Canvas.clipPath裁剪来消除锯齿
 */
public class BaiDuTieBaLoadingWaveView extends View {

    private static final float DEFAULT_TEXT_SCALE = 0.5F;
    private static final int DEFAULT_WAVE_COLOR = Color.rgb(41, 163, 254);
    private static final int DEFAULT_DURATION = 1000;
    private static final int DEFAULT_WIDTH = 50;
    private static final int DEFAULT_HEIGHT = 50;

    private PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private PorterDuffXfermode xfermode_text = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);
    private Paint mPaint;
    private Paint textPaint;
    private int mWidth;
    private int mHeight;
    private Path path;
    private float currentPercent;
    private int mWaveColor;
    private String mText = "贴";
    private float mTextScale;
    private int mTextColor;
    private int mDuration;

    private Bitmap bitmap;

    public BaiDuTieBaLoadingWaveView(Context context) {
        this(context, null);
    }

    public BaiDuTieBaLoadingWaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaiDuTieBaLoadingWaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_WIDTH, context.getResources().getDisplayMetrics());
        mHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_HEIGHT, context.getResources().getDisplayMetrics());

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BaiDuTieBaLoadingWaveView);
        //自定义颜色和文字
        mWaveColor = array.getColor(R.styleable.BaiDuTieBaLoadingWaveView_blw_wave_color, DEFAULT_WAVE_COLOR);
        mText = array.getString(R.styleable.BaiDuTieBaLoadingWaveView_blw_text);
        mTextScale = array.getFloat(R.styleable.BaiDuTieBaLoadingWaveView_blw_text_scale, DEFAULT_TEXT_SCALE);
        mTextColor = array.getColor(R.styleable.BaiDuTieBaLoadingWaveView_blw_text_color, DEFAULT_WAVE_COLOR);
        mDuration = array.getInt(R.styleable.BaiDuTieBaLoadingWaveView_blw_duration, DEFAULT_DURATION);
        array.recycle();

        //图形及路径填充画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mWaveColor);
        mPaint.setDither(true);
        //文字画笔
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        //闭合波浪路径
        path = new Path();

        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(mDuration);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPercent = animation.getAnimatedFraction();
                invalidate();
            }
        });
        animator.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //底部的字
        textPaint.setColor(mTextColor);
        drawCenterText(canvas, textPaint, mText);
        //上层的字
        textPaint.setColor(Color.WHITE);
        //生成闭合波浪路径
        path = getActionPath(currentPercent);

        //新建图层实现离屏缓冲
        int flag = canvas.saveLayer(0, 0, canvas.getWidth(), canvas.getHeight(), null, Canvas.ALL_SAVE_FLAG);
        //绘制蓝色波浪
        canvas.drawPath(path, mPaint);
        mPaint.setXfermode(xfermode);
        //用带有SRC_IN模式的画笔绘制圆形图像，这样圆和波浪相交的地方就只显示圆了
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        textPaint.setXfermode(xfermode_text);
        //用带有SRC_ATOP的文字画笔绘制文字，这样在圆形波浪和文字相交的地方绘制文字，在不相交的地方绘制圆形波浪
        drawCenterText(canvas, textPaint, mText);
        //合并图层到canvas上
        canvas.restoreToCount(flag);

        textPaint.setXfermode(null);
        mPaint.setXfermode(null);
    }

    /**
     * 绘制圆形bitmap
     */
    private Bitmap getCircleBitmap(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//		bitmap.eraseColor(Color.parseColor("#FF0000"));//填充颜色
        Canvas canvas = new Canvas(bitmap);
        canvas.drawCircle(width / 2, height / 2, width / 2, mPaint);
        return bitmap;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }
        setMeasuredDimension(mWidth, mHeight);
        bitmap = getCircleBitmap(mWidth, mHeight);
        //文字大小单位为px
        float textSize = mWidth * mTextScale;
        textPaint.setTextSize(textSize);
    }

    private Path getActionPath(float percent) {
        Path path = new Path();
        int x = -mWidth;
        //当前x点坐标（根据动画进度水平推移，一个动画周期推移的距离为一个mWidth）
        x += percent * mWidth;
        //波形的起点
        path.moveTo(x, mHeight / 2);
        //控制点的相对宽度
        int quadWidth = mWidth / 4;
        //控制点的相对高度
        int quadHeight = mHeight / 4;
        //第一个周期
        path.rQuadTo(quadWidth, quadHeight, quadWidth * 2, 0);
        path.rQuadTo(quadWidth, -quadHeight, quadWidth * 2, 0);
        //第二个周期
        path.rQuadTo(quadWidth, quadHeight, quadWidth * 2, 0);
        path.rQuadTo(quadWidth, -quadHeight, quadWidth * 2, 0);
        //右侧的直线
        path.lineTo(x + mWidth * 2, mHeight);
        //下边的直线
        path.lineTo(x, mHeight);
        //自动闭合补出左边的直线
        path.close();
        return path;
    }

    private void drawCenterText(Canvas canvas, Paint textPaint, String text) {
        Rect rect = new Rect(0, 0, mWidth, mHeight);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setFakeBoldText(true);

        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;

        int centerY = (int) (rect.centerY() - top / 2 - bottom / 2);

        canvas.drawText(text, rect.centerX(), centerY, textPaint);
    }
}
