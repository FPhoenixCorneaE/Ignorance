package com.livelearn.ignorance.ui.view.image;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.common.BaseUrl;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.bean.image.TianGouPrettyPictureModel;
import com.livelearn.ignorance.model.bean.image.TianGouPrettyPictureShowModel;
import com.livelearn.ignorance.presenter.contract.image.TianGouPrettyPicturePagerContract;
import com.livelearn.ignorance.ui.activity.gallery.PictureBrowsingActivity;
import com.livelearn.ignorance.ui.adapter.image.TianGouPrettyPicturePagerAdapter;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.RootView;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.viewtype.ProgressActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created on 2017/6/1.
 */

public class TianGouPrettyPicturePagerView extends RootView implements TianGouPrettyPicturePagerContract.View {

    @BindView(R.id.rv_image)
    RecyclerView rvImage;

    @BindView(R.id.srl_image)
    SwipeRefreshLayout srlImage;

    @BindView(R.id.pa_progress)
    ProgressActivity paProgress;

    private TianGouPrettyPicturePagerContract.Presenter mPresenter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private TianGouPrettyPicturePagerAdapter tianGouPrettyPicturePagerAdapter;
    private List<TianGouPrettyPictureModel.TngouBean> mDataList = new ArrayList<>();
    private int page = 1;
    private int rows = 10;

    public TianGouPrettyPicturePagerView(Context context) {
        super(context);
    }

    public TianGouPrettyPicturePagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TianGouPrettyPicturePagerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.view_tian_gou_pretty_picture_pager;
    }

    @Override
    public void init() {
        isInFragment = true;

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvImage.setLayoutManager(staggeredGridLayoutManager);
        rvImage.setHasFixedSize(true);
        rvImage.setItemAnimator(new DefaultItemAnimator());

        tianGouPrettyPicturePagerAdapter = new TianGouPrettyPicturePagerAdapter(mDataList);
        //设置加载动画
        tianGouPrettyPicturePagerAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        //设置是否可以下拉加载  以及加载条数
        tianGouPrettyPicturePagerAdapter.openLoadMore(rows, true);
        //设置加载中View
        View loadingView = ((Activity) mContext).getLayoutInflater().inflate(R.layout.footer_load_more, (ViewGroup) rvImage.getParent(), false);
        tianGouPrettyPicturePagerAdapter.setLoadingView(loadingView);
        rvImage.setAdapter(tianGouPrettyPicturePagerAdapter);
    }

    @Override
    public void setPresenter(@NonNull TianGouPrettyPicturePagerContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;

        showProgress();
        this.mPresenter.getTianGouPrettyPictureList(page, rows);
    }

    @Override
    public void showProgress() {
        paProgress.showLoading();
    }

    @Override
    public void hideProgress() {
        paProgress.showContent();
    }

    @Override
    public void onSuccess(TianGouPrettyPictureModel mData) {
        if (paProgress.isLoading()) {
            hideProgress();
        }
        if (srlImage.isRefreshing()) {
            srlImage.setRefreshing(false);
        }

        if (page == 1) {
            if (mData == null || mData.getTngou().isEmpty()) {
                noData();
            } else if (mData.getTngou().size() < rows) {
                //如果没有更多数据了,则隐藏footer布局
                loadCompleteAllData();
            } else {
                tianGouPrettyPicturePagerAdapter.setNewData(mData.getTngou());
            }
        } else {
            //如果没有更多数据了,则隐藏footer布局
            if (mData == null || mData.getTngou().size() < rows) {
                loadCompleteAllData();
            } else {
                tianGouPrettyPicturePagerAdapter.notifyDataChangedAfterLoadMore(mData.getTngou(), true);
            }
        }
        page++;
    }

    @Override
    public void onFailure(String message) {
        paProgress.showError(ResourceUtils.getDrawable(R.mipmap.pic_load_error), Constant.ERROR_TITLE, Constant.ERROR_CONTEXT, Constant.ERROR_BUTTON, new OnClickListener() {
            @Override
            public void onClick(View v) {
                paProgress.showLoading();
                //重试
                mPresenter.getTianGouPrettyPictureList(page, rows);
            }
        });
    }

    @Override
    public void noData() {
        paProgress.showEmpty(ResourceUtils.getDrawable(R.mipmap.pic_load_no_data), Constant.EMPTY_BOOKS_TITLE, Constant.EMPTY_BOOKS_CONTEXT);
    }

    @Override
    public void loadCompleteAllData() {
        tianGouPrettyPicturePagerAdapter.notifyDataChangedAfterLoadMore(false);
        View view = ((Activity) mContext).getLayoutInflater().inflate(R.layout.footer_no_more, (ViewGroup) rvImage.getParent(), false);
        view.setBackgroundColor(ResourceUtils.getColor(R.color.background_white));
        tianGouPrettyPicturePagerAdapter.addFooterView(view);
    }

    @Override
    public void showPrettyPictureDetails(ImageView imageView, TianGouPrettyPictureShowModel data, int position) {
        List<String> imageList = new ArrayList<>();
        for (int i = 0; i < data.getList().size(); i++) {
            imageList.add(BaseUrl.TIANGOU_IMAGE_URL_PREFIX + data.getList().get(i).getSrc());
        }
        PictureBrowsingActivity.start(mContext, imageList, 0);
    }

    @Override
    public void setListeners() {
        srlImage.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                if (mDataList != null) {
                    mDataList.clear();
                }
                mPresenter.getTianGouPrettyPictureList(page, rows);
            }
        });

        rvImage.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int[] lastPositions;
            private int lastVisibleItemPosition;
            private int currentScrollState = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                currentScrollState = newState;

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                if (visibleItemCount > 0 && currentScrollState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItemPosition >= totalItemCount - 1) {
                    //这里也可以做加载更多操作，不过LoadingView显示没找到方法
//                    mPresenter.getTianGouPrettyPictureList(page, rows);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (lastPositions == null) {
                    lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                lastVisibleItemPosition = findMax(lastPositions);
            }
        });

        tianGouPrettyPicturePagerAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.getTianGouPrettyPictureList(page, rows);
            }
        });

        tianGouPrettyPicturePagerAdapter.setOnItemClickListener(new TianGouPrettyPicturePagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ImageView imageView, TianGouPrettyPictureModel.TngouBean prettyPicture, int position) {
                mPresenter.getTianGouPrettyPictureShow(imageView, prettyPicture.getId(), position);
            }
        });
    }

    private int findMax(int[] lastPositions) {

        int max = lastPositions[0];
        for (int value : lastPositions) {
            max = Math.max(value, max);
        }
        return max;
    }
}
