package com.livelearn.ignorance.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder> {

    public List<T> mDataList;
    public Context mContext;
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_FOOTER = 2;
    public View mFooterView;

    /**
     * 添加数据
     */
    public void addDatas(List<T> data) {
        mDataList.addAll(data == null ? new ArrayList<T>() : data);
        notifyDataSetChanged();
    }

    /**
     * 设置脚布局
     */
    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }

    public View getFooterView() {
        return mFooterView;
    }

    /**
     * 更新数据
     */
    public void updateDatas(List<T> data) {
        mDataList = data == null ? new ArrayList<T>() : data;
        notifyDataSetChanged();
    }

    /**
     * 获得start
     */
    public int getStart() {
        return mDataList.size();
    }

    /**
     * 点击回调
     */
    public OnItemClickListener mOnItemClickListener;
    /**
     * 点击长按回调
     */
    public OnItemLongClickListener mOnItemLongClickListener;

    public void setOnClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    public BaseRecyclerAdapter(Context context, List<T> dataList) {
        mContext = context;
        mDataList = dataList == null ? new ArrayList<T>() : dataList;
    }

    /**
     * 获取不同类型的item
     */
    @Override
    public int getItemViewType(int position) {
        if (mFooterView == null) {
            return TYPE_NORMAL;
        }
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public abstract ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(ViewHolder holder, int position);

    /**
     * 解决StaggeredGridLayoutManager布局添加脚布局的问题
     */
    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams && holder.getLayoutPosition() == getItemCount() - 1) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    /**
     * 解决GridLayoutManager添加脚布局的问题
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                //这个方法的返回值决定了我们每个position上的item占据的单元格个数
                @Override
                public int getSpanSize(int position) {
                    if (getItemViewType(position) == TYPE_FOOTER) {
                        return gridManager.getSpanCount();
                    } else {
                        return 1;
                    }
                }
            });
        }
    }


    /**
     * 获取item数量
     */
    @Override
    public int getItemCount() {
        if (mFooterView == null) {
            return mDataList.size();
        } else {
            return mDataList.size() + 1;
        }
    }

    public interface OnItemClickListener {
        void onClick(View view, String bookId, String bookName);
    }

    public interface OnItemLongClickListener {
        void onLongClick(View view, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
