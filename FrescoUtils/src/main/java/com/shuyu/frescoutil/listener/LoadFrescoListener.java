package com.shuyu.frescoutil.listener;

import android.graphics.Bitmap;

import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.image.CloseableImage;

public interface LoadFrescoListener {

    void onProgressUpdate(DataSource<CloseableReference<CloseableImage>> dataSource);

    void onSuccess(Bitmap bitmap);

    void onFail();
}