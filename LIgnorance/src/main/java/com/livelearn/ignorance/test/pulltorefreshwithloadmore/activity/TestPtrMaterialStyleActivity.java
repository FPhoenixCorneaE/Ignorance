package com.livelearn.ignorance.test.pulltorefreshwithloadmore.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.utils.DisplayUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.TitleBar;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

public class TestPtrMaterialStyleActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.iv_image)
    ImageView ivImage;

    @BindView(R.id.pfl_pull_to_refresh)
    PtrFrameLayout pflPullToRefresh;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_ptr_material_style;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        MaterialHeader header = new MaterialHeader(mContext);
        int[] colors = ResourceUtils.getIntArray(R.array.SwipeRefresh_Colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, DisplayUtils.dp2px(15), 0, 0);
        header.setPtrFrameLayout(pflPullToRefresh);

        pflPullToRefresh.setLoadingMinTime(1000);
        pflPullToRefresh.setDurationToCloseHeader(1500);
        pflPullToRefresh.setHeaderView(header);
        pflPullToRefresh.addPtrUIHandler(header);
        pflPullToRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                pflPullToRefresh.autoRefresh(true);
            }
        }, 100);
    }

    @Override
    public void setListeners() {
        pflPullToRefresh.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                String imageUrl = getIntent().getStringExtra(Constant.IMAGE_URL);
                if (imageUrl == null) {
                    imageUrl = "http://img5.duitang.com/uploads/blog/201407/17/20140717113117_mUssJ.thumb.jpeg";
                }
                Glide.with(mContext)
                        .load(imageUrl)
                        .placeholder(new ColorDrawable(ContextCompat.getColor(mContext, R.color.color_gray)))
                        .crossFade()
                        .into(new GlideDrawableImageViewTarget(ivImage) {
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                                super.onResourceReady(resource, animation);
                                pflPullToRefresh.refreshComplete();
                            }
                        });
            }
        });
    }
}
