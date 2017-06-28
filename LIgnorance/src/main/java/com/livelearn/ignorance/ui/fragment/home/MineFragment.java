package com.livelearn.ignorance.ui.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.test.TestMainActivity;
import com.livelearn.ignorance.ui.activity.LoginActivity;
import com.livelearn.ignorance.ui.activity.mine.SettingsActivity;
import com.livelearn.ignorance.utils.GlideUtils;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.InsLoadingView;
import com.maiml.library.BaseItemLayout;
import com.maiml.library.config.ConfigAttrs;
import com.maiml.library.config.Mode;

import java.util.Arrays;
import java.util.List;

import am.widget.shapeimageview.ShapeImageView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.iv_avatar)
    InsLoadingView ivAvatar;

    @BindView(R.id.tv_nickname)
    TextView tvNickname;

    @BindView(R.id.tv_personalized_signature)
    TextView tvPersonalizedSignature;

    @BindView(R.id.fl_has_login)
    FrameLayout flHasLogin;

    @BindView(R.id.iv_avatar_not_login)
    ShapeImageView ivAvatarNotLogin;

    @BindView(R.id.btn_login_register)
    Button btnLoginRegister;

    @BindView(R.id.ll_not_login)
    LinearLayout llNotLogin;

    @BindView(R.id.bil_base_item_layout)
    BaseItemLayout bilBaseItemLayout;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        initUserInfo();

        initItemLayout();
    }

    private void initUserInfo() {
        if (getmActivity().hasLogin()) {
            llNotLogin.setVisibility(View.GONE);
            flHasLogin.setVisibility(View.VISIBLE);
            GlideUtils.setupImage(mContext, ivAvatar, mUserInfo.getUserAvatarPath(), R.drawable.shape_circle_solidlightgray);
            tvNickname.setText(mUserInfo.getUserNickname());
            String personalizedSignature = mUserInfo.getPersonalizedSignature();
            if (personalizedSignature != null && !personalizedSignature.isEmpty()) {
                tvPersonalizedSignature.setVisibility(View.VISIBLE);
                tvPersonalizedSignature.setText(personalizedSignature);
            }
        } else {
            flHasLogin.setVisibility(View.GONE);
            llNotLogin.setVisibility(View.VISIBLE);
        }
    }

    private void initItemLayout() {
        List<String> valueList = Arrays.asList(ResourceUtils.getStringArray(R.array.MineFragment_Item));
        List<Integer> resIdList = Arrays.asList(0, R.mipmap.ic_mine_setup);

        ConfigAttrs configAttrs = new ConfigAttrs();
        configAttrs.setValueList(valueList)//文字List
                .setResIdList(resIdList)//iconList
                .setIconWidth(24)//设置icon大小
                .setIconHeight(24)
                .setItemHeight(46)//设置高度
                .setItemBackgroundResId(R.drawable.selector_solid_normalwhite_pressedlightgray)//设置item背景
                .setItemMarginTop(10)//设置item间距
                .setItemMarginRight(8)
                .setItemMode(Mode.ARROW)//设置item模式
                .setIconMarginLeft(16)
                .setIconTextMargin(10)
                .setTextColor(ResourceUtils.getColor(R.color.color_light_purple))//设置文字颜色
                .setTextSize(16)//设置文字大小
                .setArrowResId(R.mipmap.ic_next)
                .setLineColor(ResourceUtils.getColor(R.color.divider))
                .setShowStartLine(false)
                .setShowEndLine(false)
        ;
        bilBaseItemLayout.setConfigAttrs(configAttrs).create();
    }

    @Override
    public void setListeners() {
        bilBaseItemLayout.setOnBaseItemClick(new int[]{0, 1}, new BaseItemLayout.OnBaseItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0://测试
                        IntentUtils.startActivity(mContext, TestMainActivity.class);
                        break;
                    case 1://设置
                        IntentUtils.startActivity(mContext, SettingsActivity.class);
                        break;
                }
            }
        });
    }

    @Override
    public void lazyFetchData() {

    }

    @OnClick({R.id.iv_avatar, R.id.btn_login_register, R.id.ll_not_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_avatar:
                switch (ivAvatar.getStatus()) {
                    case UNCLICKED:
                        ivAvatar.setStatus(InsLoadingView.Status.LOADING);
                        break;
                    case LOADING:
                        ivAvatar.setStatus(InsLoadingView.Status.CLICKED);
                        break;
                    case CLICKED:
                        ivAvatar.setStatus(InsLoadingView.Status.UNCLICKED);
                }
                break;
            case R.id.btn_login_register:
                IntentUtils.startActivity(mContext, LoginActivity.class);
                getmActivity().finish();
                break;
            case R.id.ll_not_login:
                IntentUtils.startActivity(mContext, LoginActivity.class);
                getmActivity().finish();
                break;
        }
    }
}
