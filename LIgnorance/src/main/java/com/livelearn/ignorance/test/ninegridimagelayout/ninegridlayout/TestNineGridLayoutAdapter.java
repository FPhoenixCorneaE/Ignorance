package com.livelearn.ignorance.test.ninegridimagelayout.ninegridlayout;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.livelearn.ignorance.test.ninegridimagelayout.Journal;

/**
 * Created on 2017/3/8.
 */

class TestNineGridLayoutAdapter extends RecyclerArrayAdapter<Journal> {

    TestNineGridLayoutAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewType(int position) {
        return super.getViewType(position);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new NineGridLayoutViewHolder(parent);
    }
}
