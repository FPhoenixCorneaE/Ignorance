package com.livelearn.ignorance.ui.fragment.mine;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.utils.DisplayUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.smarttablayout.FragmentPagerItemAdapter;
import com.livelearn.ignorance.widget.smarttablayout.FragmentPagerItems;
import com.livelearn.ignorance.widget.viewpager.SpringBackViewPager;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import butterknife.BindView;

/**
 * Created on 2017/7/21.
 */

public class CollectionFragment extends BaseFragment {

    @BindView(R.id.mi_indicator)
    MagicIndicator miIndicator;

    @BindView(R.id.vp_pager)
    SpringBackViewPager vpPager;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_collection;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final String[] tabTitles = ResourceUtils.getStringArray(R.array.CollectionFragment_TabTitle);
        vpPager.setAdapter(new FragmentPagerItemAdapter(getChildFragmentManager(),
                FragmentPagerItems.with(mContext)
                        .add(tabTitles[0], LongTimeBookCollectionFragment.class)
                        .add(tabTitles[1], DouBanBookCollectionFragment.class)
                        .create()));
        vpPager.setOffscreenPageLimit(3);

        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        commonNavigator.setSkimOver(true);
        commonNavigator.setScrollPivotX(0.35f);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return tabTitles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                clipPagerTitleView.setPadding(DisplayUtils.dp2px(30), 0, DisplayUtils.dp2px(30), 0);
                clipPagerTitleView.setText(tabTitles[index]);
                clipPagerTitleView.setTextSize(ResourceUtils.getDimension(R.dimen.text_size_18sp));
                clipPagerTitleView.setTextColor(ResourceUtils.getColor(R.color.color_dark_black));
                clipPagerTitleView.setClipColor(ResourceUtils.getColor(R.color.color_pale));
                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vpPager.setCurrentItem(index);
                    }
                });
                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                WrapPagerIndicator indicator = new WrapPagerIndicator(context);
                indicator.setVerticalPadding(DisplayUtils.dp2px(1));
                indicator.setHorizontalPadding(DisplayUtils.dp2px(15));
                indicator.setFillColor(ResourceUtils.getColor(R.color.color_pale_translucent));
                return indicator;
            }
        });

        miIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(miIndicator, vpPager);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyFetchData() {

    }
}
