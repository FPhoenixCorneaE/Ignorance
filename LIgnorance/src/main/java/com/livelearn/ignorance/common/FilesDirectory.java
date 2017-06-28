package com.livelearn.ignorance.common;

import com.livelearn.ignorance.application.IgnoranceApplication;

import java.io.File;

/**
 * 文件夹目录
 */

public class FilesDirectory {

    public static final File FILES_DIR = IgnoranceApplication.getInstance().getApplicationContext().getFilesDir();

    public static final File CACHE_DIR = IgnoranceApplication.getInstance().getApplicationContext().getCacheDir();

    public static final File EXTERNAL_CACHE_DIR = IgnoranceApplication.getInstance().getApplicationContext().getExternalCacheDir();

    public static final String DB_DIR_NAME = "LI_DB";

    public static final String IMAGE_DOWNLOAD_DIR_NAME = "Ignorance";
}
