package com.livelearn.ignorance.ui.adapter.book.doubanbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.model.bean.book.douban.DouBanBookDetailsBean;
import com.livelearn.ignorance.utils.GlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2017/5/11.
 */

public class LikeBookAdapter extends RecyclerView.Adapter<LikeBookAdapter.ViewHolder> {

    private Context mContext;
    private List<DouBanBookDetailsBean.LikeBookBean> mDatas;
    private LayoutInflater mInflater;
    private OnItemClickListener onItemClickListener;

    public LikeBookAdapter(Context mContext, List<DouBanBookDetailsBean.LikeBookBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.adapter_like_book, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        GlideUtils.setupImage(mContext, holder.ivLikeBook, mDatas.get(position).getLikeBookCoverUrl(), R.mipmap.ic_nocover);
        holder.tvLikeBookName.setText(mDatas.get(position).getLikeBookName());
        holder.flItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    String bookId = mDatas.get(holder.getAdapterPosition()).getLikeBookHref().replaceAll("[^(0-9)]", "");
                    String bookName = mDatas.get(holder.getAdapterPosition()).getLikeBookName();
                    onItemClickListener.onItemClick(bookId, bookName);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.fl_item)
        FrameLayout flItem;

        @BindView(R.id.iv_like_book)
        ImageView ivLikeBook;

        @BindView(R.id.tv_like_book_name)
        TextView tvLikeBookName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 点击回调接口
     */
    public interface OnItemClickListener {
        void onItemClick(String bookId, String bookName);
    }
}
