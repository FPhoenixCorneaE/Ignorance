package com.livelearn.ignorance.ui.view.video;

import android.content.Context;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.view.View;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.model.bean.video.PhotoLithographyTypeListModel;
import com.livelearn.ignorance.presenter.contract.video.PhotoLithographyPagerContract;
import com.livelearn.ignorance.ui.adapter.video.PhotoLithographyTypeListAdapter;
import com.livelearn.ignorance.utils.DisplayUtils;
import com.livelearn.ignorance.widget.RootView;
import com.livelearn.ignorance.widget.viewpagertransformer.OverspreadTransformer;

import butterknife.BindView;

/**
 * Created on 2017/6/22.
 */

public class PhotoLithographyPagerView extends RootView implements PhotoLithographyPagerContract.View {

    @BindView(R.id.rv_video_list)
    EasyRecyclerView rvVideoList;

    private PhotoLithographyPagerContract.Presenter mPresenter;
    private PhotoLithographyTypeListAdapter mAdapter;
    private int pageNum = 1;

    public PhotoLithographyPagerView(Context context) {
        super(context);
    }

    public PhotoLithographyPagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PhotoLithographyPagerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.view_photo_lithography_pager;
    }

    @Override
    public void init() {
        isInFragment = true;

        //遮盖滑动效果需要用到
        rvVideoList.setTag(OverspreadTransformer.PARALLAX_EFFECT);

        mAdapter = new PhotoLithographyTypeListAdapter(mContext);
        rvVideoList.setAdapterWithProgress(mAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        gridLayoutManager.setSpanSizeLookup(mAdapter.obtainGridSpanSizeLookUp(3));
        SpaceDecoration itemDecoration = new SpaceDecoration(DisplayUtils.dp2px(2F));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        rvVideoList.setLayoutManager(gridLayoutManager);
        rvVideoList.addItemDecoration(itemDecoration);
    }

    @Override
    public void setPresenter(@NonNull PhotoLithographyPagerContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
        showProgress();
        mPresenter.getPhotoLithographyTypeList(pageNum);
    }

    @Override
    public void showProgress() {
        rvVideoList.showProgress();
    }

    @Override
    public void hideProgress() {
        rvVideoList.showRecycler();
    }

    @Override
    public void onSuccess(PhotoLithographyTypeListModel mData) {
        hideProgress();
        if (pageNum == 1) {
            mAdapter.clear();
        }
        mAdapter.addAll(mData.getRet().getList());
        if (mAdapter.getAllData().isEmpty()) {
            rvVideoList.showEmpty();
        }
    }

    @Override
    public void onFailure(String message) {
        if (pageNum == 1) {
            rvVideoList.showError();
        } else {
            mAdapter.pauseMore();
        }
    }

    @Override
    public void setListeners() {
        rvVideoList.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getPhotoLithographyTypeList(pageNum = 1);
            }
        });
        mAdapter.setMore(R.layout.custom_view_footer_loadmore_easy_recycler_view, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                mPresenter.getPhotoLithographyTypeList(++pageNum);
            }

            @Override
            public void onMoreClick() {
            }
        });
        mAdapter.setError(R.layout.custom_view_footer_error_easy_recycler_view, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
            }

            @Override
            public void onErrorClick() {
                mAdapter.resumeMore();
            }
        });
        mAdapter.setNoMore(R.layout.custom_view_footer_nomore_easy_recycler_view, new RecyclerArrayAdapter.OnNoMoreListener() {
            @Override
            public void onNoMoreShow() {
            }

            @Override
            public void onNoMoreClick() {
                mAdapter.resumeMore();
            }
        });
        rvVideoList.getErrorView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mPresenter != null) {
                            mPresenter.getPhotoLithographyTypeList(pageNum = 1);
                        }
                    }
                }, 1500);
            }
        });
    }
}
