package com.livelearn.ignorance.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.callbacklistener.OnUploadImgCallback;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.exception.OSSException;
import com.livelearn.ignorance.model.enumeration.PhotoType;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.ToastUtils;
import com.livelearn.ignorance.utils.aliyunupload.ALiYunUploadUtils;

import org.simple.eventbus.EventBus;

/**
 * 异步上传图片服务
 * <p>
 * IntentService有以下特点：
 * <p>
 * （1）  它创建了一个独立的工作线程来处理所有的通过onStartCommand()传递给服务的intents。
 * <p>
 * （2）  创建了一个工作队列，来逐个发送intent给onHandleIntent()。
 * <p>
 * （3）  不需要主动调用stopSelft()来结束服务。因为，在所有的intent被处理完后，系统会自动关闭服务。
 * <p>
 * （4）  默认实现的onBind()返回null
 * <p>
 * （5）  默认实现的onStartCommand()的目的是将intent插入到工作队列中
 * <p>
 * 继承IntentService的类至少要实现两个函数：构造函数和onHandleIntent()函数。要覆盖IntentService的其它函数时，注意要通过super调用父类的对应的函数。
 */

public class UploadImageService extends IntentService {

    private IntentService instance;

    public UploadImageService() {
        super("UploadImageService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int position = intent.getIntExtra(Constant.POSITION, -1);
        byte[] fileByte = intent.getByteArrayExtra(Constant.FILE_BYTE_ARRAY);
        //启动上传服务
        startUpload(position, fileByte);
    }

    /**
     * 开始上传
     *
     * @param position 第几张图片
     * @param dataByte 上传字节数组资料
     */
    private void startUpload(final int position, final byte[] dataByte) {
        try {
            ALiYunUploadUtils aLiYunUploadUtils = new ALiYunUploadUtils();
            aLiYunUploadUtils.uploadPhoto(instance, dataByte, PhotoType.Feed, new OnUploadImgCallback() {
                @Override
                public void onSuccess(String imagePath) {
                    LogUtils.i("第" + (position + 1) + "张图片上传成功");
                    LogUtils.i(imagePath);

                    ToastUtils.showToast("第" + (position + 1) + "张图片上传成功");
                    EventBus.getDefault().post(new String[]{String.valueOf(position), imagePath}, Constant.PHOTO_UPLOAD_COMPLETED);
                }

                @Override
                public void onProgress(float percent, long currentSize, long totalSize) {

                }

                @Override
                public void onFailure(String imagePath, OSSException ossException) {
                    LogUtils.i("第" + (position + 1) + "张图片上传失败,将重新上传");

                    //上传失败重新添加进工作队列
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constant.POSITION, position);
                    bundle.putByteArray(Constant.FILE_BYTE_ARRAY, dataByte);
                    IntentUtils.startService(instance, UploadImageService.class, bundle);
                }
            });
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }
}
