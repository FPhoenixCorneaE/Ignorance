package com.livelearn.ignorance.test.uploadimage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.utils.ToastUtils;
import com.livelearn.ignorance.utils.aliyunupload.UploadMultipleImageUtils;
import com.livelearn.ignorance.widget.StateButton;
import com.livelearn.ignorance.widget.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import me.iwf.photopicker.widget.MultiPickResultView;

public class TestUploadMultipleImageActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.mprv_select)
    MultiPickResultView mprvSelect;

    @BindView(R.id.mprv_only_show)
    MultiPickResultView mprvOnlyShow;

    @BindView(R.id.sb_upload_start)
    StateButton sbUploadStart;

    @BindView(R.id.tv_selected_image_url)
    TextView tvSelectedImageUrl;

    @BindView(R.id.ll_root)
    LinearLayout llRoot;

    ArrayList<String> selectedPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_upload_multiple_image;
    }

    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        selectedPhotos = new ArrayList<>();

        mprvSelect.init(mContext, MultiPickResultView.ACTION_SELECT, null);
        mprvOnlyShow.init(mContext, MultiPickResultView.ACTION_ONLY_SHOW, selectedPhotos);

        sbUploadStart.setText("开始上传");
    }

    @Override
    public void setListeners() {

    }

    @OnClick(R.id.sb_upload_start)
    public void onClick() {
        if (selectedPhotos.isEmpty()) {
            ToastUtils.showToast("请选择照片");
            return;
        }

        tvSelectedImageUrl.setText("");

        new UploadMultipleImageUtils(mContext, selectedPhotos)
                .setOnUploadSuccessListener(new UploadMultipleImageUtils.OnUploadSuccessListener() {
                    private StringBuilder imageBuilder = new StringBuilder();

                    @Override
                    public void onUploadSuccess(int position, String imageUrl) {
                        imageBuilder.append("第").append(position + 1).append("张：").append(imageUrl).append("|");
                        tvSelectedImageUrl.setText(imageBuilder);
                    }
                })
                .startUpload();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mprvSelect.onActivityResult(requestCode, resultCode, data);

        selectedPhotos = mprvSelect.getPhotos();
        mprvOnlyShow.showPics(selectedPhotos);

        StringBuilder selectedImageUrls = new StringBuilder();
        for (int i = 0; i < selectedPhotos.size(); i++) {
            selectedImageUrls = selectedImageUrls.append(selectedPhotos.get(i)).append("\n");
        }
        tvSelectedImageUrl.setText(selectedImageUrls);
    }
}
