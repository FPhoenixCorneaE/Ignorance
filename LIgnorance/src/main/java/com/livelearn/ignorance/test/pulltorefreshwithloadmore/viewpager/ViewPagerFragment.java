package com.livelearn.ignorance.test.pulltorefreshwithloadmore.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.adapter.ListViewDataAdapter;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.viewholder.ViewHolder;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.viewholder.ViewHolderBase;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.viewholder.ViewHolderCreator;
import com.livelearn.ignorance.utils.DisplayUtils;
import com.livelearn.ignorance.utils.ResourceUtils;

import java.util.List;


public class ViewPagerFragment extends Fragment {

    private int mPage;
    private ListView mListView;
    private ListViewDataAdapter<String> mAdapter;

    public static ViewPagerFragment create(int page) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.mPage = page;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        mListView = (ListView) view.findViewById(R.id.lv_test);

        TextView headerView = new TextView(getContext());
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtils.dp2px(200));
        headerView.setLayoutParams(layoutParams);
        headerView.setGravity(Gravity.CENTER);
        headerView.setTextSize(16);
        headerView.setTextColor(ResourceUtils.getColor(R.color.color_dark_black));
        headerView.setBackgroundColor(0xff4d90fe * mPage / 30);
        headerView.setText(String.valueOf("Page: " + mPage));

        mListView.addHeaderView(headerView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        mAdapter = new ListViewDataAdapter<>(new ViewHolderCreator<String>() {
            @Override
            public ViewHolderBase<String> createViewHolder() {
                return new ViewHolder(getContext());
            }
        });
        mListView.setAdapter(mAdapter);
        return view;
    }

    public void updateData(List<String> data) {
        mAdapter.getDataList().clear();
        mAdapter.getDataList().addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    public boolean checkCanDoRefresh() {
        return mAdapter.getCount() == 0 || mListView == null ||
                (mListView.getFirstVisiblePosition() == 0 && mListView.getChildAt(0).getTop() == 0);
    }

    public boolean checkCanDoLoadMore() {
        return mAdapter.getCount() == 0 || mListView == null ||
                mListView.getLastVisiblePosition() < mAdapter.getCount() - 1 ||
                mListView.getChildAt(mAdapter.getCount() - 1).getBottom() > mListView.getPaddingBottom();
    }

    public void show() {

    }

    public void hide() {

    }
}
