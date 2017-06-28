package com.maiml.library.config;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;

import java.util.List;

/**
 * Created by maimingliang on 2016/12/4.
 */

public class ConfigAttrs {


    private int position;

    /**
     * item 的背景资源id
     */
    private int itemBackgroundResId;

    /**
     * item 的 高度
     */
    private int itemHeight;

    /**
     * 线的颜色
     */
    private int lineColor;

    /**
     * 线距离左边、右边大小
     */
    private int lineMarginLeft;
    private int lineMarginRight;

    /**
     * 是否显示开始的线、结束的线
     */
    private boolean showStartLine;
    private boolean showEndLine;

    /**
     * 图标的 高宽
     */
    private int iconWidth;
    private int iconHeight;

    /**
     * 字体的大小
     */
    private int textSize;

    /**
     * 字体的颜色
     */
    private int textColor;

    /**
     * 图标距离左边的 marginLeft 大小
     */
    private int iconMarginLeft;

    /**
     * 文字 距离 图标的 marginLeft 大小
     */
    private int iconTextMargin;

    /**
     * TextView 的显示文字 按顺序 插入
     */
    private List<String> valueList;

    /**
     * icon 图标的 resId 按顺序插入
     */
    private List<Integer> resIdList;

    /**
     * 箭头距离 最右边 的 marginRight 大小
     */
    private SparseIntArray marginRightArray = new SparseIntArray();

    /**
     * 箭头的资源Id
     */
    private int arrowResId;

    /**
     * 箭头的宽高
     */
    private int arrowWidth;
    private int arrowHeight;

    /**
     * 箭头是否显示
     */
    private SparseBooleanArray arrowIsShowArray = new SparseBooleanArray();

    /**
     * 字体的大小
     */
    private int rightTextSize;

    /**
     * 字体的颜色
     */
    private int rightTextColor;

    /**
     * switch图标的资源
     */
    private int trunResId;
    private int upResId;

    /**
     * rmswitch属性
     */
    private int rmSwitchWidth;
    private int rmSwitchHeight;
    private SparseBooleanArray rmSwitchCheckedArray = new SparseBooleanArray();
    private SparseBooleanArray rmSwitchEnabledArray = new SparseBooleanArray();
    private SparseBooleanArray rmSwitchForceAspectRatioArray = new SparseBooleanArray();
    private SparseIntArray rmSwitchCheckedBgColorArray = new SparseIntArray();
    private SparseIntArray rmSwitchCheckedToggleColorArray = new SparseIntArray();
    private SparseIntArray rmSwitchCheckedToggleResArray = new SparseIntArray();
    private SparseIntArray rmSwitchNotCheckedBgColorArray = new SparseIntArray();
    private SparseIntArray rmSwitchNotCheckedToggleColorArray = new SparseIntArray();
    private SparseIntArray rmSwitchNotCheckedToggleResArray = new SparseIntArray();

    /**
     * 右边字体和箭头的间距
     */
    private int rightTextMagin;

    /**
     * 每一个 item 与 item 之间的 marginTop 的大小
     */
    private SparseIntArray marginArray = new SparseIntArray();

    /**
     * item 的模式
     */
    private SparseArray<Mode> modeArray = new SparseArray<>();

    /**
     * 保存右边的text
     */
    private SparseArray<String> rightTextArray = new SparseArray<>();

    public int getPosition() {
        return position;
    }

    public ConfigAttrs setPosition(int position) {
        this.position = position;
        return this;
    }

    public int getItemHeight() {
        return itemHeight;
    }

    public ConfigAttrs setItemHeight(int itemHeight) {
        this.itemHeight = itemHeight;
        return this;
    }

    public int getItemBackgroundResId() {
        return itemBackgroundResId;
    }

    public ConfigAttrs setItemBackgroundResId(int itemBackgroundResId) {
        this.itemBackgroundResId = itemBackgroundResId;
        return this;
    }

