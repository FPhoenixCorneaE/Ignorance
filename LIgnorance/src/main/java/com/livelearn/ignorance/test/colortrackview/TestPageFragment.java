package com.livelearn.ignorance.test.colortrackview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.common.Constant;

/**
 * Created on 2017/5/22.
 */

public class TestPageFragment extends BaseFragment {

    private int mPage;

    public static TestPageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(Constant.ARG_SECTION_NUMBER, page);
        TestPageFragment pageFragment = new TestPageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(Constant.ARG_SECTION_NUMBER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TextView tvText = new TextView(mContext);
        tvText.setLayoutParams(layoutParams);
        tvText.setGravity(Gravity.CENTER);
        tvText.setTextSize(30);
        tvText.setTextColor(Color.WHITE);
        tvText.setText(String.valueOf("Page " + mPage));
        tvText.setPadding(30, 30, 30, 30);
        int bg = Color.rgb((int) Math.floor(Math.random() * 128) + 64,
                (int) Math.floor(Math.random() * 128) + 64,
                (int) Math.floor(Math.random() * 128) + 64);
        tvText.setBackgroundColor(bg);
        initLayout(inflater, container, savedInstanceState);
        return tvText;
    }

    @Override
    public int getLayoutResource() {
        return 0;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyFetchData() {

    }
}
