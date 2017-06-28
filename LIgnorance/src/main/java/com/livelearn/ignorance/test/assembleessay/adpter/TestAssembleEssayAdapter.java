package com.livelearn.ignorance.test.assembleessay.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.develop.rth.gragwithflowlayout.DebugUtil;
import com.livelearn.ignorance.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rth on 16/11/15.
 */
public class TestAssembleEssayAdapter extends RecyclerView.Adapter<TestAssembleEssayAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    private static final String TAG = "TestAssembleEssayAdapter";

    private List<String> datas = new ArrayList<>();
    private Context context;

    public TestAssembleEssayAdapter(Context context) {
        this.context = context;
    }

    public TestAssembleEssayAdapter(Context context, List<String> datas) {
        this(context);
        setDatas(datas);
    }

    public void setDatas(List<String> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DebugUtil.debugFormat("%s onCreateViewHolder",TAG);
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_test_assemble_essay,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String word = datas.get(position);
        holder.getTvWord().setText(word);
        DebugUtil.debugFormat("%s onBindViewHolder: %s, pos:%s",TAG,word,position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        final String animStartString = datas.remove(fromPosition);
        datas.add(toPosition,animStartString);
        notifyItemMoved(fromPosition,toPosition);
    }

    public List<String> getDatas() {
        return datas;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvWord;

        ViewHolder(View itemView) {
            super(itemView);
            this.tvWord = (TextView) itemView;
        }

        TextView getTvWord() {
            return tvWord;
        }
    }

}
