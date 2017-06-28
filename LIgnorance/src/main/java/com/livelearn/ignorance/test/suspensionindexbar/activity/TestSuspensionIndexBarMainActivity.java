package com.livelearn.ignorance.test.suspensionindexbar.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.widget.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

public class TestSuspensionIndexBarMainActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.btn_imitate_we_chat_address_book)
    Button btnImitateWeChatAddressBook;

    @BindView(R.id.btn_normal_city_list)
    Button btnNormalCityList;

    @BindView(R.id.btn_go_with_swipe_delete)
    Button btnGoWithSwipeDelete;

    @BindView(R.id.btn_meituan_city_list)
    Button btnMeituanCityList;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_suspension_index_bar_main;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);
    }

    @Override
    public void setListeners() {

    }

    @OnClick({R.id.btn_imitate_we_chat_address_book, R.id.btn_normal_city_list, R.id.btn_go_with_swipe_delete, R.id.btn_meituan_city_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_imitate_we_chat_address_book:
                IntentUtils.startActivity(mContext, TestImitateWeChatAddressBookActivity.class);
                break;
            case R.id.btn_normal_city_list:
                IntentUtils.startActivity(mContext, TestNormalCityListActivity.class);
                break;
            case R.id.btn_go_with_swipe_delete:
                IntentUtils.startActivity(mContext, TestGoWithSwipeDeleteActivity.class);
                break;
            case R.id.btn_meituan_city_list:
                IntentUtils.startActivity(mContext, TestMeiTuanCityListActivity.class);
                break;
        }
    }
}
