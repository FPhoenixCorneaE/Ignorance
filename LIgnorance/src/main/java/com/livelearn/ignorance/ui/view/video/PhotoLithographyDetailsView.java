package com.livelearn.ignorance.ui.view.video;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.callbacklistener.OnAppBarStateChangeListener;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.bean.video.PhotoLithographyDetailsModel;
import com.livelearn.ignorance.presenter.contract.video.PhotoLithographyDetailsContract;
import com.livelearn.ignorance.ui.adapter.video.PhotoLithographyRecommendListAdapter;
import com.livelearn.ignorance.utils.DisplayUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.utils.ToastUtils;
import com.livelearn.ignorance.widget.RootView;
import com.livelearn.ignorance.widget.StateLayout;
import com.ms.expandable.ExpandableTextView;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerController;

import butterknife.BindView;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import io.vov.vitamio.activity.VideoPlayActivity;

/**
 * Created on 2017/6/26.
 */

public class PhotoLithographyDetailsView extends RootView implements PhotoLithographyDetailsContract.View {

    @BindView(R.id.nvp_nice)
    NiceVideoPlayer nvpNice;

    @BindView(R.id.tb_toolbar)
    Toolbar tbToolbar;

    @BindView(R.id.ctl_collapsing_toolbar)
    CollapsingToolbarLayout ctlCollapsingToolbar;

    @BindView(R.id.abl_app_bar)
    AppBarLayout ablAppBar;

    @BindView(R.id.tv_score)
    TextView tvScore;

    @BindView(R.id.tv_type)
    TextView tvType;

    @BindView(R.id.tv_region)
    TextView tvRegion;

    @BindView(R.id.tv_air_time)
    TextView tvAirTime;

    @BindView(R.id.btn_jc_player)
    Button btnJcPlayer;

    @BindView(R.id.tv_director)
    TextView tvDirector;

    @BindView(R.id.tv_actors)
    TextView tvActors;

    @BindView(R.id.tv_expandable)
    TextView tvExpandable;

    @BindView(R.id.tv_description)
    ExpandableTextView tvDescription;

    @BindView(R.id.rv_recommend)
    RecyclerView rvRecommend;

    @BindView(R.id.nsv_nested_scroll)
    NestedScrollView nsvNestedScroll;

    @BindView(R.id.v_content)
    CoordinatorLayout vContent;

    @BindView(R.id.sl_state_layout)
    StateLayout slStateLayout;

    @BindView(R.id.fab_collection)
    FloatingActionButton fabCollection;

    private PhotoLithographyDetailsContract.Presenter mPresenter;
    private PhotoLithographyRecommendListAdapter mAdapter;
    private PhotoLithographyDetailsModel mData;

    public PhotoLithographyDetailsView(Context context) {
        super(context);
    }

