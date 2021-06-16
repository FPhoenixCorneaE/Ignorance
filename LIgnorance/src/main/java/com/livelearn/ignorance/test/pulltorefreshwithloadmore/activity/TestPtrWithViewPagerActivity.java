package com.livelearn.ignorance.test.pulltorefreshwithloadmore.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.model.Images;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.viewholder.TabIndicatorViewHolder;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.viewpager.TabPageIndicator;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.viewpager.ViewPagerFragment;
import com.livelearn.ignorance.widget.TitleBar;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class TestPtrWithViewPagerActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.tpi_tab)
    TabPageIndicator tpiTab;

    @BindView(R.id.vp_viewpager)
    ViewPager vpViewpager;

    @BindView(R.id.pcfl_pull_to_refresh)
    PtrClassicFrameLayout pcflPullToRefresh;

    private FragmentViewPagerAdapter mPagerAdapter;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_ptr_with_view_pager;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        ArrayList<ViewPagerFragment> pageList = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            pageList.add(ViewPagerFragment.create(i));
        }
        mPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), pageList);
        vpViewpager.setAdapter(mPagerAdapter);
        vpViewpager.setCurrentItem(0);

        tpiTab.setViewHolderCreator(new TabPageIndicator.ViewHolderCreator() {
            @Override
            public TabPageIndicator.ViewHolderBase createViewHolder() {
                return new TabIndicatorViewHolder();
            }
        });
        tpiTab.setViewPager(vpViewpager);

        pcflPullToRefresh.disableWhenHorizontalMove(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tpiTab.moveToItem(vpViewpager.getCurrentItem());
    }

    @Override
    public void setListeners() {
        tpiTab.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switchTo(position);
            }
        });
        pcflPullToRefresh.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return mPagerAdapter.checkCanDoRefresh();
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPagerAdapter.updateData();
            }
        });
    }

    private void switchTo(int position) {
        mPagerAdapter.switchTo(position);
    }

    private class FragmentViewPagerAdapter extends FragmentStatePagerAdapter {

        private final ArrayList<ViewPagerFragment> mViewPagerFragments;
        private ViewPagerFragment mCurrentFragment;
        private int mCurrentIndex;

        FragmentViewPagerAdapter(FragmentManager fm, ArrayList<ViewPagerFragment> list) {
            super(fm);
            mViewPagerFragments = list;
        }

        @Override
        public Fragment getItem(int position) {
            return mViewPagerFragments.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }

        void updateData() {
            mCurrentFragment.updateData(Arrays.asList(Images.imageUrls).subList(mCurrentIndex, 20));
            pcflPullToRefresh.refreshComplete();
        }

        @Override
        public int getCount() {
            return mViewPagerFragments.size();
        }

        void switchTo(final int position) {
            mCurrentIndex = position;
            int len = mViewPagerFragments.size();
            for (int i = 0; i < len; i++) {
                ViewPagerFragment fragment = mViewPagerFragments.get(i);
                if (i == position) {
                    mCurrentFragment = fragment;
                    fragment.show();
                } else {
                    fragment.hide();
                }
            }
        }

        public boolean checkCanDoRefresh() {
            return mCurrentFragment == null || mCurrentFragment.checkCanDoRefresh();
        }

        public boolean checkCanDoLoadMore() {
            return mCurrentFragment == null || mCurrentFragment.checkCanDoLoadMore();
        }
    }
}
