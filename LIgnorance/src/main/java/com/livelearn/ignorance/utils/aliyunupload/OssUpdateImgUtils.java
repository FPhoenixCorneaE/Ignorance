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
import com.livelearn.ignorance.exception.OSSException;
import com.livelearn.ignorance.model.enumeration.PhotoType;

import java.util.Locale;

/**
 * oss 上传图片url
 */
public class OssUpdateImgUtils {

    private static final String ACCESS_KEY_ID = ""; // 实际使用时，不建议AK/SK明文保存在代码中
    private static final String ACCESS_KEY_SECRET = "kM9Ys2gKrv4zgKm9op4dNd1nTuavI7";
    // 发帖图片地址
    private static final String FEED_PICTURE_FILTER_URL = "http://dd-feed.digi123.cn/";
    // 头像图片地址
    private static final String FACE_PICTURE_FILTER_URL = "http://dd-face.digi123.cn/";
    /**
     * 图片类型
     */
    private static final String PHOTO_BUCKET_FACE = "dd-face";
    private static final String PHOTO_BUCKET_FEED = "dd-feed";

    /**
     * 上传图片
     */
    static void uploadPhoto(final String imagePath, final Context context, byte[] data, final PhotoType photoType, final OnUploadImgCallback callback) {

        OSSCompletedCallback callback1 = new OSSCompletedCallback() {
            @Override
            public void onSuccess(OSSRequest ossRequest, OSSResult ossResult) {
                if (PhotoType.Face == photoType) {
                    callback.onSuccess(String.format(Locale.getDefault(), "%s%s", FACE_PICTURE_FILTER_URL, imagePath));
                } else {
                    callback.onSuccess(String.format(Locale.getDefault(), "%s%s", FEED_PICTURE_FILTER_URL, imagePath));
                }
            }

            @Override
            public void onFailure(OSSRequest ossRequest, ClientException e, ServiceException e1) {
                if (PhotoType.Face == photoType) {
                    callback.onFailure(String.format(Locale.getDefault(), "%s%s", FACE_PICTURE_FILTER_URL, imagePath), new OSSException(e.getMessage()));
                } else {
                    callback.onFailure(String.format(Locale.getDefault(), "%s%s", FEED_PICTURE_FILTER_URL, imagePath), new OSSException(e.getMessage()));
                }
            }
        };

        OSSProgressCallback ossProgressCallback = new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                callback.onProgress((float) currentSize / totalSize, currentSize, totalSize);
            }
        };

        OSSClient oss = initOssService(context);
        String testBucket;

        if (PhotoType.Face == photoType) {
            testBucket = PHOTO_BUCKET_FACE;
        } else {
            testBucket = PHOTO_BUCKET_FEED;
        }

        new PutObjectSamplesUtils(oss, testBucket, imagePath).asyncPutObjectFromLocalFile(data, ossProgressCallback, callback1);
    }


    private static OSSClient initOssService(Context context) {
        // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(ACCESS_KEY_ID, ACCESS_KEY_SECRET);

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
