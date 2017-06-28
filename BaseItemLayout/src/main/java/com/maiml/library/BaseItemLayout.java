package com.maiml.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.maiml.baseitemlayoutlibrary.R;
import com.maiml.library.config.CommonCons;
import com.maiml.library.config.ConfigAttrs;
import com.maiml.library.config.Mode;
import com.maiml.library.factory.AbstractItemFactory;
import com.maiml.library.factory.ItemFactory;
import com.maiml.library.item.AbstractItem;
import com.maiml.library.item.RMSwitchItem;
import com.maiml.library.item.SwitchItem;
import com.maiml.library.rmswitch.RMSwitch;
import com.maiml.library.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

public class BaseItemLayout extends LinearLayout {


    private Context mContext;


    private AbstractItemFactory factory;
    private ConfigAttrs configAttrs;

    private List<View> viewList = new ArrayList<>();
    private boolean showStartLine;
    private boolean showEndLine;
    private int lineColor;
    private int lineMarginLeft;
    private int lineMarginRight;
    private int itemBackgroundResource;
    private int itemHeight;
    private int iconWidth;
    private int iconHeight;
    private int iconMarginLeft;
    private int iconTextMargin;
    private int textSize;
    private int textColor;
    private int arrowResId;
    private int arrowMarginRight;
    private int arrowWidth;
    private int arrowHeight;
    private boolean arrowIsShow;
    private int rightTextSize;
    private int rightTextColor;
    private int rightTextMagin;
    private int switchTurnOffResId;
    private int switchTurnOnResId;
    private boolean rmswitchChecked;
    private boolean rmswitchEnabled;
    private boolean rmswitchForceAspectRatio;
    private int rmswitchCheckedBgColor;
    private int rmswitchCheckedToggleColor;
    private int rmswitchCheckedToggleResId;
    private int rmswitchNotCheckedBgColor;
    private int rmswitchNotCheckedToggleColor;
    private int rmswitchNotCheckedToggleResId;


    public BaseItemLayout(Context context) {
        this(context, null);
    }

