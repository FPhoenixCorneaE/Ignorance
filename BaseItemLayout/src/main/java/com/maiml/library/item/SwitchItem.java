package com.maiml.library.item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.maiml.library.SwitchImageView;
import com.maiml.library.utils.DensityUtil;

/**
 * Created by maimingliang on 2016/12/4.
 */

public class SwitchItem extends AbstractItem {


    private SwitchImageView switchImageView;

    private LayoutParams switchCblp;


    public SwitchItem(Context context) {
        super(context);
    }

    public SwitchItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwitchItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void createWidget() {
        switchImageView = new SwitchImageView(mContext);
    }

    @Override
    public void createWidgetLayoutParams() {

        switchCblp = new LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        switchCblp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        switchCblp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
    }


    @Override
    public void addWidget(int index) {
        super.addWidget(index);
        addView(switchImageView, switchCblp);
        setSwitchImageViewStyle(index);
    }


    public void setSwitchImageViewStyle(int index) {


        if (configAttrs == null) {
            throw new RuntimeException("config attrs is not set");
        }
        if (configAttrs.getTrunResId() <= 0 || configAttrs.getUpResId() <= 0) {
            throw new RuntimeException("switch res is not set");
        }

        switchCblp.rightMargin = DensityUtil.dip2px(mContext, configAttrs.getMarginRightArray().get(index));

        switchImageView.setImageResource(configAttrs.getTrunResId());


    }


    public SwitchImageView getSwitchImageView() {
        return switchImageView;
    }
}
