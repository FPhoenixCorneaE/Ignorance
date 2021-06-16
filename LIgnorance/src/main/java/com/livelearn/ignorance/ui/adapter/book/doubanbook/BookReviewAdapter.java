package com.livelearn.ignorance.ui.adapter.book.doubanbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.model.bean.book.douban.DouBanBookDetailsBean;
import com.livelearn.ignorance.utils.GlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2017/5/15.
 */

public class BookReviewAdapter extends RecyclerView.Adapter<BookReviewAdapter.ViewHolder> {

    private Context mContext;
    private List<DouBanBookDetailsBean.BookReviewBean> mDatas;
    private LayoutInflater mInflater;
    private OnItemClickListener onItemClickListener;

    public BookReviewAdapter(Context mContext, List<DouBanBookDetailsBean.BookReviewBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.adapter_book_review, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvReviewTitle.setText(mDatas.get(position).getBookReviewTitle());
        GlideUtils.setupCircleImage(mContext, holder.ivReviewCreateUserAvatar, mDatas.get(position).getBookReviewCreateUserAvatarUrl());
        holder.tvReviewCreateUserName.setText(mDatas.get(position).getBookReviewCreateUserName());
        String reviewCreateUserStarsRating = mDatas.get(position).getBookReviewCreateUserStarsRating();
        if (null != reviewCreateUserStarsRating) {
            holder.rbReviewCreateUserStartsRating.setVisibility(View.VISIBLE);
            switch (reviewCreateUserStarsRating) {
                case "力荐":
                    holder.rbReviewCreateUserStartsRating.setRating(5F);
                    break;
                case "推荐":
                    holder.rbReviewCreateUserStartsRating.setRating(4F);
                    break;
                case "还行":
                    holder.rbReviewCreateUserStartsRating.setRating(3F);
                    break;
                case "较差":
                    holder.rbReviewCreateUserStartsRating.setRating(2F);
                    break;
                case "很差":
                    holder.rbReviewCreateUserStartsRating.setRating(1F);
                    break;
            }
        } else {
            holder.rbReviewCreateUserStartsRating.setVisibility(View.GONE);
        }
        holder.tvReviewCreateDate.setText(mDatas.get(position).getBookReviewCreateDate());
        holder.tvReviewContent.setText(mDatas.get(position).getBookReviewShortContent());
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(holder.getAdapterPosition(), mDatas.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_review_title)
        TextView tvReviewTitle;

        @BindView(R.id.iv_review_create_user_avatar)
        ImageView ivReviewCreateUserAvatar;

        @BindView(R.id.tv_review_create_user_name)
        TextView tvReviewCreateUserName;

        @BindView(R.id.rb_review_create_user_starts_rating)
        RatingBar rbReviewCreateUserStartsRating;

        @BindView(R.id.tv_review_create_date)
        TextView tvReviewCreateDate;

        @BindView(R.id.tv_review_content)
        TextView tvReviewContent;

        @BindView(R.id.ll_item)
        LinearLayout llItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public List<DouBanBookDetailsBean.BookReviewBean> getDatas() {
        return mDatas;
    }

    public BookReviewAdapter setDatas(List<DouBanBookDetailsBean.BookReviewBean> mDatas) {
        this.mDatas = mDatas;
        return this;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 点击回调接口
     */
    public interface OnItemClickListener {
        void onItemClick(int position, DouBanBookDetailsBean.BookReviewBean mData);
    }
}
