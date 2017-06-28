package com.livelearn.ignorance.test.easyrecyclerview.staggeredgrid;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.livelearn.ignorance.test.easyrecyclerview.entites.Picture;
import com.livelearn.ignorance.test.easyrecyclerview.viewholder.ImageViewHolder;

/**
 * Created by Mr.Jude on 2016/6/7.
 */
class TestImageAdapter extends RecyclerArrayAdapter<Picture> {
    TestImageAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(parent);
    }
}
