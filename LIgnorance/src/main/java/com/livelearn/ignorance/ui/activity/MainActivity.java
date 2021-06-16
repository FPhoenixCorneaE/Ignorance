package com.livelearn.ignorance.ui.activity;

import android.os.Bundle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.ui.activity.book.SearchBookActivity;
import com.livelearn.ignorance.ui.fragment.BookFragment;
import com.livelearn.ignorance.ui.fragment.ImageFragment;
import com.livelearn.ignorance.ui.fragment.MessageFragment;
import com.livelearn.ignorance.ui.fragment.MineFragment;
import com.livelearn.ignorance.ui.fragment.VideoFragment;
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

/**
 * 主页
 */
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

        final LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
        stlTab.setCustomTabView(new SmartTabLayout.TabProvider() {
            @Override
            public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
                View itemView = mLayoutInflater.inflate(R.layout.item_tab_icon_and_text, container, false);
                ImageView ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
                TextView tvText = (TextView) itemView.findViewById(R.id.tv_text);
                tvText.setText(tabArray[position]);
                switch (position) {
                    case 0:
                        ivIcon.setImageResource(R.drawable.ic_home_video);
                        break;
                    case 1:
                        ivIcon.setImageResource(R.drawable.ic_home_book);
                        break;
                    case 2:
                        ivIcon.setImageResource(R.drawable.ic_home_message);
                        break;
                    case 3:
                        ivIcon.setImageResource(R.drawable.ic_home_image);
                        break;
                    case 4:
                        ivIcon.setImageResource(R.drawable.ic_home_mine);
                        break;
                }
                return itemView;
            }
        });
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
                    if (interrupt) {
                        return;
                    }
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