    public int getLineColor() {
        return lineColor;
    }

    public ConfigAttrs setLineColor(int lineColor) {
        this.lineColor = lineColor;
        return this;
    }

    public int getLineMarginLeft() {
        return lineMarginLeft;
    }

    public ConfigAttrs setLineMarginLeft(int lineMarginLeft) {
        this.lineMarginLeft = lineMarginLeft;
        return this;
    }

    public int getLineMarginRight() {
        return lineMarginRight;
    }

    public ConfigAttrs setLineMarginRight(int lineMarginRight) {
        this.lineMarginRight = lineMarginRight;
        return this;
    }

    public boolean isShowEndLine() {
        return showEndLine;
    }

    public ConfigAttrs setShowEndLine(boolean showEndLine) {
        this.showEndLine = showEndLine;
        return this;
    }

    public boolean isShowStartLine() {
        return showStartLine;
    }

    public ConfigAttrs setShowStartLine(boolean showStartLine) {
        this.showStartLine = showStartLine;
        return this;
    }

    public int getIconWidth() {
        return iconWidth;
    }

    public ConfigAttrs setIconWidth(int iconWidth) {
        this.iconWidth = iconWidth;
        return this;
    }

    public int getIconHeight() {
        return iconHeight;
    }

    public ConfigAttrs setIconHeight(int iconHeight) {
        this.iconHeight = iconHeight;
        return this;
    }

    public int getTextSize() {
        return textSize;
    }

    public ConfigAttrs setTextSize(int textSize) {
        this.textSize = textSize;
        return this;
    }

    public int getTextColor() {
        return textColor;
    }

    public ConfigAttrs setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public int getIconMarginLeft() {
        return iconMarginLeft;
    }

    public ConfigAttrs setIconMarginLeft(int iconMarginLeft) {
        this.iconMarginLeft = iconMarginLeft;
        return this;
    }

    public int getIconTextMargin() {
        return iconTextMargin;
    }

    public ConfigAttrs setIconTextMargin(int iconTextMargin) {
        this.iconTextMargin = iconTextMargin;
        return this;
    }

    public List<String> getValueList() {
        return valueList;
    }

    public ConfigAttrs setValueList(List<String> valueList) {
        this.valueList = valueList;
        if (valueList.size() <= 0) {
            throw new RuntimeException("");
        }

        setShowStartLine(CommonCons.DF_SHOW_START_LINE);
        setShowEndLine(CommonCons.DF_SHOW_END_LINE);

        for (int i = 0; i < valueList.size(); i++) {
            setItemMode(CommonCons.DF_ITEM_MODE);
            setItemMarginTop(CommonCons.DEFAULT_HEIGHT);
            setItemMarginRight(CommonCons.DF_ARROW_MARGIN_RIGHT);
            setArrowIsShow(CommonCons.DF_ARROW_IS_SHOW);
            setRmSwitchChecked(CommonCons.DF_RMSWITCH_CHECKED);
            setRmSwitchEnabled(CommonCons.DF_RMSWITCH_ENABLED);
            setRmSwitchForceAspectRatio(CommonCons.DF_RMSWITCH_FORCE_ASPECT_RATIO);
            setRmSwitchCheckedBgColor(CommonCons.DF_RMSWITCH_CHECKED_BG_COLOR);
            setRmSwitchNotCheckedBgColor(CommonCons.DF_RMSWITCH_NOT_CHECKED_BG_COLOR);
            setRmSwitchCheckedToggleColor(CommonCons.DF_RMSWITCH_CHECKED_TOGGLE_COLOR);
            setRmSwitchNotCheckedToggleColor(CommonCons.DF_RMSWITCH_NOT_CHECKED_TOGGLE_COLOR);
            setRmSwitchCheckedToggleRes(CommonCons.DF_RMSWITCH_CHECKED_TOGGLE_RES_ID);
            setRmSwitchNotCheckedToggleRes(CommonCons.DF_RMSWITCH_NOT_CHECKED_TOGGLE_RES_ID);
        }
        return this;
    }

