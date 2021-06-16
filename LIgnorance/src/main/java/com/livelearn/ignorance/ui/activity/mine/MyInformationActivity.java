package com.livelearn.ignorance.ui.activity.mine;

import android.os.Bundle;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.utils.GlideUtils;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.utils.SharedPreferencesUtils;
import com.livelearn.ignorance.widget.TitleBar;
import com.livelearn.ignorance.widget.movingimageview.view.MovingImageView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import am.widget.shapeimageview.ShapeImageView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的资料
 * Created on 2017/8/7.
 */

public class MyInformationActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.miv_moving_bg)
    MovingImageView mivMovingBg;

    @BindView(R.id.iv_avatar)
    ShapeImageView ivAvatar;

    @BindView(R.id.tv_nickname)
    TextView tvNickname;

    @BindView(R.id.tv_age)
    TextView tvAge;

    @BindView(R.id.tv_sex)
    TextView tvSex;

    @BindView(R.id.tv_personalized_signature)
    TextView tvPersonalizedSignature;

    @BindView(R.id.rv_background_wall)
    RecyclerView rvBackgroundWall;

    @BindView(R.id.tv_address)
    TextView tvAddress;

    @BindView(R.id.nsv_nested_scroll)
    NestedScrollView nsvNestedScroll;

    @BindView(R.id.fl_sliding_drag)
    FrameLayout flSlidingDrag;

    @BindView(R.id.supl_panel)
    SlidingUpPanelLayout suplPanel;

    @BindView(R.id.btn_change_bg)
    Button btnChangeBg;

    @BindView(R.id.btn_edit_data)
    Button btnEditData;

    @BindView(R.id.v_status_bar)
    View vStatusBar;

    private String mBackgroundUrlInUse;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_my_information;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setLeftIconTintColor(R.color.color_pale)
                .setLeftText("返回")
                .setLeftTextColorRes(R.color.color_pale)
                .setTitleText("我的资料")
                .setTitleTextColorRes(R.color.color_pale)
                .hideTitleText()
                .setTitleBarBackgroundColorRes(R.color.transparent);

        mBackgroundUrlInUse = SharedPreferencesUtils.getString(Constant.getUserInfo().getUserAccount(), Constant.BACKGROUND_URL_IN_USE);
        if (null == mBackgroundUrlInUse) {
            mBackgroundUrlInUse = ResourceUtils.getStringArray(R.array.MyInformation_Background)[0];
            SharedPreferencesUtils.put(Constant.getUserInfo().getUserAccount(), Constant.BACKGROUND_URL_IN_USE, mBackgroundUrlInUse);
        }

        GlideUtils.setupImage(mContext, mivMovingBg, mBackgroundUrlInUse, -1, false, false, true, -1);

        //四处逛荡
        mivMovingBg.getMovingAnimator().addCustomMovement()
                /*水平向右*/
                .addHorizontalMoveToRight()
                /*垂直向下*/
                .addVerticalMoveToDown()
                /*对角线左上*/
                .addDiagonalMoveToUpLeft()
                /*垂直向下*/
                .addVerticalMoveToDown()
                /*对角线右上*/
                .addDiagonalMoveToUpRight()
                /*水平向左*/
                .addHorizontalMoveToLeft()
                /*对角线右下*/
                .addDiagonalMoveToDownRight()
                /*垂直向上*/
                .addVerticalMoveToUp()
                /*对角线左下*/
                .addDiagonalMoveToDownLeft()
                /*水平向右*/
                .addHorizontalMoveToRight()
                /*垂直向上*/
                .addVerticalMoveToUp()
                /*水平向左*/
                .addHorizontalMoveToLeft()
                /*对角线右下*/
                .addDiagonalMoveToDownRight()
                /*水平向左*/
                .addHorizontalMoveToLeft()
                /*垂直向上*/
                .addVerticalMoveToUp()
                .start();
    }

    @Override
    public void setListeners() {
        suplPanel.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                switch (newState) {
                    case COLLAPSED:/*滑动到底部*/
                        tbTitle.hideTitleText()
                                .setTitleBarBackgroundColorRes(R.color.transparent);
                        vStatusBar.setBackgroundColor(ResourceUtils.getColor(R.color.transparent));
                        break;
                    case EXPANDED:/*滑动到顶部*/
                        tbTitle.showTitleText()
                                .setTitleBarBackgroundColorRes(R.color.title_bar);
                        vStatusBar.setBackgroundColor(ResourceUtils.getColor(R.color.status_bar));
                        break;
                    case ANCHORED:
                        break;
                    case DRAGGING:/*滑动中*/
                        break;
                    case HIDDEN:
                        break;
                }
            }
        });
    }

    @OnClick({R.id.btn_change_bg, R.id.btn_edit_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_change_bg:/*更换背景*/
                Bundle bundle = new Bundle();
                bundle.putString(Constant.BACKGROUND_URL_IN_USE, mBackgroundUrlInUse);
                IntentUtils.startActivity(mContext, ChangeBackgroundActivity.class, bundle);
                break;
            case R.id.btn_edit_data:/*编辑资料*/
                IntentUtils.startActivity(mContext, EditDataActivity.class);
                break;
        }
    }

    /**
     * 更换并保存背景
     * @param selectedBackground 选中的背景
     */
    @Subscriber(tag = Constant.BACKGROUND_URL_IN_USE, mode = ThreadMode.MAIN)
    public void updateMovingBg(String selectedBackground) {
        if (!TextUtils.equals(selectedBackground, mBackgroundUrlInUse)) {
            mBackgroundUrlInUse = selectedBackground;
            SharedPreferencesUtils.put(Constant.getUserInfo().getUserAccount(), Constant.BACKGROUND_URL_IN_USE, mBackgroundUrlInUse);
            GlideUtils.setupImage(mContext, mivMovingBg, mBackgroundUrlInUse, -1, false, false, true, -1);
            mivMovingBg.updateAll();
        }
    }
}
