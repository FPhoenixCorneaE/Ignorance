package com.livelearn.ignorance.utils.aliyunupload;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;

import com.apkfuns.logutils.LogUtils;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.callbacklistener.OnUploadImgCallback;
import com.livelearn.ignorance.exception.OSSException;
import com.livelearn.ignorance.model.enumeration.PhotoType;
import com.livelearn.ignorance.utils.CompressUtils;
import com.livelearn.ignorance.utils.DialogUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.utils.ToastUtils;
import com.livelearn.ignorance.widget.cbprogressBar.CBProgressBar;

import java.io.File;
import java.util.ArrayList;

import cn.finalteam.toolsfinal.io.FileUtils;
import me.iwf.photopicker.PhotoPickUtils;
import rx.functions.Action1;

/**
 * 上传单张图片
 */
public class UploadSingleImageUtils {

    private static final int CAMERA = 0x111111;//拍照
    private static final int ALBUM = 0x222222;//从相册选择

    protected BaseActivity context;
    private OnUploadImgCallback onUploadImgCallback;
    private boolean isCrop;
    private PhotoType photoType;

    public UploadSingleImageUtils(BaseActivity context) {
        this(context, true);
    }

    /**
     * @param context 上下文
     * @param isCrop  是否需要裁剪图片
     */
    public UploadSingleImageUtils(BaseActivity context, boolean isCrop) {
        this(context, isCrop, PhotoType.Face);
    }

    public UploadSingleImageUtils(BaseActivity context, boolean isCrop, PhotoType photoType) {
        this.context = context;
        this.isCrop = isCrop;
        this.photoType = photoType;
    }

    public boolean isCrop() {
        return isCrop;
    }

    public UploadSingleImageUtils setCrop(boolean crop) {
        isCrop = crop;
        return this;
    }

    public PhotoType getPhotoType() {
        return photoType;
    }

    public UploadSingleImageUtils setPhotoType(PhotoType photoType) {
        this.photoType = photoType;
        return this;
    }

    /**
     * 类似于微信朋友圈的压缩算法
     *
     * @param imgPath 图片路径
     */
    private void lubanCompress(final String imgPath) {
        //启动鲁班压缩
        CompressUtils.lubanCompress(context, imgPath, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (onUploadImgCallback != null)
                    onUploadImgCallback.onFailure(imgPath, new OSSException("读取图片失败"));
                ToastUtils.showToast("读取图片失败");
                LogUtils.e(throwable);
            }
        }, new Action1<File>() {
            @Override
            public void call(File file) {
                try {
                    byte[] targetByte = FileUtils.readFileToByteArray(file);
                    if (null == targetByte) {
                        return;
                    }
                    //启动上传服务
                    startUpload(targetByte);
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
        });
    }

    /**
     * 开始上传
     *
     * @param dataByte 上传字节数组资料
     */
    private void startUpload(byte[] dataByte) {
        try {
            final Dialog uploadingDialog = DialogUtils.createLoadingIndicatorDialog(context, true, "正在拼命上传......");
            final CBProgressBar cbProgressBar = (CBProgressBar) uploadingDialog.findViewById(R.id.cbpb_loading);
            uploadingDialog.show();

            new ALiYunUploadUtils().uploadPhoto(context, dataByte, photoType, new OnUploadImgCallback() {
                @Override
                public void onSuccess(final String imagePath) {
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            uploadingDialog.cancel();
                            ToastUtils.showToast("上传成功");
                            if (onUploadImgCallback != null)
                                onUploadImgCallback.onSuccess(imagePath);
                        }
                    });
                }

                @Override
                public void onProgress(final float percent, final long currentSize, final long totalSize) {
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            cbProgressBar.setProgress((int) (percent * 100));
                            if (onUploadImgCallback != null)
                                onUploadImgCallback.onProgress(percent, currentSize, totalSize);
                        }
                    });
                }

                @Override
                public void onFailure(final String imagePath, final OSSException ossException) {
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            uploadingDialog.cancel();
                            ToastUtils.showToast("上传失败，请重新操作");
                            if (onUploadImgCallback != null)
                                onUploadImgCallback.onFailure(imagePath, ossException);
                        }
                    });
                }
            });
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    /**
     * 显示底部弹窗
     *
     * @param rootView            根视图
     * @param onUploadImgCallback 上传回调
     */
    public void showSheetDialog(View rootView, OnUploadImgCallback onUploadImgCallback) {
        if (onUploadImgCallback != null) {
            this.onUploadImgCallback = onUploadImgCallback;
        }
        String[] dialogItems = ResourceUtils.getStringArray(R.array.ImageSourceDialogItem);
        final ActionSheetDialog actionSheetDialog = new ActionSheetDialog(context, dialogItems, rootView);
        actionSheetDialog.isTitleShow(false).show();
        actionSheetDialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        //拍照
                        if (isCrop) {
                            PhotoPickUtils.cropFromCamera(context);
                        } else {
                            PhotoPickUtils.startCamera(context);
                        }
                        break;
                    case 1:
                        //从相册选择
                        PhotoPickUtils.startGallerySingle(context, false, isCrop);
                        break;
                }
                actionSheetDialog.dismiss();
            }
        });
    }

    /**
     * 拍照
     *
     * @param onUploadImgCallback 上传回调
     */
    public void openCamera(OnUploadImgCallback onUploadImgCallback) {
        if (onUploadImgCallback != null) {
            this.onUploadImgCallback = onUploadImgCallback;
        }
        if (isCrop) {
            PhotoPickUtils.cropFromCamera(context);
        } else {
            PhotoPickUtils.startCamera(context);
        }
    }

    /**
     * 从相册选择
     *
     * @param onUploadImgCallback 上传回调
     */
    public void openAlbum(OnUploadImgCallback onUploadImgCallback) {
        if (onUploadImgCallback != null) {
            this.onUploadImgCallback = onUploadImgCallback;
        }
        PhotoPickUtils.startGallerySingle(context, false, isCrop);
    }

    public void open(int imgType, OnUploadImgCallback onUploadImgCallback) {
        if (onUploadImgCallback != null) {
            this.onUploadImgCallback = onUploadImgCallback;
        }

        switch (imgType) {
            case CAMERA:
                //拍照
                if (isCrop) {
                    PhotoPickUtils.cropFromCamera(context);
                } else {
                    PhotoPickUtils.startCamera(context);
                }
                break;
            case ALBUM:
                //从相册选择
                PhotoPickUtils.startGallerySingle(context, false, isCrop);
                break;
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
        if (isCrop) {
            PhotoPickUtils.onCropResult(context, requestCode, resultCode, data, new PhotoPickUtils.CropHandler() {
                @Override
                public void handleCropResult(Uri uri, int tag) {
                    lubanCompress(uri.getPath());
                }

                @Override
                public void handleCropError(Intent data) {
                    ToastUtils.showToast("剪裁失败");
                }
            });
        } else {
            PhotoPickUtils.onActivityResult(context, requestCode, resultCode, data, new PhotoPickUtils.PickHandler() {
                @Override
                public void onPickSuccess(ArrayList<String> photos) {
                    lubanCompress(photos.get(0));
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
    }
}