    public List<Integer> getResIdList() {
        return resIdList;
    }

    public ConfigAttrs setResIdList(List<Integer> resIdList) {
        this.resIdList = resIdList;
        return this;
    }

    public SparseIntArray getMarginRightArray() {
        return marginRightArray;
    }

    public ConfigAttrs setItemMarginRight(int index, int marginRight) {
        marginRightArray.put(index, marginRight);
        return this;
    }

    public ConfigAttrs setItemMarginRight(int marginRight) {
        if (valueList.size() <= 0) {
            throw new RuntimeException("");
        }

        for (int i = 0; i < valueList.size(); i++) {
            marginRightArray.put(i, marginRight);
        }
        return this;
    }

    public int getArrowResId() {
        return arrowResId;
    }

    public ConfigAttrs setArrowResId(int arrowResId) {
        this.arrowResId = arrowResId;
        return this;
    }

    public int getArrowHeight() {
        return arrowHeight;
    }

    public ConfigAttrs setArrowHeight(int arrowHeight) {
        this.arrowHeight = arrowHeight;
        return this;
    }

    public int getArrowWidth() {
        return arrowWidth;
    }

    public ConfigAttrs setArrowWidth(int arrowWidth) {
        this.arrowWidth = arrowWidth;
        return this;
    }

    public SparseBooleanArray getArrowIsShowArray() {
        return arrowIsShowArray;
    }

    public ConfigAttrs setArrowIsShow(int index, boolean arrowIsShow) {
        arrowIsShowArray.put(index, arrowIsShow);
        return this;
    }

    public ConfigAttrs setArrowIsShow(boolean arrowIsShow) {
        if (valueList.size() <= 0) {
            throw new RuntimeException("");
        }

        for (int i = 0; i < valueList.size(); i++) {
            arrowIsShowArray.put(i, arrowIsShow);
        }
        return this;
    }

    public int getRightTextSize() {
        return rightTextSize;
    }

    public ConfigAttrs setRightTextSize(int rightTextSize) {
        this.rightTextSize = rightTextSize;
        return this;
    }

    public int getRightTextColor() {
        return rightTextColor;
    }

    public ConfigAttrs setRightTextColor(int rightTextColor) {
        this.rightTextColor = rightTextColor;
        return this;
    }

    public int getTrunResId() {
        return trunResId;
    }

    public ConfigAttrs setTrunResId(int trunResId) {
        this.trunResId = trunResId;
        return this;
    }

    public int getUpResId() {
        return upResId;
    }

    public ConfigAttrs setUpResId(int upResId) {
        this.upResId = upResId;
        return this;
    }

    public int getRmSwitchHeight() {
        return rmSwitchHeight;
    }

    public ConfigAttrs setRmSwitchHeight(int rmSwitchHeight) {
        this.rmSwitchHeight = rmSwitchHeight;
        return this;
    }

    public int getRmSwitchWidth() {
        return rmSwitchWidth;
    }

    public ConfigAttrs setRmSwitchWidth(int rmSwitchWidth) {
        this.rmSwitchWidth = rmSwitchWidth;
        return this;
    }

    public SparseBooleanArray getRmSwitchCheckedArray() {
        return rmSwitchCheckedArray;
    }

    public ConfigAttrs setRmSwitchChecked(int index, boolean rmSwitchChecked) {
        rmSwitchCheckedArray.put(index, rmSwitchChecked);
        return this;
    }

    public ConfigAttrs setRmSwitchChecked(boolean rmSwitchChecked) {
        if (valueList.size() <= 0) {
            throw new RuntimeException("values is null");
        }

        for (int i = 0; i < valueList.size(); i++) {
            rmSwitchCheckedArray.put(i, rmSwitchChecked);
        }
        return this;
    }

