package com.livelearn.ignorance.test.viewpager;

import android.os.Bundle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.model.bean.image.WelcomeImages;
import com.livelearn.ignorance.utils.ColorFilterUtils;
import com.livelearn.ignorance.widget.TitleBar;
import com.livelearn.ignorance.widget.viewpager.threedimensionviewpager.ThreeDimensionViewPager;

import java.util.List;

import butterknife.BindView;

public class TestThreeDimensionViewPagerActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.vp_three_dimension)
    ThreeDimensionViewPager vpThreeDimension;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_three_dimensional_view_pager;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        final List<String> imageList = new WelcomeImages().getImgData();

        //Viewpager 去掉两侧的光晕效果
        vpThreeDimension.setOverScrollMode(ViewPager.OVER_SCROLL_NEVER);
        //淡入淡出动画
        vpThreeDimension.setAnimateAlpha(false);
        vpThreeDimension.setPageMargin((int) (getResources().getDisplayMetrics().density * 20));
        vpThreeDimension.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView view = new ImageView(mContext);
                //改变图片的亮度
                ColorFilterUtils.changeLight(view, 20);
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                Glide.with(mContext).load(imageList.get(position)).crossFade().into(view);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
    }

    @Override
    public void setListeners() {

    }
}
