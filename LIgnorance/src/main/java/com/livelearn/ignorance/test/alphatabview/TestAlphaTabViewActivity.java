package com.livelearn.ignorance.test.alphatabview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.test.alphatabview.fragment.TestAlphaTabFourFragment;
import com.livelearn.ignorance.test.alphatabview.fragment.TestAlphaTabOneFragment;
import com.livelearn.ignorance.test.alphatabview.fragment.TestAlphaTabThreeFragment;
import com.livelearn.ignorance.test.alphatabview.fragment.TestAlphaTabTwoFragment;
import com.livelearn.ignorance.widget.alphatabs.AlphaTabsIndicator;

import java.util.ArrayList;

import butterknife.BindView;

public class TestAlphaTabViewActivity extends BaseActivity {

    @BindView(R.id.vp_pager)
    ViewPager vpPager;

    @BindView(R.id.ati_tabs)
    AlphaTabsIndicator atiTabs;

    private ArrayList<Fragment> mFragments;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_alpha_tab_view;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {

//                ImmersionBar.with(this)
//                .transparentStatusBar()  //透明状态栏，不写默认透明色
//                .transparentNavigationBar()  //透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
//                .transparentBar()             //透明状态栏和导航栏，不写默认状态栏为透明色，导航栏为黑色（设置此方法，fullScreen()方法自动为true）
//                .statusBarColor(R.color.colorPrimary)     //状态栏颜色，不写默认透明色
//                .navigationBarColor(R.color.colorPrimary) //导航栏颜色，不写默认黑色
//                .barColor(R.color.colorPrimary)  //同时自定义状态栏和导航栏颜色，不写默认状态栏为透明色，导航栏为黑色
//                .statusBarAlpha(0.3f)  //状态栏透明度，不写默认0.0f
//                .navigationBarAlpha(0.4f)  //导航栏透明度，不写默认0.0F
//                .barAlpha(0.3f)  //状态栏和导航栏透明度，不写默认0.0f
//                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
//                .fullScreen(true)      //有导航栏的情况下，activity全屏显示，也就是activity最下面被导航栏覆盖，不写默认非全屏
//                .hideBar(BarHide.FLAG_HIDE_BAR)  //隐藏状态栏或导航栏或两者，不写默认不隐藏
//                .setViewSupportTransformColor(toolbar) //设置支持view变色，支持一个view，不指定颜色，默认和状态栏同色，还有两个重载方法
//                .addViewSupportTransformColor(toolbar)  //设置支持view变色，可以添加多个view，不指定颜色，默认和状态栏同色，还有两个重载方法
//                .statusBarView(view)  //解决状态栏和布局重叠问题
//                .fitsSystemWindows(false)    //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
//                .statusBarColorTransform(R.color.orange)  //状态栏变色后的颜色
//                .navigationBarColorTransform(R.color.orange) //导航栏变色后的颜色
//                .barColorTransform(R.color.orange)  //状态栏和导航栏变色后的颜色
//                .removeSupportView()  //移除通过setViewSupportTransformColor()方法指定的view
//                .removeSupportView(toolbar)  //移除指定view支持
//                .removeSupportAllView() //移除全部view支持
//                .init();  //必须调用方可沉浸式

        initData();
        vpPager.setAdapter(new SimplePagerAdapter(getSupportFragmentManager()));
        atiTabs.setViewPager(vpPager);

        atiTabs.getTabView(0)
                .setBadgeNumberTextSize(14)
                .showNumber(100);
        atiTabs.getTabView(1)
                .setBadgeNumberTextSize(14)
                .showNumber(6);
        atiTabs.getTabView(2)
                .setBadgeNumberTextSize(14)
                .showNumber(666);
        atiTabs.getTabView(3)
                .showPoint();
    }

    @Override
    public void setListeners() {
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (1 == position) {
                    atiTabs.getTabView(0).showNumber(atiTabs.getTabView(0).getBadgeNumber() - 1);
                } else if (2 == position) {
                    atiTabs.getCurrentItemView().removeShow();
                } else if (3 == position) {
                    atiTabs.removeAllBadge();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        mFragments = new ArrayList<>();
        TestAlphaTabOneFragment oneFragment = new TestAlphaTabOneFragment();
        TestAlphaTabTwoFragment twoFragment = new TestAlphaTabTwoFragment();
        TestAlphaTabThreeFragment threeFragment = new TestAlphaTabThreeFragment();
        TestAlphaTabFourFragment fourFragment = new TestAlphaTabFourFragment();
        mFragments.add(oneFragment);
        mFragments.add(twoFragment);
        mFragments.add(threeFragment);
        mFragments.add(fourFragment);
    }

    private class SimplePagerAdapter extends FragmentPagerAdapter {

        SimplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
