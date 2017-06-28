package com.livelearn.ignorance.utils.aliyunupload;

import android.os.Bundle;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.service.UploadImageService;
import com.livelearn.ignorance.utils.CompressUtils;
import com.livelearn.ignorance.utils.IntentUtils;

import java.io.File;
import java.util.ArrayList;

import cn.finalteam.toolsfinal.io.FileUtils;
import rx.functions.Action1;

/**
 * 上传多张图片
 */
public class UploadMultipleImageUtils {

    protected BaseActivity context;
    private ArrayList<String> selectedPhotos;

    /**
     * @param context        上下文
     * @param selectedPhotos 选择的图片
     */
    public UploadMultipleImageUtils(BaseActivity context, ArrayList<String> selectedPhotos) {
        this.context = context;
        this.selectedPhotos = selectedPhotos;
    }

    /**
     * 类似于微信朋友圈的压缩算法
     *
     * @param position 第几张图片
     * @param imgPath  图片路径
     */
    private void lubanCompress(final int position, final String imgPath) {
        //启动鲁班压缩
        CompressUtils.lubanCompress(context, imgPath, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.e(throwable.toString());
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
                    IntentUtils.startService(context, UploadImageService.class, bundle);
                } catch (Exception e) {
                    LogUtils.e(e.toString());
                }
            }
        });
    }

    public void startUpload() {
        for (int i = 0; i < selectedPhotos.size(); i++) {
            lubanCompress(i, selectedPhotos.get(i));
        }
    }
}
