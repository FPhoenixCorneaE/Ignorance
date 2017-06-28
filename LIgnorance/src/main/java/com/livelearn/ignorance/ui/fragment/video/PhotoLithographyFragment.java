package com.livelearn.ignorance.ui.fragment.video;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.ColorTrackView;
import com.livelearn.ignorance.widget.smarttablayout.FragmentPagerItem;
import com.livelearn.ignorance.widget.smarttablayout.FragmentPagerItems;
import com.livelearn.ignorance.widget.smarttablayout.FragmentStatePagerItemAdapter;
import com.livelearn.ignorance.widget.smarttablayout.SmartTabLayout;
import com.livelearn.ignorance.widget.viewpagertransformer.OverspreadTransformer;

import butterknife.BindView;

/**
 * Created by on 2017/6/22.
 */

public class PhotoLithographyFragment extends BaseFragment {

    @BindView(R.id.stl_video)
    SmartTabLayout stlVideo;

    @BindView(R.id.vp_video)
    ViewPager vpVideo;

    private int currentPosition;
    private String[] catalogArray;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_photo_lithography;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentPagerItems fragmentPagerItems = new FragmentPagerItems(mContext);
        catalogArray = ResourceUtils.getStringArray(R.array.VideoFragment_PhotoLithography);
        String[] catalogIdArray = ResourceUtils.getStringArray(R.array.PhotoLithography_CatalogId);
        for (int i = 0; i < catalogArray.length; i++) {
            Bundle args = new Bundle();
            args.putString(Constant.VIDEO_TITLE, catalogArray[i]);
            args.putString(Constant.VIDEO_CATALOG_ID, catalogIdArray[i]);
            fragmentPagerItems.add(FragmentPagerItem.of(catalogArray[i], PhotoLithographyPagerFragment.class, args));
        }

        //Fragment嵌套Fragment,用Fragment的getChildFragmentManager()代替FragmentActivity的getSupportFragmentManager()
        FragmentStatePagerItemAdapter pagerItemAdapter = new FragmentStatePagerItemAdapter(getChildFragmentManager(), fragmentPagerItems);

        //遮盖滑动效果
        vpVideo.setPageTransformer(true, new OverspreadTransformer());
        vpVideo.setAdapter(pagerItemAdapter);
        vpVideo.setOffscreenPageLimit(3);
        stlVideo.setViewPager(vpVideo);

        for (int i = 0; i < catalogArray.length; i++) {
            ((ColorTrackView) stlVideo.getTabAt(i).findViewById(R.id.ctv_text)).setText(catalogArray[i]);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //ColorTrackView这个东西存在一个小小的问题，就是颜色改变会乱掉
        //每次恢复的时候重新设置颜色
        for (int i = 0; i < catalogArray.length; i++) {
            if (i == currentPosition)
                ((ColorTrackView) stlVideo.getTabAt(currentPosition).findViewById(R.id.ctv_text)).setProgress(1f);
            else
                ((ColorTrackView) stlVideo.getTabAt(i).findViewById(R.id.ctv_text)).setProgress(0f);
        }
    }

    @Override
    public void setListeners() {
        vpVideo.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private boolean checkIfScroll = false;
            private int lastPosition;

            @SuppressWarnings("ConstantConditions")
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (checkIfScroll) {
                    if (positionOffset > 0) {
                        ColorTrackView left = (ColorTrackView) stlVideo.getTabAt(position).findViewById(R.id.ctv_text);
                        ColorTrackView right = (ColorTrackView) stlVideo.getTabAt(position + 1).findViewById(R.id.ctv_text);

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
                ((ColorTrackView) stlVideo.getTabAt(lastPosition).findViewById(R.id.ctv_text)).setProgress(0f);
                ((ColorTrackView) stlVideo.getTabAt(position).findViewById(R.id.ctv_text)).setProgress(1f);
                lastPosition = position;
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {//这个参数有三种状态(0、1、2,调用顺序为1，2，0),0表示滑动完毕，1表示按下状态， 2表示手指抬起状态
                if (state == 1) {
                    checkIfScroll = true;
                } else if (state == 0) {
                    checkIfScroll = false;
                }
            }
        });
    }

    @Override
    public void lazyFetchData() {

    }
}
