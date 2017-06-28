package com.livelearn.ignorance.utils.aliyunupload;

import android.content.Context;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.callbacklistener.OnUploadImgCallback;
import com.livelearn.ignorance.model.enumeration.PhotoType;
import com.livelearn.ignorance.utils.MD5Utils;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Random;

public class ALiYunUploadUtils {

    private Context context;

    public void uploadPhoto(Context context, byte[] data, PhotoType photoType, OnUploadImgCallback onUploadImgCallback) {
        this.context = context;

        String imagePath = createPathName(data, photoType);

        new AliyunThread(imagePath, data, photoType, onUploadImgCallback).start();
    }

    private class AliyunThread extends Thread {

        private String imagePath;
        private byte[] data;
        private PhotoType photoType;
        private OnUploadImgCallback onUploadImgCallback;


        AliyunThread(String imagePath, byte[] data, PhotoType photoType, OnUploadImgCallback callback) {
            this.imagePath = imagePath;
            this.data = data;
            this.photoType = photoType;
            this.onUploadImgCallback = callback;
        }

        @Override
        public void run() {
            OssUpdateImgUtils.uploadPhoto(imagePath, context, data, photoType, onUploadImgCallback);
        }
    }

    private String createPathName(byte[] data, PhotoType photoType) {
        StringBuilder sb = new StringBuilder();
        try {
            if (photoType == PhotoType.Face) {
                // 头像目录
                sb.append("face");
                sb.append(File.separator);
            } else {
                Calendar calendar = Calendar.getInstance();
                // 按月份分目录
                int month = calendar.get(Calendar.MONTH) + 1;
                String monthStr = String.valueOf(month);
                if (month < 10) {
                    monthStr = "0" + month;
                }
                sb.append(calendar.get(Calendar.YEAR));
                sb.append(monthStr);
                sb.append(File.separator);
            }
            String fileName = MD5Utils.MD5Encode(data);
            sb.append(MD5Utils.MD5Encode16bit(fileName + System.currentTimeMillis()))
                    .append(getRandomCharAndNumr())
                    .append(".jpg");
        } catch (NoSuchAlgorithmException e) {
            LogUtils.e(e.toString());
        }
        return sb.toString();
    }

    /**
     * 获取随机字母数字组合
     */
    private StringBuilder getRandomCharAndNumr() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 25; i++) {
            boolean b = random.nextBoolean();
            if (b) {
                // 字符
                sb.append((char) (97 + random.nextInt(26)));// 取得小写字母
            } else {
                // 数字
                sb.append(String.valueOf(random.nextInt(10)));
            }
        }
        return sb;
    }
}
