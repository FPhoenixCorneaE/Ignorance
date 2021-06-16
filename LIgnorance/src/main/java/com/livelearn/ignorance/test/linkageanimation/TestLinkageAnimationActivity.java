package com.livelearn.ignorance.test.linkageanimation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.smarttablayout.SmartTabLayout;
import com.nineoldandroids.view.ViewHelper;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class TestLinkageAnimationActivity extends BaseActivity {

    @BindView(R.id.vp_viewpager)
    ViewPager vpViewpager;

    @BindView(R.id.stl_tab)
    SmartTabLayout stlTab;

    @BindView(R.id.tv_bring_into_use)
    TextView tvBringIntoUse;

    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;

    //存放显示内容的View
    private List<Integer> mViews = Arrays.asList(
            R.layout.page_test_linkage_animation_01,
            R.layout.page_test_linkage_animation_02,
            R.layout.page_test_linkage_animation_03
    );
    //背景颜色
    private int[] colors = new int[]{
            Color.parseColor("#6c2da2"),
            Color.parseColor("#1fb7dc"),
            Color.parseColor("#ff9000")
    };
    private ViewPagerAdapter viewPagerAdapter;
    private int pagerWidth, pagerHeight;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_linkage_animation;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        //限定预加载页面个数
        vpViewpager.setOffscreenPageLimit(mViews.size());
        //ViewPager设置适配器
        viewPagerAdapter = new ViewPagerAdapter(mContext, mViews);
        vpViewpager.setAdapter(viewPagerAdapter);
        stlTab.setViewPager(vpViewpager);

        vpViewpager.post(new Runnable() {

            @Override
            public void run() {
                pagerWidth = vpViewpager.getWidth();
                pagerHeight = vpViewpager.getHeight();
            }
        });
        vpViewpager.setBackgroundColor(colors[0]);
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        private ImageView imageView1, imageView2, imageView3;
        private boolean exchange01, exchange12;
        private float lastPositionOffset1, lastPositionOffset2, translationY1, translationY3, translationY5;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (pagerWidth == 0) {
                return;
            }
            if (imageView1 == null) {
                imageView1 = (ImageView) viewPagerAdapter.getPage(0).findViewById(R.id.iv_imageView1);
            }
            if (imageView2 == null) {
                imageView2 = (ImageView) viewPagerAdapter.getPage(1).findViewById(R.id.iv_imageView2);
            }
            if (imageView3 == null) {
                imageView3 = (ImageView) viewPagerAdapter.getPage(2).findViewById(R.id.iv_imageView3);
            }
            if (0 < positionOffset) {//滑动百分比趋近于1的时候，会突变为0
                if (position == 0) {
                    translationY1 += imageView1.getHeight() * (positionOffset - lastPositionOffset1);
                    //为了防止用户手指按住页面持续快速滑动，图片飞出屏幕之外，加以限制
                    if (translationY1 < 0) {
                        translationY1 = 0;
                    }
                    if (translationY1 > imageView1.getHeight()) {
                        translationY1 = imageView1.getHeight();
                    }
                    ViewHelper.setTranslationY(imageView1, translationY1);
                    translationY3 -= imageView2.getHeight() * (positionOffset - lastPositionOffset1);
                    if (translationY3 > 0) {
                        translationY3 = 0;
                    }
                    if (translationY3 < -imageView2.getHeight()) {
                        translationY3 = -imageView2.getHeight();
                    }
                    ViewHelper.setTranslationY(imageView2, translationY3);
                    exchange01 = true;
                }
                if (position == 1) {
                    translationY3 += imageView2.getHeight() * (positionOffset - lastPositionOffset2);
                    if (translationY3 < 0) {
                        translationY3 = 0;
                    }
                    if (translationY3 > imageView2.getHeight()) {
                        translationY3 = imageView2.getHeight();
                    }
                    ViewHelper.setTranslationY(imageView2, translationY3 - imageView2.getHeight());
                    translationY5 -= imageView3.getHeight() * (positionOffset - lastPositionOffset2);
                    if (translationY5 > 0) {
                        translationY5 = 0;
                    }
                    if (translationY5 < -imageView3.getHeight()) {
                        translationY5 = -imageView3.getHeight();
                    }
                    ViewHelper.setTranslationY(imageView3, translationY5);
                    exchange12 = true;
                }
            }
            if (exchange01) {
                lastPositionOffset1 = positionOffset;
            }
            if (exchange12) {
                lastPositionOffset2 = positionOffset;
            }
            Drawable backgroundDrawable = getDrawable(position, positionOffset);
            vpViewpager.setBackground(backgroundDrawable);
            rlRoot.setBackground(backgroundDrawable);
        }

        @Override
        public void onPageSelected(int i) {
        }

        @Override
        public void onPageScrollStateChanged(int i) {
            if (i == 0) {//i==0 什么都没做; i==1 正在滑动; i==2 滑动完毕了
                exchange01 = false;
                exchange12 = false;
                lastPositionOffset1 = 0;
                lastPositionOffset2 = 0;
                translationY1 = 0;
                translationY3 = 0;
                translationY5 = 0;
            }
        }
    };

    /**
     * UI事件监听
     */
    @Override
    public void setListeners() {
        vpViewpager.addOnPageChangeListener(mOnPageChangeListener);
    }

    protected Drawable getDrawable(int position, float positionOffset) {
        Bitmap bitmap = Bitmap.createBitmap(pagerWidth, pagerHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();

        int color1 = colors[(int) Math.ceil(position + positionOffset)];
        paint.setColor(color1);
        paint.setAlpha((int) (255 * positionOffset));
        canvas.drawPaint(paint);

        paint.setColor(colors[position]);
        paint.setAlpha((int) (255 * (1 - positionOffset)));
        canvas.drawPaint(paint);

        return new BitmapDrawable(getResources(), bitmap);
    }
}
