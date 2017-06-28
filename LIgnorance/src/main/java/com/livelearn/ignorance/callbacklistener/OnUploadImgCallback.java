package com.livelearn.ignorance.callbacklistener;

import com.livelearn.ignorance.exception.OSSException;

/**
 * 上传图片回调
 */
public interface OnUploadImgCallback {

    void onProgress(float percent, long currentSize, long totalSize);

    void onFailure(String imagePath, OSSException ossException);

    void onSuccess(String imagePath);
}
