package com.livelearn.ignorance.ui.adapter.mine;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.livelearn.ignorance.model.db.dbentity.LongTimeBookCollection;
import com.livelearn.ignorance.ui.adapter.viewholder.mine.LongTimeBookCollectionGridViewHolder;

/**
 * Created on 2017/7/19.
 */

public class LongTimeBookCollectionGridAdapter extends RecyclerArrayAdapter<LongTimeBookCollection> {

    public LongTimeBookCollectionGridAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new LongTimeBookCollectionGridViewHolder(parent);
    }
}
