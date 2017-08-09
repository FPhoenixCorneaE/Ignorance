package com.livelearn.ignorance.ui.adapter.viewholder.mine;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.model.db.dbentity.LongTimeBookCollection;
import com.livelearn.ignorance.utils.GlideUtils;

/**
 * Created on 2017/7/19.
 */

public class LongTimeBookCollectionGridViewHolder extends BaseViewHolder<LongTimeBookCollection> {

    private ImageView ivBookUrl;
    private TextView tvBookName;

    public LongTimeBookCollectionGridViewHolder(ViewGroup parent) {
        super(parent, R.layout.adapter_book_recommend);
        ivBookUrl = $(R.id.iv_book_url);
        tvBookName = $(R.id.tv_book_name);
    }

    @Override
    public void setData(LongTimeBookCollection data) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ivBookUrl.getLayoutParams();
        layoutParams.height = (int) (layoutParams.width * 1.45);
        ivBookUrl.setLayoutParams(layoutParams);
        GlideUtils.setupImage(getContext(), ivBookUrl, data.getBook_image_url(), R.mipmap.ic_nocover);
        tvBookName.setText(data.getBook_name());
    }
}
