package com.livelearn.ignorance.test.ninegridlayout;

import android.widget.ImageView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.utils.DateUtils;
import com.livelearn.ignorance.utils.GlideUtils;
import com.livelearn.ignorance.utils.ToastUtils;
import com.livelearn.ignorance.widget.ninegridlayout.niceninelayout.NiceNineImageLayout;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 2017/7/6.
 */

public class TestNiceNineImageLayoutListAdapter extends BaseQuickAdapter<Journal> {

    public TestNiceNineImageLayoutListAdapter(List<Journal> data) {
        super(R.layout.adapter_test_nice_nine_image_layout_list, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Journal journal) {
        GlideUtils.setupImage(mContext, (ImageView) holder.getView(R.id.iv_friend_avatar), journal.getFriendAvatarUrl(), R.drawable.shape_circle_solidlightgray);
        holder.setText(R.id.tv_friend_name, journal.getFriendName());
        holder.setText(R.id.tv_journal_description, journal.getJournalDescription());
        ((NiceNineImageLayout) holder.getView(R.id.nnil_picture)).bindData(Arrays.asList(journal.getPictureList().split(",")));
        ((NiceNineImageLayout) holder.getView(R.id.nnil_picture)).setItemDelegate(new NiceNineImageLayout.ItemDelegate() {
            @Override
            public void onItemClick(int position) {
                ToastUtils.showToast("位置" + position);
            }
        });
        holder.setText(R.id.tv_release_location, journal.getReleaseLocation());
        holder.setText(R.id.tv_release_time, DateUtils.timeDisposal(journal.getReleaseTime(), "yyyy年MM月dd日 HH:mm:ss"));
    }
}
