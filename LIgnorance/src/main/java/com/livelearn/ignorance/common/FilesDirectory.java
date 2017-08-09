package com.livelearn.ignorance.common;

import com.livelearn.ignorance.application.IgnoranceApplication;

import java.io.File;

/**
 * 文件夹目录
 */

public class FilesDirectory {

    /**
     * 本应用文件：/data/data/com.xxx.xxx/files
     */
    public static final File FILES_DIR = IgnoranceApplication.getInstance().getApplicationContext().getFilesDir();
    /**
     * 本应用内部缓存：/data/data/com.xxx.xxx/cache
     */
    public static final File CACHE_DIR = IgnoranceApplication.getInstance().getApplicationContext().getCacheDir();
    /**
     * 外部缓存：/mnt/sdcard/android/data/com.xxx.xxx/cache
     */
    public static final File EXTERNAL_CACHE_DIR = IgnoranceApplication.getInstance().getApplicationContext().getExternalCacheDir();
    /**
     * 本应用数据库：/data/data/com.xxx.xxx/databases/LI_DB
     */
    public static final String DB_DIR_NAME = "LI_DB";
    /**
     * 图片下载：/mnt/sdcard/Ignorance
     */
    public static final String IMAGE_DOWNLOAD_DIR_NAME = "Ignorance";
}
