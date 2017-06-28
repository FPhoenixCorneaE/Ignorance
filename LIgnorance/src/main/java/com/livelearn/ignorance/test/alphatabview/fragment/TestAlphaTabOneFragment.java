package com.livelearn.ignorance.test.alphatabview.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.utils.PaletteUtils;
import com.livelearn.ignorance.utils.immersionbar.BarHide;
import com.livelearn.ignorance.utils.immersionbar.ImmersionBar;

import butterknife.BindView;


public class TestAlphaTabOneFragment extends BaseFragment {

    @BindView(R.id.iv_image)
    ImageView ivImage;

    @BindView(R.id.ll_root)
    LinearLayout llRoot;

    private boolean showStatusBar;

    @Override
    public void immersionInit() {
        ImmersionBar.with(getActivity())
                .statusBarDarkFont(false)
                .hideBar(BarHide.FLAG_HIDE_STATUS_BAR)//隐藏状态栏
                .init();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_test_alpha_tab_one;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //图书封面
        Glide.with(mContext)
                .load(R.mipmap.pic_pirates_female_emperor)
                .asBitmap()
                .placeholder(R.mipmap.pic_pirates_female_emperor)
                .error(R.mipmap.pic_pirates_female_emperor)
                .listener(new RequestListener<Integer, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, Integer model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Integer model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        int color = PaletteUtils.getColor(resource);
                        llRoot.setBackgroundColor(color);
                        return false;
                    }
                })
                .into(ivImage);
    }

    @Override
    public void setListeners() {
        llRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!showStatusBar) {
                    ImmersionBar.with(getActivity())
                            .hideBar(BarHide.FLAG_SHOW_BAR)
                            .init();
                } else {
                    ImmersionBar.with(getActivity())
                            .hideBar(BarHide.FLAG_HIDE_STATUS_BAR)
                            .init();
                }
                showStatusBar = !showStatusBar;
            }
        });
    }

    @Override
    public void lazyFetchData() {

    }
}
