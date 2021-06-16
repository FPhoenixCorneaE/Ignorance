package com.livelearn.ignorance.test.easyrecyclerview.viewholder;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.test.easyrecyclerview.Utils;
import com.livelearn.ignorance.test.easyrecyclerview.entites.Ad;
import com.livelearn.ignorance.utils.ResourceUtils;

/**
 * Created by Mr.Jude on 2016/1/6.
 */
public class AdViewHolder extends BaseViewHolder<Ad> {

    public AdViewHolder(ViewGroup parent) {
        super(new ImageView(parent.getContext()));
        ImageView imageView = (ImageView) itemView;
        imageView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) Utils.convertDpToPixel(156, getContext())));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @Override
    public void setData(final Ad data) {
        ImageView imageView = (ImageView) itemView;
        Glide.with(getContext())
                .load(data.getImage())
                .placeholder(new ColorDrawable(ResourceUtils.getColor(R.color.color_dark_pale)))
                .into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(data.getUrl())));
            }
        });
    }

}
