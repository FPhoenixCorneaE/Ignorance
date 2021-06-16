package com.livelearn.ignorance.ui.view.book;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.bean.book.BannerModel;
import com.livelearn.ignorance.model.bean.book.BookRecommendModel;
import com.livelearn.ignorance.presenter.contract.book.BookRecommendContract;
import com.livelearn.ignorance.ui.activity.book.LongTimeBookDetailsActivity;
import com.livelearn.ignorance.ui.adapter.book.BGABannerAdapter;
import com.livelearn.ignorance.ui.adapter.book.BookRecommendGridAdapter;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.RootView;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.viewtype.ProgressActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 书籍推荐
 */
public class BookRecommendView extends RootView implements BookRecommendContract.View {

    @BindView(R.id.bga_banner)
    BGABanner bgaBanner;

    @BindView(R.id.rv_recommend_hot)
    RecyclerView rvRecommendHot;

    @BindView(R.id.rv_recommend_new)
    RecyclerView rvRecommendNew;

    @BindView(R.id.pa_progress)
    ProgressActivity paProgress;

    private BookRecommendGridAdapter bookRecommendHot;
    private BookRecommendGridAdapter bookRecommendNew;
    private List<BannerModel> bannerList = new ArrayList<>();
    private BookRecommendContract.Presenter presenter;

    public BookRecommendView(Context context) {
        super(context);
    }

    public BookRecommendView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BookRecommendView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.view_book_recommend;
    }

    @Override
    public void init() {
        isInFragment = true;

        rvRecommendHot.setLayoutManager(new GridLayoutManager(mContext, 4));
        rvRecommendNew.setLayoutManager(new GridLayoutManager(mContext, 4));
        //如果Item高度固定  增加该属性能够提高效率
        rvRecommendHot.setHasFixedSize(true);
        rvRecommendNew.setHasFixedSize(true);
        //创建适配器
        bookRecommendHot = new BookRecommendGridAdapter(null);
        bookRecommendNew = new BookRecommendGridAdapter(null);
        //设置加载动画
        bookRecommendHot.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        bookRecommendNew.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //加载适配器
        rvRecommendHot.setAdapter(bookRecommendHot);
        rvRecommendNew.setAdapter(bookRecommendNew);
    }

    @Override
    public void setListeners() {
        bgaBanner.setOnItemClickListener(new BGABanner.OnItemClickListener() {
            @Override
            public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
                jumpToBookDetailsActivity(mContext, bannerList.get(position).getUrl(), bannerList.get(position).getBannerTitle());
            }
        });
        bookRecommendHot.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                jumpToBookDetailsActivity(mContext, bookRecommendHot.getItem(position).getCodeId(), bookRecommendHot.getItem(position).getBookName());
            }
        });
        bookRecommendNew.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                jumpToBookDetailsActivity(mContext, bookRecommendNew.getItem(position).getCodeId(), bookRecommendNew.getItem(position).getBookName());
            }
        });
    }

    private void jumpToBookDetailsActivity(Context mContext, String codeId, String bookName) {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.BOOK_URL, codeId);
        bundle.putString(Constant.BOOK_NAME, bookName);
        IntentUtils.startActivity(mContext, LongTimeBookDetailsActivity.class, bundle);
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
    public void onSuccess(BookRecommendModel bookRecommendModel) {
        bannerList = bookRecommendModel.getBanner();
        List<String> bannerTitle = new ArrayList<>();
        List<String> bannerImageUrl = new ArrayList<>();
        for (int i = 0; i < bookRecommendModel.getBanner().size(); i++) {
            bannerTitle.add(bannerList.get(i).getBannerTitle());
            bannerImageUrl.add(bannerList.get(i).getImageUrl());
        }
        bgaBanner.setAdapter(new BGABannerAdapter(mContext));
        bgaBanner.setData(bannerImageUrl, bannerTitle);

        bookRecommendHot.setNewData(bookRecommendModel.getHotBook());//新增数据
        bookRecommendNew.setNewData(bookRecommendModel.getNewBook());//新增数据
    }

    @Override
    public void onFailure(String msg) {
        paProgress.showError(ResourceUtils.getDrawable(R.mipmap.pic_load_error), Constant.ERROR_TITLE, Constant.ERROR_CONTEXT, Constant.ERROR_BUTTON, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paProgress.showLoading();
                //重试
                presenter.getRecommendData();
            }
        });
    }

    @Override
    public void setPresenter(@NonNull BookRecommendContract.Presenter presenter) {
        this.presenter = presenter;
        presenter.getRecommendData();
    }
}
