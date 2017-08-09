package com.livelearn.ignorance.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.bean.image.GuideModel;
import com.livelearn.ignorance.ui.fragment.GuideFragment;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.SharedPreferencesUtils;
import com.livelearn.ignorance.widget.smarttablayout.FragmentPagerItem;
import com.livelearn.ignorance.widget.smarttablayout.FragmentPagerItemAdapter;
import com.livelearn.ignorance.widget.smarttablayout.FragmentPagerItems;
import com.livelearn.ignorance.widget.smarttablayout.PagerItem;
import com.livelearn.ignorance.widget.viewpager.jellyviewpager.JellyViewPager;

import java.util.List;

import butterknife.BindView;

/**
 * 导航界面
 */
public class GuideActivity extends BaseActivity {

    @BindView(R.id.container)
    JellyViewPager mViewPager;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_guide;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        //不能滑动返回
        setSwipeBackEnable(false);

        List<GuideModel> guideModelList = new GuideModel().getGuideData(this);

        FragmentPagerItems pagerItems = new FragmentPagerItems(this);
        for (int i = 0; i < guideModelList.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constant.ARG_SECTION_NUMBER, guideModelList.get(i));
            pagerItems.add(FragmentPagerItem.of("", PagerItem.DEFAULT_WIDTH, GuideFragment.class, bundle));
        }

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pagerItems);

        mViewPager.setAdapter(adapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            boolean once = true;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (once && position == mViewPager.getAdapter().getCount() - 1 && positionOffsetPixels > mViewPager.getHeight() / 3) {
                    once = false;
                    SharedPreferencesUtils.put(Constant.USER_INFO, Constant.GUIDE_STATE, true);
                    IntentUtils.startActivity(mContext, MainActivity.class);
                    finish();
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean hasEnteredTheHomepage() {
        return false;
    }

    @Override
    public void setListeners() {

    }
}
