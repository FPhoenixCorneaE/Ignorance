package com.livelearn.ignorance.test.verticalbannerview;

import android.os.Bundle;
import android.view.View;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.TitleBar;
import com.livelearn.ignorance.widget.verticalbannerview.VerticalBannerView;

import java.util.ArrayList;
import java.util.List;

public class TestVerticalBannerViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_vertical_banner_view;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        ((TitleBar) findViewById(R.id.tb_title)).setTitleText(className);

        List<BannerModel> datas01 = new ArrayList<>();
        datas01.add(new BannerModel("白日依山尽", "--->白日依山尽"));
        datas01.add(new BannerModel("黄河入海流", "--->黄河入海流"));
        datas01.add(new BannerModel("欲穷千里目", "--->欲穷千里目"));
        datas01.add(new BannerModel("更上一层楼", "--->更上一层楼"));

        final TestVerticalBannerAdapter1 adapter01 = new TestVerticalBannerAdapter1(datas01);
        final VerticalBannerView banner01 = (VerticalBannerView) findViewById(R.id.banner_01);
        banner01.setAdapter(adapter01);

        //-----------------------
        List<BannerModel> datas02 = new ArrayList<>();
        datas02.add(new BannerModel("Life was so beautiful", "--->Life was so beautiful,"));
        datas02.add(new BannerModel("From morning to evening", "--->From morning to evening"));
        datas02.add(new BannerModel("We enjoyed the road to school", "--->We enjoyed the road to school,"));
        datas02.add(new BannerModel("Birds chirped and Peace lingered", "--->Birds chirped and Peace lingered"));
        final TestVerticalBannerAdapter2 adapter02 = new TestVerticalBannerAdapter2(datas02);
        final VerticalBannerView banner02 = (VerticalBannerView) findViewById(R.id.banner_02);
        banner02.setAdapter(adapter02);

        //------------------------

        List<BannerModel> datas03 = new ArrayList<>();
        datas03.add(new BannerModel("Life is so insecure", "最新"));
        datas03.add(new BannerModel("From afternoon to night", "最火爆"));
        datas03.add(new BannerModel("We fear the road to school", "hot"));
        datas03.add(new BannerModel("There may be destructive bombs,Peace has demolished", "new"));
        final TestVerticalBannerAdapter3 adapter03 = new TestVerticalBannerAdapter3(datas03);
        final VerticalBannerView banner03 = (VerticalBannerView) findViewById(R.id.banner_03);
        banner03.setAdapter(adapter03);


        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                banner01.start();
                banner02.start();
                banner03.start();
            }
        });

        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                banner01.stop();
                banner02.stop();
                banner03.stop();
            }
        });

        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<BannerModel> newData = new ArrayList<>();
                newData.add(new BannerModel("锄禾日当午", "--->锄禾日当午"));
                newData.add(new BannerModel("汗滴禾下土", "--->汗滴禾下土"));
                newData.add(new BannerModel("谁知盘中餐", "--->谁知盘中餐"));
                newData.add(new BannerModel("粒粒皆辛苦", "--->粒粒皆辛苦"));
                adapter01.setData(newData);
            }
        });
    }

    @Override
    public void setListeners() {

    }
}






















