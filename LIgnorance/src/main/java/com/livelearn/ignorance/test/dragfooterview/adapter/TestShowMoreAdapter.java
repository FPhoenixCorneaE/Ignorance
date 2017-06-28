package com.livelearn.ignorance.test.dragfooterview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.test.dragfooterview.Url;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by dear33 on 2016/11/13.
 */
public class TestShowMoreAdapter extends RecyclerView.Adapter<TestShowMoreAdapter.MyHolder> {
    private Context context;
    private Random random;
    private ArrayList<Integer> heights;

    public TestShowMoreAdapter(Context context) {
        this.context = context;
        random = new Random(System.currentTimeMillis());
        heights = new ArrayList<>(Url.urls.length);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_test_show_more, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();

        if (heights.size() <= position) {
            int height = dp2px(context, 200) + random.nextInt(dp2px(context, 50));
            heights.add(height);
        }

        layoutParams.height = heights.get(position);
        holder.itemView.setLayoutParams(layoutParams);

        Glide.with(context).load(Url.urls[position]).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return Url.urls.length;
    }

    private int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
        }
    }
}
