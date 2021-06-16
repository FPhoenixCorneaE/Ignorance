package com.livelearn.ignorance.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.utils.GlideUtils;
import com.livelearn.ignorance.utils.ResourceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 自定义标题栏
 * Created on 2016/11/25.
 */

public class TitleBar extends RelativeLayout {

    @BindView(R.id.iv_left)
    AppCompatImageView ivLeft;

    @BindView(R.id.tv_left)
    TextView tvLeft;

    @BindView(R.id.ll_left)
    LinearLayout llLeft;

    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.iv_right)
    AppCompatImageView ivRight;

    @BindView(R.id.tv_right)
    TextView tvRight;

    @BindView(R.id.ll_right)
    LinearLayout llRight;

    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;

    private OnClickListener onClickLeftListener;
    private OnClickListener onClickRightListener;
    private Context context;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initData(context, attrs, defStyleAttr);
        initEvent(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    private void initView(Context context) {
        this.context = context;
        inflate(context, R.layout.view_title_bar, this);
        ButterKnife.bind(this);
    }

    private void initData(Context context, AttributeSet attrs, int defStyleAttr) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Titlebar, defStyleAttr, 0);
        String leftText = typedArray.getString(R.styleable.Titlebar_tb_left_text);
        String titleText = typedArray.getString(R.styleable.Titlebar_tb_title_text);
        String rightText = typedArray.getString(R.styleable.Titlebar_tb_right_text);

        int leftIconId = typedArray.getResourceId(R.styleable.Titlebar_tb_left_icon, NO_ID);
        int rightIconId = typedArray.getResourceId(R.styleable.Titlebar_tb_right_icon, NO_ID);

        int leftTextVisibility = typedArray.getInt(R.styleable.Titlebar_tb_left_text_visibility, 0);
        int leftIconVisibility = typedArray.getInt(R.styleable.Titlebar_tb_left_icon_visibility, 2);
        int titleVisibility = typedArray.getInt(R.styleable.Titlebar_tb_title_text_visibility, 0);
        int rightTextVisibility = typedArray.getInt(R.styleable.Titlebar_tb_right_text_visibility, 0);
        int rightIconVisibility = typedArray.getInt(R.styleable.Titlebar_tb_right_icon_visibility, 0);

        if (leftIconId != NO_ID) {
            ivLeft.setImageDrawable(ResourceUtils.getDrawable(leftIconId));
        } else {
            ivLeft.setImageDrawable(ResourceUtils.getDrawable(R.mipmap.ic_nav_back_black));
        }
        if (leftIconVisibility == 2) {
            ivLeft.setVisibility(VISIBLE);
        } else if (leftIconVisibility == 1) {
            ivLeft.setVisibility(INVISIBLE);
        } else if (leftIconVisibility == 0) {
            ivLeft.setVisibility(GONE);
        }

        if (leftText != null && !TextUtils.isEmpty(leftText)) {
            tvLeft.setText(leftText);
        }
        if (leftTextVisibility == 2) {
            tvLeft.setVisibility(VISIBLE);
        } else if (leftTextVisibility == 1) {
            tvLeft.setVisibility(INVISIBLE);
        } else if (leftTextVisibility == 0) {
            tvLeft.setVisibility(GONE);
        }

        if (titleText != null && !TextUtils.isEmpty(titleText)) {
            tvTitle.setText(titleText);
        }
        if (titleVisibility == 2) {
            tvTitle.setVisibility(VISIBLE);
        } else if (leftTextVisibility == 1) {
            tvTitle.setVisibility(INVISIBLE);
        } else if (leftTextVisibility == 0) {
            tvTitle.setVisibility(GONE);
        }

        if (rightIconId != NO_ID) {
            GlideUtils.setupImage(context, ivRight, rightIconId);
        }
        if (rightIconVisibility == 2) {
            ivRight.setVisibility(VISIBLE);
        } else if (rightIconVisibility == 1) {
            ivRight.setVisibility(INVISIBLE);
        } else if (rightIconVisibility == 0) {
            ivRight.setVisibility(GONE);
        }

        if (rightText != null && !TextUtils.isEmpty(rightText)) {
            tvRight.setText(rightText);
        }
        if (rightTextVisibility == 2) {
            tvRight.setVisibility(VISIBLE);
        } else if (rightTextVisibility == 1) {
            tvRight.setVisibility(INVISIBLE);
        } else if (rightTextVisibility == 0) {
            tvRight.setVisibility(GONE);
        }

        typedArray.recycle();
    }

    private void initEvent(final Context context, AttributeSet attrs, int defStyleAttr) {
        if (context instanceof Activity) {
            onClickLeftListener = new OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((Activity) context).onBackPressed();
                }
            };
            llLeft.setOnClickListener(onClickLeftListener);
        }
    }

    /**
     * 获取可以进行tint的Drawable
     * <p>
     * 对原drawable进行重新实例化  newDrawable()
     * 包装  warp()
     * 可变操作 mutate()
     *
     * @param drawable 原始drawable
     * @return 可着色的drawable
     */
    @NonNull
    private static Drawable getCanTintDrawable(@NonNull Drawable drawable) {
        // 获取此drawable的共享状态实例
        Drawable.ConstantState state = drawable.getConstantState();
        // 对drawable 进行重新实例化、包装、可变操作
        return DrawableCompat.wrap(state == null ? drawable : state.newDrawable()).mutate();
    }

    /**
     * 对左边图标进行着色
     */
    public TitleBar setLeftIconTintColor(@ColorRes int colorResId) {
        return setLeftIconTintColor(0, colorResId);
    }

    /**
     * 对左边图标进行着色
     *
     * @param drawableResId 左边图标id
     * @param colorResId    着色的颜色值id
     */
    public TitleBar setLeftIconTintColor(@DrawableRes int drawableResId, @ColorRes int colorResId) {
        // 获取此drawable的共享状态实例
        Drawable wrappedDrawable = getCanTintDrawable(ResourceUtils.getDrawable(drawableResId == 0 ? R.mipmap.ic_nav_back_black : drawableResId));
        // 进行着色
        DrawableCompat.setTint(wrappedDrawable, ResourceUtils.getColor(colorResId));
        ivLeft.setImageDrawable(wrappedDrawable);
        return this;
    }

    /**
     * 对左边图标进行着色
     * 通过ColorStateList 指定颜色状态列表
     */
    public TitleBar setLeftIconTintColorState(@ColorRes int colorResId) {
        return setLeftIconTintColorState(0, colorResId);
    }

    /**
     * 对左边图标进行着色
     * 通过ColorStateList 指定颜色状态列表
     *
     * @param drawableResId 左边图标id
     * @param colorResId    着色的颜色状态列表id
     */
    public TitleBar setLeftIconTintColorState(@DrawableRes int drawableResId, @ColorRes int colorResId) {
        Drawable wrappedDrawable = getCanTintDrawable(ResourceUtils.getDrawable(drawableResId == 0 ? R.mipmap.ic_nav_back_black : drawableResId));
        // 进行着色
        DrawableCompat.setTintList(wrappedDrawable, ResourceUtils.getColorStateList(colorResId));
        ivLeft.setImageDrawable(wrappedDrawable);
        return this;
    }

    public TitleBar setLeftIcon(Object leftIcon) {
        GlideUtils.setupImage(context, ivLeft, leftIcon);
        return this;
    }

    public TitleBar setRightIcon(Object rightIcon) {
        GlideUtils.setupImage(context, ivRight, rightIcon);
        return showRightIcon();
    }

    public TitleBar setLeftText(CharSequence leftText) {
        tvLeft.setText(leftText);
        return showLeftText();
    }

    public TitleBar setLeftTextColor(@ColorInt int leftTextColor) {
        tvLeft.setTextColor(leftTextColor);
        return this;
    }

    public TitleBar setLeftTextColorRes(@ColorRes int leftTextColorRes) {
        tvLeft.setTextColor(ResourceUtils.getColor(leftTextColorRes));
        return this;
    }

    public TitleBar setLeftTextColorStateList(ColorStateList leftTextColorStateList) {
        tvLeft.setTextColor(leftTextColorStateList);
        return this;
    }

    public TitleBar setLeftTextColorStateListRes(@ColorRes int leftTextColorStateListRes) {
        tvLeft.setTextColor(ResourceUtils.getColorStateList(leftTextColorStateListRes));
        return this;
    }

    public TitleBar setLeftTextSize(float leftTextSize) {
        tvLeft.setTextSize(leftTextSize);
        return this;
    }

    public TitleBar setRightText(CharSequence rightText) {
        tvRight.setText(rightText);
        return showRightText();
    }

    public TitleBar setRightTextColor(@ColorInt int rightTextColor) {
        tvRight.setTextColor(rightTextColor);
        return this;
    }

    public TitleBar setRightTextColorRes(@ColorRes int rightTextColorRes) {
        tvRight.setTextColor(ResourceUtils.getColor(rightTextColorRes));
        return this;
    }

    public TitleBar setRightTextColorStateList(ColorStateList rightTextColorStateList) {
        tvRight.setTextColor(rightTextColorStateList);
        return this;
    }

    public TitleBar setRightTextColorStateListRes(@ColorRes int rightTextColorStateListRes) {
        tvRight.setTextColor(ResourceUtils.getColorStateList(rightTextColorStateListRes));
        return this;
    }

    public TitleBar setRightTextSize(float rightTextSize) {
        tvRight.setTextSize(rightTextSize);
        return this;
    }

    public TitleBar setTitleText(CharSequence titleText) {
        tvTitle.setText(titleText);
        return showTitleText();
    }

    public TitleBar setTitleTextColor(@ColorInt int titleTextColor) {
        tvTitle.setTextColor(titleTextColor);
        return this;
    }

    public TitleBar setTitleTextColorRes(@ColorRes int titleTextColorRes) {
        tvTitle.setTextColor(ResourceUtils.getColor(titleTextColorRes));
        return this;
    }

    public TitleBar setTitleTextColorStateList(ColorStateList titleTextColorStateList) {
        tvTitle.setTextColor(titleTextColorStateList);
        return this;
    }

    public TitleBar setTitleTextColorStateListRes(@ColorRes int titleTextColorStateListRes) {
        tvTitle.setTextColor(ResourceUtils.getColorStateList(titleTextColorStateListRes));
        return this;
    }

    public TitleBar setTitleTextSize(float titleTextSize) {
        tvTitle.setTextSize(titleTextSize);
        return this;
    }

    public TitleBar setTitleBarBackgroundColor(@ColorInt int titleBarBackgroundColor) {
        rlTitleBar.setBackgroundColor(titleBarBackgroundColor);
        return this;
    }

    public TitleBar setTitleBarBackgroundColorRes(@ColorRes int titleBarBackgroundColorRes) {
        rlTitleBar.setBackgroundColor(ResourceUtils.getColor(titleBarBackgroundColorRes));
        return this;
    }

    public TitleBar setTitleBarBackgroundDrawable(Drawable titleBarBackgroundDrawable) {
        rlTitleBar.setBackground(titleBarBackgroundDrawable);
        return this;
    }

    public TitleBar setTitleBarBackgroundResource(@DrawableRes int titleBarBackgroundResId) {
        rlTitleBar.setBackgroundResource(titleBarBackgroundResId);
        return this;
    }

    public TitleBar setOnClickLeftListener(OnClickListener onClickLeftListener) {
        this.onClickLeftListener = onClickLeftListener;
        if (onClickLeftListener != null) {
            llLeft.setOnClickListener(onClickLeftListener);
        }
        return this;
    }

    public TitleBar setOnClickRightListener(OnClickListener onClickRightListener) {
        this.onClickRightListener = onClickRightListener;
        if (onClickRightListener != null) {
            llRight.setOnClickListener(onClickRightListener);
        }
        return this;
    }

    public ImageView getLeftImageView() {
        return ivLeft;
    }

    public ImageView getRightImageView() {
        return ivRight;
    }

    public TextView getLeftTextView() {
        return tvLeft;
    }

    public TextView getRightTextView() {
        return tvRight;
    }

    public TextView getTitleTextView() {
        return tvTitle;
    }

    public TitleBar showLeftIcon() {
        ivLeft.setVisibility(VISIBLE);
        return this;
    }

    public TitleBar showRightIcon() {
        ivRight.setVisibility(VISIBLE);
        return this;
    }

    public TitleBar showLeftText() {
        tvLeft.setVisibility(VISIBLE);
        return this;
    }

    public TitleBar showRightText() {
        tvRight.setVisibility(VISIBLE);
        return this;
    }

    public TitleBar showLoadingView() {
        pbLoading.setVisibility(VISIBLE);
        return this;
    }

    public TitleBar showTitleText() {
        tvTitle.setVisibility(VISIBLE);
        return this;
    }

    public TitleBar hideLeftIcon() {
        ivLeft.setVisibility(GONE);
        llLeft.setOnClickListener(null);
        return this;
    }

    public TitleBar hideRightIcon() {
        ivRight.setVisibility(GONE);
        return this;
    }

    public TitleBar hideLeftText() {
        tvLeft.setVisibility(GONE);
        return this;
    }

    public TitleBar hideRightText() {
        tvRight.setVisibility(GONE);
        return this;
    }

    public TitleBar hideLoadingView() {
        pbLoading.setVisibility(GONE);
        return this;
    }

    public TitleBar hideTitleText() {
        tvTitle.setVisibility(GONE);
        return this;
    }
}
