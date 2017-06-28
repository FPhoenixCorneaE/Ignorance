package com.livelearn.ignorance.utils.aliyunupload;

import android.content.Context;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.OSSResult;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.livelearn.ignorance.callbacklistener.OnUploadImgCallback;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.exception.OSSException;
import com.livelearn.ignorance.model.enumeration.PhotoType;

/**
 * oss 上传图片url
 */
public class OssUpdateImgUtils {

    private static final String accessKey = "dQZta010R0nSUjlJ"; // 实际使用时，不建议AK/SK明文保存在代码中
    private static final String secretKey = "kM9Ys2gKrv4zgKm9op4dNd1nTuavI7";
    // 发帖图片地址
    public static String feedPicFilterUrl = "http://dd-feed.digi123.cn/";
    // 头像图片地址
    public static String facePicFilterUrl = "http://dd-face.digi123.cn/";

    /**
     * 上传图片
     */
    static void uploadPhoto(final String imagePath, final Context context, byte[] data, PhotoType photoType, final OnUploadImgCallback callback) {

        OSSCompletedCallback callback1 = new OSSCompletedCallback() {
            @Override
            public void onSuccess(OSSRequest ossRequest, OSSResult ossResult) {
                callback.onSuccess(imagePath);
            }

            @Override
            public void onFailure(OSSRequest ossRequest, ClientException e, ServiceException e1) {
                callback.onFailure(imagePath, new OSSException(e.getMessage()));
            }
        };

        OSSProgressCallback ossProgressCallback = new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                callback.onProgress((float) currentSize / totalSize, currentSize, totalSize);
            }
        };

        OSSClient oss = initOssService(context);
        String testBucket = "";

        if (PhotoType.Face == photoType) {
            testBucket = Constant.PHOTO_BUCKET_FACE;
        } else {
            testBucket = Constant.PHOTO_BUCKET_FEED;
        }

        new PutObjectSamplesUtils(oss, testBucket, imagePath).asyncPutObjectFromLocalFile(data, ossProgressCallback, callback1);
    }


    private static OSSClient initOssService(Context context) {
        // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(accessKey, secretKey);

        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(30 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(30 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(9); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSSLog.enableLog();
        String ossPath = "oss-cn-shenzhen.aliyuncs.com";
        return new OSSClient(context, ossPath, credentialProvider, conf);
    }
}
