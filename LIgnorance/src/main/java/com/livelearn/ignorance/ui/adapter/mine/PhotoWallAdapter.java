package com.livelearn.ignorance.ui.adapter.mine;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.ImageLoader;
import com.facebook.fresco.helper.Phoenix;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.utils.DisplayUtils;
import com.livelearn.ignorance.widget.animcheckbox.SmoothCircleCheckBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/8/16.
 */

public class PhotoWallAdapter extends RecyclerArrayAdapter<String> {

    private boolean enableEditPhotoWall;
    private ArrayList<String> selectedPhotoWall = new ArrayList<>();

    public PhotoWallAdapter(Context context) {
        super(context);
    }

    public PhotoWallAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(final ViewGroup parent, int viewType) {
        return new BaseViewHolder<String>(parent, R.layout.viewholder_photo_wall) {
            @Override
            public void setData(final String data) {
                int itemSize = (parent.getMeasuredWidth() - DisplayUtils.dp2px(2 * 3F)) / 3;
                $(R.id.sdv_item).getLayoutParams().width = itemSize;
                $(R.id.sdv_item).getLayoutParams().height = itemSize;

                // 如果照片墙图片特多，使用这种方式，滚动时会卡，不流畅
//        ImageLoader.loadImage(sdvItem, data);

                // 建议使用使用下面的这种加载方式，请求显示指定宽高的图片
                Uri uri = Uri.parse(data);
                if (UriUtil.isNetworkUri(uri)) {
//            ImageLoader.loadImage(sdvItem, data, itemSize, itemSize);
                    Phoenix.with((SimpleDraweeView) $(R.id.sdv_item))
                            .setWidth(itemSize)
                            .setHeight(itemSize)
                            .load(data);
                } else {
                    ImageLoader.loadFile((SimpleDraweeView) $(R.id.sdv_item), data, itemSize, itemSize);
                }

                if (isEnableEditPhotoWall()) {
                    $(R.id.cb_anim).setVisibility(View.VISIBLE);
                    ((SmoothCircleCheckBox) $(R.id.cb_anim)).setChecked(false, false);
                } else {
                    $(R.id.cb_anim).setVisibility(View.INVISIBLE);
                }

                ((SmoothCircleCheckBox) $(R.id.cb_anim)).setOnCheckedChangeListener(new SmoothCircleCheckBox.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(SmoothCircleCheckBox checkBox, boolean isChecked) {
                        if (isChecked) {
                            if (!selectedPhotoWall.contains(data)) {
                                selectedPhotoWall.add(data);
                            }
                        } else {
                            if (selectedPhotoWall.contains(data)) {
                                selectedPhotoWall.remove(data);
                            }
                        }
                    }
                });
            }
        };
    }

    public boolean isEnableEditPhotoWall() {
        return enableEditPhotoWall;
    }

    public void setEnableEditPhotoWall(boolean enableEditPhotoWall) {
        this.enableEditPhotoWall = enableEditPhotoWall;
        selectedPhotoWall.clear();
        notifyDataSetChanged();
    }

    public ArrayList<String> getSelectedPhotoWall() {
        return selectedPhotoWall;
    }
}
