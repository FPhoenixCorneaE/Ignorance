package com.maiml.library.item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maiml.baseitemlayoutlibrary.R;
import com.maiml.library.utils.DensityUtil;

/**
 * Created by maimingliang on 2016/12/5.
 */

public class TextItem extends AbstractItem {


    private TextView rightTextView;

    private LayoutParams rightTextViewlp;


    public TextItem(Context context) {
        super(context);
    }

    public TextItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void createWidget() {
        rightTextView = new TextView(mContext);

        rightTextView.setId(R.id.right_text_id);
    }

    @Override
    public void createWidgetLayoutParams() {


        rightTextViewlp = new LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightTextViewlp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        rightTextViewlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);

    }


    @Override
    public void addWidget(int index) {
        super.addWidget(index);
        addView(rightTextView, rightTextViewlp);
        setRightTextStyle(index);
    }


    /**
     * 设置文字的样式
     *
     * @param index
     */
    public void setRightTextStyle(int index) {

        if (configAttrs == null) {
            throw new RuntimeException("config attrs is null");
        }


        if (configAttrs.getRightTextArray() == null) {
            throw new RuntimeException("right text array is null");
        }

        String text = configAttrs.getRightTextArray().get(configAttrs.getPosition()) == null
                ? "" : configAttrs.getRightTextArray().get(configAttrs.getPosition());


        rightTextViewlp.rightMargin = DensityUtil.dip2px(mContext, configAttrs.getMarginRightArray().get(index));

        rightTextView.setText(text);
        rightTextView.setGravity(Gravity.CENTER_VERTICAL);

        rightTextView.setTextColor(configAttrs.getRightTextColor());
        rightTextView.setTextSize(configAttrs.getRightTextSize());


        if (configAttrs.getArrowIsShowArray().get(index) && configAttrs.getArrowResId() != 0) {

//            Drawable drawable = getResources().getDrawable(configAttrs.getArrowResId());
//            // 这一步必须要做,否则不会显示.
//            drawable.setBounds(0, 0, DensityUtil.dip2px(mContext, configAttrs.getArrowWidth()), DensityUtil.dip2px(mContext, configAttrs.getArrowHeight()));
//            rightTextView.setCompoundDrawables(null, null, drawable, null);
//            rightTextView.setCompoundDrawablePadding(DensityUtil.dip2px(mContext, configAttrs.getRightTextMagin()));

            rightTextViewlp.rightMargin = DensityUtil.dip2px(mContext, configAttrs.getMarginRightArray().get(index) + configAttrs.getArrowWidth() + configAttrs.getRightTextMagin());

            ImageView arrowView = new ImageView(mContext);
            arrowView.setId(R.id.arrow_id);

            LayoutParams arrowViewlp = new LayoutParams(DensityUtil.dip2px(mContext, configAttrs.getArrowWidth()), DensityUtil.dip2px(mContext, configAttrs.getArrowHeight()));
            arrowViewlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            arrowViewlp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);

            arrowViewlp.rightMargin = DensityUtil.dip2px(mContext, configAttrs.getMarginRightArray().get(index));
            arrowView.setImageResource(configAttrs.getArrowResId());
            arrowView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

            addView(arrowView, arrowViewlp);
        }
    }
}
