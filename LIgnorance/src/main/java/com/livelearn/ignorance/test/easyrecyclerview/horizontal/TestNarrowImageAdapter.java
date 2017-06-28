package com.livelearn.ignorance.test.easyrecyclerview.horizontal;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.livelearn.ignorance.test.easyrecyclerview.Utils;

/**
 * Created by Mr.Jude on 2016/6/7.
 */
class TestNarrowImageAdapter extends RecyclerArrayAdapter<Integer> {
    TestNarrowImageAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new NarrowImageViewHolder(parent);
    }

    private static class NarrowImageViewHolder extends BaseViewHolder<Integer> {
        ImageView imgPicture;

        NarrowImageViewHolder(ViewGroup parent) {
            super(new ImageView(parent.getContext()));
            imgPicture = (ImageView) itemView;
            imgPicture.setLayoutParams(new ViewGroup.LayoutParams((int) Utils.convertDpToPixel(72f,getContext()), ViewGroup.LayoutParams.MATCH_PARENT));
            imgPicture.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        @Override
        public void setData(Integer data) {
            imgPicture.setImageResource(data);
        }
    }
}
