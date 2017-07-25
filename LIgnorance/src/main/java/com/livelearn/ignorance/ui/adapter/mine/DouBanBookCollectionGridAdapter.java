package com.livelearn.ignorance.ui.adapter.mine;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.livelearn.ignorance.model.db.dbentity.DouBanBookCollection;
import com.livelearn.ignorance.ui.adapter.viewholder.mine.DouBanBookCollectionGridViewHolder;

/**
 * Created on 2017/7/19.
 */

public class DouBanBookCollectionGridAdapter extends RecyclerArrayAdapter<DouBanBookCollection> {

    private DouBanBookCollectionGridViewHolder.OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(DouBanBookCollectionGridViewHolder.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public DouBanBookCollectionGridAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new DouBanBookCollectionGridViewHolder(parent).setOnItemClickListener(mOnItemClickListener);
    }
}
