package com.livelearn.ignorance.ui.adapter.book;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.livelearn.ignorance.model.db.dbentity.LongTimeBookCollection;
import com.livelearn.ignorance.ui.adapter.viewholder.LongTimeBookCollectionListViewHolder;

/**
 * Created on 2017/7/19.
 */

public class LongTimeBookCollectionListAdapter extends RecyclerArrayAdapter<LongTimeBookCollection> {

    public LongTimeBookCollectionListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new LongTimeBookCollectionListViewHolder(parent);
    }
}
