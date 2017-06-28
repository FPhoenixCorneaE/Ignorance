package com.livelearn.ignorance.test.verticalbannerview;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.widget.verticalbannerview.BaseBannerAdapter;
import com.livelearn.ignorance.widget.verticalbannerview.VerticalBannerView;

import java.util.List;

/**
 * Description:
 * <p/>
 * Created by rowandjj(chuyi)<br/>
 * Date: 16/1/7<br/>
 * Time: 下午2:41<br/>
 */
class TestVerticalBannerAdapter3 extends BaseBannerAdapter<BannerModel> {
    private List<BannerModel> mDatas;

    TestVerticalBannerAdapter3(List<BannerModel> datas) {
        super(datas);
    }

    @Override
    public View getView(VerticalBannerView parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_test_vertical_banner_3, parent, false);
    }

    @Override
    public void setItem(final View view, final BannerModel data) {
        TextView tv = (TextView) view.findViewById(R.id.title);
        tv.setText(data.title);

        TextView tag = (TextView) view.findViewById(R.id.tag);
        tag.setText(data.url);

    }
}
