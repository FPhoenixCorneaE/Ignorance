package com.maiml.library.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.maiml.baseitemlayoutlibrary.R;
import com.maiml.library.utils.DensityUtil;

/**
 * Created by maimingliang on 2016/12/4.
 */

public class ArrowItem extends AbstractItem {


    private ImageView arrowImg;
    private LayoutParams arrowLp;


    public ArrowItem(Context context) {
        super(context);
    }

    public ArrowItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ArrowItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void createWidget() {

        arrowImg = new ImageView(mContext);
        arrowImg.setId(R.id.arrow_id);
    }

    @Override
    public void createWidgetLayoutParams() {
        arrowLp = new LayoutParams(DensityUtil.dip2px(mContext, configAttrs.getArrowWidth()), DensityUtil.dip2px(mContext, configAttrs.getArrowHeight()));
        arrowLp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        arrowLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
    }


    @Override
    public void addWidget(int index) {
        super.addWidget(index);

        addView(arrowImg, arrowLp);
        setArrowStyle(index);

    }


    /**
     * 箭头的颜色
     *
     * @param index
     */
    public void setArrowStyle(int index) {

        if (configAttrs == null) {
            throw new RuntimeException("config attrs is null");
        }

        if (configAttrs.getArrowResId() == 0) {
            throw new RuntimeException("arrow res id is null");
        }

        if (!configAttrs.getArrowIsShowArray().get(index)) return;

        arrowLp.rightMargin = DensityUtil.dip2px(mContext, configAttrs.getMarginRightArray().get(index));
        arrowImg.setImageResource(configAttrs.getArrowResId());
        arrowImg.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

    }
}
