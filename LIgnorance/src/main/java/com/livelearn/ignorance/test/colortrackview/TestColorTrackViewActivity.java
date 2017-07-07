package com.livelearn.ignorance.test.colortrackview;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.ColorTrackView;
import com.livelearn.ignorance.widget.TitleBar;

import butterknife.BindView;

public class TestColorTrackViewActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.tl_tab)
    TabLayout tlTab;

    @BindView(R.id.vp_pager)
    ViewPager vpPager;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_color_track_view;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        SimpleFragmentPagerAdapter pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(pagerAdapter);
        tlTab.setupWithViewPager(vpPager);
        for (int i = 0; i < tlTab.getTabCount(); i++) {
            TabLayout.Tab tab = tlTab.getTabAt(i);
            if (tab == null) return;
            if (i == 0)
                tab.setCustomView(pagerAdapter.getTabView(i, true));
            else {
                tab.setCustomView(pagerAdapter.getTabView(i, false));
            }
        }
    }

    @Override
    public void setListeners() {
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private boolean checkIfScroll = false;
            private int lastPosition;

            @SuppressWarnings("ConstantConditions")
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (checkIfScroll) {
                    if (positionOffset > 0) {
                        ColorTrackView left = (ColorTrackView) tlTab.getTabAt(position).getCustomView();
                        ColorTrackView right = (ColorTrackView) tlTab.getTabAt(position + 1).getCustomView();

                        left.setDirection(ColorTrackView.DIRECTION_RIGHT);
                        right.setDirection(ColorTrackView.DIRECTION_LEFT);
                        left.setProgress(1 - positionOffset);
                        right.setProgress(positionOffset);
                    }
                }
            }

            @SuppressWarnings("ConstantConditions")
            @Override
            public void onPageSelected(int position) {
                ((ColorTrackView) tlTab.getTabAt(lastPosition).getCustomView()).setProgress(0f);
                ((ColorTrackView) tlTab.getTabAt(position).getCustomView()).setProgress(1f);
                lastPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 1) {
                    checkIfScroll = true;
                } else if (state == 0) {
                    checkIfScroll = false;
                }
            }
        });
    }

    private class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

        private String tabTitles[] = new String[]{"推荐", "热点", "视频", "广州", "社会", "娱乐", "问答", "图片", "科技", "汽车", "体育", "财经", "军事", "国际", "段子", "趣图", "美女", "正能量", "健康", "特卖", "小说"};

        SimpleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TestPageFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        ColorTrackView getTabView(int position, boolean isSelected) {
            ColorTrackView ctvColorTrack = (ColorTrackView) LayoutInflater.from(mContext).inflate(R.layout.item_tab_color_track, null);
            ctvColorTrack.setText(tabTitles[position]);
            if (isSelected) {
                ctvColorTrack.setProgress(1f);
            }
            return ctvColorTrack;
        }
    }
}
