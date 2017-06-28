package com.livelearn.ignorance.ui.adapter.viewholder;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.bean.video.PhotoLithographyTypeListModel;
import com.livelearn.ignorance.ui.activity.video.PhotoLithographyDetailsActivity;
import com.livelearn.ignorance.utils.DisplayUtils;
import com.livelearn.ignorance.utils.GlideUtils;
import com.livelearn.ignorance.utils.IntentUtils;

/**
 * Created on 2017/6/22.
 */

public class PhotoLithographyTypeListViewHolder extends BaseViewHolder<PhotoLithographyTypeListModel.RetBean.ListBean> implements View.OnClickListener {

    private LinearLayout llItem;
    private ImageView ivPicture;
    private TextView tvTitle;

    private PhotoLithographyTypeListModel.RetBean.ListBean mData;

    public PhotoLithographyTypeListViewHolder(ViewGroup parent) {
        super(parent, R.layout.viewholder_photo_lithography_type_list);
        llItem = $(R.id.ll_item);
        ivPicture = $(R.id.iv_picture);
        tvTitle = $(R.id.tv_title);
    }

    @Override
    public void setData(PhotoLithographyTypeListModel.RetBean.ListBean data) {
        this.mData = data;
        ViewGroup.LayoutParams params = ivPicture.getLayoutParams();
        int width = DisplayUtils.getScreenWidth() / 3;
        params.height = (int) (width * 1.28);
        ivPicture.setLayoutParams(params);
        GlideUtils.setupImage(getContext(), ivPicture, data.getPic(), R.mipmap.ic_video_default_200w);
        tvTitle.setText(data.getTitle());
        llItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.VIDEO_TITLE, mData.getTitle());
        bundle.putString(Constant.VIDEO_MEDIA_ID, mData.getDataId());
        IntentUtils.startActivity(getContext(), PhotoLithographyDetailsActivity.class, bundle, R.anim.pull_in_right,R.anim.push_out_right);
    }
}
