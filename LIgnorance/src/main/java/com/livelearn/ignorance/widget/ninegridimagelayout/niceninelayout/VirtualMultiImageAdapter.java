package com.livelearn.ignorance.widget.ninegridimagelayout.niceninelayout;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.bumptech.glide.Glide;
import com.livelearn.ignorance.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wxy on 2017/5/31.
 */

public class VirtualMultiImageAdapter extends VirtualLayoutAdapter<VirtualMultiImageAdapter.ImageViewHolder> implements MyItemTouchCallback.ItemTouchAdapter {
    private List<String> pictures = new ArrayList<>();
    private Context context;
    private boolean canDrag;
    private int itemMargin;
    private int displayW;
    private NiceNineImageLayout.ItemDelegate mItemDelegate;

    public VirtualMultiImageAdapter(@NonNull VirtualLayoutManager layoutManager, Context context, boolean canDrag, int itemMargin, int displayW) {
        super(layoutManager);
        this.context = context;
        this.canDrag = canDrag;
        this.itemMargin = itemMargin;
        this.displayW = displayW;
    }

    public void bindData(List<String> pictures) {
        this.pictures = pictures;
        notifyDataSetChanged();
    }

    public void setItemDelegate(NiceNineImageLayout.ItemDelegate itemDelegate) {
        mItemDelegate = itemDelegate;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_virtual_multi_image, null));
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {
        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int width, height;
        int imageCount = pictures.size();
        if (imageCount == 1) {
            width = displayW;
            height = width;
        } else if (imageCount == 2) {
            width = (int) ((displayW - itemMargin) * 0.5);
            height = width;
        } else if (imageCount == 3) {
            if (position == 0) {
                width = (int) ((displayW - itemMargin * 2) * 0.33) * 2 + itemMargin;
                height = width;
                layoutParams.rightMargin = itemMargin;
            } else {
                width = (int) ((displayW - itemMargin * 2) * 0.33);
                height = width;
                if (position == 2) {
                    layoutParams.topMargin = itemMargin;
                }
            }
        } else if (imageCount == 4) {
            if (position == 0) {
                width = displayW;
                height = (int) (width * 0.5);
            } else {
                width = (int) ((displayW - itemMargin * 2) * 0.33);
                height = width;
            }
        } else if (imageCount == 5) {
            if (position == 0 || position == 1) {
                width = (int) ((displayW - itemMargin) * 0.5);
                height = width;
            } else {
                width = (int) ((displayW - itemMargin * 2) * 0.33);
                height = width;
            }
        } else if (imageCount == 6) {
            if (position == 0) {
                width = (int) ((displayW - itemMargin * 2) * 0.33) * 2 + itemMargin;
                height = width;
                layoutParams.rightMargin = itemMargin;
            } else {
                width = (int) ((displayW - itemMargin * 2) * 0.33);
                height = width;
                if (position == 2 || position == 3 || position == 4 || position == 5) {
                    layoutParams.topMargin = itemMargin;
                }
            }
        } else if (imageCount == 7) {
            if (position == 0) {
                width = displayW;
                height = (int) (width * 0.5);
            } else {
                width = (int) ((displayW - itemMargin * 2) * 0.33);
                height = width;
            }
        } else if (imageCount == 8) {
            if (position == 0 || position == 1) {
                width = (int) ((displayW - itemMargin) * 0.5);
                height = width;
            } else {
                width = (int) ((displayW - itemMargin * 2) * 0.33);
                height = width;
            }
        } else {
            width = (int) ((displayW - itemMargin * 2) * 0.33);
            height = width;
        }
        layoutParams.width = width;
        layoutParams.height = height;
        holder.itemView.setLayoutParams(layoutParams);

        final String imageUrl = pictures.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!canDrag) {
                    if (mItemDelegate != null) {
                        mItemDelegate.onItemClick(holder.getAdapterPosition());
                    }
                }
            }
        });
        Glide.with(context)
                .load(imageUrl)
                .centerCrop()
                .crossFade()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    @Override
    public void onMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(pictures, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(pictures, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    public List<String> getPictures() {
        return pictures;
    }

    @Override
    public void onSwiped(int position) {
        notifyItemRemoved(position);
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_multi_image);
        }
    }

}