    public BaseItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        initCostomerArrts(context, attrs);
        init(context);
    }

    private void initCostomerArrts(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ItemAttrs);

        showStartLine = a.getBoolean(R.styleable.ItemAttrs_show_start_line, CommonCons.DF_SHOW_START_LINE);
        showEndLine = a.getBoolean(R.styleable.ItemAttrs_show_end_line, CommonCons.DF_SHOW_END_LINE);
        lineColor = a.getColor(R.styleable.ItemAttrs_line_color, CommonCons.DF_LINE_COLOR);
        lineMarginLeft = a.getInt(R.styleable.ItemAttrs_line_margin_left, CommonCons.DF_LINE_MARGIN_LEFT);
        lineMarginRight = a.getInt(R.styleable.ItemAttrs_line_margin_right, CommonCons.DF_LINE_MARGIN_RIGHT);

        itemBackgroundResource = a.getResourceId(R.styleable.ItemAttrs_item_background_resource, CommonCons.DF_ITEM_BACKGROUND_RESOURCE);
        itemHeight = a.getInt(R.styleable.ItemAttrs_item_height, CommonCons.DF_ITEM_HEIGHT);

        iconWidth = a.getInt(R.styleable.ItemAttrs_icon_width, CommonCons.DF_ICON_WIDTH);
        iconHeight = a.getInt(R.styleable.ItemAttrs_icon_height, CommonCons.DF_ICON_HEIGHT);
        iconMarginLeft = a.getInt(R.styleable.ItemAttrs_icon_margin_left, CommonCons.DF_ICON_MARGIN_LEFT);
        iconTextMargin = a.getInt(R.styleable.ItemAttrs_icon_text_margin, CommonCons.DF_ICON_TEXT_MARGIN);

        textSize = a.getInt(R.styleable.ItemAttrs_text_size, CommonCons.DF_TEXT_SIZE);
        textColor = a.getColor(R.styleable.ItemAttrs_text_color, CommonCons.DF_TEXT_COLOR);

        arrowResId = a.getResourceId(R.styleable.ItemAttrs_arrow_res_id, CommonCons.DF_ARROW_RES_ID);
        arrowMarginRight = a.getInt(R.styleable.ItemAttrs_margin_right, CommonCons.DF_ARROW_MARGIN_RIGHT);
        arrowWidth = a.getInt(R.styleable.ItemAttrs_arrow_width, CommonCons.DF_ARROW_WIDTH);
        arrowHeight = a.getInt(R.styleable.ItemAttrs_arrow_height, CommonCons.DF_ARROW_HEIGHT);
        arrowIsShow = a.getBoolean(R.styleable.ItemAttrs_arrow_is_show, CommonCons.DF_ARROW_IS_SHOW);

        rightTextSize = a.getInt(R.styleable.ItemAttrs_right_text_size, CommonCons.DF_RIGHT_TEXT_SIZE);
        rightTextColor = a.getColor(R.styleable.ItemAttrs_right_text_color, CommonCons.DF_RIGHT_TEXT_COLOR);
        rightTextMagin = a.getInt(R.styleable.ItemAttrs_right_text_margin, CommonCons.DF_RIGHT_TEXT_MARGIN);

        switchTurnOffResId = a.getResourceId(R.styleable.ItemAttrs_switch_turn_off_res_id, CommonCons.DF_SWITCH_TURN_OFF_RES_ID);
        switchTurnOnResId = a.getResourceId(R.styleable.ItemAttrs_switch_turn_on_res_id, CommonCons.DF_SWITCH_TURN_ON_RES_ID);

        rmswitchChecked = a.getBoolean(R.styleable.ItemAttrs_rmswitch_checked, CommonCons.DF_RMSWITCH_CHECKED);
        rmswitchEnabled = a.getBoolean(R.styleable.ItemAttrs_rmswitch_enabled, CommonCons.DF_RMSWITCH_ENABLED);
        rmswitchForceAspectRatio = a.getBoolean(R.styleable.ItemAttrs_rmswitch_force_aspect_ratio, CommonCons.DF_RMSWITCH_FORCE_ASPECT_RATIO);
        rmswitchCheckedBgColor = a.getColor(R.styleable.ItemAttrs_rmswitch_checked_bg_color, CommonCons.DF_RMSWITCH_CHECKED_BG_COLOR);
        rmswitchCheckedToggleColor = a.getColor(R.styleable.ItemAttrs_rmswitch_checked_toggle_color, CommonCons.DF_RMSWITCH_CHECKED_TOGGLE_COLOR);
        rmswitchCheckedToggleResId = a.getResourceId(R.styleable.ItemAttrs_rmswitch_checked_toggle_res_id, CommonCons.DF_RMSWITCH_CHECKED_TOGGLE_RES_ID);
        rmswitchNotCheckedBgColor = a.getColor(R.styleable.ItemAttrs_rmswitch_not_checked_bg_color, CommonCons.DF_RMSWITCH_NOT_CHECKED_BG_COLOR);
        rmswitchNotCheckedToggleColor = a.getColor(R.styleable.ItemAttrs_rmswitch_not_checked_toggle_color, CommonCons.DF_RMSWITCH_NOT_CHECKED_TOGGLE_COLOR);
        rmswitchNotCheckedToggleResId = a.getResourceId(R.styleable.ItemAttrs_rmswitch_not_checked_toggle_res_id, CommonCons.DF_RMSWITCH_NOT_CHECKED_TOGGLE_RES_ID);

        a.recycle();
    }


    private void init(Context context) {
        mContext = context;
        setOrientation(VERTICAL);
        factory = new ItemFactory(context);
    }


    public void create() {

        if (configAttrs == null) {
            throw new RuntimeException("config attrs  is null");
        }

        if (configAttrs.getValueList() == null) {
            throw new RuntimeException("valueList is null");
        }

        for (int i = 0; i < configAttrs.getValueList().size(); i++) {

            configAttrs.setPosition(i);
            SparseArray<Mode> modeArray = configAttrs.getModeArray();
            Mode mode = modeArray.get(i);
            AbstractItem itemView = factory.createItem(mode, configAttrs, i);
            addItem(itemView, i);

        }

    }

    /**
     * 添加开始线、item、结束线
     */
    private void addItem(AbstractItem view, final int pos) {


        if (configAttrs.getMarginArray() != null) {
            addView(createLineView(true, configAttrs.getMarginArray().get(pos), configAttrs.getLineMarginLeft(), configAttrs.getLineMarginRight()));
        } else {
            addView(createLineView(true, CommonCons.DEFAULT_HEIGHT, configAttrs.getLineMarginLeft(), configAttrs.getLineMarginRight()));
        }


        addView(view);

        addView(createLineView(false, CommonCons.ZERO_HEIGHT, configAttrs.getLineMarginLeft(), configAttrs.getLineMarginRight()));

        if (onBaseItemClick != null) {
            setListener(view, pos);
        }

        if (onSwitchClickListener != null) {
            setSwitchClick();
        }

        viewList.add(view);
    }

    /**
     * 创建线
     */
    private View createLineView(boolean startLine, int marginTop, int lineMarginLeft, int lineMarginRight) {

        View view = new View(mContext);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        lp.topMargin = DensityUtil.dip2px(mContext, marginTop);
        lp.leftMargin = DensityUtil.dip2px(mContext, lineMarginLeft);
        lp.rightMargin = DensityUtil.dip2px(mContext, lineMarginRight);

        view.setLayoutParams(lp);
        if (startLine) {
            if (configAttrs.isShowStartLine())
                view.setBackgroundColor(configAttrs.getLineColor());
        } else {
            if (configAttrs.isShowEndLine())
                view.setBackgroundColor(configAttrs.getLineColor());
        }
        return view;
    }

    private void setOnClick() {
        if (onBaseItemClick != null) {
            for (int i = 0; i < viewList.size(); i++) {
                View view = viewList.get(i);
                setListener(view, i);
            }
        }
    }

    /**
     * 设置 item 的监听回调
     */
    private void setListener(final View view, final int position) {
        if (view == null) {
            return;
        }

        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBaseItemClick.onItemClick(view, position);
            }
        });
    }

    /**
     * 设置switch的点击事件
     */
    private void setSwitchClick() {

        if (onSwitchClickListener != null) {

            for (int i = 0; i < viewList.size(); i++) {

                SparseArray<Mode> modeArray = configAttrs.getModeArray();

                Mode mode = modeArray.get(i);

                if (mode == Mode.SWITCH) {

                    SwitchItem view = (SwitchItem) viewList.get(i);

                    SwitchImageView switchImageView = view.getSwitchImageView();

                    onSwitchClick(i, switchImageView);

                }
            }
        }

        if (onRMSwitchObserver != null) {
            for (int i = 0; i < viewList.size(); i++) {

                SparseArray<Mode> modeArray = configAttrs.getModeArray();

                Mode mode = modeArray.get(i);

                if (mode == Mode.RMSWITCH) {

                    RMSwitchItem view = (RMSwitchItem) viewList.get(i);

                    RMSwitch rmSwitchView = view.getRmSwitchView();

                    onRMSwitchClick(i, rmSwitchView);
                }
            }
        }
    }

    private void onRMSwitchClick(final int position, RMSwitch rmSwitchView) {

        rmSwitchView.addSwitchObserver(new RMSwitch.RMSwitchObserver() {
            @Override
            public void onCheckStateChange(RMSwitch switchView, boolean isChecked) {

                onRMSwitchObserver.onCheckedStateChange(switchView, isChecked, position);
            }
        });
    }


    private void onSwitchClick(final int pos, final SwitchImageView switchImageView) {

        switchImageView.setOnSwitchClickListener(new SwitchImageView.OnSwitchClickListener() {
            @Override
            public void onClick(boolean isChecked) {


                if (isChecked) {
                    switchImageView.setImageResource(R.drawable.img_up);
                } else {
                    switchImageView.setImageResource(R.drawable.img_turn_down);
                }
                onSwitchClickListener.onClick(pos, isChecked);
            }
        });
    }


    public BaseItemLayout setConfigAttrs(ConfigAttrs attrs) {

        if (attrs == null) {
            throw new RuntimeException("attrs is null");
        }
        this.configAttrs = attrs;

        //分割线
        if (attrs.isShowStartLine() == CommonCons.DF_SHOW_START_LINE)
            configAttrs.setShowStartLine(showStartLine);
        if (attrs.isShowEndLine() == CommonCons.DF_SHOW_END_LINE)
            configAttrs.setShowEndLine(showEndLine);
        if (attrs.getLineColor() == 0)
            configAttrs.setLineColor(lineColor);
        if (attrs.getLineMarginLeft() == 0)
            configAttrs.setLineMarginLeft(lineMarginLeft);
        if (attrs.getLineMarginRight() == 0)
            configAttrs.setLineMarginRight(lineMarginRight);

        //子项背景资源
        if (attrs.getItemBackgroundResId() == 0)
            configAttrs.setItemBackgroundResId(itemBackgroundResource);

        //子项高度
        if (attrs.getItemHeight() == 0)
            configAttrs.setItemHeight(itemHeight);

        //子项左边图标
        if (attrs.getIconWidth() == 0)
            configAttrs.setIconWidth(iconWidth);
        if (attrs.getIconHeight() == 0)
            configAttrs.setIconHeight(iconHeight);
        if (attrs.getIconMarginLeft() == 0)
            configAttrs.setIconMarginLeft(iconMarginLeft);

        //子项左边文字
        if (attrs.getTextSize() == 0)
            configAttrs.setTextSize(textSize);
        if (attrs.getTextColor() == 0)
            configAttrs.setTextColor(textColor);
        if (attrs.getIconTextMargin() == 0)
            configAttrs.setIconTextMargin(iconTextMargin);

        //子项右边文字
        if (attrs.getRightTextColor() == 0)
            configAttrs.setRightTextColor(rightTextColor);
        if (attrs.getRightTextSize() == 0)
            configAttrs.setRightTextSize(rightTextSize);
        if (attrs.getRightTextMagin() == 0)
            configAttrs.setRightTextMagin(rightTextMagin);

        //子项右边箭头
        if (attrs.getArrowResId() == 0)
            configAttrs.setArrowResId(arrowResId);
        if (attrs.getMarginRightArray().size() == 0)
            configAttrs.setItemMarginRight(arrowMarginRight);
        if (attrs.getArrowWidth() == 0)
            configAttrs.setArrowWidth(arrowWidth);
        if (attrs.getArrowHeight() == 0)
            configAttrs.setArrowHeight(arrowHeight);
        if (attrs.getArrowIsShowArray().size() == 0)
            configAttrs.setArrowIsShow(arrowIsShow);

        //开关
        if (attrs.getUpResId() == 0)
            configAttrs.setUpResId(switchTurnOnResId);
        if (attrs.getTrunResId() == 0)
            configAttrs.setTrunResId(switchTurnOffResId);
        if (attrs.getRmSwitchCheckedArray().size() == 0)
            configAttrs.setRmSwitchChecked(rmswitchChecked);
        if (attrs.getRmSwitchEnabledArray().size() == 0)
            configAttrs.setRmSwitchEnabled(rmswitchEnabled);
        if (attrs.getRmSwitchForceAspectRatioArray().size() == 0)
            configAttrs.setRmSwitchForceAspectRatio(rmswitchForceAspectRatio);
        if (attrs.getRmSwitchCheckedBgColorArray().size() == 0)
            configAttrs.setRmSwitchCheckedBgColor(rmswitchCheckedBgColor);
        if (attrs.getRmSwitchCheckedToggleColorArray().size() == 0)
            configAttrs.setRmSwitchCheckedToggleColor(rmswitchCheckedToggleColor);
        if (attrs.getRmSwitchCheckedToggleResArray().size() == 0)
            configAttrs.setRmSwitchCheckedToggleRes(rmswitchCheckedToggleResId);
        if (attrs.getRmSwitchNotCheckedBgColorArray().size() == 0)
            configAttrs.setRmSwitchNotCheckedBgColor(rmswitchNotCheckedBgColor);
        if (attrs.getRmSwitchNotCheckedToggleColorArray().size() == 0)
            configAttrs.setRmSwitchNotCheckedToggleColor(rmswitchNotCheckedToggleColor);
        if (attrs.getRmSwitchNotCheckedToggleResArray().size() == 0)
            configAttrs.setRmSwitchNotCheckedToggleRes(rmswitchNotCheckedToggleResId);
        return this;
    }

    public List<View> getViewList() {
        return viewList;
    }

    //=================================监听事件====================================


    private OnBaseItemClick onBaseItemClick;

    /**
     * 监听所有item点击事件
     */
    public void setOnBaseItemClick(OnBaseItemClick onBaseItemClick) {
        this.onBaseItemClick = onBaseItemClick;
        setOnClick();
    }

    /**
     * 只监听position下的item点击事件
     */
    public void setOnBaseItemClick(int position, OnBaseItemClick onBaseItemClick) {
        this.onBaseItemClick = onBaseItemClick;
        if (onBaseItemClick != null) {
            setListener(viewList.get(position), position);
        }
    }

    /**
     * 监听一组position下的item点击事件
     */
    public void setOnBaseItemClick(int[] positions, OnBaseItemClick onBaseItemClick) {
        this.onBaseItemClick = onBaseItemClick;
        if (onBaseItemClick != null) {
            for (int position : positions) {
                if (position < viewList.size())
                    setListener(viewList.get(position), position);
            }
        }
    }

    public interface OnBaseItemClick {

        void onItemClick(View view, int position);
    }


    private OnSwitchClickListener onSwitchClickListener;

    /**
     * 监听所有switch点击事件
     */
    public void setOnSwitchClickListener(OnSwitchClickListener onSwitchClickListener) {
        this.onSwitchClickListener = onSwitchClickListener;
        setSwitchClick();
    }

    public interface OnSwitchClickListener {

        void onClick(int position, boolean isChecked);
    }

    private OnRMSwitchObserver onRMSwitchObserver;

    /**
     * 监听所有rmswitch点击事件
     */
    public void addOnRMSwitchObserver(OnRMSwitchObserver onRMSwitchObserver) {
        this.onRMSwitchObserver = onRMSwitchObserver;
        setSwitchClick();
    }

    public interface OnRMSwitchObserver {
        void onCheckedStateChange(RMSwitch switchView, boolean isChecked, int position);
    }
}
