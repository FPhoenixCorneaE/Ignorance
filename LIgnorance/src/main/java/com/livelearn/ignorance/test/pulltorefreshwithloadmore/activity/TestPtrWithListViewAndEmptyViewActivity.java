package com.livelearn.ignorance.test.pulltorefreshwithloadmore.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.adapter.ListViewDataAdapter;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.model.Images;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.viewholder.ViewHolder;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.viewholder.ViewHolderBase;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.viewholder.ViewHolderCreator;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.widget.TitleBar;

import java.util.Arrays;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class TestPtrWithListViewAndEmptyViewActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.tv_empty)
    TextView tvEmpty;

    @BindView(R.id.lv_test)
    ListView lvTest;

    @BindView(R.id.pcfl_pull_to_refresh)
    PtrClassicFrameLayout pcflPullToRefresh;

    private ListViewDataAdapter<String> mAdapter;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_ptr_with_list_view_and_empty_view;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        tvEmpty.setVisibility(View.VISIBLE);
        pcflPullToRefresh.setVisibility(View.INVISIBLE);

        mAdapter = new ListViewDataAdapter<>(new ViewHolderCreator<String>() {
            @Override
            public ViewHolderBase<String> createViewHolder() {
                return new ViewHolder(mContext);
            }
        });

        lvTest.setAdapter(mAdapter);

        pcflPullToRefresh.setLastUpdateTimeRelateObject(this);

        // the following are default settings
        pcflPullToRefresh.setResistance(1.7f);
        pcflPullToRefresh.setRatioOfHeaderHeightToRefresh(1.2f);
        pcflPullToRefresh.setDurationToClose(200);
        pcflPullToRefresh.setDurationToCloseHeader(1000);
        // default is false
        pcflPullToRefresh.setPullToRefresh(false);
        // default is true
        pcflPullToRefresh.setKeepHeaderWhenRefresh(true);
    }

    @Override
    public void setListeners() {
        tvEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvEmpty.setVisibility(View.GONE);
                pcflPullToRefresh.setVisibility(View.VISIBLE);
                pcflPullToRefresh.autoRefresh();
            }
        });
        lvTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString(Constant.IMAGE_URL, mAdapter.getDataList().get(i));
                IntentUtils.startActivity(mContext, TestPtrMaterialStyleActivity.class, bundle);
            }
        });
        pcflPullToRefresh.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // here check $mListView instead of $content
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, lvTest, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                updateData();
            }
        });
    }

    private void updateData() {
        pcflPullToRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.getDataList().clear();
                mAdapter.getDataList().addAll(Arrays.asList(Images.imageUrls).subList(0, 20));
                mAdapter.notifyDataSetChanged();
                if (pcflPullToRefresh != null)
                    pcflPullToRefresh.refreshComplete();
            }
        }, 1000);
    }
}
