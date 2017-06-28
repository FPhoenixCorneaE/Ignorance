package com.livelearn.ignorance.ui.adapter.book;

import android.widget.ImageView;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.model.bean.book.BookListModel;
import com.livelearn.ignorance.utils.GlideUtils;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

public class BookRecommendGridAdapter extends BaseQuickAdapter<BookListModel> {

    public BookRecommendGridAdapter(List<BookListModel> data) {
        super(R.layout.adapter_book_recommend, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, BookListModel item) {
        GlideUtils.setupImage(mContext, (ImageView) holder.getView(R.id.iv_book_url), item.getImageUrl(), R.mipmap.ic_nocover);
        if (item.getBookName().length() <= 4) {
            ((TextView) holder.getView(R.id.tv_book_name)).setTextSize(15);
        } else if (item.getBookName().length() > 4 && item.getBookName().length() <= 12) {
            ((TextView) holder.getView(R.id.tv_book_name)).setTextSize(12);
        } else {
            ((TextView) holder.getView(R.id.tv_book_name)).setTextSize(9);
        }
        holder.setText(R.id.tv_book_name, item.getBookName());
    }
}
