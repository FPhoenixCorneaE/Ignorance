package com.livelearn.ignorance.test.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.livelearn.ignorance.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class TestQueryDatabaseAdapter extends BaseAdapter {

    private List<String> mDataList;
    private LayoutInflater mInflater;

    TestQueryDatabaseAdapter(Context mContext, List<String> mDataList) {
        this.mDataList = mDataList;
        this.mInflater = LayoutInflater.from(mContext);
    }

    void resetData(List<String> data) {
        mDataList = data;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_test_query_database, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvItem.setText(mDataList.get(position));
        return convertView;
    }

    static class ViewHolder {

        @BindView(R.id.tv_item)
        TextView tvItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
