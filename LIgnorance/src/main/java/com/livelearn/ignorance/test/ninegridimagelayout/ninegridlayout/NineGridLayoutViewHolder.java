package com.livelearn.ignorance.test.ninegridimagelayout.ninegridlayout;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.test.ninegridimagelayout.Journal;
import com.livelearn.ignorance.ui.activity.gallery.PhotoGalleryActivity;
import com.livelearn.ignorance.utils.DateUtils;
import com.livelearn.ignorance.utils.GlideUtils;
import com.livelearn.ignorance.widget.ninegridimagelayout.ninegridlayout.NineGridAdapter;
import com.livelearn.ignorance.widget.ninegridimagelayout.ninegridlayout.NineGridLayout;

/**
 * Created on 2017/3/8.
 */

class NineGridLayoutViewHolder extends BaseViewHolder<Journal> {

    private ImageView ivFriendAvatar;
    private TextView tvFriendName;
    private TextView tvJournalDescription;
    private NineGridLayout nglPicture;
    private TextView tvReleaseLocation;
    private TextView tvReleaseTime;

    NineGridLayoutViewHolder(ViewGroup parent) {
        super(parent, R.layout.viewholder_test_nine_grid_layout);
        ivFriendAvatar = $(R.id.iv_friend_avatar);
        tvFriendName = $(R.id.tv_friend_name);
        tvJournalDescription = $(R.id.tv_journal_description);
        nglPicture = $(R.id.ngl_picture);
        tvReleaseLocation = $(R.id.tv_release_location);
        tvReleaseTime = $(R.id.tv_release_time);
    }

    @Override
    public void setData(Journal journal) {
        GlideUtils.setupImage(getContext(), ivFriendAvatar, journal.getFriendAvatarUrl(), R.drawable.shape_circle_solidlightgray);
        nglPicture.setAdapter(new NineGridAdapter(getContext(), journal.getPictureList().split(",")) {
            @Override
            public String getUrl(int position) {
                return getItem(position) == null ? null : (String) getItem(position);
            }
        });
        tvFriendName.setText(journal.getFriendName());
        tvJournalDescription.setText(journal.getJournalDescription());
        tvReleaseLocation.setText(journal.getReleaseLocation());
        tvReleaseTime.setText(DateUtils.timeDisposal(journal.getReleaseTime(), "yyyy年MM月dd日 HH:mm:ss"));

        nglPicture.setOnItemClickListener(new NineGridLayout.OnItemClickListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void onItemClick(NineGridAdapter nineGridAdapter, View view, int position) {
                //跳转图片画廊
                int[] screenLocation = new int[2];
                view.getLocationOnScreen(screenLocation);
                PhotoGalleryActivity.start(getContext(), nineGridAdapter.getList(), position, screenLocation, view.getWidth(), view.getHeight());
            }
        });
    }
}
