package com.livelearn.ignorance.test.easyrecyclerview.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.test.easyrecyclerview.entites.Person;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


/**
 * Created by Mr.Jude on 2015/2/22.
 */
public class PersonViewHolder extends BaseViewHolder<Person> {
    private TextView mTv_name;
    private ImageView mImg_face;
    private TextView mTv_sign;


    public PersonViewHolder(ViewGroup parent) {
        super(parent, R.layout.viewholder_test_person);
        mTv_name = $(R.id.person_name);
        mTv_sign = $(R.id.person_sign);
        mImg_face = $(R.id.person_face);
    }

    @Override
    public void setData(final Person person) {
        mTv_name.setText(person.getName());
        mTv_sign.setText(person.getSign());
        Glide.with(getContext())
                .load(person.getFace())
                .placeholder(R.mipmap.ic_portrait_default)
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .into(mImg_face);
    }
}
