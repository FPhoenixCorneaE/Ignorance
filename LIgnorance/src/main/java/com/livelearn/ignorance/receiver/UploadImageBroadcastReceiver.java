package com.livelearn.ignorance.receiver;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.utils.ToastUtils;

/**
 * 上传图片广播接收器
 */
public class UploadImageBroadcastReceiver extends BroadcastReceiver {

    private Dialog uploadingDialog;
    private int selectedSize;
    private int uploadOKCount;

    public UploadImageBroadcastReceiver(Dialog uploadingDialog, int selectedSize) {
        this.uploadingDialog = uploadingDialog;
        this.selectedSize = selectedSize;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int uploadResultCode = intent.getIntExtra(Constant.RESULT_UPLOAD_IMAGE, 0);
        int position = intent.getIntExtra(Constant.POSITION, -1);

        if (uploadResultCode == Constant.RESULT_OK) {
            uploadOKCount++;
            ToastUtils.showToast("第" + (position + 1) + "张图片上传成功");
        } else
            ToastUtils.showToast("第" + (position + 1) + "张图片上传失败，等待重新上传");

        if (uploadOKCount == selectedSize)
            uploadingDialog.cancel();
    }
}
