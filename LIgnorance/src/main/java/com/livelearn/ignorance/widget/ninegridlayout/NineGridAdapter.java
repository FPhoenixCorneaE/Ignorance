package com.livelearn.ignorance.widget.ninegridlayout;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.livelearn.ignorance.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 适配器
 */
public abstract class NineGridAdapter {
    protected Context mContext;
    protected List list;

    public NineGridAdapter(Context context, ArrayList list) {
        this.mContext = context;
        this.list = list;
    }

    public NineGridAdapter(Context context, Object[] list) {
        this.mContext = context;
        this.list = list == null ? null : Arrays.asList(list);
    }

    public List getList() {
        return list;
    }

    public int getCount() {
        return (list == null) ? 0 : list.size();
    }

    public abstract String getUrl(int position);

    public Object getItem(int position) {
        return (list == null) ? null : list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view) {
        ImageView iv;
        if (view != null && view instanceof ImageView) {
            iv = (ImageView) view;
        } else {
            iv = new ImageView(mContext);
        }
        iv.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.transparent));
        String url = getUrl(position);
        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.shape_solidpale)
                .error(R.drawable.shape_solidpale)
                .crossFade()
                .into(iv);
        return iv;
    }
}
