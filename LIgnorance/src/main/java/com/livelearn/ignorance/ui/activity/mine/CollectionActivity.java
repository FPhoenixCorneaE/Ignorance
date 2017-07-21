package com.livelearn.ignorance.ui.activity.mine;

import android.os.Bundle;
import android.view.View;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.TitleBar;

import butterknife.BindView;

/**
 * Created on 2017/7/18.
 */

public class CollectionActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_collection;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText("收藏")
                .setRightText("列表");
    }

    @Override
    public void setListeners() {
        tbTitle.setOnClickLeftListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                })
                .setOnClickRightListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
    }
}
