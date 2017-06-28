package com.livelearn.ignorance.ui.adapter.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.model.bean.UserInfo;
import com.livelearn.ignorance.utils.GlideUtils;
import com.livelearn.ignorance.widget.StateButton;
import com.livelearn.ignorance.widget.SwipeMenuLayout;

/**
 * Created on 2017/4/29.
 */

public class AddressBookViewHolder extends BaseViewHolder<UserInfo> {

    public static final String CONTACTS = "contacts";
    public static final String CITY = "city";

    private SwipeMenuLayout smlSwipeMenu;
    private ImageView ivAvatar;
    private TextView tvName;
    private StateButton sbDelete;

    private String type;
    private boolean swipeEnable;
    private OnDeleteListener mOnDeleteListener;

    public AddressBookViewHolder(ViewGroup parent) {
        super(parent, R.layout.viewholder_address_book);
        smlSwipeMenu = $(R.id.sml_swipe_menu);
        ivAvatar = $(R.id.iv_avatar);
        tvName = $(R.id.tv_name);
        sbDelete = $(R.id.sb_delete);
    }

    public String getType() {
        return type == null ? CONTACTS : type;
    }

    public AddressBookViewHolder setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * 设置侧滑功能开关
     */
    public AddressBookViewHolder setSwipeEnable(boolean swipeEnable) {
        this.swipeEnable = swipeEnable;
        return this;
    }

    public AddressBookViewHolder setOnDeleteListener(OnDeleteListener mOnDeleteListener) {
        this.mOnDeleteListener = mOnDeleteListener;
        return this;
    }

    @Override
    public void setData(UserInfo data) {
        if (data.isTop()) {
            smlSwipeMenu.setSwipeEnable(false);
        } else {
            smlSwipeMenu.setSwipeEnable(swipeEnable);
        }

        switch (getType()) {
            case CITY:
                GlideUtils.setupImage(getContext(), ivAvatar, data.getCityLandscapeUrl(), R.mipmap.pic_default);
                tvName.setText(data.getCity());
                break;
            case CONTACTS:
                GlideUtils.setupCircleImage(getContext(), ivAvatar, data.getUserAvatarPath(), R.mipmap.ic_portrait_default);
                tvName.setText(data.getUserNickname());
                break;
        }

        if (smlSwipeMenu.isSwipeEnable()) {
            sbDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    smlSwipeMenu.quickClose();
                    if (mOnDeleteListener != null) {
                        mOnDeleteListener.onDelete(getLayoutPosition());
                    }
                }
            });
        }
    }

    public interface OnDeleteListener {
        void onDelete(int position);
    }
}
