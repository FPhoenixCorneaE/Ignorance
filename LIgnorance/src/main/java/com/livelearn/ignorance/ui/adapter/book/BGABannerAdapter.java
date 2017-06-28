package com.livelearn.ignorance.ui.adapter.book;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.utils.GlideUtils;

import cn.bingoogolapple.bgabanner.BGABanner;

public class BGABannerAdapter implements BGABanner.Adapter {
    private Context context;

    public BGABannerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
        GlideUtils.setupImage(context, (ImageView) view, model, R.mipmap.pic_banner_load_error);
    }
}
