package com.livelearn.ignorance.ui.adapter.book;

import android.widget.ImageView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.model.bean.book.BookTypeModel;
import com.livelearn.ignorance.utils.GlideUtils;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

/**
 * Created on 2017/2/14.
 */

public class BookClassGridAdapter extends BaseQuickAdapter<BookTypeModel> {


    public BookClassGridAdapter(List<BookTypeModel> data) {
        super(R.layout.adapter_book_class, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, BookTypeModel item) {
        GlideUtils.setupImage(mContext, (ImageView) holder.getView(R.id.iv_book_class), item.getBookTypeImageUrl(), R.mipmap.ic_error_book_class);
        holder.setText(R.id.tv_book_class_name, item.getBookTypeName());
    }
}
