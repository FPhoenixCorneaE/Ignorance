package com.livelearn.ignorance.ui.adapter.book;

import android.widget.ImageView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.model.bean.book.BookListModel;
import com.livelearn.ignorance.utils.GlideUtils;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

/**
 * Created on 2017/2/13.
 */

public class BookCategoryAdapter extends BaseQuickAdapter<BookListModel> {

    public BookCategoryAdapter(List<BookListModel> data) {
        super(R.layout.adapter_book_category, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, BookListModel item) {
        GlideUtils.setupImage(mContext, (ImageView) holder.getView(R.id.iv_book_url), item.getImageUrl(), R.mipmap.ic_nocover);
        holder.setText(R.id.tv_book_name, item.getBookName());
        holder.setText(R.id.tv_book_author, item.getAuthor());
        holder.setText(R.id.tv_book_introduction, item.getIntroduction());
    }
}
