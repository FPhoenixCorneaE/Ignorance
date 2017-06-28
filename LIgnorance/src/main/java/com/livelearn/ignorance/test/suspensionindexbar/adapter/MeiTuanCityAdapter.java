package com.livelearn.ignorance.test.suspensionindexbar.adapter;

import android.content.Context;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.test.suspensionindexbar.model.MeiTuanCity;

import java.util.List;

public class MeiTuanCityAdapter extends CommonAdapter<MeiTuanCity> {

    public MeiTuanCityAdapter(Context context, List<MeiTuanCity> datas) {
        super(context, R.layout.adapter_test_meituan_city, datas);
    }

    @Override
    public void convert(ViewHolder holder, final MeiTuanCity meiTuanCity) {
        holder.setText(R.id.tv_city, meiTuanCity.getCity());
    }
}