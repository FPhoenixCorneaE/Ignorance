package com.livelearn.ignorance.test.pulltorefreshwithloadmore.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.adapter.ListViewDataAdapter;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.model.Images;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.viewholder.ViewHolder;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.viewholder.ViewHolderBase;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.viewholder.ViewHolderCreator;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.ToastUtils;
import com.livelearn.ignorance.widget.TitleBar;

import java.util.Arrays;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class TestPtrWithListViewActivity extends BaseActivity {
    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.lv_test)
    ListView lvTest;

    @BindView(R.id.pcfl_pull_to_refresh)
    PtrClassicFrameLayout pcflPullToRefresh;

    private ListViewDataAdapter<String> mAdapter;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_ptr_with_list_view;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        mAdapter = new ListViewDataAdapter<>(new ViewHolderCreator<String>() {
            @Override
            public ViewHolderBase<String> createViewHolder() {
                return new ViewHolder(mContext);
            }
        });
        lvTest.setAdapter(mAdapter);

        pcflPullToRefresh.setLastUpdateTimeRelateObject(mContext);

        pcflPullToRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                pcflPullToRefresh.autoRefresh(true);
            }
        }, 100);
    }

    @Override
    public void setListeners() {
        lvTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString(Constant.IMAGE_URL, mAdapter.getDataList().get(i));
                IntentUtils.startActivity(mContext, TestPtrMaterialStyleActivity.class, bundle);
            }
        });
        lvTest.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                ToastUtils.showToast("Long Pressed: " + id);
                return true;
            }
        });
        pcflPullToRefresh.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
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
