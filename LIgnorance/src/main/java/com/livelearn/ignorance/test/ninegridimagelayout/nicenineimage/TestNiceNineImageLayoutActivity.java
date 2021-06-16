package com.livelearn.ignorance.test.ninegridimagelayout.nicenineimage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.TitleBar;

import butterknife.BindView;

/**
 * Created on 2017/7/6.
 */

public class TestNiceNineImageLayoutActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.tl_tab)
    TabLayout tlTab;

    @BindView(R.id.vp_pager)
    ViewPager vpPager;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_nice_nine_image_layout;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        vpPager.setAdapter(new SimpleFragmentPagerAdapter(getSupportFragmentManager()));

        tlTab.setupWithViewPager(vpPager);
    }

    @Override
    public void setListeners() {

    }

    private class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

        private String[] tabTitles = new String[]{"列表", "拖拽"};

        SimpleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment mFragment = null;
            switch (position) {
                case 0:
                    mFragment = new TestNiceNineImageLayoutListFragment();
                    break;
                case 1:
                    mFragment = new TestNiceNineImageLayoutDragFragment();
                    break;
            }
            return mFragment;
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
