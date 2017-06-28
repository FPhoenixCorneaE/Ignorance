package com.livelearn.ignorance.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.model.bean.image.GuideModel;
import com.livelearn.ignorance.presenter.contract.GuideContract;
import com.livelearn.ignorance.utils.GlideUtils;
import com.livelearn.ignorance.widget.RootView;

import butterknife.BindView;
import butterknife.OnClick;

public class GuideView extends RootView implements GuideContract.View {

    @BindView(R.id.iv_guide)
    ImageView ivGuide;

    @BindView(R.id.tv_text)
    TextView tvText;

    @BindView(R.id.tv_subtext)
    TextView tvSubtext;

    private boolean visible = false;

    public GuideView(Context context) {
        super(context);
    }

    public GuideView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GuideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.view_guide;
    }

    @Override
    public void init() {
        isInFragment = true;
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void onSuccess(GuideModel data) {
        if (data != null) {
            GlideUtils.setupImage(mContext, ivGuide, data.getResId());
            tvText.setText(data.getText());
            tvSubtext.setText(data.getSubText());
        }
    }

    @Override
    public void setPresenter(@NonNull GuideContract.Presenter presenter) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onFailure(String msg) {

    }

    public void setPresenter(@NonNull GuideContract.Presenter presenter, @NonNull BaseFragment fragment) {
        presenter.showGuideData(fragment);
    }

    @OnClick(R.id.iv_guide)
    public void onClick() {
        toggle();
    }

    private void toggle() {
        if (!visible) {
            showText();
        } else {
            hideText();
        }
    }

    public void showText() {
        visible = true;
        tvText.setVisibility(View.VISIBLE);
        tvSubtext.setVisibility(View.VISIBLE);
    }

    public void hideText() {
        visible = false;
        tvText.setVisibility(View.GONE);
        tvSubtext.setVisibility(View.GONE);
    }
}