    public SparseIntArray getRmSwitchCheckedBgColorArray() {
        return rmSwitchCheckedBgColorArray;
    }

    public ConfigAttrs setRmSwitchCheckedBgColor(int index, int rmSwitchCheckedBgColor) {
        rmSwitchCheckedBgColorArray.put(index, rmSwitchCheckedBgColor);
        return this;
    }

    public ConfigAttrs setRmSwitchCheckedBgColor(int rmSwitchCheckedBgColor) {
        if (valueList.size() <= 0) {
            throw new RuntimeException("values is null");
        }

        for (int i = 0; i < valueList.size(); i++) {
            rmSwitchCheckedBgColorArray.put(i, rmSwitchCheckedBgColor);
        }
        return this;
    }

    public SparseIntArray getRmSwitchCheckedToggleColorArray() {
        return rmSwitchCheckedToggleColorArray;
    }

    public ConfigAttrs setRmSwitchCheckedToggleColor(int index, int rmSwitchCheckedToggleColor) {
        rmSwitchCheckedToggleColorArray.put(index, rmSwitchCheckedToggleColor);
        return this;
    }

    public ConfigAttrs setRmSwitchCheckedToggleColor(int rmSwitchCheckedToggleColor) {
        if (valueList.size() <= 0) {
            throw new RuntimeException("values is null");
        }

        for (int i = 0; i < valueList.size(); i++) {
            rmSwitchCheckedToggleColorArray.put(i, rmSwitchCheckedToggleColor);
        }
        return this;
    }

    public SparseIntArray getRmSwitchCheckedToggleResArray() {
        return rmSwitchCheckedToggleResArray;
    }

    public ConfigAttrs setRmSwitchCheckedToggleRes(int index, int rmSwitchCheckedToggleRes) {
        rmSwitchCheckedToggleResArray.put(index, rmSwitchCheckedToggleRes);
        return this;
    }

    public ConfigAttrs setRmSwitchCheckedToggleRes(int rmSwitchCheckedToggleRes) {
        if (valueList.size() <= 0) {
            throw new RuntimeException("values is null");
        }

        for (int i = 0; i < valueList.size(); i++) {
            rmSwitchCheckedToggleResArray.put(i, rmSwitchCheckedToggleRes);
        }
        return this;
    }

    public SparseBooleanArray getRmSwitchEnabledArray() {
        return rmSwitchEnabledArray;
    }

    public ConfigAttrs setRmSwitchEnabled(int index, boolean rmSwitchEnabled) {
        rmSwitchEnabledArray.put(index, rmSwitchEnabled);
        return this;
    }

    public ConfigAttrs setRmSwitchEnabled(boolean rmSwitchEnabled) {
        if (valueList.size() <= 0) {
            throw new RuntimeException("values is null");
        }

        for (int i = 0; i < valueList.size(); i++) {
            rmSwitchEnabledArray.put(i, rmSwitchEnabled);
        }
        return this;
    }

    public SparseBooleanArray getRmSwitchForceAspectRatioArray() {
        return rmSwitchForceAspectRatioArray;
    }

    public ConfigAttrs setRmSwitchForceAspectRatio(int index, boolean rmSwitchForceAspectRatio) {
        rmSwitchForceAspectRatioArray.put(index, rmSwitchForceAspectRatio);
        return this;
    }

    public ConfigAttrs setRmSwitchForceAspectRatio(boolean rmSwitchForceAspectRatio) {
        if (valueList.size() <= 0) {
            throw new RuntimeException("values is null");
        }

        for (int i = 0; i < valueList.size(); i++) {
            rmSwitchForceAspectRatioArray.put(i, rmSwitchForceAspectRatio);
        }
        return this;
    }

    public SparseIntArray getRmSwitchNotCheckedBgColorArray() {
        return rmSwitchNotCheckedBgColorArray;
    }

