package com.livelearn.ignorance.test.dragfooterview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.test.dragfooterview.Url;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/2.
 */
public class TestDragFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> urlList = new ArrayList<>();
    private Context context;

    public TestDragFooterAdapter(Context context) {
        this.context = context;
        for (int i = 1; i < 10; i++) {
            urlList.add(Url.urls[i]);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_test_drag_footer, viewGroup, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Glide.with(context).load(urlList.get(i)).into((ImageView) viewHolder.itemView);
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }
}
