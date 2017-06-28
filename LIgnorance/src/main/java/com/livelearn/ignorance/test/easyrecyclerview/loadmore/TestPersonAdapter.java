package com.livelearn.ignorance.test.easyrecyclerview.loadmore;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.livelearn.ignorance.test.easyrecyclerview.entites.Person;
import com.livelearn.ignorance.test.easyrecyclerview.viewholder.PersonViewHolder;

/**
 * Created by Mr.Jude on 2015/7/18.
 */
public class TestPersonAdapter extends RecyclerArrayAdapter<Person> {
    public TestPersonAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new PersonViewHolder(parent);
    }
}
