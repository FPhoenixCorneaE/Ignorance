package com.livelearn.ignorance.ui.adapter.viewholder.mine;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.model.db.dbentity.DouBanBookCollection;
import com.livelearn.ignorance.utils.GlideUtils;

/**
 * Created on 2017/7/19.
 */

public class DouBanBookCollectionListViewHolder extends BaseViewHolder<DouBanBookCollection> {

    private ImageView ivBookUrl;
    private TextView tvBookName;
    private TextView tvBookAuthor;
    private TextView tvBookIntroduction;

    public DouBanBookCollectionListViewHolder(ViewGroup parent) {
        super(parent, R.layout.viewholder_book_collection_list);
        ivBookUrl = $(R.id.iv_book_url);
        tvBookName = $(R.id.tv_book_name);
        tvBookAuthor = $(R.id.tv_book_author);
        tvBookIntroduction = $(R.id.tv_book_introduction);
    }

    @Override
    public void setData(DouBanBookCollection data) {
        GlideUtils.setupImage(getContext(), ivBookUrl, data.getBook_cover_url(), R.mipmap.ic_nocover);
        tvBookName.setText(data.getBook_name());
        tvBookAuthor.setText(data.getAuthor());
        tvBookIntroduction.setText(data.getPublisher());
    }
}
