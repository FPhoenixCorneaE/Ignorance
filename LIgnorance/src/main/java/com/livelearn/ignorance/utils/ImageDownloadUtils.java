package com.livelearn.ignorance.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.livelearn.ignorance.common.FilesDirectory;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 图片下载工具类
 * Created on 2017/6/15.
 */

public class ImageDownloadUtils {

    private static final String FILE_DIR = FilesDirectory.IMAGE_DOWNLOAD_DIR_NAME;


    /**
     * 保存Glide图片到本地相册
     */
    public static void saveGlideImageToLocal(final Context context, final String url) {
        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext(url);
                        subscriber.onCompleted();
                    }
                })
                .compose(RxPermissions.getInstance(context).ensure(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                .observeOn(Schedulers.io())
                .filter(new Func1<Boolean, Boolean>() {
                    @Override
                    public Boolean call(Boolean aBoolean) {
                        return aBoolean;
                    }
                })
                .flatMap(new Func1<Boolean, Observable<File>>() {
                    @Override
                    public Observable<File> call(Boolean aBoolean) {
                        File file = null;
                        try {
                            file = Glide.with(context)
                                    .load(url)
                                    .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                                    .get();
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                        return Observable.just(file);
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Func1<File, Observable<Uri>>() {

                    @Override
                    public Observable<Uri> call(File file) {
                        File mFile = null;
                        try {
                            String path = Environment.getExternalStorageDirectory() + File.separator + FILE_DIR;
                            File dir = new File(path);
                            if (!dir.exists()) {
                                dir.mkdirs();
                            }

                            String fileName = url.substring(url.lastIndexOf("/"), url.length());
                            mFile = new File(dir, fileName);
                            if (mFile.exists()) {
                                return Observable.just(Uri.fromFile(mFile));
                            }

                            FileInputStream fis = new FileInputStream(file.getAbsolutePath());

                            int byteRead;
                            byte[] buf = new byte[1444];

                            FileOutputStream fos = new FileOutputStream(mFile.getAbsolutePath());
                            while ((byteRead = fis.read(buf)) != -1) {
                                fos.write(buf, 0, byteRead);
                            }
                            fos.close();
                            fis.close();
                        } catch (Exception e) {
                            LogUtils.e(e);
                            ToastUtils.showToast("图片下载失败");
                        }
                        //更新本地图库
                        Uri uri = Uri.fromFile(mFile);
                        Intent mIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
                        context.sendBroadcast(mIntent);

                        return Observable.just(uri);
                    }
                })
                .subscribeOn(Schedulers.io())
                .map(new Func1<Uri, String>() {
                    @Override
                    public String call(Uri uri) {
                        return String.format("图片已保存至 %s 文件夹", FILE_DIR);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .retry()
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showToast("保存失败");
                    }

                    @Override
                    public void onNext(String s) {
                        ToastUtils.showToast(s);
                    }
                })
        ;
    }

    /**
     * 保存图片到本地相册
     */
    public static void saveImageToLocal(final Context context, final String imageUrl) {
        Runnable saveFileRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    File dirFile = new File(Environment.getExternalStorageDirectory() + File.separator + FILE_DIR);
                    if (!dirFile.exists()) {
                        dirFile.mkdir();
                    }
                    final File myCaptureFile = new File(dirFile.getAbsolutePath(), imageUrl.substring(imageUrl.lastIndexOf("/"), imageUrl.length()));
                    if (myCaptureFile.exists()) {
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showToast(String.format("图片已保存至 %s 文件夹", FILE_DIR));
                            }
                        });
                        return;
                    }

                    Bitmap mBitmap = BitmapFactory.decodeStream(getImageStream(imageUrl));
                    if (mBitmap == null) {
                        ToastUtils.showToast("图片不存在");
                        return;
                    }
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile.getAbsolutePath()));
                    mBitmap.compress(Bitmap.CompressFormat.WEBP, 100, bos);
                    bos.flush();
                    bos.close();
                    mBitmap.recycle();
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //发广播告诉相册有图片需要更新，这样可以在图册下看到保存的图片了
                            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                            Uri uri = Uri.fromFile(myCaptureFile);
                            intent.setData(uri);
                            context.sendBroadcast(intent);
                            ToastUtils.showToast("图片保存成功");
                        }
                    });
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
        };
        new Thread(saveFileRunnable).start();
    }

    /**
     * 从网络获取图片输入流
     */
    private static InputStream getImageStream(String path) {
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return conn.getInputStream();
            }
        } catch (IOException e) {
            LogUtils.e(e);
        }
        return null;
    }
}
