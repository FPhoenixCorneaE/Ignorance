package com.livelearn.ignorance.ui.fragment.image;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.ColorTrackView;
import com.livelearn.ignorance.widget.smarttablayout.FragmentPagerItem;
import com.livelearn.ignorance.widget.smarttablayout.FragmentPagerItems;
import com.livelearn.ignorance.widget.smarttablayout.FragmentStatePagerItemAdapter;
import com.livelearn.ignorance.widget.smarttablayout.SmartTabLayout;
import com.livelearn.ignorance.widget.viewpagertransformer.DepthPageTransformer;

import butterknife.BindView;

/**
 * Created on 2017/5/31.
 */

public class TianGouPrettyPictureFragment extends BaseFragment {

    @BindView(R.id.stl_image)
    SmartTabLayout stlImage;

    @BindView(R.id.vp_image)
    ViewPager vpImage;

    private int currentPosition;
    private String[] prettyPictureArray;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_tian_gou_pretty_picture;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentPagerItems fragmentPagerItems = new FragmentPagerItems(mContext);
        prettyPictureArray = ResourceUtils.getStringArray(R.array.ImageFragment_TianGouPrettyPicture);
        for (int i = 0; i < prettyPictureArray.length; i++) {
            String prettyPicture = prettyPictureArray[i];
            Bundle bundle = new Bundle();
            bundle.putInt(Constant.IMAGE_TYPE, i + 1);
            fragmentPagerItems.add(FragmentPagerItem.of(prettyPicture, TianGouPrettyPicturePagerFragment.class, bundle));
        }
        //Fragment嵌套Fragment,用Fragment的getChildFragmentManager()代替FragmentActivity的getSupportFragmentManager()
        FragmentStatePagerItemAdapter pagerItemAdapter = new FragmentStatePagerItemAdapter(getChildFragmentManager(), fragmentPagerItems);

        //转换效果
        vpImage.setPageTransformer(true, new DepthPageTransformer());
        vpImage.setAdapter(pagerItemAdapter);
        vpImage.setOffscreenPageLimit(3);
        stlImage.setViewPager(vpImage);

        for (int i = 0; i < prettyPictureArray.length; i++) {
            ((ColorTrackView) stlImage.getTabAt(i).findViewById(R.id.ctv_text)).setText(prettyPictureArray[i]);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //ColorTrackView这个东西存在一个小小的问题，就是颜色改变会乱掉
        //每次恢复的时候重新设置颜色
        for (int i = 0; i < prettyPictureArray.length; i++) {
            if (i == currentPosition) {
                ((ColorTrackView) stlImage.getTabAt(currentPosition).findViewById(R.id.ctv_text)).setProgress(1f);
            } else {
                ((ColorTrackView) stlImage.getTabAt(i).findViewById(R.id.ctv_text)).setProgress(0f);
            }
        }
    }

    @Override
    public void setListeners() {
        vpImage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private boolean checkIfScroll = false;
            private int lastPosition;

            @SuppressWarnings("ConstantConditions")
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (checkIfScroll) {
                    if (positionOffset > 0) {
                        ColorTrackView left = (ColorTrackView) stlImage.getTabAt(position).findViewById(R.id.ctv_text);
                        ColorTrackView right = (ColorTrackView) stlImage.getTabAt(position + 1).findViewById(R.id.ctv_text);

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
                ((ColorTrackView) stlImage.getTabAt(lastPosition).findViewById(R.id.ctv_text)).setProgress(0f);
                ((ColorTrackView) stlImage.getTabAt(position).findViewById(R.id.ctv_text)).setProgress(1f);
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
