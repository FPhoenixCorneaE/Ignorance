package com.livelearn.ignorance.ui.activity.video;

import android.os.Bundle;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.presenter.video.PhotoLithographyDetailsPresenter;
import com.livelearn.ignorance.ui.view.video.PhotoLithographyDetailsView;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * 微影详情
 * Created on 2017/6/26.
 */

public class PhotoLithographyDetailsActivity extends BaseActivity {

    @BindView(R.id.v_photo_lithography_details)
    PhotoLithographyDetailsView vPhotoLithographyDetails;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_photo_lithography_details;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {

        setSwipeBackEnable(false);

        String title = getIntent().getStringExtra(Constant.VIDEO_TITLE);
        String mediaId = getIntent().getStringExtra(Constant.VIDEO_MEDIA_ID);
        mPresenter = new PhotoLithographyDetailsPresenter(vPhotoLithographyDetails, title, mediaId);
    }

    @Override
    public void setListeners() {

    }

    @Override
    protected void onPause() {
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
        JCVideoPlayer.releaseAllVideos();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if (NiceVideoPlayerManager.instance().onBackPressd()) {
            return;
        }
        if (JCVideoPlayer.backPress(mContext)) {
            return;
        }
        finish();
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_right);
    }
}
