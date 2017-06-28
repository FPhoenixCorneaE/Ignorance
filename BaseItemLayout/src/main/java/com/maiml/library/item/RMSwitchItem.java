package com.maiml.library.item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.maiml.library.rmswitch.RMSwitch;
import com.maiml.library.utils.DensityUtil;

/**
 * Created by Administrator on 2016/12/21.
 */

public class RMSwitchItem extends AbstractItem {

    private RMSwitch rmSwitch;
    private LayoutParams rmSwitchLP;

    public RMSwitchItem(Context context) {
        super(context);
    }

    public RMSwitchItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RMSwitchItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void createWidget() {
        rmSwitch = new RMSwitch(mContext);
    }

    @Override
    public void createWidgetLayoutParams() {
        rmSwitchLP = new LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rmSwitchLP.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        rmSwitchLP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
    }

    @Override
    public void addWidget(int index) {
        super.addWidget(index);

        addView(rmSwitch, rmSwitchLP);
        setRMSwitchViewStyle(index);
    }

    private void setRMSwitchViewStyle(int index) {
        if (configAttrs == null) {
            throw new RuntimeException("config attrs is not set");
        }


        rmSwitchLP.rightMargin = DensityUtil.dip2px(mContext, configAttrs.getMarginRightArray().get(index));

        if (configAttrs.getRmSwitchWidth() > 0) {
            rmSwitchLP.width = DensityUtil.dip2px(mContext, configAttrs.getRmSwitchWidth());

            //宽高比例不使用默认比例
            if (!configAttrs.getRmSwitchForceAspectRatioArray().get(index)) {
                if (configAttrs.getRmSwitchHeight() > 0) {
                    rmSwitchLP.height = DensityUtil.dip2px(mContext, configAttrs.getRmSwitchHeight());
                }
            }
        }


        rmSwitch.setChecked(configAttrs.getRmSwitchCheckedArray().get(index));

        rmSwitch.setEnabled(configAttrs.getRmSwitchEnabledArray().get(index));

        rmSwitch.setForceAspectRatio(configAttrs.getRmSwitchForceAspectRatioArray().get(index));

        rmSwitch.setSwitchBkgCheckedColor(configAttrs.getRmSwitchCheckedBgColorArray().get(index));
        rmSwitch.setSwitchToggleCheckedColor(configAttrs.getRmSwitchCheckedToggleColorArray().get(index));

        rmSwitch.setSwitchBkgNotCheckedColor(configAttrs.getRmSwitchNotCheckedBgColorArray().get(index));
        rmSwitch.setSwitchToggleNotCheckedColor(configAttrs.getRmSwitchNotCheckedToggleColorArray().get(index));

        if (configAttrs.getRmSwitchCheckedToggleResArray().get(index) > 0) {
            rmSwitch.setSwitchToggleCheckedDrawableRes(configAttrs.getRmSwitchCheckedToggleResArray().get(index));
        }
        if (configAttrs.getRmSwitchNotCheckedToggleResArray().get(index) > 0) {
            rmSwitch.setSwitchToggleNotCheckedDrawableRes(configAttrs.getRmSwitchNotCheckedToggleResArray().get(index));
        }
    }

    public RMSwitch getRmSwitchView() {
        return rmSwitch;
    }
}
