package com.livelearn.ignorance.test.circlerefreshlayout;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.TitleBar;
import com.livelearn.ignorance.widget.circlerefreshlayout.CircleRefreshLayout;

import butterknife.BindView;
import butterknife.OnClick;

public class TestCircleRefreshLayoutActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.lv_test)
    ListView lvTest;

    @BindView(R.id.crl_refresh_layout)
    CircleRefreshLayout crlRefreshLayout;

    @BindView(R.id.btn_stop_refresh)
    Button btnStopRefresh;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_circle_refresh_layout;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        String[] strs = {
                "The",
                "Canvas",
                "class",
                "holds",
                "the",
                "draw",
                "calls",
                ".",
                "To",
                "draw",
                "something,",
                "you",
                "need",
                "4 basic",
                "components",
                "Bitmap",
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strs);
        lvTest.setAdapter(adapter);
    }

    @Override
    public void setListeners() {
        crlRefreshLayout.setOnRefreshListener(new CircleRefreshLayout.OnCircleRefreshListener() {
            @Override
            public void completeRefresh() {
                // do something when refresh complete
            }

            @Override
            public void refreshing() {
                // do something when refresh starts
            }
        });
    }

    @OnClick(R.id.btn_stop_refresh)
    public void onViewClicked() {
        crlRefreshLayout.finishRefreshing();
    }
}
