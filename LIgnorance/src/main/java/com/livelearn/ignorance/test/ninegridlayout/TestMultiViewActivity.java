package com.livelearn.ignorance.test.ninegridlayout;

import android.os.Bundle;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.ui.activity.gallery.PictureBrowsingActivity;
import com.livelearn.ignorance.utils.ToastUtils;
import com.livelearn.ignorance.widget.TitleBar;
import com.livelearn.ignorance.widget.ninegridlayout.MultiView;
import com.livelearn.ignorance.widget.ninegridlayout.Utils;

import java.util.List;

import butterknife.BindView;
import cn.alien95.resthttp.request.RestHttp;

/**
 * Created on 2017/6/16.
 */

public class TestMultiViewActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.mv_multi)
    MultiView mvMulti;

    private List<String> mData;

    @Override
    public int getLayoutResource() {
        Utils.init(mContext);
        RestHttp.initialize(mContext);
        return R.layout.activity_test_multi_view;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {

        tbTitle.setTitleText(className);

        mvMulti.setImages(mData = OnePiece.mData);
    }

    @Override
    public void setListeners() {
        mvMulti.setOnItemClickListener(new MultiView.OnItemClickListener() {
            @Override
            public void onClick(List<String> mDatas, int position) {
                if (position == 8) {
                    ToastUtils.showToast("点击了第九个item");
                } else {
                    PictureBrowsingActivity.start(mContext, mData, position);
                }
            }
        });
    }
}
