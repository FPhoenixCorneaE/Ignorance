package com.livelearn.ignorance.test.easyrecyclerview.multistyle;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.livelearn.ignorance.test.easyrecyclerview.entites.Ad;
import com.livelearn.ignorance.test.easyrecyclerview.entites.Person;
import com.livelearn.ignorance.test.easyrecyclerview.viewholder.AdViewHolder;
import com.livelearn.ignorance.test.easyrecyclerview.viewholder.PersonViewHolder;

import java.security.InvalidParameterException;

/**
 * Created by Mr.Jude on 2015/7/18.
 */
class TestPersonWithAdAdapter extends RecyclerArrayAdapter<Object> {
    private static final int TYPE_INVALID = 0;
    private static final int TYPE_AD = 1;
    private static final int TYPE_PERSON = 2;
    TestPersonWithAdAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewType(int position) {
        if(getItem(position) instanceof Ad){
            return TYPE_AD;
        }else if (getItem(position) instanceof Person){
            return TYPE_PERSON;
        }
        return TYPE_INVALID;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_PERSON:
                return new PersonViewHolder(parent);
            case TYPE_AD:
                return new AdViewHolder(parent);
            default:
                throw new InvalidParameterException();
        }
    }
}
