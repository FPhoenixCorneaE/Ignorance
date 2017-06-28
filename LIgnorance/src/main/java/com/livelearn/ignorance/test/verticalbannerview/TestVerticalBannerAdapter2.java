package com.livelearn.ignorance.test.verticalbannerview;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
class TestVerticalBannerAdapter2 extends BaseBannerAdapter<BannerModel> {
    private List<BannerModel> mDatas;

    TestVerticalBannerAdapter2(List<BannerModel> datas) {
        super(datas);
    }

    @Override
    public View getView(VerticalBannerView parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_test_vertical_banner_2, parent, false);
    }

    @Override
    public void setItem(final View view, final BannerModel data) {
        TextView tv = (TextView) view.findViewById(R.id.tv_02);
        tv.setText(data.title);
        //你可以增加点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //比如打开url
                Toast.makeText(view.getContext(), data.url, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
