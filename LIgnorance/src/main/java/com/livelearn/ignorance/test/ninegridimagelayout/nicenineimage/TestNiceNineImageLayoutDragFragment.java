package com.livelearn.ignorance.test.ninegridimagelayout.nicenineimage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.test.ninegridimagelayout.OnePiece;
import com.livelearn.ignorance.widget.ninegridimagelayout.niceninelayout.NiceNineImageLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created on 2017/7/6.
 */

public class TestNiceNineImageLayoutDragFragment extends BaseFragment {

    @BindView(R.id.nnil_picture)
    NiceNineImageLayout nnilPicture;

    @BindView(R.id.btn_minus)
    Button btnMinus;

    @BindView(R.id.btn_add)
    Button btnAdd;

    @BindView(R.id.tv_picUrl)
    TextView tvPicUrl;

    @BindView(R.id.btn_get_url)
    Button btnGetUrl;

    private int num = 1;

    private List<String> pictureList;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_test_nice_nine_image_layout_drag;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        pictureList = OnePiece.mData.subList(20, 29);
        bindData();
        //模拟按钮点击
        btnGetUrl.performClick();
    }

    private void bindData() {
        List<String> picStrings = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            picStrings.add(pictureList.get(i));
        }
        nnilPicture.bindData(picStrings);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyFetchData() {

    }

    @OnClick({R.id.btn_minus, R.id.btn_add, R.id.btn_get_url})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_minus:
                num--;
                if (num <= 1) {
                    num = 1;
                }
                bindData();
                break;
            case R.id.btn_add:
                num++;
                if (num >= 9) {
                    num = 9;
                }
                bindData();
                break;
            case R.id.btn_get_url:
                tvPicUrl.setText(nnilPicture.getAfterPicList().toString());
                break;
        }
    }
}
