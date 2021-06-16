package com.livelearn.ignorance.ui.activity.gallery;

import android.content.Context;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import android.widget.TextView;

import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.loader.fresco.FrescoImageLoader;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.ui.adapter.gallery.PictureBrowsingAdapter;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.utils.ToastUtils;
import com.livelearn.ignorance.widget.viewpager.SpringBackViewPager;
import com.livelearn.ignorance.widget.viewpagertransformer.RotationAlphaTransformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import cn.iwgang.simplifyspan.SimplifySpanBuild;
import cn.iwgang.simplifyspan.unit.SpecialTextUnit;

/**
 * 图片浏览，加载高清长图却不OOM,双击放大，单击返回，手动放大等
 * Created on 2017/6/14.
 */

public class PictureBrowsingActivity extends BaseActivity {

    private static final String IMAGE_LIST = "IMAGE_LIST";
    private static final String CURRENT_ITEM = "CURRENT_ITEM";

    @BindView(R.id.vp_picture_browsing)
    SpringBackViewPager vpPictureBrowsing;

    @BindView(R.id.tv_current_item)
    TextView tvCurrentItem;

    private int currentItem = 0;
    private ArrayList<String> pictures;

    public static void start(Context context, List<String> paths, int currentItem) {
        Bundle args = new Bundle();
        args.putStringArray(IMAGE_LIST, paths.toArray(new String[paths.size()]));
        args.putInt(CURRENT_ITEM, currentItem);

        IntentUtils.startActivity(context, PictureBrowsingActivity.class, args, "fade");
    }

    @Override
    public int getLayoutResource() {
        BigImageViewer.initialize(FrescoImageLoader.with(getApplicationContext()));
        return R.layout.activity_picture_browsing;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        //不能滑动返回
        setSwipeBackEnable(false);

        pictures = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String[] pathArr = bundle.getStringArray(IMAGE_LIST);
            pictures.clear();
            if (pathArr != null) {
                pictures = new ArrayList<>(Arrays.asList(pathArr));
            }
            currentItem = bundle.getInt(CURRENT_ITEM);
        }

        PictureBrowsingAdapter mPagerAdapter = new PictureBrowsingAdapter(pictures);

        vpPictureBrowsing.setPageTransformer(true, new RotationAlphaTransformer());
        vpPictureBrowsing.setOffscreenPageLimit(3);
        vpPictureBrowsing.setAdapter(mPagerAdapter);
        vpPictureBrowsing.setCurrentItem(currentItem);
        vpPictureBrowsing.setPageMargin((int) (getResources().getDisplayMetrics().density * 30));

        setCurrentItem(currentItem);

    }

    private void setCurrentItem(int position) {
        if (!pictures.isEmpty()) {
            SimplifySpanBuild simplifySpanBuild = new SimplifySpanBuild(mContext, tvCurrentItem);
            simplifySpanBuild.appendSpecialUnit(new SpecialTextUnit(String.valueOf(position + 1), ResourceUtils.getColor(R.color.color_light_purple), 27))
                    .appendSpecialUnit(new SpecialTextUnit(String.valueOf("/" + pictures.size()), ResourceUtils.getColor(R.color.color_pale), 24));
            tvCurrentItem.setText(simplifySpanBuild.build());
        }
    }

    @Override
    public void setListeners() {
        vpPictureBrowsing.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private boolean misScrolled;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        misScrolled = false;
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        misScrolled = true;
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        //最后一页向左滑动进入首页
                        if (vpPictureBrowsing.getCurrentItem() == vpPictureBrowsing.getAdapter().getCount() - 1 && !misScrolled) {
                            ToastUtils.showCenterToast("已经是最后一页");
                        }
                        misScrolled = true;
                        break;
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        pictures.clear();
        pictures = null;

        if (vpPictureBrowsing != null) {
            vpPictureBrowsing.setAdapter(null);
        }
    }
}
