package com.livelearn.ignorance.test.pulltorefreshwithloadmore.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.BlockListView;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.adapter.BlockListAdapter;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.entity.MenuItemInfo;
import com.livelearn.ignorance.utils.DisplayUtils;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.widget.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler2;
import in.srain.cube.views.ptr.header.StoreHouseHeader;


public class TestPullToRefreshWithLoadMoreHomeActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.blv_block_list)
    BlockListView blvBlockList;

    @BindView(R.id.sv_scroll)
    ScrollView svScroll;

    @BindView(R.id.pfl_pull_to_refresh)
    PtrFrameLayout pflPullToRefresh;

    private BlockListAdapter<MenuItemInfo> blockListAdapter;
    protected ArrayList<MenuItemInfo> mItemInfos = new ArrayList<>();

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_pull_to_refresh_with_load_more_home;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        StoreHouseHeader header = new StoreHouseHeader(mContext);
        header.setPadding(0, DisplayUtils.dp2px(20), 0, DisplayUtils.dp2px(20));
        header.initWithString("ULTRA HEADER");

        pflPullToRefresh.setDurationToCloseHeader(1500);
        pflPullToRefresh.setHeaderView(header);
        pflPullToRefresh.addPtrUIHandler(header);

        StoreHouseHeader footer = new StoreHouseHeader(mContext);
        footer.setPadding(0, DisplayUtils.dp2px(20), 0, DisplayUtils.dp2px(20));
        footer.initWithString("ULTRA FOOTER");

        pflPullToRefresh.setDurationToCloseFooter(1500);
        pflPullToRefresh.setFooterView(footer);
        pflPullToRefresh.addPtrUIHandler(footer);

        setupList();
    }

    private void setupList() {

        addItemInfo();

        blockListAdapter = new BlockListAdapter<MenuItemInfo>() {
            @Override
            public View getView(LayoutInflater layoutInflater, int position) {
                return getViewForBlock(layoutInflater, position);
            }
        };

        int mSize = (DisplayUtils.getScreenWidth() - DisplayUtils.dp2px(12.5f + 12.5f + 5 + 5)) / 3;

        int horizontalSpacing = DisplayUtils.dp2px(10);
        int verticalSpacing = DisplayUtils.dp2px(10);

        blockListAdapter.setSpace(horizontalSpacing, verticalSpacing);
        blockListAdapter.setBlockSize(mSize, mSize);
        blockListAdapter.setColumnNum(3);
        blvBlockList.setAdapter(blockListAdapter);
        blockListAdapter.displayBlocks(mItemInfos);
    }

    private void addItemInfo() {
        mItemInfos.add(new MenuItemInfo(R.string.ptr_home_block_auto_load_more, R.color.color_light_blue));
        mItemInfos.add(new MenuItemInfo(R.string.ptr_home_block_auto_fresh, R.color.color_light_blue));
        mItemInfos.add(new MenuItemInfo(R.string.ptr_home_block_hide_header, R.color.color_light_blue));
        mItemInfos.add(new MenuItemInfo(R.string.ptr_home_block_keep_header, R.color.color_light_blue));
        mItemInfos.add(new MenuItemInfo(R.string.ptr_home_block_material_style, R.color.color_light_blue));
        mItemInfos.add(new MenuItemInfo(R.string.ptr_home_block_material_style_pin_content, R.color.color_light_blue));
        mItemInfos.add(new MenuItemInfo(R.string.ptr_home_block_rentals_style, R.color.color_light_blue));
        mItemInfos.add(new MenuItemInfo(R.string.ptr_home_block_storehouse_style_using_point_list, R.color.color_light_blue));
        mItemInfos.add(new MenuItemInfo(R.string.ptr_home_block_storehouse_style_using_string, R.color.color_light_blue));
        mItemInfos.add(new MenuItemInfo(R.string.ptr_home_block_storehouse_style_using_string_array, R.color.color_light_blue));
        mItemInfos.add(new MenuItemInfo(R.string.ptr_home_block_with_grid_view, R.color.color_light_blue));
        mItemInfos.add(new MenuItemInfo(R.string.ptr_home_block_with_list_view, R.color.color_light_blue));
        mItemInfos.add(new MenuItemInfo(R.string.ptr_home_block_with_list_view_and_empty_view, R.color.color_light_blue));
        mItemInfos.add(new MenuItemInfo(R.string.ptr_home_block_with_scroll_view, R.color.color_light_blue));
        mItemInfos.add(new MenuItemInfo(R.string.ptr_home_block_with_text_view, R.color.color_light_blue));
        mItemInfos.add(new MenuItemInfo(R.string.ptr_home_block_with_view_pager, R.color.color_light_blue));
        mItemInfos.add(new MenuItemInfo(R.string.ptr_home_block_with_web_view, R.color.color_light_blue));
    }

    protected View getViewForBlock(LayoutInflater layoutInflater, int position) {
        MenuItemInfo itemInfo = blockListAdapter.getItem(position);

        ViewGroup view = (ViewGroup) layoutInflater.inflate(R.layout.adapter_block_list, null);

        if (itemInfo != null) {
            TextView textView = ((TextView) view.findViewById(R.id.tv_block_item));
            textView.setText(itemInfo.getTitle());
            view.setBackgroundColor(itemInfo.getColor());
        }
        return view;
    }

    @Override
    public void setListeners() {
        pflPullToRefresh.setPtrHandler(new PtrHandler2() {
            @Override
            public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
                return PtrDefaultHandler2.checkContentCanBePulledUp(frame, content, footer);
            }

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                pflPullToRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (pflPullToRefresh != null)
                            pflPullToRefresh.refreshComplete();
                    }
                }, 1500);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pflPullToRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (pflPullToRefresh != null)
                            pflPullToRefresh.refreshComplete();
                    }
                }, 1500);
            }
        });

        blvBlockList.setOnItemClickListener(new BlockListView.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                MenuItemInfo itemInfo = blockListAdapter.getItem(position);
                String mTitle = itemInfo.getTitle();
                switch (mTitle) {
                    case "Auto Load More":
                        IntentUtils.startActivity(mContext, TestPtrAutoLoadMoreActivity.class);
                        break;
                    case "Auto Refresh":
                        IntentUtils.startActivity(mContext, TestPtrAutoRefreshActivity.class);
                        break;
                    case "Hide Header while Refreshing":
                        IntentUtils.startActivity(mContext, TestPtrHideHeaderActivity.class);
                        break;
                    case "Keep Header while Refreshing":
                        IntentUtils.startActivity(mContext, TestPtrKeepHeaderActivity.class);
                        break;
                    case "Material Style":
                        IntentUtils.startActivity(mContext, TestPtrMaterialStyleActivity.class);
                        break;
                    case "Material Style\n内容不动，原生风格":
                        IntentUtils.startActivity(mContext, TestPtrMaterialStylePinContentActivity.class);
                        break;
                    case "Rentals Style":
                        IntentUtils.startActivity(mContext, TestPtrRentalsStyleActivity.class);
                        break;
                    case "StoreHouse Style\n使用PointList":
                        IntentUtils.startActivity(mContext, TestPtrStoreHouseStyleUsingPointListActivity.class);
                        break;
                    case "StoreHouse Style\n使用字符串":
                        IntentUtils.startActivity(mContext, TestPtrStoreHouseStyleUsingStringActivity.class);
                        break;
                    case "StoreHouse Style\n使用xml中的字符串数组":
                        IntentUtils.startActivity(mContext, TestPtrStoreHouseStyleUsingStringArrayActivity.class);
                        break;
                    case "Contains A GridView With Footer":
                        IntentUtils.startActivity(mContext, TestPtrWithGridViewActivity.class);
                        break;
                    case "Contains A ListView":
                        IntentUtils.startActivity(mContext, TestPtrWithListViewActivity.class);
                        break;
                    case "EmptyView and ListView":
                        IntentUtils.startActivity(mContext, TestPtrWithListViewAndEmptyViewActivity.class);
                        break;
                    case "Contains A ScrollView":
                        IntentUtils.startActivity(mContext, TestPtrWithScrollViewActivity.class);
                        break;
                    case "Contains A TextView":
                        IntentUtils.startActivity(mContext, TestPtrWithTextViewActivity.class);
                        break;
                    case "Contains A ViewPager":
                        IntentUtils.startActivity(mContext, TestPtrWithViewPagerActivity.class);
                        break;
                    case "Contains A WebView":
                        IntentUtils.startActivity(mContext, TestPtrWithWebViewActivity.class);
                        break;
                }
            }
        });
    }
}
