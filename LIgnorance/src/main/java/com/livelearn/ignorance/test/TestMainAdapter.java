package com.livelearn.ignorance.test;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.livelearn.ignorance.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 测试主界面适配器
 * Created by Administrator on 2016/11/28.
 */
public class TestMainAdapter extends BaseAdapter {

    private Context context;
    private String[] contentList;
    private OnItemClickListener onItemClickListener;

    TestMainAdapter(Context context, String[] contentList) {
        this.context = context;
        this.contentList = contentList;
    }

    @Override
    public int getCount() {
        return contentList.length;
    }

    @Override
    public Object getItem(int position) {
        return contentList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.adapter_test_main, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvContent.setText(contentList[position]);
        viewHolder.rlTestItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position, contentList[position]);
                }
            }
        });
        return convertView;
    }

    class ViewHolder {

        @BindView(R.id.tv_content)
        TextView tvContent;

        @BindView(R.id.rl_test_item)
        RelativeLayout rlTestItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    interface OnItemClickListener {
        void onItemClick(int position, String content);
    }

    void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
