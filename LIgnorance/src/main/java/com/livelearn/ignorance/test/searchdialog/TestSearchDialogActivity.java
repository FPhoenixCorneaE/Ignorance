package com.livelearn.ignorance.test.searchdialog;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.TitleBar;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import butterknife.BindView;

public class TestSearchDialogActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.tv_keyword)
    TextView tvKeyword;

    private SearchFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_search_dialog;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className)
                .setRightIcon(R.mipmap.ic_search_black)
                .setOnClickRightListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        searchFragment.show(getSupportFragmentManager(), SearchFragment.TAG);
                    }
                });

        searchFragment = SearchFragment.newInstance();

//        searchFragment
//                .setBackResId(R.mipmap.ic_nav_back_black)
//                .setKeywordHint("搜索")
//                .setKeywordHintColor(R.color.text_black)
//                .setKeywordTextColor(R.color.colorPrimary)
//                .setKeywordTextSize(20)
//                .setSearchResId(R.drawable.ic_search_white_24dp);
    }

    @Override
    public void setListeners() {
        searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
            @Override
            public void OnSearchClick(String keyword) {
                tvKeyword.setText(keyword);
            }
        });
    }
}
