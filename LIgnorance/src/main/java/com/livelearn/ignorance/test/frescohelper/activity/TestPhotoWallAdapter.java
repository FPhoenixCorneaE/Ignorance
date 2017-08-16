package com.livelearn.ignorance.test.frescohelper.activity;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.ImageLoader;
import com.facebook.fresco.helper.Phoenix;
import com.facebook.fresco.helper.photo.entity.PhotoInfo;
import com.facebook.fresco.helper.utils.DensityUtil;
import com.livelearn.ignorance.R;

import java.util.ArrayList;

/**
 * Created by android_ls on 16/11/2.
 */

public class TestPhotoWallAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<PhotoInfo> mPhotos;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;

    public TestPhotoWallAdapter(ArrayList<PhotoInfo> photos, OnItemClickListener onItemClickListener) {
        this.mPhotos = photos;
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }

        final PhotoViewHolder photoViewHolder = new PhotoViewHolder(mLayoutInflater.inflate(R.layout.adapter_test_photo_wall, parent, false));
        photoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(mPhotos, photoViewHolder.getAdapterPosition());
                }
            }
        });

        return photoViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PhotoViewHolder) holder).bind(mPhotos.get(position));
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    private static class PhotoViewHolder extends RecyclerView.ViewHolder {

        private int itemDimensionSize;

        public PhotoViewHolder(View itemView) {
            super(itemView);

            itemDimensionSize = (DensityUtil.getDisplayWidth(itemView.getContext())
                    - DensityUtil.dipToPixels(itemView.getContext(), 30f)) / 3;
            ViewGroup.LayoutParams vlp = itemView.getLayoutParams();
            vlp.width = itemDimensionSize;
            vlp.height = itemDimensionSize;
        }

        public void bind(final PhotoInfo photoInfo) {
            // 如果照片墙图片特多，使用这种方式，滚动时会卡，不流畅
//            ImageLoader.loadImage((SimpleDraweeView)itemView, photoInfo.thumbnailUrl);
            // 建议使用使用下面的这种加载方式，请求显示指定宽高的图片

            Uri uri = Uri.parse(photoInfo.thumbnailUrl);
            if (UriUtil.isNetworkUri(uri)) {
                // ImageLoader.loadImage((SimpleDraweeView)itemView, photoInfo.thumbnailUrl, itemDimensionSize, itemDimensionSize);
                Phoenix.with((SimpleDraweeView) itemView)
                        .setWidth(itemDimensionSize)
                        .setHeight(itemDimensionSize)
                        .load(photoInfo.thumbnailUrl);
            } else {
                ImageLoader.loadFile((SimpleDraweeView) itemView, photoInfo.thumbnailUrl, itemDimensionSize, itemDimensionSize);
            }
        }
    }

}
