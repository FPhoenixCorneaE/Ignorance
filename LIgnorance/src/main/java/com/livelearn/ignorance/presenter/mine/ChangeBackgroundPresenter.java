package com.livelearn.ignorance.presenter.mine;

import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.bean.mine.BackgroundBean;
import com.livelearn.ignorance.ui.adapter.mine.BackgroundAdapter;
import com.livelearn.ignorance.ui.view.mine.IChangeBackgroundView;
import com.livelearn.ignorance.utils.DisplayUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/8/8.
 */

public class ChangeBackgroundPresenter extends RxPresenter {

    private IChangeBackgroundView iChangeBackgroundView;
    private BaseActivity mContext;
    private BackgroundAdapter mBackgroundAdapter;
    private List<BackgroundBean> mBackgroundBeans;

    public ChangeBackgroundPresenter(IChangeBackgroundView iChangeBackgroundView) {
        this.iChangeBackgroundView = iChangeBackgroundView;
        this.mContext = iChangeBackgroundView.getmContext();
    }

    /**
     * 设置适配器
     */
    public void setAdapter() {
        iChangeBackgroundView.getRecyclerView().setLayoutManager(new GridLayoutManager(mContext, 2));
        //item间距
        SpaceDecoration itemDecoration = new SpaceDecoration(DisplayUtils.dp2px(10F));
        itemDecoration.setPaddingEdgeSide(false);
        itemDecoration.setPaddingStart(false);
        itemDecoration.setPaddingHeaderFooter(false);
        iChangeBackgroundView.getRecyclerView().addItemDecoration(itemDecoration);
        iChangeBackgroundView.getRecyclerView().setNestedScrollingEnabled(false);
        iChangeBackgroundView.getRecyclerView().setHasFixedSize(true);
        mBackgroundAdapter = new BackgroundAdapter(mBackgroundBeans = getDatas());
        iChangeBackgroundView.getRecyclerView().setAdapter(mBackgroundAdapter);
    }

    /**
     * item点击监听
     */
    public void setOnItemClickListener() {
        mBackgroundAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mBackgroundAdapter.getSelectedBackground().setInUse(false);
                mBackgroundBeans.get(position).setInUse(true);
                mBackgroundAdapter.setSelectedBackground(mBackgroundBeans.get(position));
                mBackgroundAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 获取当前选中的背景
     */
    public String getBackgroundUrlInUse() {
        return mBackgroundAdapter.getSelectedBackground().getBackgroundUrl();
    }

    /**
     * 获取背景资源
     */
    private List<BackgroundBean> getDatas() {
        String[] backgroundUrls = ResourceUtils.getStringArray(R.array.MyInformation_Background);
        List<BackgroundBean> mDatas = new ArrayList<>();
        mDatas.add(new BackgroundBean()
                .setVip(true)
                .setNew(false)
                .setOriginal(true));
        mDatas.add(new BackgroundBean()
                .setVip(true)
                .setNew(false)
                .setOriginal(false));
        mDatas.add(new BackgroundBean()
                .setVip(false)
                .setNew(true)
                .setOriginal(true));
        mDatas.add(new BackgroundBean()
                .setVip(false)
                .setNew(true)
                .setOriginal(false));
        mDatas.add(new BackgroundBean()
                .setVip(false)
                .setNew(false)
                .setOriginal(true));
        mDatas.add(new BackgroundBean()
                .setVip(false)
                .setNew(false)
                .setOriginal(true));
        mDatas.add(new BackgroundBean()
                .setVip(false)
                .setNew(false)
                .setOriginal(false));
        String backgroundUrlInUse = mContext.getIntent().getStringExtra(Constant.BACKGROUND_URL_IN_USE);
        for (int i = 0; i < backgroundUrls.length; i++) {
            mDatas.get(i).setBackgroundUrl(backgroundUrls[i]);
            if (TextUtils.equals(backgroundUrls[i], backgroundUrlInUse)) {
                mDatas.get(i).setInUse(true);
            }
        }
        return mDatas;
    }
}
