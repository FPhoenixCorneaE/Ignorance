package com.livelearn.ignorance.utils.aliyunupload;

import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;

/**
 * oss官方的demo
 * //http://aliyun.github.io/aliyun-oss-android-sdk/?spm=5176.docoss/sdk/android-sdk/preface.2.4.vLVBbn
 * //https://help.aliyun.com/document_detail/oss/sdk/android-sdk/preface.html?spm=5176.docoss/sdk/java-sdk/preface.6.255.AqYfqy
 */
class PutObjectSamplesUtils {

    private OSS oss;
    private String testBucket;
    private String testObject;

    /**
     * @param client     对象存储客户端
     * @param testBucket bucket,
     * @param testObject objectkey,即文件在oss上的路径,如feed/xxxx.jpg
     */
    PutObjectSamplesUtils(OSS client, String testBucket, String testObject) {
        this.oss = client;
        this.testBucket = testBucket;
        this.testObject = testObject;
    }

    // 从本地文件上传，使用非阻塞的异步接口
    @SuppressWarnings("unchecked")
    void asyncPutObjectFromLocalFile(byte[] uploadData, OSSProgressCallback ossProgressCallback, OSSCompletedCallback ossCompletedCallback) {
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(testBucket, testObject, uploadData);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("application/octet-stream");
        put.setMetadata(metadata);

        // 异步上传时可以设置进度回调
        put.setProgressCallback(ossProgressCallback);
        OSSAsyncTask task = oss.asyncPutObject(put, ossCompletedCallback);
    }
}
