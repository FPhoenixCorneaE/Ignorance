package com.livelearn.ignorance.ui.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.bean.UserInfo;
import com.livelearn.ignorance.presenter.mine.EditDataPresenter;
import com.livelearn.ignorance.ui.view.mine.IEditDataView;
import com.livelearn.ignorance.widget.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 编辑资料
 * Created on 2017/8/8.
 */

public class EditDataActivity extends BaseActivity implements IEditDataView {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.rv_photo_wall)
    EasyRecyclerView rvPhotoWall;

    @BindView(R.id.tv_photo_wall_count)
    TextView tvPhotoWallCount;

    @BindView(R.id.iv_arrow_photo_wall)
    ImageView ivArrowPhotoWall;

    @BindView(R.id.rl_photo_wall)
    RelativeLayout rlPhotoWall;

    @BindView(R.id.rl_avatar)
    RelativeLayout rlAvatar;

    @BindView(R.id.tv_personalized_signature)
    TextView tvPersonalizedSignature;

    @BindView(R.id.iv_arrow_personalized_signature)
    ImageView ivArrowPersonalizedSignature;

    @BindView(R.id.rl_personalized_signature)
    RelativeLayout rlPersonalizedSignature;

    @BindView(R.id.et_nickname)
    EditText etNickname;

    @BindView(R.id.tv_sex)
    TextView tvSex;

    @BindView(R.id.rl_sex)
    RelativeLayout rlSex;

    @BindView(R.id.tv_birthday)
    TextView tvBirthday;

    @BindView(R.id.rl_birthday)
    RelativeLayout rlBirthday;

    @BindView(R.id.tv_location)
    TextView tvLocation;

    @BindView(R.id.iv_arrow_location)
    ImageView ivArrowLocation;

    @BindView(R.id.rl_location)
    RelativeLayout rlLocation;

    @BindView(R.id.et_email)
    EditText etEmail;

    private EditDataPresenter mEditDataPresenter;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_edit_data;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setLeftIconTintColor(R.color.color_pale)
                .setLeftText("返回")
                .setLeftTextColorRes(R.color.color_pale)
                .setTitleText("编辑资料")
                .setTitleTextColorRes(R.color.color_pale);

        mEditDataPresenter = new EditDataPresenter(this);
    }

    @Override
    public void setListeners() {
        etNickname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    getmUserInfo().setUserNickname(etNickname.getText().toString());
                }
            }
        });
    }

    @Override
    public BaseActivity getmContext() {
        return mContext;
    }

    @Override
    public UserInfo getmUserInfo() {
        return Constant.getUserInfo();
    }

    @OnClick({R.id.rl_photo_wall, R.id.rl_avatar, R.id.rl_personalized_signature, R.id.rl_sex, R.id.rl_birthday, R.id.rl_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_photo_wall:/*照片墙*/
                break;
            case R.id.rl_avatar:/*头像*/
                break;
            case R.id.rl_personalized_signature:/*签名*/
                break;
            case R.id.rl_sex:/*性别*/
                break;
            case R.id.rl_birthday:/*生日*/
                break;
            case R.id.rl_location:/*所在地*/
                break;
        }
    }
}
