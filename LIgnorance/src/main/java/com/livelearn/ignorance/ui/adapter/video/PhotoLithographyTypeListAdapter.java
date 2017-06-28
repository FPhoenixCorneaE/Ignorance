package com.livelearn.ignorance.ui.adapter.video;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.livelearn.ignorance.model.bean.video.PhotoLithographyTypeListModel;
import com.livelearn.ignorance.ui.adapter.viewholder.PhotoLithographyTypeListViewHolder;

/**
 * Created on 2017/6/22.
 */

public class PhotoLithographyTypeListAdapter extends RecyclerArrayAdapter<PhotoLithographyTypeListModel.RetBean.ListBean> {

    public PhotoLithographyTypeListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoLithographyTypeListViewHolder(parent);
    }
}
