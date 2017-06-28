package com.livelearn.ignorance.ui.adapter.video;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.bean.video.PhotoLithographyDetailsModel;
import com.livelearn.ignorance.ui.activity.video.PhotoLithographyDetailsActivity;
import com.livelearn.ignorance.utils.DisplayUtils;
import com.livelearn.ignorance.utils.GlideUtils;
import com.livelearn.ignorance.utils.IntentUtils;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

/**
 * Created on 2017/6/27.
 */

public class PhotoLithographyRecommendListAdapter extends BaseQuickAdapter<PhotoLithographyDetailsModel.RetBean.ListBean.ChildListBean> implements View.OnClickListener {

    private PhotoLithographyDetailsModel.RetBean.ListBean.ChildListBean mData;

    public PhotoLithographyRecommendListAdapter(List<PhotoLithographyDetailsModel.RetBean.ListBean.ChildListBean> data) {
        super(R.layout.adapter_photo_lithography_recommend_list, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, PhotoLithographyDetailsModel.RetBean.ListBean.ChildListBean data) {
        this.mData = data;
        ViewGroup.LayoutParams params = holder.getView(R.id.iv_picture).getLayoutParams();
        int width = DisplayUtils.getScreenWidth() / 4;
        params.height = (int) (width * 1.70);
        holder.getView(R.id.iv_picture).setLayoutParams(params);
        GlideUtils.setupImage(mContext, (ImageView) holder.getView(R.id.iv_picture), data.getPic(), R.mipmap.ic_video_default_200w);
        holder.setText(R.id.tv_title, data.getTitle());
        holder.getView(R.id.ll_item).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.VIDEO_TITLE, mData.getTitle());
        bundle.putString(Constant.VIDEO_MEDIA_ID, mData.getDataId());
        IntentUtils.startActivity(mContext, PhotoLithographyDetailsActivity.class, bundle, R.anim.pull_in_right, R.anim.push_out_right);
        ((Activity) mContext).finish();
    }
}
