package com.livelearn.ignorance.utils.aliyunupload;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.apkfuns.logutils.LogUtils;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.service.UploadImageService;
import com.livelearn.ignorance.utils.CompressUtils;
import com.livelearn.ignorance.utils.DialogUtils;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.utils.ToastUtils;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.io.File;
import java.util.ArrayList;

import cn.finalteam.toolsfinal.io.FileUtils;
import me.iwf.photopicker.PhotoPickUtils;
import rx.functions.Action1;

/**
 * 上传多张图片
 */
public class UploadMultipleImageUtils {

    protected BaseActivity mContext;
    private ArrayList<String> selectedPhotos;
    private Dialog uploadingDialog;
    private int uploadOkCount;
    private OnUploadSuccessListener mOnUploadSuccessListener;

    /**
     * @param context        上下文
     * @param selectedPhotos 选择的图片
     */
    public UploadMultipleImageUtils(BaseActivity context, @Nullable ArrayList<String> selectedPhotos) {
        this.mContext = context;
        this.selectedPhotos = selectedPhotos;
        this.uploadingDialog = DialogUtils.createLoadingIndicatorDialog(context, false, "正在拼命上传......");
        EventBus.getDefault().register(this);
    }

    /**
     * 显示照片墙底部弹窗
     *
     * @param rootView 根视图
     */
    public void showPhotoWallSheetDialog(@Nullable View rootView) {
        final ActionSheetDialog actionSheetDialog = new ActionSheetDialog(mContext, new String[]{"从手机相册选择"}, rootView);
        actionSheetDialog.title("你可以将照片上传至照片墙")
                .titleTextColor(ResourceUtils.getColor(R.color.color_gray))
                .titleTextSize_SP(13f)
                .itemTextColor(ResourceUtils.getColor(R.color.color_dark_black))
                .itemTextSize(18f)
                .cancelText(ResourceUtils.getColor(R.color.color_dark_black))
                .cancelTextSize(18f)
                .show();
        actionSheetDialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        PhotoPickUtils.startPick(mContext, null);
                        break;
                }
                actionSheetDialog.dismiss();
            }
        });
    }

    /**
     * 开始上传
     */
    public void startUpload() {
        if (null == selectedPhotos || selectedPhotos.isEmpty()) {
            return;
        }

        //上传弹窗
        uploadingDialog.show();

        for (int i = 0; i < selectedPhotos.size(); i++) {
            lubanCompress(i, selectedPhotos.get(i));
        }
    }

    /**
     * 类似于微信朋友圈的压缩算法
     *
     * @param position 第几张图片
     * @param imgPath  图片路径
     */
    private void lubanCompress(final int position, final String imgPath) {
        //启动鲁班压缩
        CompressUtils.lubanCompress(mContext, imgPath, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.e(throwable.toString());

                if (null != uploadingDialog && uploadingDialog.isShowing()) {
                    uploadingDialog.dismiss();
                }
            }
        }, new Action1<File>() {
            @Override
            public void call(File file) {
                try {
                    byte[] targetByte = FileUtils.readFileToByteArray(file);

                    if (null == targetByte) return;

                    //启动异步上传图片后台服务
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constant.POSITION, position);
                    bundle.putByteArray(Constant.FILE_BYTE_ARRAY, targetByte);
                    IntentUtils.startService(mContext, UploadImageService.class, bundle);
                } catch (Exception e) {
                    LogUtils.e(e.toString());
                }
            }
        });
    }

    /**
     * 上传完成关掉弹窗和反注册EventBus
     */
    @Subscriber(tag = Constant.PHOTO_UPLOAD_COMPLETED)
    public void onUploadCompleted(String[] photoInfo) {
        if (null != mOnUploadSuccessListener && null != photoInfo) {
            mOnUploadSuccessListener.onUploadSuccess(Integer.parseInt(photoInfo[0]), photoInfo[1]);
        }

        if (++uploadOkCount == selectedPhotos.size() && null != uploadingDialog && uploadingDialog.isShowing()) {
            uploadingDialog.dismiss();
            onDestroy();
        }
    }

    /**
     * 在Activity的onActivityResult方法中调用
     *
     * @param requestCode 请求码
     * @param resultCode  结果码
     * @param data        返回数据
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        PhotoPickUtils.onActivityResult(mContext, requestCode, resultCode, data, new PhotoPickUtils.PickHandler() {
            @Override
            public void onPickSuccess(ArrayList<String> photos) {
                selectedPhotos = photos;
                //开始上传
                startUpload();
            }

            @Override
            public void onPreviewBack(ArrayList<String> photos) {
            }

            @Override
            public void onPickFail(String error) {
                ToastUtils.showToast(error);
            }

            @Override
            public void onPickCancel() {
                ToastUtils.showToast("取消选择");
            }
        });
    }

    private void onDestroy() {
        selectedPhotos = null;
        uploadingDialog = null;
        uploadOkCount = 0;
        EventBus.getDefault().unregister(this);
    }

    /**
     * 设置上传成功监听
     */
    public UploadMultipleImageUtils setOnUploadSuccessListener(OnUploadSuccessListener mOnUploadSuccessListener) {
        this.mOnUploadSuccessListener = mOnUploadSuccessListener;
        return this;
    }

    /**
     * 上传成功监听
     */
    public interface OnUploadSuccessListener {
        void onUploadSuccess(int position, String imageUrl);
    }
}
