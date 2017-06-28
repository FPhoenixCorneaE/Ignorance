package com.livelearn.ignorance.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.application.IgnoranceApplication;
import com.livelearn.ignorance.common.FilesDirectory;

import java.io.File;
import java.math.BigDecimal;

/**
 * 本应用数据清除管理器
 */
public class CleanCacheUtils {

    private static final File cacheDir = FilesDirectory.CACHE_DIR;
    private static final File externalCacheDir = FilesDirectory.EXTERNAL_CACHE_DIR;

    /**
     * 获取总缓存大小
     */
    public static String getTotalCacheSize() {
        long cacheSize = getFolderSize(cacheDir);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(externalCacheDir);
        }
        return getFormatSize(cacheSize);
    }

    /**
     * 清除所有缓存
     */
    public static void clearAllCache() {
        deleteDir(cacheDir);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(externalCacheDir);
        }
    }

    /**
     * 删除目录
     */
    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (String aChildren : children) {
                boolean success = deleteDir(new File(dir, aChildren));
                if (!success) {
                    return false;
                }
            }
        }
        assert dir != null;
        return dir.delete();
    }

    /**
     * * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * *
     */
    public static void cleanInternalCache() {
        deleteFilesByDirectory(cacheDir);
    }

    /**
     * * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases) * *
     */
    @SuppressLint("SdCardPath")
    public static void cleanDatabases() {
        deleteFilesByDirectory(new File("/data/data/"
                + IgnoranceApplication.getInstance().getApplicationContext().getPackageName() + "/databases"));
    }

    /**
     * * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs) *
     */
    @SuppressLint("SdCardPath")
    public static void cleanSharedPreference() {
        deleteFilesByDirectory(new File("/data/data/"
                + IgnoranceApplication.getInstance().getApplicationContext().getPackageName() + "/shared_prefs"));
    }

    /**
     * * 按名字清除本应用数据库 * *
     *
     * @param dbName 数据库名称
     */
    public static void cleanDatabaseByName(Context context, String dbName) {
        context.deleteDatabase(dbName);
    }

    /**
     * * 清除/data/data/com.xxx.xxx/files下的内容 * *
     */
    public static void cleanFiles() {
        deleteFilesByDirectory(IgnoranceApplication.getInstance().getApplicationContext().getFilesDir());
    }

    /**
     * * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
     */
    public static void cleanExternalCache() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(externalCacheDir);
        }
    }

    /**
     * * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除 * *
     *
     * @param filePath 文件路径
     */
    public static void cleanCustomCache(String filePath) {
        deleteFilesByDirectory(new File(filePath));
    }

    /**
     * * 清除本应用所有的数据 * *
     *
     * @param filepath 文件路径
     */
    public static void cleanApplicationData(Context context, String... filepath) {
        cleanInternalCache();
        cleanExternalCache();
        cleanDatabases();
        cleanSharedPreference();
        cleanFiles();
        if (filepath == null) {
            return;
        }
        for (String filePath : filepath) {
            cleanCustomCache(filePath);
        }
    }

    /**
     * * 删除指定目录下文件
     *
     * @param directory 文件目录
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists()) {
            if (directory.isDirectory()) {
                File[] files = directory.listFiles();
                if (files == null) {
                    return;
                }
                for (File file : files) {
                    deleteFilesByDirectory(file);
                }
                directory.delete();
            } else {
                directory.delete();
            }
        }
    }

    // 获取文件
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public static long getFolderSize(File file) {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            if (fileList == null || fileList.length == 0) {
                return size;
            }
            for (File aFileList : fileList) {
                // 如果下面还有文件
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return size;
    }

    /**
     * 删除指定目录下文件及目录
     *
     * @param filePath       文件路径
     * @param deleteThisPath 是否删除
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {// 如果下面还有文件
                    File files[] = file.listFiles();
                    for (File file1 : files) {
                        deleteFolderFile(file1.getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    } else {// 目录
                        if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
    }

    /**
     * 格式化单位
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            BigDecimal result = new BigDecimal(Double.toString(size));
            return result.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "B";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }
}
