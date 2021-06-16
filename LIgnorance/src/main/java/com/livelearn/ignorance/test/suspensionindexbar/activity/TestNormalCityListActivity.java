package com.livelearn.ignorance.test.suspensionindexbar.activity;

import android.graphics.Color;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.model.bean.UserInfo;
import com.livelearn.ignorance.test.dragfooterview.Url;
import com.livelearn.ignorance.test.easyrecyclerview.header.TestBannerAdapter;
import com.livelearn.ignorance.ui.adapter.AddressBookAdapter;
import com.livelearn.ignorance.ui.adapter.viewholder.AddressBookViewHolder;
import com.livelearn.ignorance.utils.DisplayUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.TitleBar;
import com.mcxtzhang.indexbar.IndexBar;
import com.mcxtzhang.suspension.SuspensionDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

public class TestNormalCityListActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.rv_normal_city)
    EasyRecyclerView rvNormalCity;

    @BindView(R.id.ib_index_bar)
    IndexBar ibIndexBar;

    @BindView(R.id.tv_side_bar_hint)
    TextView tvSideBarHint;

    @BindView(R.id.btn_update_datas)
    Button btnUpdateDatas;

    private RecyclerArrayAdapter mAddressBookAdapter;

    private List<UserInfo> mDatas = new ArrayList<>();
    private SuspensionDecoration mDecoration;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_normal_city_list;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        rvNormalCity.setLayoutManager(mLayoutManager);

        mAddressBookAdapter = new AddressBookAdapter(mContext).setType(AddressBookViewHolder.CITY);

        mAddressBookAdapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                RollPagerView header = new RollPagerView(mContext);
                header.setHintView(new ColorPointHintView(mContext, ResourceUtils.getColor(R.color.color_light_purple), Color.GRAY));
                header.setHintPadding(0, 0, 0, DisplayUtils.dp2px(8));
                header.setPlayDelay(2000);
                header.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtils.dp2px(200)));
                header.setAdapter(new TestBannerAdapter(mContext));
                return header;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });

        rvNormalCity.setAdapter(mAddressBookAdapter);
        rvNormalCity.addItemDecoration(mDecoration = new SuspensionDecoration(this, mDatas).setHeaderViewCount(mAddressBookAdapter.getHeaderCount()));
        //如果add两个，那么按照先后顺序，依次渲染
        DividerDecoration itemDecoration = new DividerDecoration(ResourceUtils.getColor(R.color.divider), 1, DisplayUtils.dp2px(16), DisplayUtils.dp2px(24));
        itemDecoration.setDrawLastItem(false);
        rvNormalCity.addItemDecoration(itemDecoration);

        //indexbar初始化
        ibIndexBar.setmPressedShowTextView(tvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mLayoutManager)//设置RecyclerView的LayoutManager
                .setHeaderViewCount(mAddressBookAdapter.getHeaderCount());

        //模拟线上加载数据
        initDatas();
    }

    private void initDatas() {
        final String[] cities = ResourceUtils.getStringArray(R.array.AddressBook_City);
        //延迟零点五秒 模拟加载数据中....
        getWindow().getDecorView().postDelayed(new Runnable() {
            @SuppressWarnings("unchecked")
            @Override
            public void run() {
                for (int i = 0; i < cities.length; i++) {
                    UserInfo userInfo = new UserInfo();
                    mDatas.add(userInfo.setCity(cities[i]).setTarget(userInfo.getCity()).setCityLandscapeUrl(Url.urls[i % Url.urls.length]));
                }

                ibIndexBar.setmSourceDatas(mDatas)//设置数据
                        .invalidate();
                mAddressBookAdapter.addAll(ibIndexBar.getmSourceDatas());
                mDecoration.setmDatas(mDatas);

                btnUpdateDatas.setVisibility(View.VISIBLE);
            }
        }, 500);
    }

    @Override
    public void setListeners() {

    }

    @SuppressWarnings("unchecked")
    @OnClick(R.id.btn_update_datas)
    public void onViewClicked() {
        for (int i = 0; i < 2; i++) {
            UserInfo userInfo1 = new UserInfo();
            mDatas.add(userInfo1.setCity("东京")
                    .setTarget(userInfo1.getCity())
                    .setCityLandscapeUrl(Url.urls[new Random().nextInt(Url.urls.length)]));
            UserInfo userInfo2 = new UserInfo();
            mDatas.add(userInfo2.setCity("伦敦")
                    .setTarget(userInfo2.getCity())
                    .setCityLandscapeUrl(Url.urls[new Random().nextInt(Url.urls.length)]));
        }

        ibIndexBar.setNeedRealIndex(true)
                .setmSourceDatas(mDatas)
                .invalidate();
        mAddressBookAdapter.clear();
        mAddressBookAdapter.addAll(ibIndexBar.getmSourceDatas());
        mDecoration.setmDatas(mDatas);
    }
}
