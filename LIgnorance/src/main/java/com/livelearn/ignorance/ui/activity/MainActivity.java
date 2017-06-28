package com.livelearn.ignorance.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.ui.activity.book.SearchBookActivity;
import com.livelearn.ignorance.ui.fragment.home.BookFragment;
import com.livelearn.ignorance.ui.fragment.home.ImageFragment;
import com.livelearn.ignorance.ui.fragment.home.MessageFragment;
import com.livelearn.ignorance.ui.fragment.home.MineFragment;
import com.livelearn.ignorance.ui.fragment.home.VideoFragment;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.utils.SnackbarUtils;
import com.livelearn.ignorance.widget.TitleBar;
import com.livelearn.ignorance.widget.smarttablayout.FragmentPagerItem;
import com.livelearn.ignorance.widget.smarttablayout.FragmentPagerItemAdapter;
import com.livelearn.ignorance.widget.smarttablayout.FragmentPagerItems;
import com.livelearn.ignorance.widget.smarttablayout.SmartTabLayout;
import com.livelearn.ignorance.widget.viewpager.UnScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.vp_un_scroll)
    UnScrollViewPager vpUnScroll;

    @BindView(R.id.stl_tab)
    SmartTabLayout stlTab;

    private String[] tabArray;
    //最后按下的时间
    private long lastTime;
    private List<OnBackPressedListener> mOnBackPressedListenerList;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        //不能滑动返回
        setSwipeBackEnable(false);

        tabArray = ResourceUtils.getStringArray(R.array.MainActivityTab);

        tbTitle.hideLeftIcon().setTitleText(tabArray[0]);

        FragmentPagerItems pagerItems = new FragmentPagerItems(mContext);
        pagerItems.add(FragmentPagerItem.of(tabArray[0], VideoFragment.class));
        pagerItems.add(FragmentPagerItem.of(tabArray[1], BookFragment.class));
        pagerItems.add(FragmentPagerItem.of(tabArray[2], MessageFragment.class));
        pagerItems.add(FragmentPagerItem.of(tabArray[3], ImageFragment.class));
        pagerItems.add(FragmentPagerItem.of(tabArray[4], MineFragment.class));
        FragmentPagerItemAdapter pagerItemAdapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pagerItems);

        vpUnScroll.setAdapter(pagerItemAdapter);
        stlTab.setViewPager(vpUnScroll);
    }

    @Override
    public void setListeners() {
        vpUnScroll.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    tbTitle.setTitleText(tabArray[position])
                            .setRightIcon(R.mipmap.ic_search_black)
                            .setOnClickRightListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    IntentUtils.startActivity(mContext, SearchBookActivity.class);
                                }
                            });
                } else {
                    tbTitle.setTitleText(tabArray[position]).hideRightIcon();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 按返回键的时候不希望退出（默认就finish了），而是只希望置后台，就可以调这个方法
     * 按二次返回键退到后台
     */
    @Override
    public void onBackPressed() {
        if (mOnBackPressedListenerList != null) {
            for (OnBackPressedListener onBackPressedListener : mOnBackPressedListenerList) {
                if (onBackPressedListener != null) {
                    boolean interrupt = onBackPressedListener.onPressed();
                    if (interrupt) return;
                }
            }
        }
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime < 2 * 1000) {
            moveTaskToBack(true);
        } else {
            SnackbarUtils.getInstance().makeShortSnackbar(vpUnScroll, ResourceUtils.getString(R.string.main_activity_exit))
                    .setSnackbarBackgroundColor(ResourceUtils.getColor(R.color.color_light_purple))
                    .setSnackbarAlpha(0.95f)
                    .setTextColor(ResourceUtils.getColor(R.color.color_pale))
                    .setTextSize(16)
                    .show();
            lastTime = currentTime;
        }
    }

    public interface OnBackPressedListener {
        boolean onPressed();
    }

    public void addOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        if (this.mOnBackPressedListenerList == null) {
            this.mOnBackPressedListenerList = new ArrayList<>();
        }
        this.mOnBackPressedListenerList.add(onBackPressedListener);
    }
}
