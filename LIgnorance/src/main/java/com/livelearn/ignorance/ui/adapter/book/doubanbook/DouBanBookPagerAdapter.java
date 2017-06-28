package com.livelearn.ignorance.ui.adapter.book.doubanbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseRecyclerAdapter;
import com.livelearn.ignorance.model.bean.book.douban.DouBanBookBean;
import com.livelearn.ignorance.utils.GlideUtils;

import java.util.List;

public class DouBanBookPagerAdapter extends BaseRecyclerAdapter<DouBanBookBean.BooksBean> {

    public DouBanBookPagerAdapter(Context context, List<DouBanBookBean.BooksBean> data) {
        super(context, data);
    }

    @Override
    public BaseRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mFooterView != null && viewType == TYPE_FOOTER)
            return new ViewHolder(mFooterView);

        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_book_pager, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BaseRecyclerAdapter.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_FOOTER) return;

        ((ViewHolder) holder).tvName.setText(mDataList.get(position).getTitle());
        ((ViewHolder) holder).tvGrade.setText(String.valueOf(mDataList.get(position).getRating().getAverage() + ""));
        ((ViewHolder) holder).rbGrade.setRating(Float.parseFloat(mDataList.get(position).getRating().getAverage()) / 2);

        GlideUtils.setupImage(mContext, ((ViewHolder) holder).ivCover, mDataList.get(position).getImages().getLarge(), R.mipmap.ic_nocover);

        //设置点击事件
        ((ViewHolder) holder).llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onClick(holder.itemView, mDataList.get(position).getId(), mDataList.get(position).getTitle());
                }
            }
        });

        //设置长按点击事件
        ((ViewHolder) holder).llItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mOnItemLongClickListener != null) {
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onLongClick(holder.itemView, position);
                }
                return true;
            }
        });
    }

    class ViewHolder extends BaseRecyclerAdapter.ViewHolder {

        ImageView ivCover;
        RatingBar rbGrade;
        TextView tvGrade;
        TextView tvName;
        LinearLayout llItem;

        ViewHolder(View itemView) {
            super(itemView);
            //这里不能用ButterKnife注入，因为FooterView布局与Item不一样
            ivCover = (ImageView) itemView.findViewById(R.id.iv_cover);
            tvGrade = (TextView) itemView.findViewById(R.id.tv_grade);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            rbGrade = (RatingBar) itemView.findViewById(R.id.rb_grade);
            llItem = (LinearLayout) itemView.findViewById(R.id.ll_item);
        }
    }
}