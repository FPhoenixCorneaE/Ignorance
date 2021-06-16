package com.livelearn.ignorance.ui.adapter.book.doubanbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.model.bean.book.douban.DouBanBookDetailsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2017/5/15.
 */

public class ShortCommentAdapter extends RecyclerView.Adapter<ShortCommentAdapter.ViewHolder> {

    private Context mContext;
    private List<DouBanBookDetailsBean.ShortCommentBean> mDatas;
    private LayoutInflater mInflater;

    public ShortCommentAdapter(Context mContext, List<DouBanBookDetailsBean.ShortCommentBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.adapter_short_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvCommentUserName.setText(mDatas.get(position).getCommentUserName());
        String commentUserStarsRating = mDatas.get(position).getCommentUserStarsRating();
        if (null != commentUserStarsRating) {
            holder.rbCommentUserStartsRating.setVisibility(View.VISIBLE);
            switch (commentUserStarsRating) {
                case "力荐":
                    holder.rbCommentUserStartsRating.setRating(5F);
                    break;
                case "推荐":
                    holder.rbCommentUserStartsRating.setRating(4F);
                    break;
                case "还行":
                    holder.rbCommentUserStartsRating.setRating(3F);
                    break;
                case "较差":
                    holder.rbCommentUserStartsRating.setRating(2F);
                    break;
                case "很差":
                    holder.rbCommentUserStartsRating.setRating(1F);
                    break;
            }
        } else {
            holder.rbCommentUserStartsRating.setVisibility(View.GONE);
        }
        holder.tvCommentDate.setText(mDatas.get(position).getCommentDate());
        holder.tvCommentContent.setText(mDatas.get(position).getCommentContent());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_comment_user_name)
        TextView tvCommentUserName;

        @BindView(R.id.rb_comment_user_starts_rating)
        RatingBar rbCommentUserStartsRating;

        @BindView(R.id.tv_comment_date)
        TextView tvCommentDate;

        @BindView(R.id.tv_comment_content)
        TextView tvCommentContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public List<DouBanBookDetailsBean.ShortCommentBean> getDatas() {
        return mDatas;
    }

    public ShortCommentAdapter setDatas(List<DouBanBookDetailsBean.ShortCommentBean> mDatas) {
        this.mDatas = mDatas;
        return this;
    }
}
