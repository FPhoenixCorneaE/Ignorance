package com.livelearn.ignorance.ui.adapter.mine;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.livelearn.ignorance.model.db.dbentity.DouBanBookCollection;
import com.livelearn.ignorance.ui.adapter.viewholder.mine.DouBanBookCollectionListViewHolder;

/**
 * Created on 2017/7/19.
 */

public class DouBanBookCollectionListAdapter extends RecyclerArrayAdapter<DouBanBookCollection> {

    public DouBanBookCollectionListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new DouBanBookCollectionListViewHolder(parent);
    }
}
