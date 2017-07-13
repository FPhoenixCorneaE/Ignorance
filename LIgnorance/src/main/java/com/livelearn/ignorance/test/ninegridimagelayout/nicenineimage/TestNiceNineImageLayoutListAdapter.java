package com.livelearn.ignorance.test.ninegridimagelayout.nicenineimage;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.hitomi.tilibrary.loader.glideloader.GlideImageLoader;
import com.hitomi.tilibrary.style.index.NumberIndexIndicator;
import com.hitomi.tilibrary.style.progress.ProgressPieIndicator;
import com.hitomi.tilibrary.transfer.TransDialog;
import com.hitomi.tilibrary.transfer.TransferConfig;
import com.hitomi.tilibrary.transfer.Transferee;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.test.ninegridimagelayout.Journal;
import com.livelearn.ignorance.utils.DateUtils;
import com.livelearn.ignorance.utils.GlideUtils;
import com.livelearn.ignorance.widget.ninegridimagelayout.niceninelayout.NiceNineImageLayout;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 2017/7/6.
 */

public class TestNiceNineImageLayoutListAdapter extends BaseQuickAdapter<Journal> {

    private Transferee transferee;

    public TestNiceNineImageLayoutListAdapter(List<Journal> data, Transferee transferee) {
        super(R.layout.adapter_test_nice_nine_image_layout_list, data);

        this.transferee = transferee;
    }

    @Override
    protected void convert(final BaseViewHolder holder, Journal journal) {
        GlideUtils.setupImage(mContext, (ImageView) holder.getView(R.id.iv_friend_avatar), journal.getFriendAvatarUrl(), R.drawable.shape_circle_solidlightgray);
        holder.setText(R.id.tv_friend_name, journal.getFriendName());
        holder.setText(R.id.tv_journal_description, journal.getJournalDescription());
        final List<String> pictureList = Arrays.asList(journal.getPictureList().split(","));
        ((NiceNineImageLayout) holder.getView(R.id.nnil_picture)).bindData(pictureList);
        ((NiceNineImageLayout) holder.getView(R.id.nnil_picture)).setItemDelegate(new NiceNineImageLayout.ItemDelegate() {
            @Override
            public void onItemClick(int position) {
                LogUtils.i("位置" + position);

                List<ImageView> originImageList = new ArrayList<>();
                for (int i = 0; i < pictureList.size(); i++) {
                    originImageList.add((ImageView) ((ViewGroup) ((NiceNineImageLayout) holder.getView(R.id.nnil_picture)).getRecyclerView().getChildAt(i)).getChildAt(0));
                }
                TransferConfig config = TransferConfig.build()
                        .setNowThumbnailIndex(position)
                        .setSourceImageList(pictureList)
                        .setMissPlaceHolder(R.drawable.shape_solidpale)
                        .setOriginImageList(originImageList)
                        .setProgressIndicator(new ProgressPieIndicator())
                        .setIndexIndicator(new NumberIndexIndicator())
                        .setImageLoader(GlideImageLoader.with(mContext.getApplicationContext()))
                        .create();
                transferee.apply(config).show(new TransDialog.OnTransfereeStateChangeListener() {
                    @Override
                    public void onShow() {
                        Glide.with(mContext).pauseRequests();
                    }

                    @Override
                    public void onDismiss() {
                        Glide.with(mContext).resumeRequests();
                    }
                });
            }
        });
        holder.setText(R.id.tv_release_location, journal.getReleaseLocation());
        holder.setText(R.id.tv_release_time, DateUtils.timeDisposal(journal.getReleaseTime(), "yyyy年MM月dd日 HH:mm:ss"));
    }
}
