package com.livelearn.ignorance.test.pulltorefreshwithloadmore.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.utils.DisplayUtils;
import com.livelearn.ignorance.utils.GlideUtils;

/**
 * Created on 2017/3/13.
 */

public class ViewHolder extends ViewHolderBase<String> {

    private Context mContext;
    private ImageView mImageView;
    private int sGirdImageSize;

    public ViewHolder(Context mContext) {
        this.mContext = mContext;
        this.sGirdImageSize = (DisplayUtils.getScreenWidth() - DisplayUtils.dp2px(12 + 12 + 10)) / 2;
    }

    @Override
    public View createView(LayoutInflater inflater) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.viewholder_test_list_grid, null);
        mImageView = (ImageView) view.findViewById(R.id.iv_image);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        LinearLayout.LayoutParams lyp = new LinearLayout.LayoutParams(sGirdImageSize, sGirdImageSize);
        mImageView.setLayoutParams(lyp);
        return view;
    }

    @Override
    public void showData(int position, String pictureUrl) {
        GlideUtils.setupImage(mContext, mImageView, pictureUrl);
    }
}