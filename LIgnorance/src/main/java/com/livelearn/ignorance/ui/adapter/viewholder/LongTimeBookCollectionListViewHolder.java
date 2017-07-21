package com.livelearn.ignorance.ui.adapter.viewholder;

import android.text.Html;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.model.db.dbentity.LongTimeBookCollection;
import com.livelearn.ignorance.utils.GlideUtils;

/**
 * Created on 2017/7/19.
 */

public class LongTimeBookCollectionListViewHolder extends BaseViewHolder<LongTimeBookCollection> {

    private ImageView ivBookUrl;
    private TextView tvBookName;
    private TextView tvBookAuthor;
    private TextView tvBookIntroduction;

    public LongTimeBookCollectionListViewHolder(ViewGroup parent) {
        super(parent, R.layout.adapter_book_category);
        ivBookUrl = $(R.id.iv_book_url);
        tvBookName = $(R.id.tv_book_name);
        tvBookAuthor = $(R.id.tv_book_author);
        tvBookIntroduction = $(R.id.tv_book_introduction);
    }

    @Override
    public void setData(LongTimeBookCollection data) {
        GlideUtils.setupImage(getContext(), ivBookUrl, data.getBook_image_url(), R.mipmap.ic_nocover);
        tvBookName.setText(data.getBook_name());
        tvBookAuthor.setText(data.getBook_author());
        tvBookIntroduction.setText(Html.fromHtml(data.getBook_introduction()));
    }
}
