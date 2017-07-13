package com.livelearn.ignorance.test.ninegridimagelayout.ninegridimageview;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.TitleBar;
import com.livelearn.ignorance.widget.viewpager.SpringBackViewPager;

import butterknife.BindView;

/**
 * Created on 2017/7/11.
 */

public class TestNineGridImageViewActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.tl_tab)
    TabLayout tlTab;

    @BindView(R.id.vp_pager)
    SpringBackViewPager vpPager;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_nine_grid_image_view;
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

        private String[] tabTitles = new String[]{"Fill Style", "Grid Style"};

        SimpleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment mFragment = null;
            switch (position) {
                case 0:
                    mFragment = new TestNineGridImageViewFillStyleFragment();
                    break;
                case 1:
                    mFragment = new TestNineGridImageViewGridStyleFragment();
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
