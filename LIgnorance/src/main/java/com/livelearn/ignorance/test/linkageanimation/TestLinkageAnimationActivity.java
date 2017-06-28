package com.livelearn.ignorance.test.linkageanimation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
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

    private ImageView imageView1, imageView2, imageView3;
    private int pagerWidth;
    private int pagerHeight;
    private int[] colors;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_linkage_animation;
    }

    public void initLayout(Bundle savedInstanceState) {
        //存放显示内容的View
        ArrayList<View> mViews = new ArrayList<>();
        //背景颜色
        colors = new int[]{Color.parseColor("#6c2da2"), Color.parseColor("#1fb7dc"), Color.parseColor("#ff9000")};

        View layout1 = View.inflate(mContext, R.layout.page_test_linkage_animation_01, null);
        imageView1 = (ImageView) layout1.findViewById(R.id.iv_imageView1);

        View layout2 = View.inflate(mContext, R.layout.page_test_linkage_animation_02, null);
        imageView2 = (ImageView) layout2.findViewById(R.id.iv_imageView2);

        View layout3 = View.inflate(mContext, R.layout.page_test_linkage_animation_03, null);
        imageView3 = (ImageView) layout3.findViewById(R.id.iv_imageView3);

        mViews.add(layout1);
        mViews.add(layout2);
        mViews.add(layout3);

        //ViewPager设置适配器
        vpViewpager.setOffscreenPageLimit(mViews.size());
        vpViewpager.setAdapter(new ViewPagerAdapter(mViews));
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

    /**
     * UI事件监听
     */
    public void setListeners() {
        vpViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            boolean exchange01;
            boolean exchange12;
            private float lastPositionOffset1;
            private float lastPositionOffset2;
            private float translationY1 = 0;
            private float translationY3 = 0;
            private float translationY5 = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (pagerWidth == 0) {
                    return;
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
                        imageView1.setTranslationY(translationY1);
                        translationY3 -= imageView2.getHeight() * (positionOffset - lastPositionOffset1);
                        if (translationY3 > 0) {
                            translationY3 = 0;
                        }
                        if (translationY3 < -imageView2.getHeight()) {
                            translationY3 = -imageView2.getHeight();
                        }
                        imageView2.setTranslationY(translationY3);
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
                        imageView2.setTranslationY(translationY3 - imageView2.getHeight());
                        translationY5 -= imageView3.getHeight() * (positionOffset - lastPositionOffset2);
                        if (translationY5 > 0) {
                            translationY5 = 0;
                        }
                        if (translationY5 < -imageView3.getHeight()) {
                            translationY5 = -imageView3.getHeight();
                        }
                        imageView3.setTranslationY(translationY5);
                        exchange12 = true;
                    }
                }
                if (exchange01) {
                    lastPositionOffset1 = positionOffset;
                }
                if (exchange12) {
                    lastPositionOffset2 = positionOffset;
                }
                vpViewpager.setBackground(getDrawable(position, positionOffset));
                rlRoot.setBackground(getDrawable(position, positionOffset));
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
        });
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

    public class ViewPagerAdapter extends PagerAdapter {
        // 界面列表
        private List<View> views;

        ViewPagerAdapter(List<View> mViews) {
            this.views = mViews;
        }

        /**
         * 获得当前界面数
         */
        @Override
        public int getCount() {
            if (views != null) {
                return views.size();
            }
            return 0;
        }

        /**
         * 初始化position位置的界面
         */
        @Override
        public Object instantiateItem(View view, int position) {
            ((ViewPager) view).addView(views.get(position), 0);
            return views.get(position);
        }

        /**
         * 判断是否由对象生成界面
         */
        @Override
        public boolean isViewFromObject(View view, Object arg1) {
            return (view == arg1);
        }

        /**
         * 销毁position位置的界面
         */
        @Override
        public void destroyItem(View view, int position, Object arg2) {
            ((ViewPager) view).removeView(views.get(position));
        }
    }
}
