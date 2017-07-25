package com.livelearn.ignorance.ui.adapter.viewholder.mine;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.model.db.dbentity.DouBanBookCollection;
import com.livelearn.ignorance.utils.GlideUtils;

/**
 * Created on 2017/7/19.
 */

public class DouBanBookCollectionGridViewHolder extends BaseViewHolder<DouBanBookCollection> {

    private ImageView ivCover;
    private RatingBar rbGrade;
    private TextView tvGrade;
    private TextView tvName;
    private LinearLayout llItem;

    public DouBanBookCollectionGridViewHolder(ViewGroup parent) {
        super(parent, R.layout.adapter_book_pager);
        ivCover = $(R.id.iv_cover);
        rbGrade = $(R.id.rb_grade);
        tvGrade = $(R.id.tv_grade);
        tvName = $(R.id.tv_name);
        llItem = $(R.id.ll_item);
    }

    @Override
    public void setData(final DouBanBookCollection data) {
        GlideUtils.setupImage(getContext(), ivCover, data.getBook_cover_url(), R.mipmap.ic_nocover);
        rbGrade.setRating(Float.parseFloat(data.getRating()) / 2);
        tvGrade.setText(data.getRating());
        tvName.setText(data.getBook_name());

        //设置点击事件
        llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(getAdapterPosition(), data.getBook_id(), data.getBook_name());
                }
            }
        });
    }

    /**
     * 点击回调
     */
    private OnItemClickListener mOnItemClickListener;

    public DouBanBookCollectionGridViewHolder setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
        return this;
    }

    public interface OnItemClickListener {
        void onClick(int position, String bookId, String bookName);
    }
}