    public PhotoLithographyDetailsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PhotoLithographyDetailsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.view_photo_lithography_details;
    }

    @Override
    public void init() {
        //初始化状态布局
        slStateLayout.setContentViewResId(R.id.v_content)
                .setErrorViewResId(R.id.v_error)
                .setEmptyViewResId(R.id.v_empty)
                .setLoadingViewResId(R.id.v_loading)
                .initWithState(StateLayout.VIEW_LOADING);

        ctlCollapsingToolbar.setTitle(((Activity) mContext).getIntent().getStringExtra(Constant.VIDEO_TITLE));
        //设置收缩后Toolbar上字体的颜色
        ctlCollapsingToolbar.setCollapsedTitleTextColor(ResourceUtils.getColor(R.color.color_pale));
        //设置还没收缩时状态下字体颜色
        ctlCollapsingToolbar.setExpandedTitleColor(ResourceUtils.getColor(R.color.color_light_purple));
        //导航栏返回图标
//        tbToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);

        rvRecommend.setAdapter(mAdapter = new PhotoLithographyRecommendListAdapter(null));
        rvRecommend.setLayoutManager(new GridLayoutManager(getContext(), 3));
        SpaceDecoration itemDecoration = new SpaceDecoration(DisplayUtils.dp2px(4F));
        itemDecoration.setPaddingEdgeSide(false);
        itemDecoration.setPaddingStart(false);
        itemDecoration.setPaddingHeaderFooter(false);
        rvRecommend.addItemDecoration(itemDecoration);
    }

    @Override
    public void setPresenter(@NonNull PhotoLithographyDetailsContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
        showProgress();
        this.mPresenter.getPhotoLithographyDetailsByMediaId();
    }

    @Override
    public void showProgress() {
        slStateLayout.setState(StateLayout.VIEW_LOADING);
    }

    @Override
    public void hideProgress() {
        slStateLayout.setState(StateLayout.VIEW_CONTENT);
    }

    @Override
    public void onSuccess(PhotoLithographyDetailsModel mData) {
        this.mData = mData;
        if (null == mData) {
            onEmpty();
        } else {
            hideProgress();

            //初始化NiceVideoPlayer
            NiceVideoPlayerController controller = new NiceVideoPlayerController(mContext);
            //视频标题
            controller.setTitle(mData.getRet().getTitle());
            //视频未开始时显示的图片
            controller.setImage(mData.getRet().getPic());
            nvpNice.setController(controller);
            //视频播放器类型
            nvpNice.setPlayerType(NiceVideoPlayer.PLAYER_TYPE_IJK);
            //视频地址
            nvpNice.setUp(mData.getRet().getVideoUrl(), null);

            tvScore.setText(String.valueOf(mData.getRet().getScore()));
            tvType.setText(mData.getRet().getVideoType());
            tvRegion.setText(mData.getRet().getRegion());
            tvAirTime.setText(String.valueOf(mData.getRet().getAirTime()));

            tvDirector.setText(String.valueOf("导演：" + mData.getRet().getDirector()));
            tvActors.setText(String.valueOf("主演：" + mData.getRet().getActors()));
            tvDescription.setText(mData.getRet().getDescription());

            if (mData.getRet().getList().size() > 1) {
                mAdapter.setNewData(mData.getRet().getList().get(1).getChildList());
            } else {
                mAdapter.setNewData(mData.getRet().getList().get(0).getChildList());
            }
        }
    }

    @Override
    public void onFailure(String message) {
        slStateLayout.setState(StateLayout.VIEW_ERROR);
    }

    @Override
    public void onEmpty() {
        slStateLayout.setState(StateLayout.VIEW_EMPTY);
    }

    @Override
    public void setListeners() {
        ablAppBar.addOnOffsetChangedListener(new OnAppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    //展开状态,隐藏导航栏返回图标
                    tbToolbar.setNavigationIcon(null);
                    tbToolbar.setNavigationOnClickListener(null);
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                } else {
                    //中间状态,显示导航栏返回图标
                    tbToolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
                    tbToolbar.setNavigationOnClickListener(mOnBackClickListener);
                }
            }
        });
        slStateLayout.getErrorView().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress();
                mPresenter.getPhotoLithographyDetailsByMediaId();
            }
        });
    }

    private OnClickListener mOnBackClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            ((Activity) mContext).onBackPressed();
        }
    };

    @OnClick({R.id.btn_jc_player, R.id.btn_vitamio_player, R.id.fab_collection})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_jc_player:/*节操播放器*/
                if (null != mData && !mData.getRet().getVideoUrl().isEmpty()) {
                    JCVideoPlayer.startFullscreen(mContext, JCVideoPlayerStandard.class,
                            mData.getRet().getVideoUrl(), mData.getRet().getTitle());
                } else {
                    ToastUtils.showToast("该视频暂时不能播放");
                }
                break;
            case R.id.btn_vitamio_player:/*vitamio播放器*/
                if (null != mData && !mData.getRet().getVideoUrl().isEmpty()) {
                    VideoPlayActivity.startVideoPlay((Activity) mContext, mData.getRet().getVideoUrl(), mData.getRet().getTitle());
                } else {
                    ToastUtils.showToast("该视频暂时不能播放");
                }
                break;
            case R.id.fab_collection:
                break;
        }
    }
}