    public ConfigAttrs setRmSwitchNotCheckedBgColor(int index, int rmSwitchNotCheckedBgColor) {
        rmSwitchNotCheckedBgColorArray.put(index, rmSwitchNotCheckedBgColor);
        return this;
    }

    public ConfigAttrs setRmSwitchNotCheckedBgColor(int rmSwitchNotCheckedBgColor) {
        if (valueList.size() <= 0) {
            throw new RuntimeException("values is null");
        }

        for (int i = 0; i < valueList.size(); i++) {
            rmSwitchNotCheckedBgColorArray.put(i, rmSwitchNotCheckedBgColor);
        }
        return this;
    }

    public SparseIntArray getRmSwitchNotCheckedToggleColorArray() {
        return rmSwitchNotCheckedToggleColorArray;
    }

    public ConfigAttrs setRmSwitchNotCheckedToggleColor(int index, int rmSwitchNotCheckedToggleColor) {
        rmSwitchNotCheckedToggleColorArray.put(index, rmSwitchNotCheckedToggleColor);
        return this;
    }

    public ConfigAttrs setRmSwitchNotCheckedToggleColor(int rmSwitchNotCheckedToggleColor) {
        if (valueList.size() <= 0) {
            throw new RuntimeException("values is null");
        }

        for (int i = 0; i < valueList.size(); i++) {
            rmSwitchNotCheckedToggleColorArray.put(i, rmSwitchNotCheckedToggleColor);
        }
        return this;
    }

    public SparseIntArray getRmSwitchNotCheckedToggleResArray() {
        return rmSwitchNotCheckedToggleResArray;
    }

    public ConfigAttrs setRmSwitchNotCheckedToggleRes(int index, int rmSwitchNotCheckedToggleRes) {
        rmSwitchNotCheckedToggleResArray.put(index, rmSwitchNotCheckedToggleRes);
        return this;
    }

    public ConfigAttrs setRmSwitchNotCheckedToggleRes(int rmSwitchNotCheckedToggleRes) {
        if (valueList.size() <= 0) {
            throw new RuntimeException("values is null");
        }

        for (int i = 0; i < valueList.size(); i++) {
            rmSwitchNotCheckedToggleResArray.put(i, rmSwitchNotCheckedToggleRes);
        }
        return this;
    }

    public int getRightTextMagin() {
        return rightTextMagin;
    }

    public ConfigAttrs setRightTextMagin(int rightTextMagin) {
        this.rightTextMagin = rightTextMagin;
        return this;
    }


    public SparseIntArray getMarginArray() {
        return marginArray;
    }

    public SparseArray<Mode> getModeArray() {
        return modeArray;
    }

    public SparseArray<String> getRightTextArray() {
        return rightTextArray;
    }


    public ConfigAttrs setItemMode(Mode value) {

        if (valueList.size() <= 0) {
            throw new RuntimeException("values is null");
        }

        for (int i = 0; i < valueList.size(); i++) {
            modeArray.put(i, value);
        }
        return this;
    }

    public ConfigAttrs setItemMode(int index, Mode value) {
        modeArray.put(index, value);
        return this;
    }


    public ConfigAttrs setRightText(List<String> values) {

        if (values == null) {
            throw new RuntimeException("values is null");
        }

        if (values.size() <= 0) {
            throw new RuntimeException("");
        }

        for (int i = 0; i < values.size(); i++) {
            rightTextArray.put(i, values.get(i));
        }

        return this;
    }

    public ConfigAttrs setRightText(int position, String text) {
        rightTextArray.put(position, text);
        return this;
    }


    public ConfigAttrs setItemMarginTop(int index, int value) {
        marginArray.put(index, value);
        return this;
    }

    public ConfigAttrs setItemMarginTop(int value) {

        if (valueList.size() <= 0) {
            throw new RuntimeException("");
        }

        for (int i = 0; i < valueList.size(); i++) {
            marginArray.put(i, value);
        }
        return this;
    }
}
