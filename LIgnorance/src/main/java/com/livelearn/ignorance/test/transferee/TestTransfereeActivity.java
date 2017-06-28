package com.livelearn.ignorance.test.transferee;

import android.os.Bundle;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.test.ninegridlayout.OnePiece;
import com.livelearn.ignorance.widget.ninegridlayout.SimpleNineGridView;

import butterknife.BindView;

/**
 * Created on 2017/6/16.
 */

public class TestTransfereeActivity extends BaseActivity {

    @BindView(R.id.sngv_nine_grid)
    SimpleNineGridView sngvNineGrid;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_transferee;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        sngvNineGrid.setDatas(OnePiece.mData.subList(10, 19));
    }

    @Override
    public void setListeners() {
    }
}
